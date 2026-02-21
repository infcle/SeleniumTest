package pages;

import base.BasePage;
import io.qameta.allure.Step;
import model.ProductData;
import org.openqa.selenium.By;

/**
 * Proyecto: SeleniumTest Nombre del archivo: ProductPage Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
public class ProductPage extends BasePage {

    // Locator
    private final By productTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");

    @Step("Obtener titulo de Products")
    public String getTitle() {
        return getText(productTitle);
    }

    public void clickOnAddToCartButtonSelected(String productName) {
        By item = By.id(buildAddToCartId(productName));
        clickElement(item);
    }

    public String getTextButtonSelected(String productName) {
        By productButton = By.xpath("//div[@data-test='inventory-item-name' and normalize-space()="
            + xpathLiteral(productName)
            + "]/ancestor::div[@data-test='inventory-item']//button");
        return getText(productButton);
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(getText(cartBadge));
    }

    public ProductData getProductDataByName(String productName) {
        String containerXpath = "//div[@data-test='inventory-item-name' and normalize-space()="
            + xpathLiteral(productName)
            + "]/ancestor::div[@data-test='inventory-item']";

        String name = getText(By.xpath(containerXpath + "//div[@data-test='inventory-item-name']"));
        String description = getText(
            By.xpath(containerXpath + "//div[@data-test='inventory-item-desc']"));
        String price = getText(
            By.xpath(containerXpath + "//div[@data-test='inventory-item-price']"));

        return new ProductData(name, description, price);
    }

    private String buildAddToCartId(String productName) {
        return "add-to-cart-" + productName.toLowerCase()
            .replaceAll(" ", "-");
    }

    public void clickButtonCart() {
        clickElement(cartBadge);
    }
}
