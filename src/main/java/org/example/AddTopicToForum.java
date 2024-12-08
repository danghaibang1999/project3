package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddTopicToForum {

    public static void run() {
        runTestCase(AddTopicToForum::AddTopicToForum_TC01, "AddTopicToForum_TC01");
    }

    private static void runTestCase(Runnable testCase, String testCaseName) {
        try {
            System.out.println("Running: " + testCaseName);
            testCase.run();
        } catch (Exception e) {
            System.err.println("Test case failed: " + testCaseName);
            e.printStackTrace();
        }
    }

    public static void AddTopicToForum_TC01() {
        WebDriver driver = new ChromeDriver();

        Precondition.loginAsManager(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // click item with role="menubar" then child item is li -> a
        WebElement siteAdministration = driver.findElement(By.className("moremenu"));
        System.out.println("1");
        WebElement siteAdministrationLink = siteAdministration.findElement(By.cssSelector("ul > li:nth-child(2) > a"));
        System.out.println("2");
        siteAdministrationLink.click();
        System.out.println(siteAdministrationLink.getText());
        System.out.println("3");
        WebElement addATopicButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a new topic")));
        addATopicButton.click();
        System.out.println("4");
        String expectedTopicName = "Software Testing";
        driver.findElement(By.id("id_subject")).sendKeys(expectedTopicName);

        String expectedMessage = "Software testing is an important process in the software development lifecycle . It involves verifying and validating that a software application is free of bugs, meets the technical requirements set by its design and development , and satisfies user requirements efficiently and effectively.";
        driver.findElement(By.id("id_message_ifr")).sendKeys(expectedMessage);

        WebElement saveAndDisplay = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveAndDisplay.click();

        WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-header-headings")));
        // Verify the fileName
        WebElement fileName = pageHeader.findElement(By.xpath("//*[@id=\"page-header\"]/div/div[2]/div[1]/div/div[2]/h1"));

        if (!fileName.getText().equals(expectedTopicName)) {
            throw new AssertionError("Expected topic's name: " + expectedTopicName + ", but got: " + fileName.getText());
        }
        System.out.println("Expected topic's name: " + expectedTopicName + ", got: " + fileName.getText());
        System.out.println("Test passed successfully, test case: AddTopicToForum_TC01");
        driver.close();
        driver.quit();
    }
}