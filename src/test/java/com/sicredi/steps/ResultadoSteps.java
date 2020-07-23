package com.sicredi.steps;

import com.sicredi.pages.ResultadoPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import static org.junit.Assert.assertTrue;


public class ResultadoSteps {

    private ResultadoPage resultadoPage = new ResultadoPage();

    @Entao("^sera visualizada a tela com o resultado da simulacao com \"([^\"]*)\" e \"([^\"]*)\" em meses$")
    public void verificaTempo(String tempo, String medida){
        assertTrue("Erro Tempo incorreto ", resultadoPage.comparaTempoInvestimento(tempo, medida));
    }

    @E("^\"([^\"]*)\" valor$")
    public void verificaMontante(String valor) {
        assertTrue("Erro valor montante incorreto",resultadoPage.verificaValorMontante(valor));

    }

//    @Quando("^consultar api$")
//    public void consultarApi() throws IOException {
//        SimulacaoGetRequest.MyGETRequest();
//    }
}
