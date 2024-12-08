package org.example;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.time.Duration;

public class Precondition {

    private static JSONObject credentials;

    static {
        try (InputStream is = Precondition.class.getResourceAsStream("/loginData.json")) {
            if (is != null) {
                JSONTokener tokener = new JSONTokener(is);
                credentials = new JSONObject(tokener);
            } else {
                throw new RuntimeException("Could not find credentials.json file");
            }
        } catch (Exception e) {
            System.out.println("Error reading loginData.json file: " + e.getMessage());
        }
    }

    public static void loginAsTeacher(WebDriver driver) {
        login(driver, "teacher");
    }

    public static void loginAsStudent(WebDriver driver) {
        login(driver, "student");
    }

    public static void loginAsManager(WebDriver driver) {
        login(driver, "manager");
    }

    public static void loginAsParent(WebDriver driver) {
        login(driver, "parent");
    }

    public static void loginAsPrivacyOfficer(WebDriver driver) {
        login(driver, "privacyOfficer");
    }

    private static void login(WebDriver driver, String role) {
        System.out.println("******* Preconditions *******");
        System.out.println("Logging in as " + role);

        String username = credentials.getJSONObject(role).getString("username");
        String password = credentials.getJSONObject(role).getString("password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        driver.get("https://school.moodledemo.net/login/index.php");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();

        if (role.equals("teacher")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mindful course creation")));
            courseLink.click();

            driver.manage().timeouts().implicitlyWait(2, java.util.concurrent.TimeUnit.SECONDS);
            WebElement setModeButton = driver.findElement(By.cssSelector("input[name*='setmode']"));
            setModeButton.click();
        } else if (role.equals("student")) {

        } else if (role.equals("manager")) {

        }

    }
}