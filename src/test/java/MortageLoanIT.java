import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MortageLoanCalculatorPageObject;

public class MortageLoanIT {

    {
        System.setProperty("webdriver.chrome.driver", "C:\\MichalH\\selenium\\src\\test\\resources\\chromedriver.exe");
    }

    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        switch ("driver type property") {
            default:
                driver = new ChromeDriver();
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void happyPath(){

        driver.get("https://www.mortgageloan.com/calculator");

        MortageLoanCalculatorPageObject mortageLoanCalculatorPageObject = new MortageLoanCalculatorPageObject(driver);

        mortageLoanCalculatorPageObject.setLoanAMount(200000);
        mortageLoanCalculatorPageObject.setInterestRate(5);
        mortageLoanCalculatorPageObject.setLength(30);
        mortageLoanCalculatorPageObject.setHomeValue(235000);

        mortageLoanCalculatorPageObject.getNextButton().click();

        mortageLoanCalculatorPageObject.setAnnualTaxes(2000);
        mortageLoanCalculatorPageObject.setAnnualInsurance(1865);
        mortageLoanCalculatorPageObject.setAnnualPMI(0.52);

        mortageLoanCalculatorPageObject.getShowResultsButton().click();

        //Asserts
        mortageLoanCalculatorPageObject.validateCalculationResult();
    }
}
