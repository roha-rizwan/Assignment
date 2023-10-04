package org.example.Pages;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static utilities.AllConstants.HEADER_MESSAGE;

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
    @FindBy(id = "forename-err")
    WebElement foremanError;
    @FindBy(id = "email-err")
    WebElement emailError;
    @FindBy(id = "message-err")
    WebElement messageError;
    @FindBy(xpath = "//*[@id=\"header-message\"]/div")
    WebElement headerError;
    @FindBy(xpath = "//div[2]/div/div")
    WebElement successfulMessage;
    @FindBy(xpath = "//div[2]/div/a")
    WebElement okButton;

    WebDriverWait wait;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitFormButton)).click();
         }
    public boolean verifyErrorMessage(Map<String, String> form) {
        String Field = form.get("Field");
        String expectedMessage = form.get("ErrorMessage");
        switch (Field) {
            case "Forename":
                return expectedMessage.equals(foremanError.getText());
            case "Email": {
                return expectedMessage.equals(emailError.getText());
            }
            case "Message": {
                return expectedMessage.equals(messageError.getText());
            }
            case "Header":
                return expectedMessage.equals(headerError.getText());
        }
        return false;
    }
    public void enterForename(String data, String Field) {
        if (Field.equals("Forename")) {
            wait.until(ExpectedConditions.visibilityOf(foremanField));
            foremanField.sendKeys(data);
        }
    }
    public void enterEmail(String data, String Field) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        if (Field.equals("Email")) {
            emailField.sendKeys(data);
        }
    }
    public void enterMessage(String data, String Field) {
        wait.until(ExpectedConditions.visibilityOf(messageField));
        if (Field.equals("Message")) {
            messageField.sendKeys(data);
        }
    }
    public Boolean errorMessageNotExist() {

        String blueMessage= headerError.getAttribute("textContent").trim();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            if (HEADER_MESSAGE.equals(blueMessage)) {
                if ((wait.until(ExpectedConditions.invisibilityOf(foremanError))) &&
                        (wait.until(ExpectedConditions.invisibilityOf(messageError))) &&
                        (wait.until(ExpectedConditions.invisibilityOf(emailError)))) {
                    return true;
                }
            }
            } catch (NoSuchElementException ignored) {
            //Do nothing
            }
         return false;
    }

    public boolean verifyMessage(String message)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(okButton));
        String result = successfulMessage.getAttribute("textContent").trim();
        return message.equals(result);
    }
}

