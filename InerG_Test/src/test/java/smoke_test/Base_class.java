package smoke_test;

import pages.covid_page;
import utility.GenericUtiity;
import utility.ReadProp;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

public class Base_class {

    protected WebDriver driver; 
    public GenericUtiity u;     
    public covid_page covi;           
    public String baseURL;      

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) throws Exception {
      
    	u = new GenericUtiity(browser);  
        driver = u.getDriver();  

        covi = new covid_page(u);
        
        ReadProp prop = new ReadProp();
        baseURL = prop.getPropData().getProperty("base.URL");

        System.out.println("Base URL: " + baseURL);
        
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();  
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Test Starting: " + this.getClass().getName()); 
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Test Finished: " + this.getClass().getName()); 
    }

    public void printHeading() {
        if (covi != null) {
            String heading = covi.getCovidHeading();
            System.out.println("Page Heading: " + heading);  
        } else {
            System.out.println("home_page object is not initialized.");
        }
    }
}
