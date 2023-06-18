package org.example.pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class capturescreenshots {

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Convert WebDriver object to TakesScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Capture screenshot as File object
            File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

            // Selection of directory
            String directoryPath = "D:\\screenshots";

            // Specify the complete file path to save the screenshot
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String screenshotPath = directoryPath + File.separator + screenshotName + "_" + timestamp + ".png";

            // Save the screenshot to the specified path
            Files.copy(screenshotFile.toPath(), Path.of(screenshotPath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot captured: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}