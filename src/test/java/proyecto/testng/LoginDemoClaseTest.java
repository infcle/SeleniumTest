package proyecto.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
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
 * Proyecto: SeleniumTest Nombre del archivo: LoginDemoClaseTest Descripción: [Añade una breve
 * descripción aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 06/02/2026
 */
public class LoginDemoClaseTest {

    @Test(groups = {"regression"})
    @Tag("regression")
    @Owner("Pedro Suarez")
    @Description("Registro Exitos")
    public void primeraPruebaSelenium() {

        WebDriverManager.chromedriver()
            .setup();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)); //Declarar la clase Wait

        Allure.step("Ir a la pagina https://www.demoblaze.com/");
        driver.get("https://www.demoblaze.com/");

        driver.manage()
            .window()
            .maximize();

        WebElement btnsignUp = driver.findElement(By.id("signin2"));
        WebElement signUpUserName = driver.findElement(By.id("sign-username"));
        WebElement signUpPass = driver.findElement(By.id("sign-password"));
        WebElement signBtnsignUp = driver.findElement(By.xpath("//button[text()='Sign up']"));

        btnsignUp.click();
        wait.until(ExpectedConditions.visibilityOf(signUpUserName));
        signUpUserName.sendKeys("ramurrio0009");
        signUpPass.sendKeys("ramurrio");
        signBtnsignUp.click();

        wait.until(ExpectedConditions.alertIsPresent());  //Espera a un alert

        Alert alert = driver.switchTo()
            .alert();          // cambia el driver a la alerta
        String message = alert.getText();                 // copiamos el mensaje de la alerta
        alert.accept();                                   // Aceptamos la alerta

        Allure.step(
            "Validar mensaje de registro", () -> Assert.assertEquals(
                message, "Sign up successful.",
                "El mensaje no es el esperado"
            )
        );

        //Assert.assertEquals(message, "Sign up successful.", "La alerta no tiene el mensaje esperado de registro exitoso");
        //Assert.assertTrue(message=="Sign up successful.", "La alerta no tiene el mensaje esperado de registro exitoso");

        driver.close();

    }

    @Test(groups = {"regression"})
    @Tag("regression")
    @Owner("Rodrigo Amurrio")
    @Description("Login Exitos")
    public void test2() {
        Assert.assertTrue(true);
    }

    @Test
    public void test3() {
        Assert.assertFalse(false);
    }

}
