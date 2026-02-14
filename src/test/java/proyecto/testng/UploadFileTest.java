package proyecto.testng;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadFileTest {

    public static String URL = "https://the-internet.herokuapp.com/upload";

    WebDriver driver;
    WebDriverWait wait;

    By uploadInput = By.id("file-upload");
    By uploadButton = By.id("file-submit");
    By uploadedTitle = By.tagName("h3");
    By uploadedFileName = By.id("uploaded-files");

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
    public void uploadFileTest() {
        Path filePath = Paths.get("src", "test", "resources", "upload-demo.txt")
            .toAbsolutePath();

        WebElement upload = driver.findElement(uploadInput);
        upload.sendKeys(filePath.toString());

        driver.findElement(uploadButton)
            .click();

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedTitle));
        Assert.assertEquals(
            title.getText(), "File Uploaded!", "El mensaje de carga no es el esperado");
        WebElement fileName = driver.findElement(uploadedFileName);
        Assert.assertEquals(
            fileName.getText(), "upload-demo.txt", "El nombre del archivo subido no coincide");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
