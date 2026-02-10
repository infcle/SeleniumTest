package proyecto.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    @Test
    public void loginAndValidateAuthentication() {
        WebDriverManager.chromedriver()
            .setup();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get(URL);
            driver.manage()
                .window()
                .maximize();

            ensureUserExists(driver, wait);
            login(driver, wait);

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
        } finally {
            driver.quit();
        }
    }

    private void ensureUserExists(WebDriver driver, WebDriverWait wait) {
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

        Assert.assertTrue(
            "Sign up successful.".equals(message) ||
                "This user already exist.".equals(message),
            "Mensaje inesperado en la alerta: " + message
        );

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));
    }

    private void login(WebDriver driver, WebDriverWait wait) {
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
    }
}
