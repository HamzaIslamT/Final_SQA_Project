package org.example.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void verifyProductDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));

        WebElement productTitle = driver.findElement(By.xpath("//span[@id='productTitle']"));
        WebElement productPrice = driver.findElement(By.xpath("//div[@id='corePrice_feature_div']"));
        WebElement productDescription = driver.findElement(By.xpath("//div[@id='productDescription_feature_div']"));
        WebElement productImages = driver.findElement(By.xpath("//div[@id='imageBlock_feature_div']"));
        WebElement customerReviews = driver.findElement(By.xpath("//div[@id='customer-reviews_feature_div']"));

        Assert.assertTrue(productTitle.isDisplayed(), "Product title is not displayed correctly.");
        Assert.assertTrue(productPrice.isDisplayed(), "Product price is not displayed correctly.");
        Assert.assertTrue(productDescription.isDisplayed(), "Product description is not displayed correctly.");
        Assert.assertTrue(productImages.isDisplayed(), "Product images are not displayed correctly.");
        Assert.assertTrue(customerReviews.isDisplayed(), "Customer reviews are not displayed correctly.");

        TimeUnit.SECONDS.sleep(2);

        WebElement quantityDropdown = driver.findElement(By.xpath("//select[@id='quantity']"));
        Select quantitySelect = new Select(quantityDropdown);
        quantitySelect.selectByVisibleText("2");

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        verifyProductDetails();
    }
}
