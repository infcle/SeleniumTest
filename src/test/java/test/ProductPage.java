package test;

import base.BasePage;
import io.qameta.allure.Step;
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

    @Step("Obtener titulo de Products")
    public String getTitle() {
        return getText(productTitle);
    }
}
