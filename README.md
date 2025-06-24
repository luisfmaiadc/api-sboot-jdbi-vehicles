<h1 align="center">api-sboot-jdbi-vehicles</h1>

<p align="center" style="margin-bottom: 20;"> 
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" /> 
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Springboot" /> 
  <img src="https://img.shields.io/badge/apache%20maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Maven" /> 
  <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white" alt="Flyway" /> 
</p> 

<p align="center">O <b>api-sboot-jdbi-vehicles</b> é uma API REST desenvolvida em Java com Spring Boot para gerenciar veículos e suas manutenções. O sistema permite o cadastro, atualização e consulta de veículos e suas respectivas manutenções, com estrutura relacional entre as entidades.</p>

<h2>📌 Visão Geral</h2> <p align="justify">Este projeto utiliza a abordagem <b>Contract First</b>, com a especificação OpenAPI servindo como base para geração das classes de request e response via Swagger Codegen. A persistência dos dados é feita com o MySQL, e o controle de versionamento do banco é gerenciado com Flyway. A manipulação dos dados é realizada por meio da biblioteca <b>JDBI</b>.</p>

<p>Além disso, o projeto foi expandido para um ecossistema maior, incluindo:</p>

- Componente de Orquestração desenvolvido com <b>Apache Camel</b>, responsável pela integração entre serviços.
- API de Oficinas, desenvolvida em Java e Spring Boot, focada na gestão de oficinas mecânicas.

Essa arquitetura proporciona desacoplamento entre serviços, facilitando manutenção e evolução futura.

<h2>🚀 Tecnologias Utilizadas</h2>

- <b>Java 21 + Spring Boot 3.4.5</b>
- <b>Spring Web</b>
- <b>RestTemplate</b> (realização de chamadas HTTP entre aplicações)
- <b>JDBI</b> (acesso ao banco de dados relacional)
- <b>MySQL</b> (persistência dos dados)
- <b>Flyway</b> (controle de versionamento do banco)
- <b>OpenAPI + Swagger Codegen</b> (geração de contratos a partir de especificações)

<h2>🏛️ Arquitetura do Sistema</h2>

```bash
    1. [Cliente/API Consumers] --> HTTP (api-sboot-jdbi-vehicles)
    2. [api-sboot-jdbi-vehicles] --> HTTP (api-sboot-camel-vehicle)
    3. [api-sboot-camel-vehicle] --> HTTP (api-sboot-jdbi-workshops)
```

- api-sboot-jdbi-vehicles → Responsável por veículos e manutenções.
- api-sboot-camel-vehicle → Camada de orquestração utilizando Apache Camel.
- api-sboot-jdbi-workshops → API independente que gerencia oficinas e serviços.

<h2>🏗️ Estrutura do Projeto</h2>

```bash
vehicle-maintenance-api
│-- src/main/java/com/portfolio/luisfmdc/vehicle_maintenance_api
│   ├── config/            # Configuração do JDBI
│   ├── controller/        # Endpoints REST
│   ├── mapper/            # Mapeamento de entidades
│   ├── model/             # Classes de modelo
│   ├── repository/        # Interfaces JDBI e mapeamentos SQL
│   ├── service/           # Regras de negócio
│   ├── VehiclesApplication.java  # Classe principal
│-- src/main/resources
│   ├── api-sboot-jdbi-vehicles\src\main\resources\com\portfolio\luisfmdc\api_sboot_jdbi_vehicles\repository\VehicleRepository  # Queries SQL utiilizadas
│   ├── application.properties  # Configurações do projeto
│   ├── db/migration            # Scripts de migration (Flyway)
│   ├── openapi.yml             # Arquivo de contrato OpenAPI
│-- pom.xml                     # Dependências do projeto
```

<h2>🛠️ Configuração e Execução</h2>

<h3>📌 Pré-requisitos</h3>

1. Ter o <b>MySQL</b> instalado e rodando localmente.
2. O Flyway criará as tabelas automaticamente com base nos scripts localizados em <code>db/migration</code>.

<h3>📜 Configuração do Banco de Dados (<code>application.properties</code>)</h3>

```properties
spring.application.name=api-sboot-jdbi-vehicles
spring.datasource.url=jdbc:mysql://localhost:3306/dbVeiculos?createDatabaseIfNotExist=true
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

<h3>🚀 Executando a API</h3>

```sh
git clone https://github.com/luisfmaiadc/api-sboot-jdbi-vehicles.git
cd api-sboot-jdbi-vehicles

mvn clean install
mvn spring-boot:run
```

<h2>🧩 OpenAPI / Contract First</h2>

<p>O contrato OpenAPI está definido em <code>resources/openapi.yml</code>. As classes de request e response são geradas automaticamente utilizando <b>Swagger Codegen</b> com base nesse contrato.</p>

<h2>📚 Mais Informações</h2>

<p>Este projeto foi desenvolvido com fins didáticos e evolutivos, com o objetivo de explorar práticas modernas como o uso do JDBI, OpenAPI (Contract First), versionamento com Flyway, integração entre serviços REST via Apache Camel e uma arquitetura orientada a microsserviços utilizando Java 21 e Spring Boot.</p>