package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/05/2023","29/05/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();


        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream().map(el -> el.getAttribute("textContent")).toList();


        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");

    }
}
