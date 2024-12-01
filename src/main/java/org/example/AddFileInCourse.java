package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddFileInCourse {
    public static void AddFileInCourse_TC01() {
        System.out.println("AddFileInCourse_TC01");
        WebDriver driver = new ChromeDriver();

        Precondition.loginAsTeacher(driver);

        // Wait for the course link to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mindful course creation")));
        courseLink.click();

        // Add file resource
        WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
        addFileButton.click();

        WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
        fileOption.click();

        WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("File")));
        fileLink.click();
        System.out.println("Page title is: " + driver.getTitle());
        // Add file name and click on the file container

        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("fm-empty-container"))));
        driver.findElement(By.id("id_name")).sendKeys("Lecture Notes");

        WebElement fmEmptyContainer = wait.until(ExpectedConditions.elementToBeClickable(By.className("fm-empty-container")));
        fmEmptyContainer.click();

        WebElement chooseRecentFiles = driver.findElement(By.linkText("Recent files"));
        chooseRecentFiles.click();

        // Wait for the file upload interaction
        WebElement uploadInteraction = driver.findElement(By.linkText("ccc.png"));
        uploadInteraction.click();

        WebDriverWait waitUpload = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement uploadFile = waitUpload.until(ExpectedConditions.elementToBeClickable(By.className("fp-select-confirm")));
        uploadFile.click();

        WebElement saveAndDisplay = waitUpload.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveAndDisplay.click();

        WebElement fileName = waitUpload.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-header\"]/div/div[2]/div[1]/div/div[2]/h1")));

        System.out.println("File name is: " + driver.getTitle());

        // Close the browser
        driver.close();
        driver.quit();
    }

    public static void AddFileInCourse_TC02() {
        System.out.println("AddFileInCourse_TC02");
        WebDriver driver = new ChromeDriver();

        Precondition.loginAsTeacher(driver);

        // Wait for the course link to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mindful course creation")));
        courseLink.click();

        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Enable editing mode
        driver.manage().timeouts().implicitlyWait(2, java.util.concurrent.TimeUnit.SECONDS);
        WebElement setModeButton = driver.findElement(By.cssSelector("input[name*='setmode']"));
        setModeButton.click();

        // Add file resource
        WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
        addFileButton.click();

        WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
        fileOption.click();

        WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("File")));
        fileLink.click();
        System.out.println("Page title is: " + driver.getTitle());

        WebElement fmEmptyContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fm-empty-container")));
        if (fmEmptyContainer.isEnabled()) {
            fmEmptyContainer.click();
        } else {
            driver.navigate().refresh();
            fmEmptyContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fm-empty-container")));
            fmEmptyContainer.click();
        }

        // Wait for the file upload interaction
        WebElement uploadInteraction = driver.findElement(By.linkText("ccc.png"));
        uploadInteraction.click();

        WebElement uploadFile = wait.until(ExpectedConditions.elementToBeClickable(By.className("fp-select-confirm")));
        uploadFile.click();

        WebElement saveAndDisplay = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveAndDisplay.click();

        WebElement errorText = driver.findElement(By.id("id_error_name"));

        System.out.println("Error text: " + errorText.getText());
        // Close the browser
        driver.close();
        driver.quit();
    }

    public static void AddFileInCourse_TC03() {
        System.out.println("AddFileInCourse_TC03");
        WebDriver driver = new ChromeDriver();

        Precondition.loginAsTeacher(driver);

        // Wait for the course link to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mindful course creation")));
        courseLink.click();

        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Enable editing mode
        driver.manage().timeouts().implicitlyWait(2, java.util.concurrent.TimeUnit.SECONDS);
        WebElement setModeButton = driver.findElement(By.cssSelector("input[name*='setmode']"));
        setModeButton.click();

        // Add file resource
        WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
        addFileButton.click();

        WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
        fileOption.click();

        WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("File")));
        fileLink.click();
        System.out.println("Page title is: " + driver.getTitle());
        // Add file name and click on the file container

        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("fm-empty-container"))));
        driver.findElement(By.id("id_name")).sendKeys("Lecture Notes");

        WebElement saveAndDisplay = driver.findElement(By.id("id_submitbutton"));
        saveAndDisplay.click();

        WebElement errorText = driver.findElement(By.id("id_error_files"));

        System.out.println("Error text: " + errorText.getText());
        // Close the browser
        driver.close();
        driver.quit();
    }

    public static void AddFileInCourse_TC04() {
        System.out.println("AddFileInCourse_TC04");
        WebDriver driver = new ChromeDriver();

        Precondition.loginAsTeacher(driver);

        // Wait for the course link to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mindful course creation")));
        courseLink.click();

        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Enable editing mode
        driver.manage().timeouts().implicitlyWait(2, java.util.concurrent.TimeUnit.SECONDS);
        WebElement setModeButton = driver.findElement(By.cssSelector("input[name*='setmode']"));
        setModeButton.click();

        // Add file resource
        WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
        addFileButton.click();

        WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
        fileOption.click();

        WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("File")));
        fileLink.click();
        System.out.println("Page title is: " + driver.getTitle());

        WebElement fmEmptyContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fm-empty-container")));
        if (fmEmptyContainer.isEnabled()) {
            fmEmptyContainer.click();
        } else {
            driver.navigate().refresh();
            fmEmptyContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fm-empty-container")));
            fmEmptyContainer.click();
        }

        WebElement saveAndDisplay = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveAndDisplay.click();

        WebElement errorText = driver.findElement(By.id("id_error_name"));

        System.out.println("Error text: " + errorText.getText());
        // Close the browser
        driver.close();
        driver.quit();
    }
}