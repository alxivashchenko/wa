package exampagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//li[@data-tab='currency']")
    private WebElement currencyTab;
    @FindBy(xpath = "//td[text()='USD']/following-sibling::td[@class='nbu']")
    private WebElement rateUsdNbu;
    @FindBy(xpath = "//*[@class='feed__section--title']//a[contains(@href, 'politika')]")
    private WebElement politicsSection;
    @FindBy(xpath = "//span[@class='act-lang']")
    private WebElement langField;
    @FindBy(xpath = "//a[contains(text(), 'на Русском')]")
    private WebElement ruLangField;
    @FindBy(xpath = "//a[contains(text(), 'Главное')]")
    private WebElement mainSection;



    public String checkShiftLang() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
       langField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'на Русском')]")));
       ruLangField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'Главное')]")));
        return mainSection.getText();
    }

    public float checkRateUsd() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        currencyTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//td[text()='USD']/following-sibling::td[@class='nbu']")));
        float usdRate = Float.parseFloat(rateUsdNbu.getText());
        return usdRate;
    }

    public PoliticsPage clickPoliticsPage() {
      politicsSection.click();
        return new PoliticsPage(driver);
    }
}
