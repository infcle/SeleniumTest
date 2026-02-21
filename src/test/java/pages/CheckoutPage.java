package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By checkoutTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public String getTitle() {
        return getText(checkoutTitle);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterText(firstNameInput, firstName);
        enterText(lastNameInput, lastName);
        enterText(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public void waitForCheckoutInformationPage() {
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }

    public void waitForCheckoutOverviewPage() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }
}
