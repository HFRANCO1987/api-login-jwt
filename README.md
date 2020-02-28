# Informações de configuração

## Requisitos obrigatórios
Desenvolvimento realizado com Java, framework Spring Boot e banco de dados MYSQL.  

**Java        (v1.8)** 

**Spring Boot  (2.1.1.BUILD-SNAPSHOT)** 

**Mysql        (v5.1.35)** 

## Dependências (jars)
O projeto faz uso do **maven** para gerenciamento das dependências, o arquivo **pom.xml** possui todas
as dependências utilizadas com suas devidas versões.

## Configurações do Spring JPA
O BD utilizado foi **MYSQL**, crie o banco e configure os dados de acesso no arquivo **application.properties**. 

## Configurações de Segurança
Spring Security é a base para autenticação e autorização, a classe **SegurancaConfig** possui as configurações básicas. 
Utilização de JWT para validação das requisições entre API e APP, foi criado um filtro para validação do Token **JwtAuthenticationTokenFilter**.

## Configurações de E-mail
O arquivo **application.properties** possui 2 (duas) chaves para configuração da conta para envio de e-mail, são elas:
**conta.email**
**conta.senha**
A classe **EmailConfig** é responsável pelas configurações e envio de e-mail. **AutenticadorGmail** cria um autenticador com base nas chaves especificadas.
  
## Utilitários e UtilMessage
Classes que centralizam métodos utilitários.

## Executando o Projeto
Execute a classe **ServiceApplication.java**, por padrão será iniciado na porta **http://localhost:8080**, entretanto, confira no 
console a porta executada, uma mensagem como **Tomcat started on port(s): 8080 (http)** deve ser exibida.

## Acessando o Swagger
Após iniciar o projeto basta acessar a url **http://localhost:8080/swagger-ui.html#/**.