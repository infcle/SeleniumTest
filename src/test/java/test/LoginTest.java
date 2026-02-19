package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginTest Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
public class LoginTest extends BaseTest {

    @Test
    public void loginSuccessTest() {
        LoginPage loginPage = new LoginPage();
        ProductPage productPage = new ProductPage();

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getTitle(), "Products", "Title is not equal to Products");
    }

}
