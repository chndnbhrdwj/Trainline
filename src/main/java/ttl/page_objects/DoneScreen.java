package ttl.page_objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * Created by chandan on 13/01/2017.
 */
public class DoneScreen extends Screen{

    @iOSFindBy(accessibility = "Done!")
    private MobileElement title;

    public DoneScreen(AppiumDriver driver){
        super(driver);
    }

    public boolean pageLoaded(){
        return isPageLoaded(title);
    }

}
