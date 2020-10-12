# Demo Microservicios con NetflixOSS
## Proyecto Microservicios

_Introducción al proyecto_

## Comenzando

_Preparación inicial y consideraciones_

## Pre-requisitos

_Para la instalación se necesita:_
* [Java 8](https://www.oracle.com/mx/java/technologies/javase/javase-jdk8-downloads.html)  
* [Maven](http://maven.apache.org/download.cgi)  
* [Docker](https://docs.docker.com/install/)  

## Compilción  

_Como compilar el proyecto_

´´´bash
mvn clean package -f api-manager
mvn clean package -f oauth-server
mvn clean package -f order-service
mvn clean package -f product-service
mvn clean package -f user-service
´´´

## Ejecución  

_Como ejecutar el proyecto_

java -jar api-manager/target/*.jar
java -jar oauth-server/oauth-web/target/*.jar
java -jar order-service/order-web/target/*.jar
java -jar product-service/product-web/target/*.jar
java -jar user-service/user-web/target/*.jar

Puerto ApiManager: 8000
Puerto OAuthServer: 8090
Puerto OrderService: 8020
Puerto ProductService: 8030
Puerto UserService: 8010


## Ejemplos de uso directo a servicios

### Invocar método ping/pong
**Authorize** Url [GET][http://localhost:8020/order/ping]  
* **Headers**
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhb...
```

### Invocar método users
**Authorize** Url [GET][http://localhost:8020/order/users]  
* **Headers**
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhb...
```

## Ejemplos de uso desde ApiManager

_Como probar el proyecto_

### Conseguir un Token
**Authorize** Url [POST][http://localhost:8000/api/oauth/oauthrs/authorize]  
* **Body**
```
{
	"user": "qwe",
	"password":"123",
	"redirectUri":"redirectUri",
	"appId": "app"
}
```
* **Response Ok** [Status 200]
```
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhb...",
    "token_type": "Bearer",
    "expires_in": 3600,
    "scope": ""
}
```

### Invocar método ping/pong
**Authorize** Url [GET][http://localhost:8000/api/order/order/ping]  
* **Headers**
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhb...
```

### Invocar método users
**Authorize** Url [GET][http://localhost:8000/api/order/order/users]  
* **Headers**
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhb...
```

### Contributors

Javier Rodríguez  
[hazelapd@gmail.com]   