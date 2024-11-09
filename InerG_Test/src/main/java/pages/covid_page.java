package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.GenericUtiity;

import java.util.List;

public class covid_page {
    GenericUtiity u;

    public covid_page(GenericUtiity u) {
        this.u = u;
    }
    
    By bycovid19 = By.xpath("//h1[contains(text(),'COVID-19 Tracker - India')]");
    By bydrop = By.xpath("//select[contains(@class,'data-')]");
    By bystate = By.xpath("//select/option[position() > 1]");
    
    By lineChartPoints = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/*[name()='svg'][1]/*[name()='g'][2]/*[name()='g'][1]/*[name()='rect']");

    public String getCovidHeading() {
        return u.element(bycovid19).getText();
    }

    public void selectState(String stateName) {
        u.waitTime(4);

        u.aClick(bydrop);
        By bystate = By.xpath("//select[@class='data-filter-input']/option[text()='" + stateName + "']");
        u.waitTime(2);

        WebElement stateOption = u.element(bystate);  
        stateOption.click();  
        u.waitTime(4);
    }

    public void printLineChartValues() {
        List<WebElement> points = u.elements(lineChartPoints);

        for (WebElement point : points) {
            String xValue = point.getAttribute("x");
            String yValue = point.getAttribute("y");
            System.out.println("Point - X: " + xValue + ", Y: " + yValue);
        }
    }
}
