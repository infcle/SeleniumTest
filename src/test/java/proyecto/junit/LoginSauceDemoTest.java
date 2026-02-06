package proyecto.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class LoginSauceDemoTest {

    @Test
    public void loginSauceDemo() throws InterruptedException {
        WebDriverManager.chromedriver()
            .setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage()
            .window()
            .maximize();
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        Thread.sleep(3000);
        username.clear();
        username.sendKeys("standard_user");
        Thread.sleep(3000);
        password.sendKeys("secret_sauce");
        Thread.sleep(3000);
        buttonLogin.click();

        WebElement titlePage = driver.findElement(By.xpath("//div[text()='Swag Labs']"));

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOf(titlePage));

        Thread.sleep(3000);
        driver.close();

    }
}
