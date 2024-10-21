package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {

    @FindBy(tagName = "a")
    public List<WebElement> btnRegister;
    @FindBy(id = "firstName")
    WebElement txtFirstname;
    @FindBy(id = "lastName")
    WebElement txtLastname;
    @FindBy(id = "email")
    WebElement txtemail;
    @FindBy(id = "password")
    WebElement txtpassword;
    @FindBy(id = "phoneNumber")
    WebElement txtphoneNumber;
    @FindBy(id = "address")
    WebElement txtaddress;
    @FindBy(css = "[type=radio]")
    List<WebElement> radiobtnGender;
    @FindBy(css = "[type=checkbox]")
    WebElement checkBox;
    @FindBy(id = "register")
    WebElement fillupRegister;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doRegistration(UserModel userModel) {
        txtFirstname.sendKeys(userModel.getFirstname());
        txtLastname.sendKeys(userModel.getLastname() != null ? userModel.getLastname() : "");
        txtemail.sendKeys(userModel.getEmail());
        txtpassword.sendKeys(userModel.getPassword());
        if (userModel.getPhoneNumber() != null && !userModel.getPhoneNumber().isEmpty()) {
            txtphoneNumber.sendKeys(userModel.getPhoneNumber());
        }

        txtaddress.sendKeys(userModel.getAddress() != null ? userModel.getAddress() : "");
        radiobtnGender.get(1).click();
        checkBox.click();
        fillupRegister.click();
    }
}
