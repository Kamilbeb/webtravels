package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;



public class SingUpTest extends BaseTest {


    @Test
    public void singUpTest() {
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email = "nowytester"+randomNumber+"@tester.pl";

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
        Assert.assertEquals(loggedUserPage.getHeadingText(),"Hi, Kamil Testowy");


    }
    @Test
    public void singUpTest2() {

        int randomNumber = (int) (Math.random()*1000);
        String email = "nowytester"+randomNumber+"@tester.pl";

        User user = new User();
        user.setFirstName("Kamil");
        user.setLastName("Testowy");
        user.setPhone("111111111");
        user.setEmail(email);
        user.setPassword("Test1234");


        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        //signUpPage.fillSignUpForm("Kamil",lastName,"111111111",email,"Test1234");
        signUpPage.fillSignUpForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(),"Hi, Kamil Testowy");
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
