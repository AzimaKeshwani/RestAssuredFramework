package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//TestData//userTest.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int rowNum = xlUtility.getRowCount("Sheet1");
        int colCount = xlUtility.getCellCount("Sheet1", 0);

        String[][] apiData = new String[rowNum][colCount];

        for (int i = 1; i < rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                apiData[i][j] = xlUtility.readCellData("Sheet1", i, j);
            }
        }

        return apiData;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//TestData//userTest.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int rowNum = xlUtility.getRowCount("Sheet1");
      String apidata[] = new String[rowNum];
        for (int i = 1; i <= rowNum; i++) {
            apidata[i - 1] = xlUtility.readCellData("Sheet1", i, 0);
        }
       return apidata;
    }

}


