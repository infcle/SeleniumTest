package test;

import base.BaseTest;
import model.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

/**
 * Proyecto: SeleniumTest Nombre del archivo: LoginTest Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
public class LoginTest extends BaseTest {

    final String productName = "Sauce Labs Onesie";


    @Test
    public void loginSuccessTest() {
        LoginPage loginPage = new LoginPage();
        ProductPage productPage = new ProductPage();

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getTitle(), "Products", "Title is not equal to Products");
    }

    @Test
    public void e2eTest() {
        LoginPage loginPage = new LoginPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getTitle(), "Products", "Title is not equal to Products");

        ProductData expectedProduct = productPage.getProductDataByName(productName);
        productPage.clickOnAddToCartButtonSelected(productName);

        Assert.assertEquals(
            productPage.getTextButtonSelected(productName), "Remove",
            "Title is not equal to Remove"
        );
        Assert.assertTrue(
            productPage.getCartBadgeCount() > 0,
            "Cart badge should be greater than zero"
        );
        productPage.clickButtonCart();

        Assert.assertEquals(cartPage.getTitle(), "Your Cart", "Title is not equal to Cart");
        ProductData productInCart = cartPage.getProductDataByName(productName);
        Assert.assertEquals(
            productInCart, expectedProduct, "Product data in cart does not match inventory");

        cartPage.clickCheckout();
        checkoutPage.waitForCheckoutInformationPage();
        Assert.assertEquals(
            checkoutPage.getTitle(),
            "Checkout: Your Information",
            "Title is not equal to Checkout: Your Information"
        );
        checkoutPage.fillInformationAndContinue("Elmer", "Coronel", "10101");
        Assert.assertEquals(
            checkoutPage.getTitle(),
            "Checkout: Overview",
            "Title is not equal to Checkout: Overview"
        );
        ProductData productInCheckoutOverview = checkoutPage.getProductDataByName(productName);
        Assert.assertEquals(
            productInCheckoutOverview,
            expectedProduct,
            "Product data in checkout overview does not match inventory"
        );

    }


}
