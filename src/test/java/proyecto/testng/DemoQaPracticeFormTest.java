package proyecto.testng;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
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

    public static String BASEURL = "https://demoqa.com";

    WebDriver driver;
    WebDriverWait wait;
    By formsCard = By.cssSelector("a[href='/forms']");
    By practiceForm = By.cssSelector("a[href='/automation-practice-form']");

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
        driver.get(BASEURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    @Test
    public void submitPracticeFormTest() {

        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(formsCard));
        card.click();

        WebElement formPractice = wait.until(ExpectedConditions.elementToBeClickable(practiceForm));
        formPractice.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement inputName = driver.findElement(firstNameInput);
        inputName.sendKeys("Elmer");

        WebElement inputLastName = driver.findElement(lastNameInput);
        inputLastName.sendKeys("Coronel");

        WebElement inputEmail = driver.findElement(emailInput);
        inputEmail.sendKeys("elmer@test.com");

        WebElement labelGenderMale = driver.findElement(genderMaleLabel);
        wait.until(ExpectedConditions.visibilityOf(labelGenderMale));
        labelGenderMale.click();

        WebElement inputMobile = driver.findElement(mobileInput);
        inputMobile.sendKeys("7777777777");

        WebElement dateInput = driver.findElement(dateOfBirthInput);
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys("15 Aug 1998");
        dateInput.sendKeys(Keys.ENTER);

        WebElement inputSubjets = driver.findElement(subjectsInput);
        inputSubjets.sendKeys("Maths");
        inputSubjets.sendKeys(Keys.ENTER);

        WebElement labelHobbySports = driver.findElement(hobbySportsLabel);
        labelHobbySports.click();

        Path filePath = Paths.get("src", "test", "resources", "upload-demo.txt")
            .toAbsolutePath();

        WebElement inputUploadPicture = driver.findElement(uploadPictureInput);
        inputUploadPicture.sendKeys(filePath.toString());

        WebElement inputCurrentAddress = driver.findElement(currentAddressInput);
        inputCurrentAddress.sendKeys("Av. Siempre Viva 123");

        WebElement inputState = driver.findElement(stateInput);
        inputState.sendKeys("NCR");
        inputState.sendKeys(Keys.ENTER);

        WebElement inputCity = driver.findElement(cityInput);
        inputCity.sendKeys("Delhi");
        inputCity.sendKeys(Keys.ENTER);

        WebElement submit = driver.findElement(submitButton);
        submit.click();

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
