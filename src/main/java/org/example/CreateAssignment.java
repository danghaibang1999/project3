package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class CreateAssignment {

    public static void run() throws InterruptedException, IOException {
        CreateAssignment_TC("TC01");
        CreateAssignment_TC("TC02");
        CreateAssignment_TC("TC03");
        CreateAssignment_TC("TC04");
    }

    public static void CreateAssignment_TC(String testCaseId) throws InterruptedException, IOException {
        System.out.println("CreateAssignment_" + testCaseId);

        WebDriver driver = new ChromeDriver();

        Precondition.loginAsTeacher(driver);

        // Load test data
        Map<String, TestData.TestCase> testDataMap = TestData.loadTestData();
        TestData.TestCase testData = testDataMap.get(testCaseId);
        System.out.println("Description: " + testData.description);

        // Wait for the course link to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Add file resource
        WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
        addFileButton.click();

        WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
        fileOption.click();

        WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Assignment")));
        fileLink.click();

        driver.findElement(By.id("id_name")).sendKeys("Assignment " + testCaseId);

        // Set "Allow submissions from" date
        if (testData.allowSubmissionsFrom.enabled) {
            WebElement enableCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_allowsubmissionsfromdate_enabled")));
            if (!enableCheckbox.isSelected()) {
                enableCheckbox.click();
            }
            new Select(driver.findElement(By.id("id_allowsubmissionsfromdate_day"))).selectByVisibleText(String.valueOf(testData.allowSubmissionsFrom.day));
            new Select(driver.findElement(By.id("id_allowsubmissionsfromdate_month"))).selectByVisibleText(testData.allowSubmissionsFrom.month);
            new Select(driver.findElement(By.id("id_allowsubmissionsfromdate_year"))).selectByVisibleText(String.valueOf(testData.allowSubmissionsFrom.year));
            new Select(driver.findElement(By.id("id_allowsubmissionsfromdate_hour"))).selectByVisibleText(String.format("%02d", testData.allowSubmissionsFrom.hour));
            new Select(driver.findElement(By.id("id_allowsubmissionsfromdate_minute"))).selectByVisibleText(String.format("%02d", testData.allowSubmissionsFrom.minute));

            System.out.println("Allow submissions from: " + testData.allowSubmissionsFrom.day + " " + testData.allowSubmissionsFrom.month + " " + testData.allowSubmissionsFrom.year + " " + testData.allowSubmissionsFrom.hour + " " + testData.allowSubmissionsFrom.minute);
        }

        // Set "Due date"
        if (testData.dueDate.enabled) {
            WebElement enableCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_duedate_enabled")));
            if (!enableCheckbox.isSelected()) {
                enableCheckbox.click();
            }
            new Select(driver.findElement(By.id("id_duedate_day"))).selectByVisibleText(String.valueOf(testData.dueDate.day));
            new Select(driver.findElement(By.id("id_duedate_month"))).selectByVisibleText(testData.dueDate.month);
            new Select(driver.findElement(By.id("id_duedate_year"))).selectByVisibleText(String.valueOf(testData.dueDate.year));
            new Select(driver.findElement(By.id("id_duedate_hour"))).selectByVisibleText(String.format("%02d", testData.dueDate.hour));
            new Select(driver.findElement(By.id("id_duedate_minute"))).selectByVisibleText(String.format("%02d", testData.dueDate.minute));

            System.out.println("Due date: " + testData.dueDate.day + " " + testData.dueDate.month + " " + testData.dueDate.year + " " + testData.dueDate.hour + " " + testData.dueDate.minute);
        }

        // Set "Cut-off date"
        if (testData.cutOffDate.enabled) {
            WebElement enableCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_cutoffdate_enabled")));
            if (!enableCheckbox.isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", enableCheckbox);
            }
            new Select(driver.findElement(By.id("id_cutoffdate_day"))).selectByVisibleText(String.valueOf(testData.cutOffDate.day));
            new Select(driver.findElement(By.id("id_cutoffdate_month"))).selectByVisibleText(testData.cutOffDate.month);
            new Select(driver.findElement(By.id("id_cutoffdate_year"))).selectByVisibleText(String.valueOf(testData.cutOffDate.year));
            new Select(driver.findElement(By.id("id_cutoffdate_hour"))).selectByVisibleText(String.format("%02d", testData.cutOffDate.hour));
            new Select(driver.findElement(By.id("id_cutoffdate_minute"))).selectByVisibleText(String.format("%02d", testData.cutOffDate.minute));

            System.out.println("Cut-off date: " + testData.cutOffDate.day + " " + testData.cutOffDate.month + " " + testData.cutOffDate.year + " " + testData.cutOffDate.hour + " " + testData.cutOffDate.minute);
        }

        // Set "Remind me to grade by" date
        if (testData.remindMeToGradeBy.enabled) {
            WebElement enableCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gradingduedate_label")));
            if (!enableCheckbox.isSelected()) {
                enableCheckbox.click();
            }
            new Select(driver.findElement(By.id("id_gradingduedate_day"))).selectByVisibleText(String.valueOf(testData.remindMeToGradeBy.day));
            new Select(driver.findElement(By.id("id_gradingduedate_month"))).selectByVisibleText(testData.remindMeToGradeBy.month);
            new Select(driver.findElement(By.id("id_gradingduedate_year"))).selectByVisibleText(String.valueOf(testData.remindMeToGradeBy.year));
            new Select(driver.findElement(By.id("id_gradingduedate_hour"))).selectByVisibleText(String.format("%02d", testData.remindMeToGradeBy.hour));
            new Select(driver.findElement(By.id("id_gradingduedate_minute"))).selectByVisibleText(String.format("%02d", testData.remindMeToGradeBy.minute));

            System.out.println("Remind me to grade by: " + testData.remindMeToGradeBy.day + " " + testData.remindMeToGradeBy.month + " " + testData.remindMeToGradeBy.year + " " + testData.remindMeToGradeBy.hour + " " + testData.remindMeToGradeBy.minute);
        }

        WebElement saveAndDisplay = driver.findElement(By.id("id_submitbutton"));
        saveAndDisplay.click();

        if (Objects.equals(testData.expectedResult.result, "passed")) {

            // Wait for the page header to be visible
            WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-header-headings")));

            // Verify the text inside the div and h1 elements
            WebElement assignmentText = pageHeader.findElement(By.cssSelector("div.text-muted.text-uppercase.small.line-height-3"));
            WebElement assignmentTitle = pageHeader.findElement(By.cssSelector("h1.h2.mb-0"));
            String expectedAssignmentText = testData.expectedResult.signal.message;
            String expectedAssignmentTitle = String.format("Assignment %s", testCaseId);

            if (!assignmentText.getText().equals(expectedAssignmentText)) {
                throw new AssertionError("Expected text: " + expectedAssignmentText + ", but got: " + assignmentText.getText());
            }

            if (!assignmentTitle.getText().equals(expectedAssignmentTitle)) {
                throw new AssertionError("Expected title: " + expectedAssignmentTitle + ", but got: " + assignmentTitle.getText());
            }

            System.out.println("Test passed successfully, test name: Create Assignment " + testCaseId);
        } else if (Objects.equals(testData.expectedResult.result, "failed")) {
            String expectedErrorId = testData.expectedResult.signal.id;
            String expectedErrorText = testData.expectedResult.signal.message;
            WebElement errorText = driver.findElement(By.id(expectedErrorId));

            if (!errorText.getText().equals(expectedErrorText)) {
                throw new AssertionError("Expected error text: " + expectedErrorText + ", but got: " + errorText.getText());
            }

            System.out.println("Test passed successfully, test name: Create Assignment " + testCaseId);
        } else {
            System.out.println("Test failed, test name: Create Assignment " + testCaseId);
        }

        // Close the browser
        driver.close();
        driver.quit();
    }
}