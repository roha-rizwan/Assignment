
package Hooks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

WebDriver driver;
    @After
     public void closeBrowser()
    {
        driver.quit();
    }
}

