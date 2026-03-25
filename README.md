# 📺 Backend Java Developer - API de Shows e Episódios

## 📌 Visão Geral
Este projeto é uma aplicação **Java Spring Boot** que implementa uma API REST para gerenciamento de **shows de TV e episódios**, com integração externa à API **TVMaze**.  
O objetivo é fornecer endpoints para:
- Sincronizar shows e episódios a partir da API externa.
- Listar shows com paginação e filtro.
- Calcular a nota média de episódios por temporada.
- Garantir segurança com autenticação e autorização via **Spring Security + JWT**.

---

## 🏗️ Arquitetura em Camadas
A aplicação segue a arquitetura em camadas:

- **Controller**  
  Expõe os endpoints REST e recebe requisições HTTP.  
  Exemplo: `ShowController`, `EpisodeController`.

- **Service**  
  Contém a lógica de negócio, como sincronização de dados da API externa, cálculo de médias e validações.  
  Exemplo: `ShowService`, `EpisodeService`.

- **Repository**  
  Responsável pela persistência e acesso ao banco de dados PostgreSQL via **Spring Data JPA**.  
  Exemplo: `ShowRepository`, `EpisodeRepository`.

Fluxo:  
`Controller → Service → Repository → Banco de Dados`

---

## 🗄️ Fonte de Dados
- Banco de dados: **PostgreSQL 16.x**
- Migrações: **Flyway**
- Tabelas principais:
    - **user** (usuários e permissões)
    - **show** (informações de seriados)
    - **episode** (episódios vinculados a shows)

---

## 📖 Endpoints Disponíveis

### 🔄 Sincronizar Show
`POST /api/shows`
- Consome a API externa TVMaze.
- Persiste show + episódios.
- Evita duplicados.
- **Apenas ADMIN pode executar.**

---

### 📋 Listar Shows
`GET /api/shows`
- Paginação.
- Filtro por nome.
- Ordenação opcional.

---

### ⭐ Nota Média por Temporada
`GET /api/episodes/average`
- Agrupa episódios por temporada.
- Calcula média de rating.
- Ignora ratings nulos.
- Se todos forem nulos → retorna `0`.
- Se não houver episódios → retorna erro.

---

## ⚠️ Tratamento de Erros
- **Show não encontrado** → `404 Not Found`
- **Lista de episódios vazia** → `400 Bad Request`
- **Usuário sem permissão** → `403 Forbidden`
- **Erro interno** → `500 Internal Server Error`

---

## ⚙️ Configuração

### 1. Clonar o repositório
```bash
git clone https://github.com/wfsouza23/backend-java-developer.git
cd backend-java-developer
