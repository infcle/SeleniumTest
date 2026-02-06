package proyecto.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PruebaSeleniumTest {

    @Test
    void abrirPagina() {

        WebDriverManager.chromedriver()
            .setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
        driver.quit();

    }
}
