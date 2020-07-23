package com.sicredi.setup;

import com.aventstack.extentreports.Status;
import com.sicredi.utils.ReportUtils;
import com.sicredi.utils.SeleniumUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestRule {

    private static WebDriver driver;

    @Before
    public void beforeCenario(Scenario s) {

        ReportUtils.criarReportFeature(s);
        String nomeCenario = s.getName();
        ReportUtils.logMensagem(Status.INFO, "Executando Cenario de Teste: " + nomeCenario);

    }

    @After
    public void afterCenario(Scenario cenario) {
        if (!(driver == null)) {
            ReportUtils.logMensagem(Status.INFO, "Finalizando instância do chromeDriver", SeleniumUtils.getScreenshotReport());
            driver.quit();
            driver = null;
        }
        ReportUtils.atualizaReport(cenario);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            String headless = SeleniumUtils.loadFromPropertiesFile("config.properties", "CHROME_HEADLESS").toUpperCase();

            String pathProjeto = System.getProperty("user.dir");
            String sistemaOperacional = System.getProperty("os.name");

            if (sistemaOperacional.toUpperCase().contains("WINDOWS")) {
                System.setProperty("webdriver.chrome.driver", pathProjeto + "/drivers/chromedriver.exe");
                System.setProperty("os.download.path", System.getProperty("user.home").replaceAll("\\\\", "/") + "/Downloads/");
            } else {
                System.setProperty("webdriver.chrome.driver", pathProjeto + "/drivers/chromedriver");
                System.setProperty("os.download.path", System.getProperty("user.home") + "/Downloads/");
            }

            if (headless.equals("TRUE")) {
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                ReportUtils.logMensagem(Status.INFO, "Iniciando ChromeDriver em modo Headless.");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
            } else {
                ReportUtils.logMensagem(Status.INFO, "Iniciando ChromeDriver.");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            }
            driver.manage().deleteAllCookies();
        }
        return driver;
    }
}

