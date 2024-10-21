package pages;

import config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddCostPage extends Setup {

    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;

    @FindBy(id = "itemName")
    public WebElement txtItemName;

    @FindBy(id = "amount")
    public WebElement txtAmount;

    @FindBy(css = "[type=button]")
    public List<WebElement> btnQuantity; // Assuming there are quantity buttons

    @FindBy(id = "purchaseDate")
    public WebElement txtPurchaseDate;

    @FindBy(id = "month")
    public List<WebElement> chooseMonth;

    @FindBy(id = "remarks")
    public WebElement txtRemarks;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;

    @FindBy(className = "search-input")
    public WebElement searchItem;


    public AddCostPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    public void addCost(String itemName, String quantity, String amount, String purchase_date, String month, String remark) {
        btnAddCost.click();
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);

        int quantityIndex = Integer.parseInt(quantity);

        if (quantityIndex == 1 || quantityIndex == 2) {
            btnQuantity.get(quantityIndex).click();
        }

        txtPurchaseDate.sendKeys(purchase_date);

        for (WebElement monthOption : chooseMonth) {
            if (monthOption.getText().equalsIgnoreCase(month)) {
                monthOption.click();
                break;
            }
        }

        txtRemarks.sendKeys(remark);
        btnSubmit.click();

    }

    public String searchForItem(String itemName) {
        searchItem.sendKeys(itemName);
        return itemName;
    }
}
