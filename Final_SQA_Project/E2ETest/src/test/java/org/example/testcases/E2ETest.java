package org.example.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class E2ETest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void runTests() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.execute();

        ProductPage productPage = new ProductPage(driver);
        productPage.execute();

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.execute();

        CartPage cartPage = new CartPage(driver);
        cartPage.execute();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.execute();
    }

    public static void main(String[] args) throws InterruptedException {
        E2ETest e2e = new E2ETest();
        e2e.setup();
        e2e.runTests();
        e2e.teardown();
    }
}
