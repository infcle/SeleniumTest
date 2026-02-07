package proyecto.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


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
