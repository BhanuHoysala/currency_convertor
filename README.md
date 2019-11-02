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


## Answers to the questions asked -

### Will it work with a 2 TB input file as well?
My implementation works for the 2 TB file as I'm not loading the entire file data to the memory.

### What happens if the input file has one malformed line towards the end of the file?
My implementation gracefully handles the malformed line at any lines of the input file

### Is it fine to encode the price value as a number in JSON? What kind of problems could arise?
I prefer to keep the numeric value in JSON as a string as it eliminates any loss of precision or ambiguity in the transfer.  
