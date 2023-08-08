package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;


public class SingUpTest extends BaseTest {


    @Test
    public void singUpTest() {
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "nowytester" + randomNumber + "@tester.pl";


        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSingUpForm()
                .setFirstName("Kamil")
                .setLastName(lastName)
                .setPhone("111111111")
                .setEmail(email)
                .setPassword("Test1234")
                .confirmPassword("Test1234")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Kamil Testowy");


    }


    @Test
    public void singUpEmptyFormTest() {

        SignUpPage signUpPage = new HotelSearchPage(driver).openSingUpForm();
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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSingUpForm()
                .setFirstName("Kamil")
                .setLastName("Testowy")
                .setPhone("111111111")
                .setEmail("email")
                .setPassword("Test1234")
                .confirmPassword("Test1234");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
