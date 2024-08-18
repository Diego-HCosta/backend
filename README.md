# Authenticação por token 

**Descrição:**  
Este projeto é uma API RESTful para gerenciar usuários e autenticação, desenvolvido em Java com Spring Boot. Ele permite criar, atualizar, excluir e consultar usuários, além de oferecer autenticação JWT com estilo de autorização 0AUTH2

## Tecnologias Utilizadas

- **Java 22**: Linguagem de programação.
- **Spring Boot 3.3.2**: Framework para criação de aplicações web em Java.
- **Maven**: Gerenciador de dependências e build.
- **JWT**: Para autenticação e autorização segura.
- **Spring Security**: para criar uma cadeia de authenticação ( lidar com ataques CSRF e etc ).

## Requisitos

Antes de começar, você precisa ter os seguintes softwares instalados:

- [Java 22](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/install.html) ( caso tenha IntelliJ instalado basta cliclar no arquivo POM.xml ir em maven e depois por último em reload project eliminando necessidade de installar o maven.)
- Um IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/downloads/)

## Instalação e Execução

1. Clone o repositório para sua máquina local:

   ```bash
   git clone https://github.com/Diego-HCosta/backend
2. Navegue até diretório do projeto:

   ```bash
   cd backend 
3. Instale as dependências do projeto e compile o código:
   ```bash
   mvn clean install
   
4. Instale as dependências do projeto e compile o código:
   ```bash
    mvn spring-boot:run

5. A aplicação estará disponível em http://localhost:8080.

## Autenticação
A autenticação é feita através de tokens JWT. Para obter um token, faça uma requisição POST para **/login** com as credenciais de usuário:
```json
  {
    "username": "usuario",
    "password": "senha"
  }
```
Em caso de sucesso, você receberá um token JWT no seguinte formato:
```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
```
Use este token no cabeçalho Authorization das demais requisições:
```makefile
   Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
