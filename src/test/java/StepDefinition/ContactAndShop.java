package StepDefinition;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.ContactAndShopPage.ContactAndShopPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Map;
public class ContactAndShop {
    public WebDriver driver = new ChromeDriver();
    ContactAndShopPage contactAndShopPage = new ContactAndShopPage(driver);
    @Given("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String contactPage) {
        switch (contactPage) {
            case "Home":
                WebDriverManager.chromedriver().setup();
                driver.get("https://jupiter.cloud.planittesting.com");
                break;
            case "Contact":
                driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();
                break;
            case "Shop":
                driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
                break;
            case "Cart":

                driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
                break;
        }
    }

    @When("^I submit the form$")
    public void iSubmitTheForm() {
        contactAndShopPage.submitForm();
    }

    @After
    public void iCloseThePage() {
        driver.quit();
    }
    @Then("^I Verify error messages as follows$")
    public void IVerifyErrorMessagesAsFollows(DataTable dataTable) {
        List<Map<String, String>> errorMessagesTable = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : errorMessagesTable) {
            boolean isErrorMessageExist = contactAndShopPage.verifyErrorMessage(form);
            Assert.assertTrue("Expected error message does not exist", isErrorMessageExist);
        }
    }
    @And("^I enter mandatory Fields as follows$")
    public void iEnterMandatoryFieldsAsFollows(DataTable dataTable) {
       List<Map<String, String>> enterData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> contactDetails : enterData) {
            contactAndShopPage.enterForename(contactDetails.get("Data"), contactDetails.get("Field"));
            contactAndShopPage.enterEmail(contactDetails.get("Data"), contactDetails.get("Field"));
            contactAndShopPage.enterMessage(contactDetails.get("Data"), contactDetails.get("Field"));
           }
    }

    @Then("^I verify error messages are gone$")
    public void iVerifyErrorMessagesAreGone() throws InterruptedException {
        Thread.sleep(2000);
        Boolean errorMessageNotExist = contactAndShopPage.errorMessageNotExsist();
        Assert.assertTrue("Expected error message is existing", errorMessageNotExist);
    }

    @And("^I buy following items$")
    public void iBuyFollowingItems(DataTable dataTable)  {
        List<Map<String, String>> buyThings = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> thing : buyThings) {
        int count = Integer.parseInt(thing.get("Count"));
        String item =thing.get("Item");
        for(int i=0;i<count;i++)
        {
            switch (item) {
                case "Stuffed Frog":
                    contactAndShopPage.buyStuffedFrog();
                    break;
                case "Fluffy Bear":
                    contactAndShopPage.buyFluffyBunny();
                    break;
                case "Valentine Bear":
                    contactAndShopPage.buyValentineBear();
                    break;
            }
        }

        }
    }
    @Then("^I Verify the shopping$")
    public void iVerifyTheShopping(DataTable dataTable) throws InterruptedException {
      Thread.sleep(2000);
        int rows = driver.findElements(By.xpath("//tbody//tr")).size();
        List<Map<String, String>> buyThings = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> thing : buyThings) {
        for(int i =1 ; i<=rows; i++)
        {
          WebElement itemCell = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[1]"));
          if (itemCell.getText().equals(thing.get("Item")))
          {
            WebElement priceCEll = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]"));
            WebElement quantityCEll = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]/input"));
            WebElement subtotalCell = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[4]"));
            Assert.assertEquals(thing.get("Price"),priceCEll.getText());
            Assert.assertEquals(thing.get("Quantity"),quantityCEll.getAttribute("value"));
            Assert.assertEquals(thing.get("SubTotal"),subtotalCell.getText());
          }
        }
    }
}
@And("^I verify the total \"([^\"]*)\"$")
    public void iVerifyTheTotal(String total) {
        boolean checkActualTotal = contactAndShopPage.checkTotal(total);
        Assert.assertTrue("Total is wrong",checkActualTotal);
    }
    @Then("^I Verify the successful message as \"([^\"]*)\"$")
    public void iVerifyTheSuccessfulMessageAs(String message)  {
        boolean result = contactAndShopPage.verifyMessage(message);
        Assert.assertTrue("Message is wrong",result);
    }
}