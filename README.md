# BankWebApp
Definex final project BankWebbApp is on live at 
#### http://definex.analysis4nft.com 

Clone repo: ```git clone https://github.com/onurcantasbas/BankWebApp.git```

To create a Docker container, navigate to the project directory containing the docker-compose.yml file and execute the code down below on powerShell:
```console
docker-compose up
```
then go to:
#### http://localhost:3000/

## Technologies
###### Back-end
- Java (version 17)
- Spring boot (Framework)
- PostgreSQL (Database)
- Maven (Build tool, Build artifact and dependency repo)
- Log4j (Log pattern: Client ip address, Triggered service & method, Username, Result success or fail).
- Spring Security (Authentication - Authorization)
  - Jwt (Token)
- JPA/Hibernate (ORM)
- Jakarta persistence (Entity relations)
- Javax validator (Entity field validations)
- Swagger UI (API Documentation)
- Lombok (Code generator)
- JUnit (Unit test)
- Twilio (Sms service - outer api)
- Monolithic architecture
###### Front-end
- React Js
- Axios (Promised-based HTTP client)
- Material UI (React component library)
- Validator (Input validation)
###### Deployment
- Amazon Web Service
   - EC2 Instance t2.micro (Virtual machine)
   - Route 53 (Dns zone service)
- Docker
   - Back-end image -> openjdk:17-oracle
   - Front-end image -> node : alpine
   - Database image -> postgres:alpine
- Windows IIS 

## Aws deploy scheme 
![awsScheme](https://user-images.githubusercontent.com/65484711/221343400-029d9960-4558-40b5-b667-4f438578ccc7.PNG)


## Swagger 
http://localhost:2023/swagger-ui/index.html    OR   http://definex.analysis4nft.com:2023/swagger-ui/index.html

![swaggerUI](https://user-images.githubusercontent.com/65484711/221343562-61d02136-b177-437d-b907-78530de5332a.PNG)
            

## Index
![localhost3000](https://user-images.githubusercontent.com/65484711/221343596-608bce52-8d62-4cb1-a874-d8715328b8bf.png)

## Structure
![finalScheme](https://user-images.githubusercontent.com/65484711/221385358-3b9bd7e4-be65-4d6b-8c40-fd4fd8a164b6.PNG)

