package ro.videanuadrian.smartHome.config;

import jodd.madvoc.component.MadvocConfig;
import jodd.madvoc.petite.PetiteWebApplication;
import jodd.petite.PetiteContainer;

public class SmartHomeWebApplication extends PetiteWebApplication {

	final SmartHomeServiceCore serviceCore;

    public SmartHomeWebApplication() {
        serviceCore = new SmartHomeServiceCore();
        serviceCore.start();
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
