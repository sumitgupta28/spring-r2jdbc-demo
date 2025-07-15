#!/bin/bash

for i in {1..10000}; do
    curl -X POST http://localhost:8080/api/books -H 'Content-Type: application/json' -d '{"title":"My Book '"$i"'","author":"m1"}'
done