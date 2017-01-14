package ttl.page_objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by chandan on 13/01/2017.
 */
public class Screen {

    protected Logger log = Logger.getLogger(this.getClass().getSimpleName());
    protected AppiumDriver driver;
    private WebDriverWait wait;

    public Screen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 0, TimeUnit.SECONDS), this);
    }

    protected WebElement waitForElement(WebElement element) {
        return waitForElement(element, 10);
    }

    protected boolean isDisplayed(WebElement... element) {
        boolean displayed = false;
        for (int f = 0; f < element.length; f++) {
            displayed = elementDisplayed(element[f]);
            if (!displayed) {
                throw new IllegalStateException("Expected element was not displayed on screen. " + element[f]);
            }
        }
        return displayed;
    }

    protected boolean isPageLoaded(WebElement... element) {
        return isDisplayed(element);
    }

    protected boolean elementDisplayed(WebElement element) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean displayed = false;
        try {
            if (element != null) {
                displayed = element.isDisplayed();
            }
        } catch (Exception e) {
            displayed = false;
        }
        return displayed;
    }

    protected WebElement waitForElement(WebElement element, int timeInSeconds) {
        WebElement element1 = null;
        wait = new WebDriverWait(driver, timeInSeconds);
        try {
            element1 = wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.info("Couldn't find expected element " + element + " " + e.getMessage());
        }
        return element1;
    }
}
