package com.sicredi.steps;

import com.sicredi.pages.SimulacaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import static org.junit.Assert.assertTrue;


public class SimulacaoSteps {

    private SimulacaoPage simulacaoPage = new SimulacaoPage();

    @Dado("^que acessei a pagina do simulador de investimento$")
    public void acessaPaginaSimuladorInvestimento() {
        simulacaoPage.acessaPaginaSimulador();
    }

    @Quando("^eu preencher o formulario de simulacao com o dados  \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" e \"([^\"]*)\"$")
    public void realizoAInsercaoDeDados(String perfil, String valorAplicacao, String valorInvestimentoMes, String tempo, String medida) throws Throwable {
        simulacaoPage.inserirDados(perfil,valorAplicacao, valorInvestimentoMes, tempo, medida);
    }

    @Quando("eu preencher o formulario com perfil \"([^\"]*)\" e o campo valor de aplicacao: \"([^\"]*)\"$")
    public void insiroValorAplicacao(String perfil, String valorAplicacao) {
        simulacaoPage.inserirValorAplicacao(perfil, valorAplicacao);
    }

    @Quando("eu preencher o formulario com o perfil \"([^\"]*)\" e o campo valor poupar: \"([^\"]*)\"$")
    public void insiroValorPoupar(String perfil, String valorInvestimentoMes) {
        simulacaoPage.inserirValorPoupar(perfil, valorInvestimentoMes);
    }

    @Quando("eu preencher o formulario com o perfil \"([^\"]*)\" e o campo tempo: \"([^\"]*)\" \"([^\"]*)\"$")
    public void insiroValorTempo(String perfil, String tempo, String medida) {
        simulacaoPage.inserirValorTempo(perfil, tempo, medida);
    }

    @E("^clicar no botao simular$")
    public void clicoNoBotaoSimular() {
        simulacaoPage.clicoNoBotaoSimular();
    }

    @Entao("^sera exibida mensagem erro de valor aplicar \"([^\"]*)\"")
    public void seraExibidoAvisoAplicacao(String mensagemErroAplicacao) {
        assertTrue("Erro nas validacoes de critica de campos", simulacaoPage.validarMensagemAplicacao(mensagemErroAplicacao));
    }

    @Entao("^sera exibida mensagem erro de valor poupar \"([^\"]*)\"")
    public void seraExibidoAvisoPoupar(String mensagemErroInvestimento) {
        assertTrue("Erro nas validacoes de critica de campos", simulacaoPage.validarMensagemPoupar(mensagemErroInvestimento));
    }

    @Entao("^sera exibida mensagem de erro tempo \"([^\"]*)\"")
    public void seraExibidoAvisoTempo(String mensagemErroTempo) {
        assertTrue("Erro nas validacoes de critica de campos", simulacaoPage.validarMensagemTempo(mensagemErroTempo));
    }
}
