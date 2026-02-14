package proyecto.testng;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoQaPracticeFormTest {

    public static String URL = "https://demoqa.com/automation-practice-form";

    WebDriver driver;
    WebDriverWait wait;

    By firstNameInput = By.id("firstName");
    By lastNameInput = By.id("lastName");
    By emailInput = By.id("userEmail");
    By genderMaleLabel = By.cssSelector("label[for='gender-radio-1']");
    By mobileInput = By.id("userNumber");
    By dateOfBirthInput = By.id("dateOfBirthInput");
    By subjectsInput = By.id("subjectsInput");
    By hobbySportsLabel = By.cssSelector("label[for='hobbies-checkbox-1']");
    By uploadPictureInput = By.id("uploadPicture");
    By currentAddressInput = By.id("currentAddress");
    By stateInput = By.id("react-select-3-input");
    By cityInput = By.id("react-select-4-input");
    By submitButton = By.id("submit");
    By modalTitle = By.id("example-modal-sizes-title-lg");
    By studentNameValue = By.xpath("//td[text()='Student Name']/following-sibling::td");
    By studentEmailValue = By.xpath("//td[text()='Student Email']/following-sibling::td");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage()
            .window()
            .maximize();
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void submitPracticeFormTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput))
            .sendKeys("Elmer");
        driver.findElement(lastNameInput)
            .sendKeys("Coronel");
        driver.findElement(emailInput)
            .sendKeys("elmer@test.com");
        driver.findElement(genderMaleLabel)
            .click();
        driver.findElement(mobileInput)
            .sendKeys("7777777777");

        WebElement dateInput = driver.findElement(dateOfBirthInput);
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys("15 Aug 1998");
        dateInput.sendKeys(Keys.ENTER);

        driver.findElement(subjectsInput)
            .sendKeys("Maths");
        driver.findElement(subjectsInput)
            .sendKeys(Keys.ENTER);

        driver.findElement(hobbySportsLabel)
            .click();

        Path filePath = Paths.get("src", "test", "resources", "upload-demo.txt")
            .toAbsolutePath();
        driver.findElement(uploadPictureInput)
            .sendKeys(filePath.toString());

        driver.findElement(currentAddressInput)
            .sendKeys("Av. Siempre Viva 123");

        driver.findElement(stateInput)
            .sendKeys("NCR");
        driver.findElement(stateInput)
            .sendKeys(Keys.ENTER);

        driver.findElement(cityInput)
            .sendKeys("Delhi");
        driver.findElement(cityInput)
            .sendKeys(Keys.ENTER);

        WebElement submit = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
        Assert.assertEquals(title.getText(), "Thanks for submitting the form");
        Assert.assertEquals(
            driver.findElement(studentNameValue)
                .getText(), "Elmer Coronel"
        );
        Assert.assertEquals(
            driver.findElement(studentEmailValue)
                .getText(), "elmer@test.com"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
