package test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.GoogleResultSearchPage;
import page.GoogleStartPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    private WebDriver driver;
    private DesiredCapabilities capabilities;
    private String url = "http://google.com";
    private String stringForSearch = "bmw e34";
    private GoogleStartPage googleSP;
    private Wait<WebDriver> wait;
    private GoogleResultSearchPage googleRSP;

    @BeforeTest
    public void beforeTest(){
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.get(url);
    }

    @Test
    public void jsTest(){
        googleSP = new GoogleStartPage(driver);
        System.out.println("Ширина поля ввода поискового запроса: " + googleSP.getQueryStringWidth(driver));
        System.out.println("Наличие скролла на начальной странице: " + googleSP.isScrollAvailable(driver));
        googleSP.writeInQueryString(stringForSearch, driver);
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur()");
        googleSP.findInGoogle(driver);
        googleRSP = new GoogleResultSearchPage(driver);
        System.out.println("Наличие скролла на странице результатов поиска: " + googleRSP.isScrollAvailable(driver));
    }

    @AfterTest
    public void tearDown(){ driver.quit(); }

}
