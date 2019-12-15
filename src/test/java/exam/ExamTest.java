package exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.concurrent.TimeUnit;

public class ExamTest {

    private WebDriver driver;
    SoftAssert asert;
    private MainPage mainPage;


    @BeforeMethod
    public void startTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.ukr.net/");
        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void finishTest() {
        driver.quit();
    }


    @Test(groups = "positive", description = "Checks USD rate NBU")
    public void testUsdCurrencyPresence() {
        asert = new SoftAssert();
         WebElement currencyTab = driver.findElement(By.
                 xpath("//li[@data-tab='currency']"));
        currencyTab.click();
        WebElement rateUsdNbu = driver.findElement(By.
                xpath("//td[text()='USD']/following-sibling::td[@class='nbu']"));
        float usdRate = Float.parseFloat(rateUsdNbu.getText());
        asert.assertNotEquals(0, usdRate, "USD should be more than 0, but = " + usdRate);
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks that politics page is opened")
    public void politicsPageIsOpened() {
        asert = new SoftAssert();
        WebElement politicsSection = driver.findElement(By.
                xpath("//*[@class='feed__section--title']//a[contains(@href, 'politika')]"));
        politicsSection.click();
        WebElement politicsField = driver.findElement(By.
                xpath("//div[@id='update-feed']/following-sibling::h2"));
        asert.assertTrue(politicsField.isDisplayed(), "Politics page is not displayed");
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks shift from UA to RU")
    public void shiftFromUaToRu() {
        asert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement langField = driver.findElement(By.
                xpath("//span[@class='act-lang']"));
        langField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'на Русском')]")));
        WebElement ruLangField = driver.findElement(By.
                xpath("//a[contains(text(), 'на Русском')]"));
        ruLangField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//a[contains(text(), 'Главное')]")));
        WebElement mainSection = driver.findElement(By.
                xpath("//a[contains(text(), 'Главное')]"));

        asert.assertEquals(mainSection.getText(), "Главное", "Doesn't shift language");
        asert.assertAll();
    }


}
