package backend.DataObjects;

import org.testng.annotations.DataProvider;

public class GenTokData {


      @DataProvider(name = "Data")
      public static Object[][] data () {
          return new Object[][]{
                {"tttt", "TestAutomation1@#1", 200},
                {"tttt", "", 400},
                {"alinatkabladze", "Automation@!@1234", 200}
        };

    }

}
