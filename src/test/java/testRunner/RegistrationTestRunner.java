package testRunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    RegistrationPage registrationPage;

    @Test(priority = 1, description = "User can try to register without one mandatory fields")
    public void UserRegWithoutOneMandatoryFields() throws InterruptedException, IOException, ParseException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String email = "sania" + Utils.generateRandom(1000, 9999) + "@gmail.com";
        String password = "2344";
        String phoneNumber = "01516" + Utils.generateRandom(100000, 999999);

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);

        registrationPage.doRegistration(userModel);
        registrationPage.btnRegister.get(0).click();
    }

    public void doAssertionWithoutMandatory() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("home-title")));
        String actualmessage = driver.findElement(By.className("home-title")).getText();
        String expectedMessage = "Welcome";
        System.out.println(actualmessage);
        Assert.assertTrue(actualmessage.contains(expectedMessage));
    }

    @Test(priority = 2, description = "user can register by providing all fields")
    public void UserRegByallFields() throws InterruptedException, IOException, ParseException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = "sania" + Utils.generateRandom(1000, 9999) + "@gmail.com";
        String password = "2344";
        String phoneNumber = "01516" + Utils.generateRandom(100000, 999999);
        String address = faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        registrationPage.doRegistration(userModel);
        //assertion
        doAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstname);
        userObj.put("lastName", lastname);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("PhoneNumber", phoneNumber);
        userObj.put("Address", address);

        Utils.saveUserInfo("./src/test/resources/userInfo.json", userObj);
        Thread.sleep(5000);
    }

    @Test(priority = 3, description = "user can register by providing mandatory fields")
    public void UserRegByMandatoryFields() throws InterruptedException, IOException, ParseException {

        registrationPage = new RegistrationPage(driver);
        registrationPage.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = "sania" + Utils.generateRandom(1009, 9999) + "@gmail.com";
        String password = "2344";
        String phoneNumber = "01516" + Utils.generateRandom(100000, 999999);
        String address = faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);

        registrationPage.doRegistration(userModel);

        //assertion
        doAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstname);
        userObj.put("lastName", lastname);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("PhoneNumber", phoneNumber);

        Utils.saveUserInfo("./src/test/resources/userInfo.json", userObj);

    }

    public void doAssertion() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Toastify__toast")));
        String actualmessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expectedMessage = "successfully";
        System.out.println(actualmessage);
        Assert.assertTrue(actualmessage.contains(expectedMessage));
    }
}
