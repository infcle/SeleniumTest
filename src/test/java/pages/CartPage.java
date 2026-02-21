package pages;

import base.BasePage;
import model.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Proyecto: SeleniumTest Nombre del archivo: CardPage Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 20/02/2026
 */
public class CartPage extends BasePage {

    private final By cartTitle = By.className("title");
    private final By checkoutButton = By.id("checkout");

    public String getTitle() {
        return getText(cartTitle);
    }

    public ProductData getProductDataByName(String productName) {
        String containerXpath = "//div[@data-test='inventory-item-name' and normalize-space()="
            + xpathLiteral(productName)
            + "]/ancestor::div[contains(@class,'cart_item')]";

        String name = getText(By.xpath(containerXpath + "//div[@data-test='inventory-item-name']"));
        String description = getText(
            By.xpath(containerXpath + "//div[@data-test='inventory-item-desc']"));
        String price = getText(
            By.xpath(containerXpath + "//div[@data-test='inventory-item-price']"));

        return new ProductData(name, description, price);
    }

    public void clickCheckout() {
        WebElement checkoutBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }
}
