package ttl.page_objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * Created by chandan on 13/01/2017.
 */
public class JourneyDateScreen extends Screen {

    @iOSFindBy(xpath = "//XCUIElementTypePickerWheel[1]")
    public MobileElement date;

    @iOSFindBy(xpath = "//XCUIElementTypePickerWheel[2]")
    public MobileElement hours;

    @iOSFindBy(xpath = "//XCUIElementTypePickerWheel[3]")
    public MobileElement minutes;

    @iOSFindBy(xpath = "//XCUIElementTypePickerWheel[4]")
    public MobileElement amPm;

    @iOSFindBy(accessibility = "Back")
    public MobileElement backButton;

    @iOSFindBy(accessibility = "Done")
    public MobileElement doneButton;

    public JourneyDateScreen(AppiumDriver driver){
        super(driver);
    }

    public SearchJourneyScreen goToSearchJourneyScreen(){
        getBackButton().click();
        return new SearchJourneyScreen(driver);
    }

    public SearchJourneyScreen selectDateTime(String date, String hour, String minute, String amPm){
        getDate().sendKeys(date);
        getHours().sendKeys(hour);
        getMinutes().sendKeys(minute);
        getAmPm().sendKeys(amPm);
        getDoneButton().click();
        return new SearchJourneyScreen(driver);
    }

    public MobileElement getDate() {
        return date;
    }

    public MobileElement getHours() {
        return hours;
    }

    public MobileElement getMinutes() {
        return minutes;
    }

    public MobileElement getAmPm() {
        return amPm;
    }

    public MobileElement getBackButton() {
        return backButton;
    }

    public MobileElement getDoneButton() {
        return doneButton;
    }
}
