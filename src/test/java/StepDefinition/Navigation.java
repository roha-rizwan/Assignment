package StepDefinition;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.CommonPages.NavigationPage.ContactPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Navigation {
    public WebDriver driver = new ChromeDriver();
    ContactPage contactPage = new ContactPage(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Given("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String contactPage) throws Throwable {
        WebDriverManager.chromedriver().setup();
        driver.get("https://jupiter.cloud.planittesting.com");
        if (contactPage.equals("Contact")) {
            driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();
        }
        if (contactPage.equals("Shop")) {
            driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
        }
        if (contactPage.equals("Cart")) {
            driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
        }

        // driver.quit();
    }

    @When("^I sumbit the form$")
    public void iSumbitTheForm() throws InterruptedException {

        contactPage.submitForm();
    }

    @And("^I close the page$")
    public void iCloseThePage() {
        driver.quit();
    }

    @Then("^I Verify error messages as follows$")
    public void IVerifyErrorMessagesAsFollows(DataTable dataTable) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        Thread.sleep(3000);

        List<Map<String, String>> errorMessagesTable = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : errorMessagesTable) {

            boolean isErrorMessageExist = contactPage.verifyErrorMessage(form);
            Assert.assertTrue("Expected error message is not existing", isErrorMessageExist);

        }
    }

    @And("^I enter mandatory Fields as follows$")
    public void iEnterMandatoryFieldsAsFollows(DataTable dataTable) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        List<Map<String, String>> enterData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> contactDetails : enterData) {
            Thread.sleep(3000);
            contactPage.enterforeman(contactDetails.get("Data"), contactDetails.get("Field"));
            Thread.sleep(3000);
            contactPage.enterEmail(contactDetails.get("Data"), contactDetails.get("Field"));
            Thread.sleep(3000);
            contactPage.enterMessage(contactDetails.get("Data"), contactDetails.get("Field"));
            Thread.sleep(3000);

        }


    }

    @Then("^I verify error message are gone$")
    public void iVerifyErrorMessageAreGone() {
        WebDriverManager.chromedriver().setup();
        Boolean errormessageNotexist = contactPage.errorMessageNotExsist();
        Assert.assertTrue("Expected error message is existing", errormessageNotexist);

    }

    @And("^I buy following items$")
    public void iBuyFollowingItems(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> buyThings = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> thing : buyThings) {

        int count = Integer.parseInt(thing.get("Count"));
        String item =thing.get("Item");

        for(int i=0;i<count;i++)
        {
            if(item.equals("Stuffed Frog"))
            {
                contactPage.buyStuffedFrog();
            }
            else if (item.equals("Fluffy"))
            {
                contactPage.buyFluffyBunny();
            }
        }

        }
    }

    @Then("^I Veriy the shopping$")
    public void iVeriyTheShopping(DataTable dataTable) {
        List<Map<String, String>> buyThings = dataTable.asMaps(String.class, String.class);

    }
}