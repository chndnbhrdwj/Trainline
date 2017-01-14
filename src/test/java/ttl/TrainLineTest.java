package ttl;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;
import ttl.config.AppiumService;
import ttl.config.Driver;
import ttl.page_objects.Journey_Type;
import ttl.page_objects.SearchJourneyScreen;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by chandan on 13/01/2017.
 */
public class TrainLineTest {

    protected AppiumDriver driver;
    protected String deviceName= "iPhone 6";
    protected String version= "10.2";
    private SearchJourneyScreen searchJourneyScreen;
    private String origin="hook",
            destination="clapham",
            startDate ="Feb 12",
            endDate ="Feb 10",
            hour="8",
            minute="45",
            amPm="AM",
    pastDepartureDate="Dec 20";

    @Test
    public void originDepartureShouldNotBeEmpty(){
        assertTrue(searchJourneyScreen
                .searchInvalidJourney()
                .emptyFieldMessageDisplayed()
        );
    }

    @Test
    public void originDepartureShouldNotBeSame(){
        assertTrue(searchJourneyScreen
                .enterJourneyDetails(origin,origin)
                .searchInvalidJourney()
                .sameFieldMessageDisplayed()
        );
    }

    @Test
    public void departureShouldNotBePast(){
        assertTrue(searchJourneyScreen
                .enterJourneyDetails(origin,destination,Journey_Type.OPEN_RETURN)
                .enterOneWayJourneyTime(pastDepartureDate,hour,minute,amPm)
                .searchInvalidJourney()
                .departureInPastMessageDisplayed()
        );
    }

    @Test
    public void returnShouldNotBeforeStartDate(){
        assertTrue(searchJourneyScreen
                .enterJourneyDetails(origin,destination,Journey_Type.RETURN)
                .enterOneWayJourneyTime(startDate,hour,minute,amPm)
                .enterReturnJourneyTime(endDate,hour,minute,amPm)
                .searchInvalidJourney()
                .arrivalBeforeDepartureMessageDisplayed()
        );
    }

    @Test
    public void navigateToDoneScreen(){
        assertTrue(searchJourneyScreen
                .enterJourneyDetails(origin,destination)
                .searchValidJourney()
                .goToDoneScreen()
                .pageLoaded()
        );
    }

    @BeforeSuite
    public void beforeClass(){
        AppiumService.startService();
    }

    @BeforeTest
    public void beforeTest(){
        driver = new Driver().getDriver(deviceName,version);
        searchJourneyScreen = new SearchJourneyScreen(driver);
    }

    @AfterMethod
    public void afterTest(){
        driver.resetApp();
    }

    @AfterSuite
    public void afterClass(){
        AppiumService.stopService();
    }

}
