# Projeto Automação

Este projeto demonstra a automação de testes do simulador de investimentos do Sicredi.

## Começando

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 8: Necessário para executar o projeto Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- [Gradle 5.6: Necessário para realizar o build do projeto e execução dos testes](https://gradle.org/next-steps/?version=5.6&format=all)
- [IntelliJ: Para desenvolvimento do projeto](https://www.jetbrains.com/pt-br/idea/download/)
- [ChromeDriver: Para execução dos testes (copiá-lo para a pasta /drivers do projeto)](https://chromedriver.chromium.org/downloads)
- [Google Chrome: Navagador Web](https://www.google.com/intl/pt-BR/chrome/)

## Desenvolvimento e Execução de testes

Para iniciar o desenvolvimento ou execução dos testes, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/HenriqueChamorra/automacao.git
```

## Testes

Para rodar os testes e baixar as suas dependências, importe como um projeto gradle utilizando o Intellij e execute as features desenvolvidas em cucumber,  localizadas em \src\test\resources\com\sicredi\features, ou utilize o comando abaixo:

```
gradle clean test
```

## Relatório de Execução dos Testes 

A cada execução é gerado um relatório via ExtentReports com o resultado dos testes, localizado na pasta raiz do projeto /report.

## Arquivos .properties

Existem dois arquivos de properties localizados na pasta \src\test\resources. O Arquivo config.properties configura o chromeDriver para exexução em modo Headless ou não . Já o arquivo base_hlg.properties possui as urls utilizadas nos testes.




