package selenium;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandlingTest {

	public static void main(String[] args) throws Exception {

		//Setup WebDriver
        WebDriverManager.chromedriver().setup();

        // 1. Open a new Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // 2. Navigate to the URL
            driver.navigate().to("https://the-internet.herokuapp.com/windows");

            // 3. Click the "Click Here" button to open a new window
            WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
            clickHereButton.click();
            Thread.sleep(3000);
            
            // 4. Switch to the newly opened window
            String originalWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            Thread.sleep(3000);
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // 5. Verify that the text "New Window" is present on the page
            String newWindowText = driver.findElement(By.tagName("h3")).getText();
            if (newWindowText.equals("New Window")) {
                System.out.println("Text 'New Window' is present on the page.");
            } else {
                System.out.println("Text 'New Window' is NOT present on the page.");
            }

            // 6. Close the new window
            driver.close();

            // 7. Switch back to the original window and verify it's active
            driver.switchTo().window(originalWindow);
            String originalWindowTitle = driver.getTitle();
            if (originalWindowTitle.equals("The Internet")) {
                System.out.println("Original window is active.");
            } else {
                System.out.println("Original window is NOT active.");
            }

        } finally {
            // 8. Close the browser instance
            driver.quit();
        }
    }
}

