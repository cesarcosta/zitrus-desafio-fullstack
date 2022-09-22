
# Zitrus - Desafio FullStack

Construir uma aplicação RESTful para controle de estoque com no mínimo duas entidades, como por exemplo: Produto e Movimento Estoque.

- CRUD de Produtos;

- A aplicação deve efetuar entrada e saída dos produtos no estoque. É importante validar o saldo ao efetuar uma saída do produto, caso não haja quantidade suficiente, deve ser retornado uma mensagem específica;

- Consulta de produtos por tipo, com quantidade de saída e quantidade disponível;

- Consulta de lucro por produto, exibindo a quantidade total de saída, e total do lucro (valor de venda – valor do fornecedor)

## Clone e execução dos projetos

### Backend

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 17: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3: Necessário para realizar o build do projeto Java](https://maven.apache.org/download.cgi)

#### Desenvolvimento

Para a execução do projeto, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/condessalovelace/mavenquickstart
```

#### Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

#### Testes

Para rodar os testes, utilize o comando abaixo:

```
mvn test
```

#### Iniciando a API Backend

Para iniciar o servidor da API, utilize o comando abaixo:

```
mvn spring-boot:run
```

Para acessar a aplicação: 

http://localhost:8080/products

### Frontend

Para executar o projeto, será necessário instalar os seguintes programas:

- [Node.js / NPM](https://nodejs.org/en/download/)
- [Yarn](https://yarnpkg.com/getting-started/install)

#### Desenvolvimento

Para a execução do projeto, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/cesarcosta/zitrus-desafio-fullstack
cd zitrus-desafio-fullstack/frontend
```

#### Construção

Para instalar todas as dependências existentens no projeto, executar os comando abaixo:

```shell
yarn ou npm install
```

#### Iniciando Frontend

Para iniciar o servidor da API, utilize o comando abaixo:

```
yarn serve / npm run serve
```

Para acessar a aplicação: 

http://localhost:8081
