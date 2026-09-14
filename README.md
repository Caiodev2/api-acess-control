# Login API

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de usuários e autenticação através de login.

O projeto foi desenvolvido com o objetivo de praticar conceitos de desenvolvimento **Back-end**, como criação de APIs REST, integração com banco de dados utilizando JPA, organização em camadas, tratamento de exceções e validação de credenciais.

---

## Funcionalidades

*  Cadastro de usuários
*  Autenticação através de e-mail e senha
*  Busca de usuário por ID
*  Listagem de usuários
*  Atualização de usuários
*  Exclusão de usuários
*  Tratamento de recursos não encontrados
*  Busca de usuário por e-mail

---

## Tecnologias

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **REST API**
* **Banco de dados relacional**
* **Maven**

---

## 📁 Estrutura do Projeto

```text
src/
└── main/
    └── java/
        └── com.exemplo.login/
            ├── config/
            |   ├── TesteConfig.java 
            ├── controllers/
            │   ├── UserController.java
            │   └── exception/
            │       ├── StandardError.java
            │       └── ControllerExceptionHandler.java
            │
            ├── repositories/
            │   └── UserRepository.java
            │
            ├── services/
            │   ├── UserService.java
            │   └── exceptions/
            │       └── ResourceNotFoundException.java
            │
            └── entites/
                └── User.java
```

## Endpoints

### 👤 Cadastrar usuário

```http
POST /register
```

Exemplo de requisição:

```json
{
  "name": "caio",
  "email": "caio@email.com",
  "password": "123456"
}
```

Retorna `201 Created` quando o usuário é cadastrado com sucesso.

---

### 🔑 Login

```http
POST /login
```

Exemplo:

```json
{
  "email": "joao@email.com",
  "password": "123456"
}
```

Em caso de credenciais válidas:

```text
Logado com sucesso!!
```

Caso contrário:

```text
Email ou senha incorreto
```

A autenticação atualmente é realizada através da validação do e-mail e da senha informados.

---

### 🔎 Buscar usuário por ID

```http
GET /users/{id}
```

Exemplo:

```http
GET /users/1
```

---

### 📋 Listar usuários

```http
GET /users
```

Retorna a lista de usuários cadastrados.

---

### ✏️ Atualizar usuário

```http
PUT /users/update/{id}
```

Exemplo:

```http
PUT /users/update/1
```

---

### 🗑️ Excluir usuário

```http
DELETE /users/delete/{id}
```

Exemplo:

```http
DELETE /users/delete/1
```

A exclusão retorna `204 No Content` quando realizada com sucesso.

---

## ⚠️ Tratamento de Erros

O projeto possui um `ControllerExceptionHandler` utilizando `@ControllerAdvice` para centralizar o tratamento de determinadas exceções.

Quando um recurso não é encontrado, a API retorna um objeto contendo informações como:

```json
{
  "timestamp": "2026-09-14T12:00:00Z",
  "status": 404,
  "message": "Usuário não encontrado",
  "path": "/users/1"
}
```

## A estrutura `StandardError` foi criada para padronizar essas respostas de erro.

## Repository

A persistência dos usuários é realizada através de `UserRepository`, que utiliza `JpaRepository`.

Além das operações padrão fornecidas pelo Spring Data JPA, existe uma consulta personalizada para encontrar usuários através do e-mail:

```java
Optional<User> findByEmail(String email);
```

Isso permite que o serviço localize o usuário utilizado durante o processo de autenticação.

---

Exemplo de fluxo:

```text
Cadastro
   ↓
POST /register
   ↓
Usuário armazenado no banco
   ↓
POST /login
   ↓
Validação de e-mail e senha
   ↓
Login realizado
```

---

## Próximas melhorias

Algumas melhorias que podem ser implementadas futuramente:

* [ ] Criptografia de senhas com BCrypt
* [ ] Autenticação utilizando JWT
* [ ] Spring Security
* [ ] DTOs para entrada e saída de dados
* [ ] Validação de dados com Bean Validation
* [ ] Tratamento de erros de validação
* [ ] Padronização das respostas da API
* [ ] Testes unitários e de integração
* [ ] Melhorias na validação do login