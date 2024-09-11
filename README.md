Aqui está um exemplo de `README.md` para sua aplicação **kitchenorders**:

```md
# KitchenOrders

KitchenOrders é um microserviço de pedidos de cozinha desenvolvido com **Spring Boot** e **Spring WebFlux**, utilizando **Java 22**. Este projeto visa a construção de um sistema escalável e resiliente com integração de **RabbitMQ** para mensageria e persistência no **PostgreSQL**. A aplicação é containerizada utilizando **Docker** e pode ser orquestrada via **Docker Compose** para um ambiente completo com banco de dados e mensageria.

## Features

- **Reativo**: Totalmente construído com Spring WebFlux, suportando operações não bloqueantes.
- **Persistência**: Integração com PostgreSQL utilizando Spring Data JDBC.
- **Mensageria**: Integração com RabbitMQ para processamento de pedidos.
- **Containerizado**: Suporte completo para Docker e Docker Compose.
- **Testes**: Inclui testes unitários e de integração com Testcontainers.

## Tecnologias Utilizadas

- **Java 22**
- **Spring Boot 3.1.0**
- **Spring WebFlux**
- **Spring Data JDBC**
- **RabbitMQ**
- **PostgreSQL**
- **Flyway**
- **Docker**
- **Testcontainers**
- **Maven**

## Pré-requisitos

Certifique-se de ter instalado:

- **Docker** e **Docker Compose**
- **Java 22**
- **Maven**

## Como Rodar a Aplicação

### 1. Compilar e Gerar o JAR

Primeiro, compile a aplicação com o Maven:

```bash
mvn clean package -DskipTests
```

### 2. Construir a Imagem Docker

Após a compilação, construa a imagem Docker:

```bash
docker build -t kitchenorders-app .
```

### 3. Rodar a Aplicação com Docker

Agora, você pode rodar a aplicação em um container:

```bash
docker run -d -p 8080:8080 --name kitchenorders-container kitchenorders-app
```

A aplicação estará acessível em `http://localhost:8080`.

### 4. Rodar com Docker Compose

Se desejar rodar a aplicação junto com o PostgreSQL e RabbitMQ, use o Docker Compose:

```bash
docker-compose up --build
```

Isso iniciará a aplicação e seus serviços dependentes.

## Endpoints Disponíveis

A aplicação expõe os seguintes endpoints:

### `GET /orders`
Retorna a lista de todos os pedidos de cozinha.

### `POST /orders`
Cria um novo pedido de cozinha.

Exemplo de corpo da requisição:
```json
{
  "description": "Pizza Margherita",
  "price": 25.90,
  "customer_id": 1
}
```

### `GET /customers`
Retorna a lista de todos os clientes.

### `POST /customers`
Cria um novo cliente.

Exemplo de corpo da requisição:
```json
{
  "email": "email@exemplo.com",
  "nome": "Nome do Cliente"
}
```

## Configurações de Banco de Dados

Por padrão, a aplicação usa as seguintes configurações de banco de dados (definidas no `docker-compose.yml`):

- **Database**: kitchen_db
- **User**: postgres
- **Password**: password
- **Host**: `db` (quando executado via Docker Compose)

### Flyway

As migrações de banco de dados são gerenciadas pelo Flyway. O esquema inicial cria duas tabelas: `customers` e `kitchenorders`, conforme as migrações SQL.

## Testes

Os testes utilizam o **Testcontainers** para simular um ambiente real com PostgreSQL e RabbitMQ em containers Docker.

Para executar os testes:

```bash
mvn test
```

## Contribuindo

Contribuições são bem-vindas! Se você tiver sugestões de melhorias, por favor, abra uma *issue* ou envie um *pull request*.

## Licença

Este projeto está licenciado sob os termos da [MIT License](LICENSE).

```

### Instruções para Uso:
- Esse README inclui uma visão geral do projeto, as tecnologias utilizadas, como rodar a aplicação, endpoints disponíveis e como contribuir.
- Adapte conforme necessário caso surjam mudanças no código, funcionalidades ou configurações. 

Posso ajustar mais detalhes, se necessário.
