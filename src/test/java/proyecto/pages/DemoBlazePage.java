package proyecto.pages;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoBlazePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://www.demoblaze.com/index.html";

    private final By signUpOpenButton = By.id("signin2");
    private final By loginOpenButton = By.id("login2");
    private final By signUpModal = By.id("signInModal");
    private final By loginModal = By.id("logInModal");

    private final By signUpUsername = By.id("sign-username");
    private final By signUpPassword = By.id("sign-password");
    private final By signUpSubmit = By.xpath(".//button[contains(text(),'Sign up')]");
    private final By signUpClose = By.xpath(
        "//div[@id='signInModal']//button[@data-dismiss='modal' and normalize-space()='Close']"
    );

    private final By loginUsername = By.id("loginusername");
    private final By loginPassword = By.id("loginpassword");
    private final By loginSubmit = By.xpath("//button[contains(text(),'Log in')]");

    private final By welcomeUser = By.id("nameofuser");
    private final By logoutLink = By.id("logout2");

    public DemoBlazePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(URL);
        driver.manage()
            .window()
            .maximize();
    }

    public void openSignUp() {
        driver.findElement(signUpOpenButton)
            .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
    }

    public String signUp(String username, String password) {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
        modal.findElement(signUpUsername)
            .sendKeys(username);
        modal.findElement(signUpPassword)
            .sendKeys(password);
        modal.findElement(signUpSubmit)
            .click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public void closeSignUpModal() {
        driver.findElement(signUpClose)
            .click();
    }

    public void openLogin() {
        driver.findElement(loginOpenButton)
            .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginModal));
    }

    public void login(String username, String password) {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(loginModal));
        modal.findElement(loginUsername)
            .sendKeys(username);
        modal.findElement(loginPassword)
            .sendKeys(password);
        modal.findElement(loginSubmit)
            .click();
    }

    public String getWelcomeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser))
            .getText();
    }

    public boolean isLogoutVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink))
            .isDisplayed();
    }
}
