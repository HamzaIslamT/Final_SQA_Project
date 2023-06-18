package org.example.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void searchProduct() throws InterruptedException {
        driver.get("https://www.amazon.ae/");

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Perfumes");
        searchBox.submit();

        // Additional search page validation if needed
        WebElement searchResults = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div"));
        if (searchResults.isDisplayed()) {
            System.out.println("Search results are displayed.");
        } else {
            System.out.println("Search results are not displayed.");
        }

        TimeUnit.SECONDS.sleep(2);
    }

    @Test(dependsOnMethods = "searchProduct")
    public void advancedSearchWithFilters() throws InterruptedException {
        // Perform an advanced search with filters
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement brandsButton = driver.findElement(By.xpath("//*[@id=\"p_89/Lattafa\"]/span/a/div"));
        brandsButton.click();

        TimeUnit.SECONDS.sleep(3);
        // Select price range filter
        WebElement priceRangeFilter = driver.findElement(By.id("priceRefinements"));
        priceRangeFilter.click();

        WebElement minPriceInput = driver.findElement(By.id("low-price"));
        minPriceInput.sendKeys("50");

        WebElement maxPriceInput = driver.findElement(By.id("high-price"));
        maxPriceInput.sendKeys("100");

        WebElement goButton = driver.findElement(By.xpath("//*[@id=\"a-autoid-1\"]"));
        goButton.click();

        TimeUnit.SECONDS.sleep(2);

            // Capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Define the destination directory to save the screenshots
            String directoryPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Define the destination path with a timestamp in the filename
            String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
            String filePath = directoryPath + fileName;

            try {
                // Save the screenshot to the destination path
                FileUtils.copyFile(srcFile, new File(filePath));
                System.out.println("Screenshot saved at: " + filePath);

                // Verify if the screenshot file exists
                File screenshotFile = new File(filePath);
                if (screenshotFile.exists()) {
                    System.out.println("Screenshot saved successfully.");
                } else {
                    System.out.println("Failed to save the screenshot.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void execute() throws InterruptedException {
        searchProduct();
        advancedSearchWithFilters();
    }
}
