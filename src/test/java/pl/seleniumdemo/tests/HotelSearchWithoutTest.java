package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;


public class HotelSearchWithoutTest extends BaseTest {
    @Test
    public void searchHotelWithoutNameTest() throws IOException {

        ExtentTest test = extentReporter.createTest("Search Hotel Test Without city");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("07/08/2023", "10/08/2023");
        test.log(Status.PASS,"Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(0, 1);
        test.log(Status.PASS,"Setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"Performing search done", SeleniumHelper.getScreenshot(driver));
        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        test.log(Status.PASS,"Assertions done", SeleniumHelper.getScreenshot(driver));

    }
}
