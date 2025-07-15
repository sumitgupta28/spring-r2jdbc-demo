# Getting Started

### Reference Documentation

#### Start local database

```bash

docker pull postgres
docker volume create postgres_data
docker run --name postgres_container -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 -v postgres_data:/var/lib/postgresql/data postgres

```

