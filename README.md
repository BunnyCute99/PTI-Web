# PROJETO INTEGRADOR IV: DESENVOLVIMENTO DE SISTEMAS ORIENTADO A DISPOSITIVOS MÓVEIS E BASEADOS NA WEB

## Projeto Integrador Senac 2025-1

## Integrantes:
* BRUNO PASSARELI COSTA
* GUSTAVO LUIZ PEREIRA LIMA
* IZABEL RARIDJA SANTOS GUIMARÃES
* JOAO PEDRO DE OLIVEIRA GOULART
* PRISCILLA FERREIRA SILVA


## Como Rodar o Projeto

### Pré-requisitos:
<a href="https://www.oracle.com/br/java/technologies/downloads/#jdk24-windows"> JDK - Versão 24 - Instalado </a>

<a href="https://www.postgresql.org/download/"> PostgreSQL - Instalado </a> Lembrar de Salvar a senha e usuário na instalção, será usado mais tarde

<a href="https://code.visualstudio.com/"> Visual Studio Code - Instalado </a> com as seguintes estenções:
<a href="https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack"> Extension Pack for Java </a>  e 
<a href="https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack"> Spring Boot Extension Pack </a>

### Passos:

1 - Crie uma conecção e uma databse no PostgreSQL usando o pgAdin que vem junto com o postgres, na hora da criação da coneção use o usuário e senha da instação.

2 - Abrir o a pasta do projeto no Visual Code

3 - Alterar o arquivo src/main/resources/application.properties com as seuintes informações:
* spring.datasource.url=jdbc:postgresql://localhost:5432/estoque    para  a url do seu banco de dados no postgreSQL, para saber mais veja o capítulo sobre URL do banco de dados
* spring.datasource.username=postgres    para o seu nome de usuário do postgreSQL
* spring.datasource.password=123    para o sua senha do postgreSQL
* spring.jpa.hibernate.ddl-auto=update    precisa ser mudado para     CREAT  na primeira vez que rodar o programa, depois mudar devolta para UPDATE

4 - Rodar o src/main/java/com/example/demo/DemoApplication.java    ->   F5 no Visual Studio Code ele deve compilar a aplicação com sucesso

5 - Abrir no seu navegador web favorito o seguinte link (http://localhost:8080/home)

### URL do banco de Dados
a URL do banco de dados é composde das seguintes informações e seguindo o seguinte modelo:
* spring.datasource.url=jdbc:postgresql://[Host name ou address]:[Número da Porta]/[Nome do Banco de Dados]


Então uma url cujo o Host name é "localhost" com número da porta é "5432" e tem como banco de dados o nome "estoque" ficaria assim:
* spring.datasource.url=jdbc:postgresql://localhost:5432/estoque






