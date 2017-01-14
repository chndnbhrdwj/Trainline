package ttl.page_objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

/**
 * Created by chandan on 13/01/2017.
 */
public class SearchJourneyScreen extends Screen {

    JourneyDateScreen journeyDateScreen= new JourneyDateScreen(driver);

    @iOSFindBy(accessibility = "FAQ")
    private MobileElement faqButton;

    @iOSFindBy(accessibility = "Search")
    private MobileElement searchButton;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[1]")
    private MobileElement origin;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[2]")
    private MobileElement destination;

    @iOSFindBy(accessibility = "One way")
    private MobileElement oneWayTab;

    @iOSFindBy(accessibility = "Open return")
    private MobileElement openReturnTab;

    @iOSFindBy(accessibility = "Return")
    private MobileElement returnTab;

    @iOSFindBy(xpath = "//XCUIElementTypeSegmentedControl/following-sibling::XCUIElementTypeButton[1]")
    private MobileElement outwardTime;

    @iOSFindBy(xpath = "//XCUIElementTypeSegmentedControl/following-sibling::XCUIElementTypeButton[2]")
    private MobileElement returnTime;

    @iOSFindBy(accessibility = "Origin or destination cannot be empty")
    private WebElement errorMessageForEmptyField;

    @iOSFindBy(accessibility = "Origin and destination cannot be same")
    private WebElement errorMessageForSameOriginDestination;

    @iOSFindBy(accessibility = "Arrival cannot be before departure")
    private WebElement errorMessageArrivalBeforeDeparture;

    @iOSFindBy(accessibility = "Departure cannot be in the past")
    private WebElement errorMessageForPastDateJourney;

    @iOSFindBy(accessibility = "OK")
    private MobileElement okButton;

    public SearchJourneyScreen(AppiumDriver driver){
        super(driver);
    }

    public SearchJourneyScreen enterJourneyDetails(String origin, String destination, Journey_Type type){
        enterJourneyDetails(origin,destination);
        switch (type){
            case ONE_WAY:
                getOneWayTab().click();
                break;
            case OPEN_RETURN:
                getOpenReturnTab().click();
                break;
            case RETURN:
                getReturnTab().click();
                break;
            default:
                log.info("Unexpected Journey Type provided.");
        }
        return this;
    }

    public SearchJourneyScreen enterJourneyDetails(String origin, String destination){
        getOrigin().sendKeys(origin);
        getDestination().sendKeys(destination);
        return this;
    }

    public SearchJourneyScreen enterOneWayJourneyTime(String date, String hour, String minute, String amPm){
        getOutwardTime().click();
        journeyDateScreen
                .selectDateTime(date,hour,minute,amPm);
        return this;
    }

    public SearchJourneyScreen enterReturnJourneyTime(String date, String hour, String minute, String amPm){
        getReturnTime().click();
        journeyDateScreen
                .selectDateTime(date,hour,minute,amPm);
        return this;
    }

    public JourneyResultScreen searchValidJourney(){
        getSearchButton().click();
        return new JourneyResultScreen(driver);
    }

    public SearchJourneyScreen searchInvalidJourney(){
        getSearchButton().click();
        return this;
    }

    public boolean emptyFieldMessageDisplayed(){
        boolean displayed=elementDisplayed(getErrorMessageForEmptyField());
        if(displayed){
            getOkButton().click();
        }
        return displayed;
    }

    public boolean sameFieldMessageDisplayed(){
        boolean displayed=elementDisplayed(getErrorMessageForSameOriginDestination());
        if(displayed){
            getOkButton().click();
        }
        return displayed;
    }

    public boolean arrivalBeforeDepartureMessageDisplayed(){
        boolean displayed=elementDisplayed(getErrorMessageArrivalBeforeDeparture());
        if(displayed){
            getOkButton().click();
        }
        return displayed;
    }

    public boolean departureInPastMessageDisplayed(){
        boolean displayed=elementDisplayed(getErrorMessageForPastDateJourney());
        if(displayed){
            getOkButton().click();
        }
        return displayed;
    }

    public MobileElement getFaqButton() {
        return faqButton;
    }

    public MobileElement getSearchButton() {
        return searchButton;
    }

    public MobileElement getOrigin() {
        return origin;
    }

    public MobileElement getDestination() {
        return destination;
    }

    public MobileElement getOneWayTab() {
        return oneWayTab;
    }

    public MobileElement getOpenReturnTab() {
        return openReturnTab;
    }

    public MobileElement getReturnTab() {
        return returnTab;
    }

    public MobileElement getOutwardTime() {
        return outwardTime;
    }

    public MobileElement getReturnTime() {
        return returnTime;
    }

    public WebElement getErrorMessageForEmptyField() {
        return waitForElement(errorMessageForEmptyField);
    }

    public WebElement getErrorMessageArrivalBeforeDeparture() {
        return waitForElement(errorMessageArrivalBeforeDeparture);
    }

    public WebElement getErrorMessageForSameOriginDestination() {
        return waitForElement(errorMessageForSameOriginDestination);
    }

    public WebElement getErrorMessageForPastDateJourney() {
        return waitForElement(errorMessageForPastDateJourney);
    }

    public MobileElement getOkButton() {
        return okButton;
    }
}
