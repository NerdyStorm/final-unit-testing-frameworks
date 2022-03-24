package com.techfios.tests;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.techfios.pages.DashboardPage;
import com.techfios.pages.LoginPage;
import com.techfios.pages.AddCustomerPage;
import com.techfios.utils.BrowserFactory;
import com.techfios.utils.Database;
import com.techfios.utils.Excel;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ObjectSpyTest {
    
    private WebDriver driver;
    private AddCustomerPage addCustomerPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private Excel excel;

    //Database Login Information
    private String username;
    private String password;

    //TestData
    private String fullName;
    private String company;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String zip;
    private String country;

    @Before
    public void beforeTest() throws SQLException{

        //Logger LOG = LoggerFactory.getLogger(ObjectSpyTest.class);

        driver = BrowserFactory.initDriver();
        addCustomerPage = new AddCustomerPage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        Database database = new Database();
        ResultSet rs = database.retrieveData();
        username = rs.getString("username");
        password = rs.getString("password");
        
        excel = new Excel("Excel/data.xlsx", "CustomerInformationData");
        //excel.createFileWithData();
        //value = excel.readFileWithData("Zip", 1);
        
        //System.out.println("Got from Excel File: " + value);
        int occurence = 2;

        fullName = excel.readFileWithData("Full Name", occurence);
        company = excel.readFileWithData("Company", occurence);
        email = excel.readFileWithData("Email", occurence);
        phone = excel.readFileWithData("Phone", occurence);
        city = excel.readFileWithData("City", occurence);
        state = excel.readFileWithData("State", occurence);
        zip = excel.readFileWithData("Zip", occurence);
        country = excel.readFileWithData("Country", occurence);
        

        driver.get("https://techfios.com/billing");

    }

    @Test
    public void test(){

        loginPage.login(username, password);

        dashboardPage.clickOnCustomers();
        dashboardPage.clickOnAddCustomers();

        addCustomerPage.insertFullName(fullName);
        addCustomerPage.insertCompany(company);
        addCustomerPage.insertEmail(email);
        addCustomerPage.insertPhone(phone);
        addCustomerPage.insertCity(city);
        addCustomerPage.insertState(state);
        addCustomerPage.insertZip(zip);
        addCustomerPage.insertCountry(country);
        addCustomerPage.submit();
        addCustomerPage.waitUntilSubmitted();
        addCustomerPage.clickOnListCustomers();
        addCustomerPage.verifyListCustomersPage();
        addCustomerPage.verifyCustomerAdded();



    }
}
