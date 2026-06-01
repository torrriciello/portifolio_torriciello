# ClinicMedic API

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de clínicas médicas.

O sistema permite o cadastro de médicos e pacientes, além do controle de agendamentos de consultas com validações de regras de negócio.

---

# Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- Swagger / OpenAPI
- Maven

---

# Arquitetura do Projeto

A aplicação segue uma arquitetura em camadas para manter organização e escalabilidade.

src/main/java/com/api

domain  
Contém as entidades e regras de negócio da aplicação.

doctor  
Gerenciamento de médicos.

patient  
Gerenciamento de pacientes.

role  
Controle de permissões de usuário.

user  
Entidade e autenticação de usuários.

infra  
Configurações de infraestrutura da aplicação.

security  
Configurações de autenticação e autorização usando JWT.

exception  
Tratamento global de exceções.

---

# Segurança

A API utiliza **Spring Security com JWT** para proteger os endpoints.

Fluxo de autenticação:

1. Usuário realiza login
2. A API gera um token JWT
3. O token deve ser enviado no header das requisições
4. O backend valida o token antes de permitir acesso

---

# Documentação da API

A API possui documentação interativa utilizando Swagger.

Após iniciar a aplicação acesse:
