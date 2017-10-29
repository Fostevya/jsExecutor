package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class GoogleStartPage {

    @FindBy (id="lst-ib")
    private WebElement queryString;

    private Wait<WebDriver> wait;
    private WebDriver driver;

    public GoogleStartPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void writeInQueryString(String findString, WebDriver webDriver){
        queryString.sendKeys(findString);
    }

    public void findInGoogle(WebDriver webDriver){
        queryString.submit();
    }

    public Long getQueryStringWidth(WebDriver webDriver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return (Long) jsExecutor.executeScript("return document.getElementById('lst-ib').offsetWidth;");
    }

    public boolean isScrollAvailable(WebDriver webDriver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return Boolean.valueOf(jsExecutor.
                executeScript("return (document.body.clientHeight != document.documentElement.clientHeight)").
                toString());
    }
}
