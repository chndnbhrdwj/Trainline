package ttl.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by chandan on 03/02/2016.
 */
public class AppiumService {

    protected static AppiumDriverLocalService service;
    private static Logger log = Logger.getLogger(AppiumService.class);

    private AppiumService() {
    }

    private static AppiumDriverLocalService buildService() {
        if (service != null && service.isRunning()) {
            log.info("An instance of service was already running, Skipping starting service.");
            return service;
        }
        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingAnyFreePort()
                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "error:warn"));
        return service;
    }

    public static void stopService() {
        if (service != null) {
            while (service.isRunning()) {
                log.info("Stopping Appium service running on " + service.getUrl());
                service.stop();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }
    }

    public static void startService() {
        buildService().start();
        log.info("Starting Appium service on " + service.getUrl() + "\n");
    }

    public static AppiumDriverLocalService getService() {
        return service;
    }
}
