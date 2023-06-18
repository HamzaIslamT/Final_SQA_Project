package org.example.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void selectProduct() throws InterruptedException {
        WebElement firstProductLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='a-link-normal s-no-outline'])[5]")));
        firstProductLink.click();

        // Additional product page validation if needed

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

        TimeUnit.SECONDS.sleep(5);
    }
    public void execute() throws InterruptedException {
        selectProduct();
    }
}
