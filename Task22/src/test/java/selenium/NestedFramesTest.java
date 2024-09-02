package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedFramesTest {

	public static void main(String[] args) throws Exception {

        // Set the path to the chromedriver executable
		  WebDriverManager.chromedriver().setup();

        // 1. Open a new Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // 2. Navigate to the URL
            driver.navigate().to("https://the-internet.herokuapp.com/nested_frames");

            // 3. Switch to the top frame (By Index or Name)
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
            Thread.sleep(3000);
            
            // 4. Verify that there are three frames on the page
            int frameCount = driver.findElements(By.xpath("frame")).size();
            if (frameCount == 3) {
                System.out.println("There are three frames on the page.");
            } else {
                System.out.println("The number of frames on the page is incorrect.");
            }

            // 5. Switch to the left frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
            
            
            // 6. Verify that the left frame has the text "LEFT"
            String leftFrameText = driver.findElement(By.xpath("//body")).getText();
            if (leftFrameText.trim().equals("LEFT")) {
                System.out.println("Left frame has the text 'LEFT'.");
            } else {
                System.out.println("Left frame does not have the text 'LEFT'.");
            }

            // 7. Switch back to the top frame
            driver.switchTo().parentFrame();
            
            
            // 8. Switch to the middle frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));

            // 9. Verify that the middle frame has the text "MIDDLE"
            String middleFrameText = driver.findElement(By.xpath("//div")).getText();
            if (middleFrameText.trim().equals("MIDDLE")) {
                System.out.println("Middle frame has the text 'MIDDLE'.");
            } else {
                System.out.println("Middle frame does not have the text 'MIDDLE'.");
            }

            // 10. Switch back to the top frame
            driver.switchTo().parentFrame();
            
            
            // 11. Switch to the right frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));

            // 12. Verify that the right frame has the text "RIGHT"
            String rightFrameText = driver.findElement(By.xpath("//body")).getText();
            if (rightFrameText.trim().equals("RIGHT")) {
                System.out.println("Right frame has the text 'RIGHT'.");
            } else {
                System.out.println("Right frame does not have the text 'RIGHT'.");
            }

            // 13. Switch back to the top frame
            driver.switchTo().parentFrame();
            
            
            // 14. Switch back to the main content
            driver.switchTo().defaultContent();

            // 15. Switch to the bottom frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-bottom']")));

            // 16. Verify that the bottom frame has the text "BOTTOM"
            String bottomFrameText = driver.findElement(By.xpath("//body")).getText();
            if (bottomFrameText.trim().equals("BOTTOM")) {
                System.out.println("Bottom frame has the text 'BOTTOM'.");
            } else {
                System.out.println("Bottom frame does not have the text 'BOTTOM'.");
            }

            // 17. Switch back to the main content
            driver.switchTo().defaultContent();

            // 18. Verify that the page title is "Frames"
            String pageTitle = driver.getTitle();
            if (pageTitle.equals("Frames")) {
                System.out.println("Page title is 'Frames'.");
            } else {
                System.out.println("Page title is not 'Frames'.");
            }

        } finally {
            // Close the browser instance
            driver.close();
        }
    }

}
