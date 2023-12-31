package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;


public class SignUpTest extends BaseTest {


    @Test
    public void singUpTest() {
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "nowytester" + randomNumber + "@tester.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Kamil");
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("111111111");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test1234");
        signUpPage.confirmPassword("Test1234");
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Kamil Testowy");


    }

    @Test
    public void singUpEmptyFormTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();

        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }

    @Test
    public void singUpInvalidEmailTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Kamil");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhone("111111111");
        signUpPage.setEmail("email");
        signUpPage.setPassword("Test1234");
        signUpPage.confirmPassword("Test1234");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
