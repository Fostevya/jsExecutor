package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class GoogleResultSearchPage {

    @FindBy(id="lst-ib")
    private WebElement queryString;

    @FindBy(id="hdtb-msb")
    private WebElement navigatePanel;

    private Wait<WebDriver> wait;
    private WebDriver driver;

    public GoogleResultSearchPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public boolean isScrollAvailable(WebDriver webDriver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return Boolean.valueOf(jsExecutor.
                executeScript("return (document.body.clientHeight != document.documentElement.clientHeight)").
                toString());
    }

}
