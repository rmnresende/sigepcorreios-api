[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


## O que é este projeto?
O intuito deste projeto é ter acesso ao Webservice SIGEP Web dos Correios através de uma arquitetura Rest em vez da arquitetura SOAP, esta api atua como "fachada" para o SIGEP Web.
Neste projeto, você recebe requições e devolve respostas no em estilo arquitetural Rest, mas acessa o Sigep Web usando o estilo arquitetural SOAP, já que até hoje os Correios não disponibilizaram acesso ao seu Webservice através do estilo arquitetural Rest.

### O que é o SIGEP?
Existe o Web Service do SIGEP Web e existe uma versão desktop que utiliza o Web Service para fornencer um sistema que prepara e gerencia as postagens de clientes dos Correios. Você pode obter mais informações sobre este sistema desktop no <a href="https://www.correios.com.br/a-a-z/sigep-web-gerenciador-de-postagens-dos-correios">link oficial dos correios</a> e para mais detalhes sobre o Web Service consulte a documentação oficial <a href="http://www.corporativo.correios.com.br/encomendas/sigepweb/doc/Manual_de_Implementacao_do_Web_Service_SIGEP_WEB.pdf">neste link</a>.

### Por que usar este projeto/API?
Caso você não queira trabalhar diretamente com a arquitetura SOAP, seja por não gostar do estilo, por alguma limitação de projeto, seja porque os desenvolvedores do seu projeto nunca trabalharam com SOAP, ou por qualquer outro motivo, este projeto lhe será bastante útil, pois como mencionado anteriormente, esta API acessa o Webservice dos Correios usando a arquitetura SOAP mas você irá acessar esta API usando arquitetura Rest, e as únicas configurações necessárias são os dados de acesso ao Web Service do SIGEP! Toda a configuração de WSDL, XML e XSD são foram feitas neste projeto!

Então em vez de fazer uma integração do zero com o SIGEP Web, você só precisa consumir esta API, e assim ter acesso ao SIGEP Web, pondendo fazer consuta de disponibilidade dos serviços para o cartão de postagem, obter o status do cartão, consultar CEP, solicitar PLP(pré-lista de postagem), bloquear plp, etc.

E esta API não faz conexão com nenhum banco de dados, então você não precisa deste tipo de configuração, você pode persistir os retornos em seu próprio banco, do jeito que lhe for mais conveniente.

### Como usar?
O primeiro passo é localizar a classe Credencias, ela está no pacote constantes, e lá nesta classe preenchear os dados de acesso ao Web Service do SIGEP/Correios, que são: usuário, senha, contrato, cartão, CNPJ e código administrativo.Caso você já use o SIGEP deve ter este dados consigo, mas caso não tenha, entre em contato com o representante do seu contrato (ou do seu cliente) nos Correios.



