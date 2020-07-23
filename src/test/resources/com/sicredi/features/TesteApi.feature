  # language: pt
  Funcionalidade: Verificar retorno da API
    Ao se consultar a API
    Deverá vir o resultado da simulacao no formato esperado

    @CONTRATO_API_SIMULACAO
    Cenario: Validar contrato da api de simulacao
      Quando eu realizo a consulta
      Entao recebo o resultado conforme esperado
