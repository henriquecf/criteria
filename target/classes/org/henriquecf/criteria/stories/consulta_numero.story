
Scenario: consulta por telefone
Given os seguintes telefones:
  | orgao | telefone          |
  | INF    | 62 32232332 |
  | IME    | 62 39939339 |
When eu fizer uma consulta por “INF”
Then eu devo ver “62 32232332”

Scenario: consulta sem retorno
Given os seguintes telefones:
  | orgao | telefone          |
  | INF    | 62 32232332 |
  | IME    | 62 39939339 |
When eu fizer uma consulta por “IF”
Then eu devo ver “Nenhum resultado encontrado”


