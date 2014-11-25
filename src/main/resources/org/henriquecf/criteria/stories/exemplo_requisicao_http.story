Scenario: consulta HTTP exemplo
Given o seguinte header chave com valor valor
And o seguinte corpo JSON: ["json":"json"]
When eu der um get na url http://httpbin.org/get
Then eu deveria receber o seguinte header Type com valor Valor
And o seguinte corpo JSON: ["hi":"hi"]
And o codigo de status 401


