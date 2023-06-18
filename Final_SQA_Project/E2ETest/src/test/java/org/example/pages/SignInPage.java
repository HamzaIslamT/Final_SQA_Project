package org.example.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SignInPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void proceedToCheckout() {
        // Click on the Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[@class='a-button-inner']"));
        proceedToCheckoutButton.click();

        // Validate if the sign-in page is visible
        WebElement signInPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"authportal-main-section\"]")));
        assert signInPageTitle.isDisplayed() : "Sign-In page not displayed";

        // Enter an email and validate error message
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ap_email']")));
        emailField.sendKeys("123mail");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='continue']")));
        continueButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-alert-content']")));
        assert errorMessage.isDisplayed() : "Error message not displayed for invalid email";

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

    public void execute() {
        proceedToCheckout();
    }
}
