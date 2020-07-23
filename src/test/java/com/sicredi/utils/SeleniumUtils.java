package com.sicredi.utils;

import com.aventstack.extentreports.Status;
import com.sicredi.setup.TestRule;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class SeleniumUtils {
    public static WebDriver driver;

    public SeleniumUtils() {
        driver = TestRule.getDriver();
    }

    protected static Boolean verificaExistenciaDeElementoNaTela(WebElement element) {
        boolean elementoOK = false;
        try {
            if (element.isDisplayed()) {
                elementoOK = true;
            }
        } catch (Exception e) {
            System.out.println("Elemento: " + element + " não foi encontrado!");
        }

        return elementoOK;
    }

    public static String loadFromPropertiesFile(String propertieFileName, String propertLoad) {
        Properties prop = new Properties();
        InputStream input = null;
        String path = "src/test/resources/";
        String property = "";

        try {
            input = new FileInputStream(path + propertieFileName);
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            property = prop.getProperty(propertLoad);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return property;
    }

    private static void setDropodownListByIndex(int index, WebElement element) {
        Select listboxelementsPesquisa = new Select(element);
        listboxelementsPesquisa.selectByIndex(index);
    }

    public static void preencheCampo(WebElement elemento, String conteudo) {
        esperaElementoFicarClicavel(elemento, 120);
        elemento.clear();
        elemento.sendKeys(conteudo);
        elemento.sendKeys(Keys.TAB);

    }


    private static void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    protected static Boolean esperaElementoVisivel(WebElement element, long segundos) {
        WebElement webElement;
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(segundos))
                    .pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);
            try {
                webElement = wait.until(ExpectedConditions.visibilityOf(element));
            } catch (Exception e) {
                return false;
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
        return webElement != null;
    }

    protected static Boolean esperaElementoFicarClicavel(WebElement element, long segundos) {
        WebElement webElement = element;
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(segundos))
                    .pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);
            try {
                webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            } catch (Exception e) {
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Elemento não foi encontrado!");
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
        return webElement != null;
    }

    public static String getScreenshotReport() {
        String dir;
        String imagem_dir = "";
        driver.getCurrentUrl();
        String nomePrint = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        nomePrint = nomePrint.replace(".", "_");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Thread.sleep(1000);
            criarDiretorio(ReportUtils.getDiretorioReport() + "/screenshots");
            dir = ReportUtils.getDiretorioReport() + "/screenshots/" + nomePrint + ".png";
            imagem_dir = "./screenshots/" + nomePrint + ".png";
            copyFileUsingStream(scrFile, new File(dir));
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "Erro ao salvar o Screenshot - " + e);
            //  Log4jUtils.logMensagem("ERROR", "Erro ao salvar o Screenshot - " + e);
        }
        return imagem_dir;
    }

    static void criarDiretorio(String diretorioASerCriado) {
        try {
            File diretorio = new File(diretorioASerCriado);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
        } catch (Exception e) {
            ReportUtils.logMensagem(Status.FAIL, "" + e.getMessage());
        }
    }
}





