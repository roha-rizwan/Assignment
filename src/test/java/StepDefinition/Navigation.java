package StepDefinition;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.CommonPages.NavigationPage.ContactPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();

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
    public void IVerifyErrorMessagesAsFollows(DataTable dataTable) {
        WebDriverManager.chromedriver().setup();
        driver.get("https://jupiter.cloud.planittesting.com");
        driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();
        String actualforenmaeErrorMEssage = driver.findElement(By.id("//*[@id=\"forename-err\"]")).toString();
        String messagErrorMessage = driver.findElement(By.id("//*[@id=\"message-err\"]")).toString();

        List<Map<String, String>> user = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {


        }
    }
}