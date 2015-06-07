package ro.videanuadrian.smartHome.dao;

import jodd.db.DbSession;
import jodd.db.ThreadDbSessionHolder;
import jodd.db.connection.ConnectionProvider;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class SmartHomeDbSession {

    @PetiteInject
    ConnectionProvider dbpool;

    public void start() {
        DbSession dbSession = new DbSession(dbpool);
        ThreadDbSessionHolder.set(dbSession);
    }

    public void stop() {
        ThreadDbSessionHolder.remove();
    }
}