package exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By currencyTab = By.xpath("//li[@data-tab='currency']");
    private By rateUsdNbu = By.xpath("//td[text()='USD']/following-sibling::td[@class='nbu']");
    private By politicsSection = By.xpath("//*[@class='feed__section--title']//a[contains(@href, 'politika')]");
    //private By politicsField = By.xpath("//div[@id='update-feed']/following-sibling::h2");
/*
    @FindBy(xpath = "//td[text()='USD']/following-sibling::td[@class='nbu']")
    private WebElement rateUsdNbu;

    @FindBy(xpath = "//*[@class='feed__section--title']//a[contains(@href, 'politika')]")
    private WebElement politicsSection;

    @FindBy(xpath = "//div[@id='update-feed']/following-sibling::h2") //
    private WebElement politicsField;*/

    private By langField = By.xpath("//span[@class='act-lang']");
    private By ruLangField = By.xpath("//a[contains(text(), 'на Русском')]");
    private By mainSection = By.xpath("//a[contains(text(), 'Главное')]");


    public String checkShiftLang() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(langField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'на Русском')]")));
        driver.findElement(ruLangField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'Главное')]")));
        return driver.findElement(mainSection).getText();
    }

    public float checkRateUsd() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(currencyTab).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//td[text()='USD']/following-sibling::td[@class='nbu']")));
        float usdRate = Float.parseFloat(driver.findElement(rateUsdNbu).getText());
        return usdRate;
    }

    public PoliticsPage clickPoliticsPage() {
        driver.findElement(politicsSection).click();
        return new PoliticsPage(driver);
    }
}
