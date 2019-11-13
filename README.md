## Instruction to bootstrap the Server

1. For code review/analysis please import as Gradle project.
2. To run the application either you run on the IDE or bootstrap the service from the root folder of the project
###### ./gradlew bootRun

The server will create the output.json at the current execution directory. It also deletes the previously created file (if any)

### Example URL

###### http://localhost:8080/currency/v1/convert?from=USD&to=EUR&amount=10

#### query params - "from" is the source currency, "to" is the target currency, "amount" is the price to be converted


## Instruction to execute the Client
After bootstrapping the server run the python script currency_onvertor_client.py

###### python currency_onvertor_client.py
