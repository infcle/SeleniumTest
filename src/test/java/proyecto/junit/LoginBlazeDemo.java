package proyecto.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginBlaze Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 06/02/2026
 */
public class LoginBlazeDemo {

    private String url = "https://www.demoblaze.com/index.html";

    @Test
    public void loginTest() throws InterruptedException {
        WebDriverManager.chromedriver()
            .setup();

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        driver.manage()
            .window()
            .maximize();
        WebElement linkSingUp = driver.findElement(By.id("signin2"));

        linkSingUp.click();

        WebElement modalSingUp = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("signInModal"))
        );

        WebElement username = modalSingUp.findElement(By.id("sign-username"));
        WebElement password = modalSingUp.findElement(By.id("sign-password"));
        WebElement signUpButton = modalSingUp.findElement(
            By.xpath(".//button[contains(text(),'Sign up')]")
        );

        username.sendKeys("usuario_test");
        password.sendKeys("123456");
        signUpButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(1000);
        alert.accept();

        Thread.sleep(1000);
        driver.close();
    }

}
