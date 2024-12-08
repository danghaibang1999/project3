package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class AddQuiz {
    public static void AddQuizTestCases() throws InterruptedException {
        // Set the path to chromedriver
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open Moodle demo site
            driver.get("https://sandbox.moodledemo.net/");

            // Log in, navigate to course and turn editing on
            Precondition.loginAsTeacher(driver);

            // Add a quiz

            WebElement addFileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/button")));
            addFileButton.click();

            WebElement fileOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[5]/div/div[3]/div/section/div/div/div/ul/li[5]/div[1]/div[2]/div[2]/div/div/div/button")));
            fileOption.click();

            WebElement fileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quiz")));
            fileLink.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("id_name"))).sendKeys("Unit 1 Quiz");
            driver.findElement(By.id("id_submitbutton")).click();

            // Read quiz data from CSV and add quizzes
            String csvFile = "quiz_data.csv";
            String line;
            String csvSplitBy = ",";
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String header = br.readLine(); // Skip header row
                while ((line = br.readLine()) != null) {
                    String[] quizData = line.split(csvSplitBy);
                    String quizName = quizData[0]; // Assume first column is quiz name

                    // Navigate back to course page
                    driver.get("URL_course_page"); // Replace with actual course page URL
                    driver.findElement(By.linkText("Turn editing on")).click();
                    driver.findElement(By.linkText("Add an activity or resource")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quiz"))).click();
                    driver.findElement(By.id("id_name")).sendKeys(quizName);
                    driver.findElement(By.id("id_submitbutton")).click();
                    Thread.sleep(2000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Test cases
            String[][] testCases = {
                    {"Q", "Quiz added successfully"},
                    {"A".repeat(255), "Quiz added successfully"},
                    {"", "You must supply a value here"}
            };

            for (String[] testCase : testCases) {
                String name = testCase[0];
                String expected = testCase[1];

                driver.get("URL_course_page"); // Replace with actual course page URL
                driver.findElement(By.linkText("Turn editing on")).click();
                driver.findElement(By.linkText("Add an activity or resource")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quiz"))).click();
                driver.findElement(By.id("id_name")).sendKeys(name);
                driver.findElement(By.id("id_submitbutton")).click();

                // Validate result
                try {
                    WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), '" + expected + "')]"));
                    System.out.println("Test passed for " + name);
                } catch (Exception e) {
                    System.out.println("Test failed for " + name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}