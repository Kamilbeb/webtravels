import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class HotelSearchWithoutTest {
    @Test
    public void searchHotelWithoutName() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
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
