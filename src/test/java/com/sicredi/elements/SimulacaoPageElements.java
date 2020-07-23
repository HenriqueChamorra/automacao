package com.sicredi.elements;

import com.sicredi.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimulacaoPageElements extends SeleniumUtils {

    public static WebDriver driver;

    @FindBy(css = "input[type='radio'][value='paraVoce']")
    protected WebElement PERFIL_PF;

    @FindBy(css = "input[type='radio'][value='paraEmpresa']")
    protected WebElement PERFIL_PJ;

    @FindBy(id = "valorAplicar")
    protected WebElement VALOR_APLICAR;

    @FindBy(id = "valorInvestir")
    protected WebElement VALOR_INVESTIR;

    @FindBy(id = "tempo")
    protected WebElement TEMPO;

    @FindBy(css = "[class='btn btnLimpar']")
    protected WebElement LIMPAR;

    @FindBy(css = "[class='btn btnAmarelo btnSimular']")
    protected WebElement SIMULAR;

    @FindBy(css = "[class='seta']")
    protected WebElement SETA;

    @FindBy(css = "[rel='A']")
    protected WebElement ANOS;

    @FindBy(css = "[rel='M']")
    protected WebElement MESES;

    @FindBy(id = "valorAplicar-error")
    protected WebElement VALOR_APLICAR_ERRO;

    @FindBy(id = "valorInvestir-error")
    protected WebElement VALOR_INVESTIR_ERRO;

    @FindBy(id = "tempo-error")
    protected WebElement VALOR_TEMPO_ERRO;
}
