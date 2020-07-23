package com.sicredi.steps;

import com.sicredi.utils.ApiUtils;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ApiSteps {
    StringBuffer response = new StringBuffer();

    @Quando("^eu realizo a consulta")
    public void realizoAConsultaDaApi() throws IOException {
        response = ApiUtils.consultaUrl();

    }

    @Entao("^recebo o resultado conforme esperado$")
    public void verificoContratoDaApi() {
        assertTrue("Contrato de API invalido",ApiUtils.verificaContrato(response));

    }
}
