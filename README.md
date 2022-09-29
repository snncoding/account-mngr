[![Build Status](https://dev.azure.com/snnd/account-mngr/_apis/build/status/snncoding.account-mngr?branchName=main)](https://dev.azure.com/snnd/account-mngr/_build/latest?definitionId=1&branchName=main)
# account-mngr Rest Api For Existing Customer
----------------------------
## Spring Boot
---------------------------

### Summary

The assessment consists of an API to be used for opening a new “current account” of already existing customers.

#### Requirements
- The API will expose an endpoint which accepts the user information (customerID, initialCredit).
- Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.
- Also, if initialCredit is not 0, a transaction will be sent to the new account.
- Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

Applications url on heroku
https://account-mngr.herokuapp.com/swagger-ui.html


### Tech Info

- Java 8
- Spring Boot
- Spring Data JPA
- H2 In memory database
- JUnit 5
- Lombok
- Swagger
- Maven

### Run & Build
Application also runs on heroku https://account-mngr.herokuapp.com/swagger-ui.html#/ 
It may be closed because it is a free account. It is redeployed depending on the update of the main branch by the github workflow.

### Maven
```ssh
 $ cd /account-mngr
 $ mvn clean install
 $ mvn spring-boot:run
```
Swagger UI will be run on this url
http://localhost:8080/swagger-ui.html


