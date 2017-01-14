package ttl.page_objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

/**
 * Created by chandan on 13/01/2017.
 */
public class JourneyResultScreen extends Screen {

    @iOSFindBy(accessibility = "London - Waterloo")
    private WebElement results;

    public JourneyResultScreen(AppiumDriver driver){
        super(driver);
    }

    public DoneScreen goToDoneScreen(){
        if(elementDisplayed(getResults())){
            getResults().click();
        }
        return new DoneScreen(driver);
    }

    public WebElement getResults() {
        return waitForElement(results);
    }
}
