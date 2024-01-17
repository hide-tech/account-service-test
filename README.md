Account-service for test

quickstart: write terminal command in root folder: docker compose up

when service started - check app out with next endpoints:
http://localhost:8001/swagger-ui/index.html - swagger docs
http://localhost:8001/openapi.json - docs with json
POST http://localhost:8001/app/v1/customers - create new customer
POST http://localhost:8001/app/v1/customers/{customerId}/accounts - create account for customer
PUT http://localhost:8001/app/v1/customers/{customerId} - update customer's data
GET http://localhost:8001/app/v1/customers?idnum={idNum} - get data about customer with accounts and transactions
POST http://localhost:8001/app/v1/customers/{customerId}/accounts/{accountId}/deposit - deposit account
POST http://localhost:8001/app/v1/customers/{customerId}/accounts/{accountId} - transfer between accounts

notes: I didn't use the dockerfile, instead I used paketo buildpacks, that integrated in spring boot(create layered docker image)
docker compose use image from container registry: for start app you don't need to build project(used github actions CI)