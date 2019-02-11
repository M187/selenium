package pageobjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

@Getter
public class MortageLoanCalculatorPageObject {

    private WebDriver driver;

    private WebElement nextButton;
    private WebElement previousButton;
    private WebElement showResultsButton;

    private WebElement loanAmount;
    private WebElement interestRate;
    private WebElement length;
    private WebElement homeValue;

    private WebElement annualTaxes;
    private WebElement annualInsurance;
    private WebElement annualPMI;

    public MortageLoanCalculatorPageObject(WebDriver driver){
        this.driver = driver;

        nextButton = driver.findElement(By.xpath("//*[@id=\"wizard-pager\"]/li[2]/a"));
        previousButton = driver.findElement(By.xpath("//*[@id=\"wizard-pager\"]/li[1]/a"));
        showResultsButton = driver.findElement(By.xpath("//*[@id=\"wizard-pager\"]/li[5]/a"));

        loanAmount = driver.findElement(By.xpath("//*[@id=\"calculator_widget_amount\"]"));
        interestRate = driver.findElement(By.xpath("//*[@id=\"calculator_widget_interest\"]"));
        length = driver.findElement(By.xpath("//*[@id=\"calculator_widget_Length\"]"));
        homeValue = driver.findElement(By.xpath("//*[@id=\"calculator_widget_HomeValue\"]"));

        annualTaxes = driver.findElement(By.xpath("//*[@id=\"calculator_widget_PropertyTaxes\"]"));
        annualInsurance = driver.findElement(By.xpath("//*[@id=\"calculator_widget_Insurance\"]"));
        annualPMI = driver.findElement(By.xpath("//*[@id=\"calculator_widget_PMI\"]"));
    }

    public void setLoanAMount(int amount){
        loanAmount.clear();
        loanAmount.click();
        loanAmount.sendKeys(String.valueOf(amount));
    }

    public void setInterestRate(int interestRate){
        this.interestRate.clear();
        this.interestRate.click();
        this.interestRate.sendKeys(String.valueOf(interestRate));
    }

    public void setLength(int length){
        this.length.clear();
        this.length.click();
        this.length.sendKeys(String.valueOf(length));
    }

    public void setHomeValue(int homeValue){
        length.clear();
        length.click();
        length.sendKeys(String.valueOf(homeValue));
    }

    public void setAnnualInsurance(double insurance){
        this.annualInsurance.clear();
        this.annualInsurance.click();
        this.annualInsurance.sendKeys(String.valueOf(insurance));
    }

    public void setAnnualTaxes(double taxes){
        this.annualTaxes.clear();
        this.annualTaxes.click();
        this.annualTaxes.sendKeys(String.valueOf(taxes));
    }

    public void setAnnualPMI(double PMI){
        this.annualPMI.clear();
        this.annualPMI.click();
        this.annualPMI.sendKeys(String.valueOf(PMI));
    }

    public void validateCalculationResult(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"analysisDiv\"]/table/thead/tr[1]/td")));

        WebElement monthlyPrincipalAndInterests;
        WebElement loanToValueRatio;
        WebElement totalMonthlyPayments;

        monthlyPrincipalAndInterests = driver.findElement(By.xpath("//*[@id=\"analysisDiv\"]/table/thead/tr[1]/td"));
        assertTrue(monthlyPrincipalAndInterests.isDisplayed(), "Monthly principal & interests is not displayed!");
        assertEquals(monthlyPrincipalAndInterests.getText(),"$1,073.64","Monthly principal & interests has been incorrectly calculated!");

//        loanToValueRatio = driver.findElement(By.xpath("//*[@id=\"analysisDiv\"]/table/thead/tr[4]/td"));
//        assertTrue(loanToValueRatio.isDisplayed(),"Loan To Value Ratio is not displayed!");
//        assertEquals(loanToValueRatio.getText(), "85.11%","Loan To Value Ratio has been incorrectly calculated!");

        totalMonthlyPayments = driver.findElement(By.xpath("//*[@id=\"analysisDiv\"]/table/thead/tr[7]/td"));
        assertTrue(totalMonthlyPayments.isDisplayed(),"Total monthly payments is not displayed!");
        assertEquals(totalMonthlyPayments.getText(), "$1,482.39","Total monthly payments has been incorrectly calculated!");
    }
}
