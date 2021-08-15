# Read Me
O seguinte projeto foi desenvolvido por Keoma Pereira para prática da programação. O projeto consiste em um serviço para movimentações financeiras, utilizado o banco de dados H2. Os lançamentos de saques são salvos em tabela, nome tabela: lancamento.

# Serviços

### POST/Saques/{conta}
Realiza um lançamento de saque e retorna o valor em cédulas.

### GET/Saques/{conta}
Busca lançamentos de saques realizados de uma determinada conta.

### Documentação adicional
Para consultar mais projetos do autor, consulte o seu repositório no github.

* [GITHUB - Autor](https://github.com/KeomaPereira)

### Configuração
Para rodar o projeto com o banco H2, necessário utilizar a configuração a baixo:

VM options: -Dspring.profiles.active=local

Links:

Swagger: http://localhost:8080/swagger-ui.html#/

H2: http://localhost:8080/h2-console/login.jsp


