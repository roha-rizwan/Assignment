package org.example.Pages.CommonPages.NavigationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.DefaultEditorKit;
import java.time.Duration;

public class ContactPage  {
    WebDriver driver ;
    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    WebElement submitFormButton;
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void submitForm()  {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(submitFormButton)).click();
     }
}
