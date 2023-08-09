package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;


    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(name = "travellers")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> singUpLink;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger(); //dodanie log4j

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCity(String cityName) {
        logger.info("Setting city "+ cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver,By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");
    }

    public void setDates(String checkin, String checkout) {
        logger.info("Setting dates check-in "+ checkin + " check-out "+checkout);
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("Adding adults:  "+ adultsToAdd + " and kids: "+childToAdd);
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("Adding travellers done");
    }

    private void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerBtn.click();
        }
    }

    public void performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
    }

    public void openSingUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        singUpLink.get(1).click();
    }

}
