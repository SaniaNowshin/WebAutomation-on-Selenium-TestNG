package config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCostDataset {
    @DataProvider(name = "AddCostCSVData")
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/AddCost.csv";
        List<Object[]> data = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord csvRecord : csvParser) {
            String itemName = csvRecord.get("itemName");
            String quantity = csvRecord.get("quantity");
            String amount = csvRecord.get("amount");
            String purchase_date = csvRecord.get("purchase_date");
            String month = csvRecord.get("month");
            String remark = csvRecord.get("remark");
            data.add(new Object[]{itemName, quantity, amount, purchase_date, month, remark});
        }
        return data.toArray(new Object[0][]);
    }
}