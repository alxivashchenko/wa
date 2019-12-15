package exampagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class ExamTestPageObject {
    private WebDriver driver;
    SoftAssert asert;
    private MainPage mainPage;

    @BeforeMethod
    public void startTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.ukr.net/");
        //mainPage = new MainPage(driver);
        mainPage = PageFactory.initElements(driver, MainPage.class);

    }

    @AfterMethod
    public void finishTest() {
        driver.quit();
    }

    /*@Test
    public void signInTest(){
        LoginPage loginPage = mainPage.clickSignIn();
        String heading = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub", heading);
    }*/

    @Test(groups = "positive", description = "Checks USD rate NBU")
    public void testUsdCurrencyPresence() {
        asert = new SoftAssert();
        float usdRate = mainPage.checkRateUsd();
        asert.assertNotEquals(0, usdRate, "USD should be more than 0, but = " + usdRate);
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks that politics page is opened")
    public void politicsPageIsOpened() {
        asert = new SoftAssert();
        PoliticsPage politicsPage = mainPage.clickPoliticsPage();
        asert.assertTrue(politicsPage.checkPoliticsPageIsDisplayed(),
                "Politics page is not displayed");
        asert.assertAll();
    }

    @Test(groups = "positive", description = "Checks shift from UA to RU")
    public void shiftFromUaToRu() {
        asert = new SoftAssert();
        String keyWord = mainPage.checkShiftLang();
        asert.assertEquals(keyWord, "Главное", "Doesn't shift language");
        asert.assertAll();
    }

}
