package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Proyecto: SeleniumTest Nombre del archivo: DriverManager Descripción: [Añade una breve
 * descripción aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver()
                    .setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver()
                    .setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver()
                    .setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser no soportado");
        }

        driver.manage()
            .window()
            .maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
