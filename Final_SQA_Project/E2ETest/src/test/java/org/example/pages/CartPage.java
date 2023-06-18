package org.example.pages;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addToCart() throws InterruptedException {
        // Add the product to the cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
        TimeUnit.SECONDS.sleep(5);
        WebElement goToBasketButton = driver.findElement(By.id("nav-cart"));
        goToBasketButton.click();
        TimeUnit.SECONDS.sleep(5);

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
        addToCart();
    }
}
