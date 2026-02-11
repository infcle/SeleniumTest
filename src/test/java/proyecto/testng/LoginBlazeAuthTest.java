package proyecto.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginBlaze Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 10/02/2026
 */
public class LoginBlazeAuthTest {

    private static final String URL = "https://www.demoblaze.com/index.html";
    private static final String USERNAME = "usuario_test_ecl";
    private static final String PASSWORD = "123456";

    @Description("Validates sign up and login authentication flow in DemoBlaze.")
    @Test
    public void loginAndValidateAuthentication() {
        WebDriverManager.chromedriver()
            .setup();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(URL);
        driver.manage()
            .window()
            .maximize();

        driver.findElement(By.id("signin2"))
            .click();
        WebElement modalSignUp = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("signInModal"))
        );
        modalSignUp.findElement(By.id("sign-username"))
            .sendKeys(USERNAME);
        modalSignUp.findElement(By.id("sign-password"))
            .sendKeys(PASSWORD);
        modalSignUp.findElement(By.xpath(".//button[contains(text(),'Sign up')]"))
            .click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        alert.accept();
        if (message.equals("This user already exist.")) {
            modalSignUp.findElement(By.xpath(
                    "//div[@id='signInModal']//button[@data-dismiss='modal' and normalize-space()='Close']"))
                .click();
        }
        driver.findElement(By.id("login2"))
            .click();
        WebElement modalLogin = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("logInModal"))
        );
        modalLogin.findElement(By.id("loginusername"))
            .sendKeys(USERNAME);
        modalLogin.findElement(By.id("loginpassword"))
            .sendKeys(PASSWORD);
        modalLogin.findElement(By.xpath(".//button[contains(text(),'Log in')]"))
            .click();

        WebElement welcomeUser = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))
        );
        WebElement logoutLink = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("logout2"))
        );

        Assert.assertTrue(
            welcomeUser.getText()
                .contains(USERNAME),
            "El usuario autenticado no coincide con el esperado."
        );
        Assert.assertTrue(logoutLink.isDisplayed(), "El enlace Logout no esta visible.");

        driver.close();
    }

}
