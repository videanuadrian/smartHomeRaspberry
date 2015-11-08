package ro.videanuadrian.smartHome.config;

import jodd.db.DbManager;
import jodd.db.DbSessionProvider;
import jodd.db.connection.ConnectionProvider;
import jodd.db.jtx.DbJtxSessionProvider;
import jodd.db.jtx.DbJtxTransactionManager;
import jodd.db.oom.DbOomManager;
import jodd.db.oom.config.AutomagicDbOomConfigurator;
import jodd.db.oom.naming.ColumnNamingStrategy;
import jodd.db.oom.naming.TableNamingStrategy;
import jodd.db.pool.CoreConnectionPool;
import jodd.jtx.JtxTransactionManager;
import jodd.jtx.meta.Transaction;
import jodd.jtx.proxy.AnnotationTxAdvice;
import jodd.jtx.proxy.AnnotationTxAdviceManager;
import jodd.jtx.proxy.AnnotationTxAdviceSupport;
import jodd.log.LoggerFactory;
import jodd.log.Logger;
import jodd.log.impl.SimpleLoggerFactory;
import jodd.petite.PetiteContainer;
import jodd.petite.config.AutomagicPetiteConfigurator;
import jodd.petite.proxetta.ProxettaAwarePetiteContainer;
import jodd.props.Props;
import jodd.props.PropsUtil;
import jodd.proxetta.MethodInfo;
import jodd.proxetta.ProxyAspect;
import jodd.proxetta.impl.ProxyProxetta;
import jodd.proxetta.pointcuts.MethodAnnotationPointcut;

public class SmartHomeServiceCore {

	private ProxyProxetta proxetta;
	private JtxTransactionManager jtxManager;
    private ConnectionProvider connectionProvider;
    protected PetiteContainer petite;
    
    public static SmartHomeServiceCore ref;

	/**
	 * Default constructor.
	 */
	public SmartHomeServiceCore() {
		ref = this;
	}
	
    public synchronized void start() {
        //AppUtil.resolveDirs();
        initLogger();
    	initProxetta();
        initPetite();
        initDb();
        // init everything else
    }

    public void initProxetta() {
    	
        ProxyAspect txServiceProxy = new ProxyAspect(AnnotationTxAdvice.class,
            new MethodAnnotationPointcut(Transaction.class) {
                @Override
                public boolean apply(MethodInfo mi) {
                    return isPublic(mi) &&
                            isTopLevelMethod(mi) &&
                            matchClassName(mi, "*ServiceImpl") &&
                            super.apply(mi);
                }
            });
        
        proxetta = ProxyProxetta.withAspects(txServiceProxy);
        proxetta.setClassLoader(this.getClass().getClassLoader());
    }
	    
	    
    public void stop() {
           // close everything
    }

    

   void initPetite() {
    	
	    petite = new ProxettaAwarePetiteContainer(proxetta);
	    AutomagicPetiteConfigurator pcfg = new AutomagicPetiteConfigurator();	    
	    pcfg.setIncludedEntries("ro.videanuadrian.smartHome.*");
	    pcfg.configure(petite);
	    
	 // load parameters
	    Props appProps = new Props();
	    appProps.loadSystemProperties("sys");
	    appProps.loadEnvironment("env");
	    PropsUtil.loadFromClasspath(appProps, "/app*.prop*");
	    petite.defineParameters(appProps);
	    // add appCore to Petite (and resolve parameters)
	    petite.addBean("app", this);
        
        
    }

    void initDb() { 
    	 
        petite.registerPetiteBean(CoreConnectionPool.class, "dbpool", null, null, false);
        connectionProvider = (ConnectionProvider) petite.getBean("dbpool");
        connectionProvider.init();

     // transactions
        jtxManager = new DbJtxTransactionManager(connectionProvider);
        jtxManager.setValidateExistingTransaction(true);
        AnnotationTxAdviceSupport.manager = new AnnotationTxAdviceManager(jtxManager, "$class");
        DbSessionProvider sessionProvider = new DbJtxSessionProvider(jtxManager);

        // global settings
        DbManager dbManager = DbManager.getInstance();
        dbManager.setDebug(true);
        dbManager.setConnectionProvider(connectionProvider);
        dbManager.setSessionProvider(sessionProvider);
        
       
        DbOomManager dbOomManager = DbOomManager.getInstance();

        // manual configuration (before entities registration)
        TableNamingStrategy tns = new TableNamingStrategy();
        tns.setPrefix("sh_");
        tns.setUppercase(false);
        dbOomManager.setTableNames(tns);

        ColumnNamingStrategy cns = new ColumnNamingStrategy();
        cns.setUppercase(false);
        dbOomManager.setColumnNames(cns);

        // automatic configuration
        AutomagicDbOomConfigurator dbcfg = new AutomagicDbOomConfigurator();
        dbcfg.setIncludedEntries("ro.videanuadrian.smartHome.entities.*");
        dbcfg.configure(dbOomManager);
    	    
    }
     
    
    protected void initLogger() {
        LoggerFactory.setLoggerFactory(new SimpleLoggerFactory(Logger.Level.DEBUG));        
    }
    
    public void stopDb() {
    	 jtxManager.close();
         connectionProvider.close();
    }
        
    public PetiteContainer getPetite() {
        return petite;
    }
     
}
	

