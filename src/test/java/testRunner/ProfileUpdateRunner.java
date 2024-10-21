package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class ProfileUpdateRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 2, description = "User login with correct creds")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/userInfo.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);

        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");

        loginPage.doLogin(email, password);
        loginPage.btnProfileicon.click();
        loginPage.btnProfileMenuItems.get(0).click();
        loginPage.btnProfileEdit.click();

        String relativePath = "\\src\\test\\resources\\building.jpg";
        String absolutePath = System.getProperty("user.dir") + relativePath;
        loginPage.btnChoosePhoto.sendKeys("D:\\building.jpg");

        loginPage.btnUpdateImage.get(0).click();
        loginPage.btnUpdateImage.get(1).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

    }
}
