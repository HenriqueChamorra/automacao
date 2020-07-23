package com.sicredi.utils;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.sicredi.model.ApiSimulacaoModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.sicredi.utils.SeleniumUtils.loadFromPropertiesFile;

public class ApiUtils {

    public static StringBuffer consultaUrl() throws IOException {

       loadFromPropertiesFile("config.properties","ENVIRONMENT");

       String  url = loadFromPropertiesFile("base_"+loadFromPropertiesFile("config.properties",
                "ENVIRONMENT").toLowerCase()+".properties","API_URL");

        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        StringBuffer response = new StringBuffer();

        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));

            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();

        }
        return response;
    }

    public static boolean verificaContrato(StringBuffer response){
        Gson g = new Gson();
        boolean validador = false;

        try {
            ApiSimulacaoModel so = g.fromJson(response.toString(), ApiSimulacaoModel.class);
            validador = (so.getMeses().size() == so.getValor().size());
            ReportUtils.logMensagem(Status.INFO, "Validacao de Contrato Realizada com sucesso ! \n"+ response.toString());
            return validador;
        }catch (Exception e){
            ReportUtils.logMensagem(Status.ERROR, "Erro na Validacao de Contrato");
            return false;
        }
    }
}
