# Getting Started

This Repo shows

- How spring boot can connect with Postgres SQL in reactive way
- Flyway setup for Postgres

### Reference Documentation

#### Start local database

```bash

docker pull postgres
docker volume create postgres_book_data
docker run --name postgres_container -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 -v postgres_book_data:/var/lib/postgresql/data postgres

```

#### Test Curls

##### Create

```bash
curl --location 'http://localhost:8080/api/books' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "id":"1",
    "title":"Tests",
     "author":"asdasda"
}'
```

##### Update

```bash
curl --location --request PUT 'http://localhost:8080/api/books' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "id":"1111",
    "title":"Tests",
     "author":"asdasda"

}'
```

##### Get By Id

```bash
curl --location 'http://localhost:8080/api/books/1' 
```

##### Get ALL

```bash
curl --location 'http://localhost:8080/api/books' 
```

##### Delete

```bash
curl --location --request DELETE 'http://localhost:8080/api/books/1' 
```
