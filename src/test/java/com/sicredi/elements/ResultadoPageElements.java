package com.sicredi.elements;
import com.sicredi.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultadoPageElements extends SeleniumUtils {

    public static WebDriver driver;

    @FindBy(css = "[class='texto']")
    protected WebElement TEXTO_RESULTADO_TEMPO;

    @FindBy(css = "[class='valor']")
    protected WebElement VALOR_RESULTADO;
}
