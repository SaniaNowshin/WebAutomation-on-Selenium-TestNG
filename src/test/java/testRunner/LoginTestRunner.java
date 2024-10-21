package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import javax.swing.text.html.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin login with correct creds")
    public void adminlogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com", "admin123");
        String ActualHeading = driver.findElement(By.tagName("h2")).getText();
        String ExpectedHeading = "Admin Dashboard";
        Assert.assertTrue(ActualHeading.contains(ExpectedHeading));
        loginPage.doLogOut();
    }

    @Test(priority = 2, description = "User login with correct creds")
    public void userLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/userInfo.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);

        String firstName = (String) userObj.get("firstName");
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");
        String phoneNumber = (String) userObj.get("PhoneNumber");

        loginPage.doLogin(email, password);

        System.out.println("First Name: " + firstName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);

        Assert.assertEquals("Margarito", userObj.get("firstName"));
        Assert.assertEquals("sania9946@gmail.com", userObj.get("email"));
        Assert.assertEquals("01516198410", userObj.get("PhoneNumber"));
    }
}
