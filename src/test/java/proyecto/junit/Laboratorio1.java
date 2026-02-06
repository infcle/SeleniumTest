package proyecto.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Laboratorio1 {

    @Test
    public void lab1_E2() {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);

        driver.get(" https://www.toolsqa.com/");

        driver.manage().window().maximize();
        driver.quit();
    }

}
