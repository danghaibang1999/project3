package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PrivateMessagingTest {

    public static void run() throws InterruptedException {
        PrivateMessagingTest test = new PrivateMessagingTest();
        test.testValidMessage();
        test.testEmptyMessage();
        test.testEmojiMessage();
        test.testDeleteMessage();
    }

    public WebDriver setupTestPrecondition() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
        // Login
        driver.get("https://school.moodledemo.net/login/index.php?loginredirect=1");
        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("moodle2024");
        driver.findElement(By.id("loginbtn")).click();
        return driver;
    }

    public void openChatBoxAndSelectConversation(WebDriver driver) {
        driver.findElement(By.xpath("//i[@aria-label=\"Toggle messaging drawer\"]")).click();
        driver.findElement(By.id("view-overview-messages-toggle")).click();
        WebElement conversationContainer = driver.findElement(By.xpath("//div[@aria-labelledby='view-overview-messages-toggle']"));
        WebElement conversationList = conversationContainer.findElement(By.className("list-group"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(conversationList));
        conversationList.findElement(By.className("list-group-item")).click();
    }

    public void closeChatBox(WebDriver driver) {
        driver.findElement(By.xpath("//i[@aria-label=\"Toggle messaging drawer\"]")).click();
    }

    public void testValidMessage() throws InterruptedException {
        WebDriver driver = setupTestPrecondition();
        openChatBoxAndSelectConversation(driver);
        String message = "This is a test message.";  // Example message

        // Send message to the other side
        WebElement textbox = driver.findElement(By.xpath("//textarea[@data-region=\"send-message-txt\"]"));
        textbox.sendKeys(message);
        driver.findElement(By.xpath("//button[@aria-label=\"Send message\"]")).click();
        Thread.sleep(10000);

        WebElement messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> messages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));
        String lastMessageContent = messages.get(messages.size() - 1).findElement(By.tagName("p")).getText();
        assert lastMessageContent.equals(message);

        closeChatBox(driver);
        logout(driver);
    }

    public void testEmptyMessage() throws InterruptedException {
        WebDriver driver = setupTestPrecondition();
        openChatBoxAndSelectConversation(driver);

        Thread.sleep(5000);
        WebElement messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> messages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));
        String lastMessageContent = messages.get(messages.size() - 1).findElement(By.tagName("p")).getText();

        // Try to send empty message
        driver.findElement(By.xpath("//textarea[@data-region=\"send-message-txt\"]")).click();
        driver.findElement(By.xpath("//button[@aria-label=\"Send message\"]")).click();
        Thread.sleep(5000);

        messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> newMessages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));
        String newLastMessageContent = newMessages.get(newMessages.size() - 1).findElement(By.tagName("p")).getText();

        assert lastMessageContent.equals(newLastMessageContent);

        closeChatBox(driver);
        logout(driver);
    }

    public void testEmojiMessage() throws InterruptedException {
        WebDriver driver = setupTestPrecondition();
        openChatBoxAndSelectConversation(driver);

        // Open emoji box
        driver.findElement(By.xpath("//button[@aria-label=\"Toggle emoji picker\"]")).click();
        WebElement emojiContainer = driver.findElement(By.xpath("//div[@data-region=\"emoji-picker-container\"]"));
        WebElement emojiButton = emojiContainer.findElement(By.xpath("//button[@class=\"btn btn-link btn-icon p-0 rounded-lg emoji-button\"]"));
        String emojiValue = emojiButton.getAttribute("data-unified");
        emojiValue = new String(Character.toChars(Integer.parseInt(emojiValue, 16)));
        emojiButton.click();
        Thread.sleep(5000);

        // Send the emoji
        driver.findElement(By.xpath("//button[@aria-label=\"Send message\"]")).click();
        Thread.sleep(5000);

        WebElement messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> messages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));
        String lastMessageContent = messages.get(messages.size() - 1).findElement(By.tagName("p")).getText();
        assert lastMessageContent.equals(emojiValue);

        closeChatBox(driver);
        logout(driver);
    }

    public void testDeleteMessage() throws InterruptedException {
        WebDriver driver = setupTestPrecondition();
        openChatBoxAndSelectConversation(driver);

        // Choose last message to delete
        Thread.sleep(5000);
        WebElement messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> lastMessages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));

        lastMessages.get(lastMessages.size() - 1).click();

        // Press button to delete
        driver.findElement(By.xpath("//button[@data-action=\"delete-selected-messages\"]")).click();
        driver.findElement(By.xpath("//button[@data-action=\"confirm-delete-selected-messages\"]")).click();
        Thread.sleep(5000);

        messageContainer = driver.findElement(By.xpath("//div[@data-region=\"content-message-container\"]"));
        List<WebElement> newLastMessages = messageContainer.findElements(By.xpath("//div[@data-region=\"message\"]"));
        assert newLastMessages.size() == lastMessages.size() - 1;

        closeChatBox(driver);
        logout(driver);
    }

    public void logout(WebDriver driver) {
        driver.findElement(By.xpath("//a[@aria-label='Log out']")).click();
    }
}
