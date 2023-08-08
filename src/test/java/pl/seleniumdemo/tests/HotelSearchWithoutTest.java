package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;


public class HotelSearchWithoutTest extends BaseTest {
    @Test
    public void searchHotelWithoutNameTest() {

        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("07/08/2023", "10/08/2023")
                .setTravellers(0, 1)
                .performSearch();           //zostanie zwrócony nowy obiekt ResultPage


        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }
}
