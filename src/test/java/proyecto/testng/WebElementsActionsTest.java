package proyecto.testng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
    By checkBoxOption2 = By.id("checkBoxOption2");
    By inputName = By.id("inputName");
    By alertButton = By.id("alertbtn");
    By mouseHoverButton = By.id("mousehover");
    By mouseHoverTopOption = By.xpath("//div[@class='mouse-hover-content']/a[text()='Top']");
    By hideTextBoxButton = By.id("hide-textbox");
    By showTextBoxButton = By.id("show-textbox");
    By displayedTextBox = By.id("displayed-text");
    By openWindowButton = By.id("openwindow");
    By openTabButton = By.id("opentab");

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
    public void dropDownTest() throws InterruptedException {
        WebElement dropdown = driver.findElement(dropDown);

        Select selectDropdown = new Select(dropdown);
        selectDropdown.selectByVisibleText("Option3");
        Thread.sleep(3000);
        selectDropdown.deselectByValue("Option1");
        Thread.sleep(3000);
    }

    @Test
    public void checkBoxTest() throws InterruptedException {
        WebElement checkBox = driver.findElement(checkBoxOption2);
        checkBox.click();
        Assert.assertTrue(checkBox.isSelected(), "El checkbox se ha seleccionado");
        Thread.sleep(3000);
        Assert.assertFalse(checkBox.isSelected(), "El checkbox se ha deseleccionado");
        Thread.sleep(3000);
    }

    @Test
    public void alertTest() {
        WebElement input = driver.findElement(inputName);
        WebElement alertNameButton = driver.findElement(alertButton);

        input.sendKeys("Elmer");
        alertNameButton.click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo()
            .alert();
        Assert.assertTrue(
            alert.getText()
                .contains("Elmer"), "El alert se ha seleccionado"
        );
        alert.accept();

    }

    @Test
    public void mouseOverTest() {
        WebElement hoverButton = driver.findElement(mouseHoverButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton)
            .perform();

        WebElement topOption = wait.until(
            ExpectedConditions.visibilityOfElementLocated(mouseHoverTopOption));

        Assert.assertTrue(topOption.isDisplayed(), "La opcion Top se muestra al hacer mouse over");
    }

    @Test
    public void mouseOver2Test() {
        WebElement hoverButton = driver.findElement(mouseHoverButton);
        WebElement topOption = driver.findElement(mouseHoverTopOption);
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton)
        ;
        actions.moveToElement(hoverButton);
        actions.moveToElement(topOption);
        actions.click()
            .build()
            .perform();

        wait.until(ExpectedConditions.urlContains("#top"));
        Assert.assertTrue(
            driver.getCurrentUrl()
                .contains("#top"), "The url doesn't change"
        );

    }

    @Test
    public void displayedPageTest() {
        WebElement displayedText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(displayedTextBox));
        Assert.assertTrue(
            displayedText.isDisplayed(), "El textbox debe mostrarse al cargar la pagina");

        driver.findElement(hideTextBoxButton)
            .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(displayedTextBox));
        Assert.assertFalse(
            driver.findElement(displayedTextBox)
                .isDisplayed(), "El textbox debe ocultarse"
        );

        driver.findElement(showTextBoxButton)
            .click();
        WebElement displayedTextAgain = wait.until(
            ExpectedConditions.visibilityOfElementLocated(displayedTextBox));
        Assert.assertTrue(
            displayedTextAgain.isDisplayed(), "El textbox debe mostrarse al hacer click en Show");
    }

    @Test
    public void windowHandlingTest() {
        String mainWindow = driver.getWindowHandle();
        String mainUrl = driver.getCurrentUrl();

        driver.findElement(openWindowButton)
            .click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo()
                    .window(window);
                break;
            }
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(mainUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), mainUrl, "Debe cambiar a una nueva ventana");

        driver.close();
        driver.switchTo()
            .window(mainWindow);

        Assert.assertEquals(driver.getCurrentUrl(), mainUrl, "Debe volver a la ventana principal");
    }

    @Test
    public void newTabHandlingTest() {
        String mainTab = driver.getWindowHandle();
        String mainUrl = driver.getCurrentUrl();

        driver.findElement(openTabButton)
            .click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo()
                    .window(tab);
                break;
            }
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(mainUrl)));
        Assert.assertNotEquals(
            driver.getCurrentUrl(), mainUrl, "Debe abrirse un nuevo tab con URL distinta");

        driver.close();
        driver.switchTo()
            .window(mainTab);
        Assert.assertEquals(driver.getCurrentUrl(), mainUrl, "Debe regresar al tab principal");
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
