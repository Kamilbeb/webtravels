package pl.seleniumdemo.tests;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.seleniumdemo.utils.DriverFactory;
import java.io.IOException;


public class BaseTest {

    protected WebDriver driver;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReporter;

    @BeforeSuite  //adnotacja
    public void beforeSuite(){
        htmlReporter = new ExtentHtmlReporter("index.html"); //tworzymy nowy obiekt i podajemy ścieżkę gdzie zapisze raport
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(htmlReporter);
    }
    @AfterSuite
    public void afterSuite(){       //metoda będzie zamykała raporty
        htmlReporter.flush();
        extentReporter.flush();
    }

    @BeforeMethod
    public void setup() throws IOException {

        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
