package com.techfios.pages;

import java.util.List;
import java.util.Random;

import com.techfios.utils.Excel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AddCustomerPage {
    
    WebDriver driver;

    public AddCustomerPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    //WebElement Library
    @FindBy (how = How.XPATH, using = "//input[@id='account']") WebElement accountNameElement;
    @FindBy (how=How.XPATH, using = "//select[@id='cid']/*") List<WebElement> companyElement;
    @FindBy (how=How.XPATH, using = "//input[@id='email']") WebElement emailElement;
    @FindBy (how=How.XPATH, using = "//input[@id='phone']") WebElement phoneElement;
    @FindBy (how=How.XPATH, using = "//input[@id='city']") WebElement cityElement;
    @FindBy (how=How.XPATH, using = "//input[@id='state']") WebElement stateElement;
    @FindBy (how=How.XPATH, using = "//input[@id='zip']") WebElement zipCodeElement;
    @FindBy (how=How.XPATH, using = "//input[@id='country']") List<WebElement> CountryElement;
    @FindBy (how=How.XPATH, using = "//button[@id='submit']") WebElement submit;
    @FindBy (how=How.XPATH, using = "//a[contains(text(), 'List Customers')]") WebElement listCustomers;
    @FindBy (how= How.XPATH, using = "//a[@id='summary']") WebElement summaryText;

    public void clickOnListCustomers() {
        listCustomers.click();
    }
    public void verifyListCustomersPage (){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://techfios.com/billing/?ng=contacts/list/"));
        //Assert.assertEquals("Add Contact", pageTitle.getText(), "Not on Add Customers Page!");
     }
     public void waitUntilSubmitted(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(summaryText));
     }
     public void verifyCustomerAdded(){

        int c = 0;

        Excel excel = new Excel("Excel/data.xlsx", "CustomerInformationData");
        //excel.createFileWithData();
        //value = excel.readFileWithData("Zip", 1);
        
        //System.out.println("Got from Excel File: " + value);
        int occurence = 2;

        String fullName = excel.readFileWithData("Full Name", occurence);

        for (int i=1; i < 11; i++){
            

            String xpath = "//table//tbody//" + "tr[" + i +"]/td[3]";

            WebElement name = driver.findElement(By.xpath(xpath));

            if (name.getText().equalsIgnoreCase(fullName)){
                c++;
            }

        }

        // System.out.println("Times Found: " + c);
        Assert.assertTrue(c > 0); //not full proof but works for now.

     }


    public void insertFullName(String name){

        accountNameElement.sendKeys(name);

    }

    public void insertCompany(String companyName){

        for (WebElement element: companyElement){
            if (element.getText().equalsIgnoreCase(companyName)){
                element.click();
            }
        }

    }

    public void insertEmail(String email){

        emailElement.sendKeys(email + (new Random()).nextInt(127419824));

    }

    public void insertPhone(String number){

        phoneElement.sendKeys(number + ((new Random()).nextInt(8999) + 1000));

    }

    public void insertCity(String city){

        cityElement.sendKeys(city);

    }

    public void insertState(String state){

        stateElement.sendKeys(state);

    }

    
    public void insertZip(String zip){

        zipCodeElement.sendKeys(zip);

    }

    public void insertCountry(String country){


        
        for (WebElement element: CountryElement){
            if (element.getText().equalsIgnoreCase(country)){
                element.click();
            }
        }

    }

    public void submit(){
        submit.click();
    }

}
