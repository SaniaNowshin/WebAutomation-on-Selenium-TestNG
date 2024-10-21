package testRunner;

import config.AddCostDataset;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCostPage;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AddCostRunner extends Setup {
    AddCostPage addCostPage;
    LoginPage loginPage;

    private double totalAmount = 0.0;
    //    private Map<String, Double> amount = new HashMap<>();
    private final double expectedTotalAmount = 1900.0;
    private final double expectedTotalAmountofLaptop = 1200.0;

    @Test(priority = 1, description = "last registered user Login", groups = "smoke")
    public void userLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/userInfo.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1); // Get the last user

        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");

        loginPage.doLogin(email, password);
        System.out.println("Logged in as: " + userObj.get("firstName"));
    }

    @Test(priority = 2, dataProvider = "AddCostCSVData", dataProviderClass = AddCostDataset.class, description = "Add a cost/expenditure from a CSV file", groups = "smoke")
    public void addCosts(String itemName, String quantity, String amount, String purchase_date, String month, String remark) {
        addCostPage = new AddCostPage(driver);
        addCostPage.addCost(itemName, quantity, amount, purchase_date, month, remark);

        totalAmount += Double.parseDouble(amount);

        System.out.println("Cost added for: " + itemName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Test(priority = 3, dependsOnMethods = "addCosts", description = "Print the total cost and assert it", groups = "smoke")
    public void verifyTotalAmount() {
        System.out.println("Total amount: " + totalAmount);
        Assert.assertEquals(totalAmount, expectedTotalAmount);
    }

    @Test(priority = 4, description = "Search for an item by name from the list and check the total cost matches the item's price.", groups = "smoke")
    public void searchItemAndMatchAmount() throws IOException {
        addCostPage = new AddCostPage(driver);
        double expectedAmountForItem = 1200.0;

        AddCostDataset dataset = new AddCostDataset();
        Object[][] csvData = dataset.getCSVData();

        double acquiredAmount = 0.0;
        boolean itemFound = false;

        for (Object[] data : csvData) {
            String itemName = (String) data[0];
            String amount = (String) data[2];

            if (itemName.equalsIgnoreCase(addCostPage.searchForItem("Laptop"))) {
                acquiredAmount = Double.parseDouble(amount);
                itemFound = true;
                break;
            }

        }
        if (itemFound) {
            Assert.assertEquals(acquiredAmount, expectedAmountForItem);
        }
    }

}




