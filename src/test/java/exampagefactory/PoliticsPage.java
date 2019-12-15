package exampagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PoliticsPage {
    WebDriver driver;

    public PoliticsPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='update-feed']/following-sibling::h2")
    private WebElement politicsField;


    public boolean checkPoliticsPageIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[@id='update-feed']/following-sibling::h2")));
        return  politicsField.isDisplayed();
    }





}
