package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"product-2\"]/div/h4/following-sibling::p/a")
    WebElement stuffedFrogBuy;
    @FindBy(xpath = "//*[@id=\"product-4\"]/div/h4/following-sibling::p/a")
    WebElement fluffyBunny;
    @FindBy(xpath = "//*[@id=\"product-7\"]/div/h4/following-sibling::p/a")
    WebElement valentineBear;
    @FindBy(xpath = "//table/tfoot/tr[1]/td/strong")
    WebElement actualTotal;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public void buyStuffedFrog() {
        wait.until(ExpectedConditions.elementToBeClickable(stuffedFrogBuy)).click();
    }

    public void buyFluffyBunny() {
        wait.until(ExpectedConditions.elementToBeClickable(fluffyBunny)).click();
    }
    public void buyValentineBear()  {
        wait.until(ExpectedConditions.elementToBeClickable(valentineBear)).click();
    }

    public boolean checkTotal(String total) {
        String overAllTotal = actualTotal.getText();
        return total.equals(overAllTotal);
    }

}
