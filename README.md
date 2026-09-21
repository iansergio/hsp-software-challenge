## Encurtador de URLs

Desafio de desenvolvimento de um encurtador de URLs feito utilizando Java com Spring Boot. A ideia é receber uma URL longa, gerar um código curto único usando a base de ID da própria entidade (com Base62), redirecionar o usuário quando ele acesa o link e manter um contador de acessos.

## Stack

Java 21, Spring Boot, Spring Data JPA, Banco de Dados H2

## Funcionalidades

- Encurtar URL: Recebe uma URL original e retorna o objeto com o código encurtado.
- Redirecionamento: Busca pelo código encurtado, incrementa um contador de cliques e redireciona para o link original.
- Estatísticas: Retorna as informações da URL e a quantidade de cliques que ela recebeu.

## Como rodar o projeto

Clone o repositório:
```bash 
git clone https://github.com/seu-usuario/seu-repositorio.git
```

Entre na pasta do projeto:
```bash 
cd backend
```

Execute a aplicação via Maven:
```bash 
./mvnw spring-boot:run
```

A aplicação subirá por padrão na porta 8080.

## Endpoints da API
1. Encurtar URL
```json 
HTTP Method: POST
URL: /api/urls

{
  "url": "https://google.com"
}
```

2. Redirecionar para URL Original
```json
HTTP Method: GET
URL: /api/urls/{code}
```

3. Ver Estatísticas
```json
HTTP Method: GET
URL: /api/urls/{code}/stats

Resposta:

{
  "id": 1,
  "originalUrl": "https://google.com",
  "shortCode": "b",
  "clickCount": 5
}
```
