package base;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Proyecto: SeleniumTest Nombre del archivo: BaseTest Descripción: [Añade una breve descripción
 * aquí]
 *
 * @author Elmer Coronel (Elmer)
 * @version 1.0
 * @since 18/02/2026
 */
@Listeners(AllureTestNg.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        DriverManager.initDriver(browser);
        DriverManager.getDriver()
            .get("http://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        /*if (result.getStatus() == ITestResult.FAILURE) {
            attachScreenshot();
        }*/

        DriverManager.quitDriver();
    }

    /*@Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }*/
}
