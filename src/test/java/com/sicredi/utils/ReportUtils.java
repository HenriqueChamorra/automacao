package com.sicredi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtils extends SeleniumUtils {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest feature;
    public static ExtentTest child;
    public static String diretorioReport;


    public static void criarReportFeature(Scenario cenario) {
        if (extentReports == null) {
            extentReports = new ExtentReports();

            String sistemaOperacional =  System.getProperty("os.name");

            String dir = System.getProperty("user.dir");
            String filename = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            filename = filename.replace(".", "_");

            criarDiretorio(dir + File.separator+"report");//File.separator
            setDiretorioReport("./report/Teste-" + filename);
            criarDiretorio(diretorioReport);

            if (sistemaOperacional.toUpperCase().contains("WINDOWS")) {
                htmlReporter = new ExtentHtmlReporter(diretorioReport + "\\report.html");
            }else{
                htmlReporter = new ExtentHtmlReporter(diretorioReport + "/report.html");}
            htmlReporter.config().setEncoding("utf-8");
            extentReports.attachReporter(htmlReporter);
        }
        child = extentReports.createTest(cenario.getName()); //,
    }

    public static void atualizaReport(Scenario cenario) {
        if (cenario.isFailed()) {
            child.log(Status.ERROR, "Erro encontrado durante a execução.");
        } else {
            child.log(Status.PASS, "Cenário executado com sucesso.");
        }
        extentReports.flush();
    }

    public static ExtentTest getFeature() {
        return feature;
    }

    public static void setDiretorioReport(String diretorio) {
        diretorioReport = diretorio;
    }

    public static String getDiretorioReport() {
        return diretorioReport;
    }

    public static void logMensagem(Status status, String mensagem, String imagem) {
        try {
            System.out.println(mensagem);
            child.log(status, mensagem, MediaEntityBuilder.createScreenCaptureFromPath(imagem).build());
            extentReports.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logMensagem(Status status, String mensagem) {
        System.out.println(mensagem);
        child.log(status, mensagem);
        extentReports.flush();
    }
}