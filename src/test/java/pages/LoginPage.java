package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginPage Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
public class LoginPage extends BasePage {

    //Locator
    private final By userNameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    //Actions Web Locators
    @Step("Ingresar usuario: {userName}")
    public void enterUserName(String userName) {
        enterText(userNameInput, userName);
    }

    @Step("Ingresar password")
    public void enterPassword(String password) {
        enterText(passwordInput, password);
    }

    @Step("Click en Login")
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    @Step("Autenticarse con usuario: {username}")
    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }
}
