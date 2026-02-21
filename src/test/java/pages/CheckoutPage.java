package pages;

import base.BasePage;
import model.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By checkoutTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public String getTitle() {
        return getText(checkoutTitle);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        WebElement firstNameElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        WebElement postalCodeElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(postalCodeInput));

        setReactInputValue(firstNameElement, firstName);
        setReactInputValue(lastNameElement, lastName);
        setReactInputValue(postalCodeElement, postalCode);
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

    public void fillInformationAndContinue(String firstName, String lastName, String postalCode) {
        fillCheckoutInformation(firstName, lastName, postalCode);

        WebElement firstNameElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        WebElement postalCodeElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(postalCodeInput));

        if (!firstName.equals(firstNameElement.getAttribute("value"))) {
            setReactInputValue(firstNameElement, firstName);
        }
        if (!lastName.equals(lastNameElement.getAttribute("value"))) {
            setReactInputValue(lastNameElement, lastName);
        }
        if (!postalCode.equals(postalCodeElement.getAttribute("value"))) {
            setReactInputValue(postalCodeElement, postalCode);
        }

        clickContinue();
        if (driver.getCurrentUrl()
            .contains("checkout-step-one"))
        {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(continueButton))
            );
        }
        wait.until(checkoutStepTwoOrError());
        if (isCheckoutErrorVisible()) {
            throw new IllegalStateException(
                "Checkout did not continue. Error: " + getCheckoutErrorMessage());
        }
        waitForCheckoutOverviewPage();
    }

    private ExpectedCondition<Boolean> checkoutStepTwoOrError() {
        return driver -> driver.getCurrentUrl()
            .contains("checkout-step-two")
            || isCheckoutErrorVisible();
    }

    private boolean isCheckoutErrorVisible() {
        return !driver.findElements(errorMessage)
            .isEmpty();
    }

    private String getCheckoutErrorMessage() {
        try {
            return driver.findElement(errorMessage)
                .getText();
        } catch (NoSuchElementException e) {
            return "No error message visible";
        }
    }

    private void setReactInputValue(WebElement element, String value) {
        ((JavascriptExecutor) driver).executeScript(
            "const input = arguments[0];"
                + "const val = arguments[1];"
                + "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
                + "setter.call(input, val);"
                + "input.dispatchEvent(new Event('input', { bubbles: true }));",
            element, value
        );
    }
}
