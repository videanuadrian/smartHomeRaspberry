package ro.videanuadrian.smartHome.config;
import jodd.madvoc.component.MadvocConfig;
import jodd.madvoc.config.AutomagicMadvocConfigurator;
import jodd.madvoc.config.MadvocConfigurator;
import jodd.madvoc.petite.PetiteWebApplication;
import jodd.petite.PetiteContainer;

public class SmartHomeWebApplication extends PetiteWebApplication {

	final SmartHomeServiceCore serviceCore;

    public SmartHomeWebApplication() {
        serviceCore = new SmartHomeServiceCore();
        serviceCore.start();     
    }
    
           
    /**
	 * Adds configurator to Madvoc container and invokes configuration.
	 */
    @Override
	public void configure(MadvocConfigurator configurator) {
				
    	if (configurator instanceof AutomagicMadvocConfigurator){
    		AutomagicMadvocConfigurator amc = (AutomagicMadvocConfigurator) configurator;
            amc.setExcludeAllEntries(true);
            amc.setIncludedEntries("ro.videanuadrian.*");

    		registerComponent(amc);
    		amc.configure();
    	}
	}
	

    @Override
    protected PetiteContainer providePetiteContainer() {
        return serviceCore.getPetite();
    }

    @Override
    protected void destroy(MadvocConfig madvocConfig) {
        serviceCore.stop();
        super.destroy(madvocConfig);
    }
	
	
}
