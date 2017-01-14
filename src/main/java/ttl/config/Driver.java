package ttl.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * Created by chandan on 13/01/2017.
 */
public class Driver {

    private AppiumDriver driver;
    private String iosApp = "TTL.app";
    private String appPath;
    private URL appiumUrl;
    private AppiumDriverLocalService service;
    private DesiredCapabilities caps;


    public AppiumDriver buildIosDriver(String deviceName,String version){
        File appDir = new File(System.getProperty("user.dir"), "../apps/");
        appPath = new File(appDir, iosApp).getAbsolutePath();
        service = AppiumService.getService();
        appiumUrl = service.getUrl();
        caps = buildIosCapabilities(deviceName, version, appPath);
        driver = new IOSDriver(appiumUrl, caps);
        return driver;
    }

    public DesiredCapabilities buildIosCapabilities(String deviceName,String version,String appPath){
        DesiredCapabilities caps= new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
        caps.setCapability(MobileCapabilityType.APP, appPath);
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        return caps;
    }

    public AppiumDriver getDriver(String deviceName,String version){
        return (driver!=null) ? driver : buildIosDriver(deviceName,version);
    }

}
