import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;



public class SingUpTest {
    @Test
    public void singUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email = "nowytester"+randomNumber+"@tester.pl";
        driver.findElement(By.name("firstname")).sendKeys("Kamil");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111111111");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test1234");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test1234");
        driver.findElement(By.xpath("//button[@type = 'submit' and text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Kamil Testowy");

    }

    @Test
    public void singUpEmptyForm() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type = 'submit' and text()=' Sign Up']")).click();

        List<String> error = driver.findElements(By.xpath("//div[@class='resultsignup']//p"))
                .stream().map(WebElement::getText).toList();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(error.contains("The Email field is required."));
        softAssert.assertTrue(error.contains("The Password field is required."));
        softAssert.assertTrue(error.contains("The Password field is required."));
        softAssert.assertTrue(error.contains("The First name field is required."));
        softAssert.assertTrue(error.contains("The Last Name field is required."));
        softAssert.assertAll();

    }
    @Test
    public void singUpInvalidEmail() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastName = "Testowy";

        String email = "tester.pl";
        driver.findElement(By.name("firstname")).sendKeys("Kamil");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111111111");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test1234");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test1234");
        driver.findElement(By.xpath("//button[@type = 'submit' and text()=' Sign Up']")).click();

        WebElement errors = driver.findElement(By.xpath("//div[@class='resultsignup']//p"));
        Assert.assertEquals(errors.getText(),"The Email field must contain a valid email address.");

    }
}
