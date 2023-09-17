package org.example.Pages.CommonPages.NavigationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;


public class ContactPage {
    WebDriver driver;
    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    WebElement submitFormButton;

    @FindBy(id = "forename")
    WebElement foremanField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "message")
    WebElement messageField;
    @FindBy(xpath = "//*[@id=\"product-2\"]/div/h4/following-sibling::p/a")
    WebElement stuffedFrogBuy;
    @FindBy(xpath = "//*[@id=\"product-4\"]/div/h4/following-sibling::p/a")
    WebElement flufflyBunny;



    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void submitForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(submitFormButton)).click();
        Thread.sleep(5000);


    }

    public boolean verifyErrorMessage(Map<String, String> form) {


        String Field = form.get("Field");
        if (Field.equals("Forename")) {
            String expectedErrorMessage1 = form.get("ErrorMessage");
            String actualForenmaeErrorMessage = driver.findElement(By.xpath("//*[@id='forename-err']")).getText();

            return expectedErrorMessage1.equals(actualForenmaeErrorMessage);

        }
        if (Field.equals("Message")) {
            String expectedErrorMessage2 = form.get("ErrorMessage");

            String actualMessagErrorMessage = driver.findElement(By.id("message-err")).getText();
            return expectedErrorMessage2.equals(actualMessagErrorMessage);
        }


        return false;

    }


    public void enterforeman(String data, String Field) {
        if (Field.equals("Forename")) {
            foremanField.sendKeys(data);

        }
    }

    public void enterEmail(String data, String Field) {

        if (Field.equals("Email")) {
            emailField.sendKeys(data);
        }
    }

    public void enterMessage(String data, String Field) {

        if (Field.equals("Message")) {
            messageField.sendKeys(data);
        }
    }

    public Boolean errorMessageNotExsist() {
        boolean foremnanNotDisplayed = false;
        boolean MessageNotDisplay = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {


            foremnanNotDisplayed = wait.until(ExpectedConditions.invisibilityOf((driver.findElement(By.xpath("//*[@id='forename-err']")))));
            System.out.println("foremnanNotDisplayed" + foremnanNotDisplayed);
            // Boolean foremanMessageNotDisplay = isDisplayed();
            MessageNotDisplay = wait.until(ExpectedConditions.invisibilityOf((driver.findElement(By.id("message-err")))));
            // Boolean MessageNotDisplay = driver.findElement(By.id("message-err")).isDisplayed();
        } catch (NoSuchElementException ignored) {
            return true;
        }
return false;

    }

    public void buyStuffedFrog() throws InterruptedException {
        Thread.sleep(2000);
        stuffedFrogBuy.click();

    }

    public void buyFluffyBunny() throws InterruptedException {
        Thread.sleep(2000);
        flufflyBunny.click();
    }
}
