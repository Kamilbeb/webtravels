import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;




public class HotelSearchWithoutTest extends BaseTest{
    @Test
    public void searchHotelWithoutNameTest() {

        driver.findElement(By.name("checkin")).sendKeys("07/08/2023");
        driver.findElement(By.name("checkout")).click();
        //ustawianie daty za pomocą kalendarza
        driver.findElements(By.xpath("//td[@class='day ' and text()='10']"))
                .stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click); //tworzymy listę elementów i odfiltrowujemy element

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        driver.findElement(By.xpath("//button[text()=' Search']")).click();


        Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='text-center']"))
                .getText(),"No Results Found");


    }
}
