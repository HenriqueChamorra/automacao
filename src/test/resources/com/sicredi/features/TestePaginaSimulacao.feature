  # language: pt
  Funcionalidade: Simular um Investimento na Poupança
    como um Associado,
    eu gostaria de preencher o formulário de simulação
    e ver a tabela de resultado com Mês e Valor.

    Contexto:
      Dado que acessei a pagina do simulador de investimento

    @EFETUA_SIMULACAO
    Esquema do Cenario: Simular Investimento
      Quando eu preencher o formulario de simulacao com o dados  "<perfil>", "<valorAplicacao>" , "<valorInvestimentoMes>" , "<tempo>" e "<prazo>"
      E  clicar no botao simular
      Entao sera visualizada a tela com o resultado da simulacao com "<tempo>" e "<prazo>" em meses
      E "<valorGuardado>" valor
      Exemplos:
        | perfil | valorAplicacao | valorInvestimentoMes | tempo | prazo | valorGuardado |
        | pf     | 1000.00        | 100.00               | 10    | meses | 2.019         |
        | pf     | 500.00         | 500.00               | 10    | anos  | 65.485        |
        | pj     | 9999.00        | 500.00               | 5     | anos  | 41.994        |
        | pj     | 1000.00        | 100.00               | 10    | meses | 2.019         |

    @ERROS_VALORES_APLICACAO
    Esquema do Cenario: Validar avisos de erro valor aplicacao
      Quando eu preencher o formulario com perfil "<perfil>" e o campo valor de aplicacao: "<valorAplicacao>"
      Entao sera exibida mensagem erro de valor aplicar "<mensagemErroAplicacao>"

      Exemplos:
        | perfil | valorAplicacao | mensagemErroAplicacao |
        | pj     | 0.01            | Valor mínimo de 20.00  |
        | pf     | 0.01            | Valor mínimo de 20.00  |
        | pj     | 19.99           | Valor mínimo de 20.00  |
        | pf     | 19.99           | Valor mínimo de 20.00  |
        | pj     | 99999999.00    | Máximo de 9999999.00   |
        | pf     | 99999999.00    | Máximo de 9999999.00    |


    @ERROS_VALORES_POUPAR
    Esquema do Cenario: Validar avisos de erro valor poupar
      Quando eu preencher o formulario com o perfil "<perfil>" e o campo valor poupar: "<valorInvestimentoMes>"
      Entao sera exibida mensagem erro de valor poupar "<mensagemErroInvestimento>"

      Exemplos:
        | perfil | valorInvestimentoMes | mensagemErroInvestimento |
        | pj     | 0.01                   | Valor mínimo de 20.00  |
        | pf     | 0.01                   | Valor mínimo de 20.00  |
        | pj     | 19.99                  | Valor mínimo de 20.00  |
        | pf     | 19.99                  | Valor mínimo de 20.00  |
        | pj     | 99999999.00           | Máximo de 9999999.00   |
        | pf     | 99999999.00           | Máximo de 9999999.00   |


    @ERROS_VALORES_TEMPO
    Esquema do Cenario: Validar avisos de erro de tempo
      Quando eu preencher o formulario com o perfil "<perfil>" e o campo tempo: "<tempo>" "<medida>"
      Entao sera exibida mensagem de erro tempo "<mensagemErroTempo>"

      Exemplos:
        | perfil | tempo | medida | mensagemErroTempo         |
        | pj     | 0      | meses |  Valor esperado não confere|
        | pf     | 0      | meses |  Valor esperado não confere|
        | pj     | 0.1    | anos  |  Valor esperado não confere|
        | pf     | 0.1    | anos  |  Valor esperado não confere|
        | pj     | -1     | meses |  Valor esperado não confere|
        | pf     | -1     | meses |  Valor esperado não confere|
        | pj     | -1     | anos  |  Valor esperado não confere|
        | pf     | -1     | anos  |  Valor esperado não confere|
