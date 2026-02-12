package proyecto.testng;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Proyecto: SeleniumTest Nombre del archivo: WebElementsActions Descripción: [Añade una breve
 * descripción aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 11/02/2026
 */
public class WebElementsActionsTest {

    public static String URL = "https://rahulshettyacademy.com/AutomationPractice/";

    WebDriver driver;
    WebDriverWait wait;

    By radioButton = By.cssSelector("[value='radio2']");
    By autoSuggest = By.id("autocomplete");
    By suggestResult = By.xpath("//ul[@id='ui-id-1']/li");
    By suggestListResult = By.id("ui-id-1");
    By dropDown = By.id("dropdown-class-example");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage()
            .window()
            .maximize();
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void radioTest() {
        WebElement radioButton2 = driver.findElement(radioButton);
        wait.until(ExpectedConditions.visibilityOf(radioButton2));
        radioButton2.click();
        Assert.assertTrue(radioButton2.isSelected(), "El radio button se ha seleccionado");
    }

    @Test
    public void autoCompleteTest() {
        WebElement autoComplete = driver.findElement(autoSuggest);
        autoComplete.sendKeys("Uni");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(suggestListResult)));

        List<WebElement> suggestedList = driver.findElements(suggestResult);

        for (WebElement option : suggestedList) {
            if (option.getText()
                .equals("Tunisia"))
            {
                option.click();
                break;
            }
        }
    }

    @Test
    public void dropDownTest() {
        WebElement dropdown = driver.findElement(dropDown);

        Select selectDropdown = new Select(dropdown);
        selectDropdown.selectByVisibleText("Option3");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
