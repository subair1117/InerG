package smoke_test;

import pages.covid_page;
import utility.ExceUtiity;

import org.testng.annotations.Test;
import java.io.IOException;

public class Covid_TC extends Base_class {

    covid_page covi;

    @Test
    public void verify_url() throws IOException {
        u.launchUrl(baseURL);

        printHeading();

        ExceUtiity excel = new ExceUtiity("Sheet1"); 
        String state = excel.readData("State");

        System.out.println("State fetched from Excel: " + state);

        covi = new covid_page(u);  

        covi.selectState(state);

        System.out.println("Selected State: " + state);
        printHeading(); 

        covi.printLineChartValues();  
    }
}
