package com.sicredi.pages;

import com.sicredi.elements.ResultadoPageElements;
import com.sicredi.setup.TestRule;
import com.sicredi.utils.ReportUtils;
import com.sicredi.utils.SeleniumUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.PageFactory;


public class ResultadoPage extends ResultadoPageElements {

    public ResultadoPage() {
        driver = TestRule.getDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean comparaTempoInvestimento(String tempo, String medida) {
        boolean resultado = false;
        esperaElementoVisivel(TEXTO_RESULTADO_TEMPO, 1);
        if (medida.toUpperCase().equals("ANOS")) {
            tempo = Integer.toString(12 * Integer.parseInt(tempo));
            medida = "meses";
        }
        String a = TEXTO_RESULTADO_TEMPO.getText();
        String esperado = "Em " + tempo + " " + medida + " você terá guardado";
        ReportUtils.logMensagem(Status.INFO, "Tempo a poupar esperado: " + tempo + " " + medida, SeleniumUtils.getScreenshotReport());
        if (a.equals(esperado)) {
            resultado = true;
            ReportUtils.logMensagem(Status.INFO, "Tempo a poupar encontrado :" + a);
        } else {
            resultado = false;
            ReportUtils.logMensagem(Status.FAIL, "Tempo a poupar incorreto");
        }
        return resultado;
    }

    public boolean verificaValorMontante(String valor) {
        ReportUtils.logMensagem(Status.INFO, "Valor guardado esperado: R$ " + valor);
        String info = VALOR_RESULTADO.getText().replace("R$ ", "");
        boolean resultado = valor.equals(info);

        if (!resultado) {
            ReportUtils.logMensagem(Status.FAIL, "Valor guardado encontrado :" + VALOR_RESULTADO.getText());
        } else {
            ReportUtils.logMensagem(Status.INFO, "Valor guardado encontrado :" + VALOR_RESULTADO.getText());
        }
        return resultado;
    }
}



