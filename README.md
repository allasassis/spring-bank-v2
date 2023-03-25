## Spring Bank - Backend

<p align="center">
  <img width="600px" height="auto" src="https://user-images.githubusercontent.com/88514585/227735587-9c5dc572-89ad-46b7-a61d-b92464041af6.png"/>
</p>

Esse é o backend de um projeto de um sistema bancário criado em Java, chamado de Spring Bank. Ele conta com um sistema de autenticação usando Spring Security,
onde o usuário precisa fazer o login e ai gerará um token JWT (com validade de 10 minutos), que acompanhará todas as requisições do usuário na API.<br>
Todas as funções usam DTO na entrada e saída de dados.

## Funções da API
### Clientes:
- Listar clientes
- Detalhar cliente
- Cadastrar cliente
- Atualizar cliente

### Operações:
- Mostrar saldo do cliente em especifico
- Depositar
- Sacar
- Transferir

## Tecnologias usadas:

- Java
- Spring Boot
- Spring Security
- MySQL
- JPA
- Tomcat
- Maven
- Flyway (Migrations)
- Postman
- Spring Doc (Open API - Swagger)
- Auth0 (Gerador de token JWT)
- BCrypt Password Encoder
- JUnit
- Lombok

## Documentação
Clone ou baixe a aplicação e a execute-a, após isso consulte a documentação com todos os métodos no seguinte link: <br>
http://localhost:8080/swagger-ui.html

## Autor

#### Allas Assis de Oliveira
https://www.linkedin.com/in/allasassis
