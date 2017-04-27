# WebhookManager

### Introdução

O Moip tem uma aplicação que envia webhooks para os ecommerces de seus clientes, esses webhooks possuem informações sobre pagamentos (se foram autorizados, cancelados, etc).

Esta aplicação gera logs bastante grandes, precisamos descobrir através do log quem são os clientes que mais recebem os webhooks e verificar todos o response status retornados pelo servidores dos clientes.

### Task
O arquivo de log em anexo contém informações de envio de webhooks no format:

`level=info response_body="" request_to"<url>" response_headers= response_status="<code>"`

#### Onde:

* url: é a url para onde foi enviado o webhook
* code: é o status code retornado pelo servidor do cliente

As outras informações são irrelevantes para esta task.

#### O que deve ser feito: 
Parsear o arquivo e no final mostrar as seguintes informações na saída:

* 3 urls mais chamadas com a quantidade
* Uma tabela mostrando a quantidade de webhooks por status

## Arquitetura

* Spring Boot 1.5.3.RELEASE
* AngularJS 1.4.9
* Distribuição: .jar (compilado em Java 8)

## Estrutura
![Alt text](https://raw.githubusercontent.com/leticiaalves/WebhookManager/master/docs/FolderStructure.png)

## Execução do Programa
1. Copiar todo o conteúdo da pasta [dist](https://github.com/leticiaalves/WebhookManager/tree/master/dist) para o diretório de sua preferência (inclusive o log.xml).
2. Abrir o prompt de comando (deve estar no diretório do item anterior)
3. Digitar a seguinte linha:
`java -jar WebhookManager-1.0-SNAPSHOT.jar`
4. Acessar a URL http://localhost:8099/index.html#/home no navegador (a tela proposta pela task fica em http://localhost:8099/index.html#/dashboard).
* Obs.: Atenção ao encerrar o spring-boot, ele é um processo, aperte ctrl+c ou execute o comando kill.

## Screenshot
![Alt text](https://raw.githubusercontent.com/leticiaalves/WebhookManager/master/docs/Screenshot.PNG)
