package com.sicredi.pages;

import com.sicredi.elements.SimulacaoPageElements;
import com.sicredi.setup.TestRule;
import com.sicredi.utils.ReportUtils;
import com.sicredi.utils.SeleniumUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SimulacaoPage extends SimulacaoPageElements {

    public SimulacaoPage() {
        driver = TestRule.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void acessaPaginaSimulador() {
        ReportUtils.logMensagem(Status.INFO, "Acessando Pagina");
        String urlSistema = loadFromPropertiesFile("base_" + loadFromPropertiesFile("config.properties",
                "ENVIRONMENT").toLowerCase() + ".properties", "SIMULACAO_URL");

        driver.manage().deleteAllCookies();
        driver.navigate().to(urlSistema);
    }

    public void preencherPerfil(String perfil){
        switch (perfil.toUpperCase()) {
            case "PF":
                PERFIL_PF.click();
                break;
            case "PJ":
                PERFIL_PJ.click();
                break;
            default:
                ReportUtils.logMensagem(Status.WARNING, "Selecionado Perfil Default");
        }
    }

    public void preencherMedidaTempo(String medida){
        SETA.click();
        esperaElementoVisivel(ANOS, 1);
        switch (medida.toUpperCase()) {
            case "MESES":
                MESES.click();
                break;
            case "ANOS":
                ANOS.click();
                break;
            default:
                ReportUtils.logMensagem(Status.WARNING, "Selecionado medida de de tempo deafult");
        }
    }

    public void inserirValorAplicacao(String perfil,String valorAplicacao){
        preencherPerfil(perfil);
        VALOR_APLICAR.sendKeys(valorAplicacao);
        VALOR_APLICAR.sendKeys(Keys.ENTER);
        ReportUtils.logMensagem(Status.INFO, "Selecionado Perfil: " + perfil.toUpperCase());
        ReportUtils.logMensagem(Status.INFO, "Valor Aplicar: " + valorAplicacao);
    }

    public void inserirValorPoupar(String perfil,String valorInvestimentoMes){
        preencherPerfil(perfil);
        VALOR_INVESTIR.sendKeys(valorInvestimentoMes);
        VALOR_INVESTIR.sendKeys(Keys.ENTER);
        ReportUtils.logMensagem(Status.INFO, "Selecionado Perfil: " + perfil.toUpperCase());
        ReportUtils.logMensagem(Status.INFO, "Valor Poupar: " + valorInvestimentoMes);
    }

    public void inserirValorTempo(String perfil,String tempo, String medida){
        preencherPerfil(perfil);
        TEMPO.sendKeys(tempo);
        preencherMedidaTempo(medida);
        ReportUtils.logMensagem(Status.INFO, "Selecionado Perfil: " + perfil.toUpperCase());
        ReportUtils.logMensagem(Status.INFO, "Valor Tempo: " + tempo +" "+medida);
    }

    public void inserirDados(String perfil, String valorAplicacao, String valorInvestimentoMes, String tempo, String medida) {

        preencherPerfil(perfil);
        VALOR_APLICAR.sendKeys(valorAplicacao);
        VALOR_INVESTIR.sendKeys(valorInvestimentoMes);
        TEMPO.sendKeys(tempo);
        preencherMedidaTempo(medida);
        ReportUtils.logMensagem(Status.INFO, "Selecionado Perfil: " + perfil.toUpperCase());
        ReportUtils.logMensagem(Status.INFO, "Valor Aplicar: " + valorAplicacao);
        ReportUtils.logMensagem(Status.INFO, "Valor Poupar mensalmente: " + valorInvestimentoMes);
        ReportUtils.logMensagem(Status.INFO, "Tempo poupar: " + tempo + " " + medida, SeleniumUtils.getScreenshotReport());
    }

    public void clicoNoBotaoSimular() {
        SIMULAR.click();
    }


    public boolean validarMensagem(WebElement e, String mensagem) {
        boolean validador = false;
        ReportUtils.logMensagem(Status.INFO, "Mensagem Esperada: " + mensagem);
        if (verificaExistenciaDeElementoNaTela(e)) {
            ReportUtils.logMensagem(Status.INFO, "Mensagem Valor " + e.getAttribute("id") + " encontrada: " + e.getText());
            validador = mensagem.equals(e.getText());
        }
        return validador;
    }


    public boolean validarMensagemAplicacao(String mensagemErroAplicacao) {
        boolean validador = false;
        if (!mensagemErroAplicacao.isEmpty()) {
            validador = validarMensagem(VALOR_APLICAR_ERRO, mensagemErroAplicacao);
        }else {
            ReportUtils.logMensagem(Status.ERROR, "Erro ao validar mensagem de de valor aplicar ", SeleniumUtils.getScreenshotReport());
        }
        return validador;
    }

    public boolean validarMensagemPoupar(String mensagemErroInvestimento) {
        boolean validador = false;
        if (!mensagemErroInvestimento.isEmpty()) {
            validador = validarMensagem(VALOR_INVESTIR_ERRO, mensagemErroInvestimento);
        }else {
            ReportUtils.logMensagem(Status.ERROR, "Erro ao validar mensagem de valor poupar", SeleniumUtils.getScreenshotReport());
        }
        return validador;
    }

    public boolean validarMensagemTempo(String mensagemErroInvestimento) {
        boolean validador = false;
        if (!mensagemErroInvestimento.isEmpty()) {
            validador = validarMensagem(VALOR_TEMPO_ERRO, mensagemErroInvestimento);
        }else {
            ReportUtils.logMensagem(Status.ERROR, "Erro ao validar mensagem de valor tempo", SeleniumUtils.getScreenshotReport());
        }
        return validador;
    }

}