package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(id = "email")
    public WebElement txtemail;

    @FindBy(id = "password")
    public WebElement loginPassword;

    @FindBy(css = "[type=submit]")
    public WebElement btnLogin;

    @FindBy(css = "[data-testid=AccountCircleIcon]")
    public WebElement btnProfileicon;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnProfileMenuItems;

    @FindBy(className = "css-zj8u5p")
    public WebElement btnProfileEdit;

    @FindBy(className = "upload-input")
    public WebElement btnChoosePhoto;

    @FindBy(className = "css-1bnfzcw")
    public List<WebElement> btnUpdateImage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String email, String password) {
        txtemail.sendKeys(email);
        loginPassword.sendKeys(password);
        btnLogin.click();
        //doLogOut();
    }

    public void doLogOut() {
        btnProfileicon.click();

        btnProfileMenuItems.get(1).click();
    }
}
