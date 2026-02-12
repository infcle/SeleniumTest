package proyecto.testng;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import proyecto.pages.DemoBlazePage;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginBlaze Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 10/02/2026
 */
public class LoginBlazeAuthTest {

    private static final String USERNAME = "usuario_test_ecl";
    private static final String PASSWORD = "123456";

    WebDriver driver;
    DemoBlazePage demoBlaze;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        demoBlaze = new DemoBlazePage(driver);
        demoBlaze.open();
    }

    @Description("Validates sign up and login authentication flow in DemoBlaze.")
    @Test
    public void loginAndValidateAuthentication() {

        demoBlaze.openSignUp();
        String message = demoBlaze.signUp(USERNAME, PASSWORD);
        if (message.equals("This user already exist.")) {
            demoBlaze.closeSignUpModal();
        }
        demoBlaze.openLogin();
        demoBlaze.login(USERNAME, PASSWORD);

        Assert.assertTrue(
            demoBlaze.getWelcomeText()
                .contains(USERNAME),
            "El usuario autenticado no coincide con el esperado."
        );
        Assert.assertTrue(demoBlaze.isLogoutVisible(), "El enlace Logout no esta visible.");

    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

}
