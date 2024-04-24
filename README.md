# Projeto Gateway Brasil CEP

Este é um projeto Java que utiliza JDBC e HttpClient para interagir com uma API de consulta de CEP e persistir os dados em um banco de dados MySQL.

## Requisitos do Ambiente

- **Java 11**
- **Apache Maven 3.9.2**
- **Docker e Docker Compose** (caso não tenha o MySQL8 instalado localmente)

## Tecnologias Utilizadas

- JDBC (Java Database Connectivity) para interação com o banco de dados MySQL.
- HttpClient para fazer chamadas HTTP para a API de consulta de CEP.

## Instruções de Configuração e Execução

### 1. Clonar o Repositório

```bash
git clone https://github.com/Eduardosilva3/Projeto_Gateway_Cep.git
cd Projeto_Gateway_Cep 
```
### 2.  Configurar Banco de Dados

## Usando Docker Compose (para configurar o MySQL)
Se você estiver utilizando Docker Compose para configurar o MySQL Rode o seguinte comando, ele irá iniciar o container docker e aplicação logo após:
```bash
./RunGatewayCompose.sh
```

## Usando MySQL Local

Se já tiver o MySQL configurado localmente:

- Abra o arquivo src/main/resources/application.properties.
- Substitua username e password com suas credenciais do MySQL.
- Substitua data_gateway pelo nome da sua base de dados.
- O programa criará as tabelas automaticamente, se ainda não existirem.

Depois Rode o comando abaixo para iniciar o programa:

```bash
./RunGateway.sh
```

### Seguir Instruções no Terminal
- Após iniciar o programa, siga as instruções exibidas no terminal para interagir com o sistema.