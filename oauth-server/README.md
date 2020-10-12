# OAuth2 Server

_Proyecto OAuthServer Axity desarrollado en Java8 con Springboot 2.0_

## Comenzando 🚀

_Proyecto OAuth2.0 que permite la administración de tokens para consulta de servicios_

Este proyecto incluye las operaciones básicas como:

* **Authorize**, que permite la autenticación de usuario y el retorno de un token con tiempo de vida configurable. 
* **Validate**, que valida que el token ingresado sea válido en estructura y tiempo vida.
* **Renew**, que a partir de un token existente caducado, genera uno nuevo

### Pre-requisitos 📋

_Para la instalación se necesita:_

* [Maven 3.5+](https://maven.apache.org/download.cgi) 
* [Java 8+](https://www.java.com/es/download/)
* [Docker](https://docs.docker.com/install/) *opcional


### Instalación 🔧

_El proyecto fue realizado con maven para compilar ejecutar dentro de /OAuthServer:_


```
mvn clean package
```

_Si se cuenta con Docker ejecutar_

```
docker build -t oauth .
docker run -d -p 8090:8090 oauth
```

_Si **No** se cuenta con Docker ejecutar_

```
java -jar oauth-web/target/oauth-web-1.0-SNAPSHOT.jar
```

_La aplicación por default levanta en el puerto 8090_
La url del sitio web para autenticación es:  
http://localhost:8090/oauth?client_id=app&redirect_uri=http://localhost:4200/  

Donde:  
* client_id: La aplicación a la que se le solicita permisos
* redirect_uri: La url a donde se solicita acceder una vez que se ha realizado la autenticación

El token es devuelto detro de una cookie

## Métodos Rest ⚙️

* **Authorize** Url [POST][http://localhost:8090/oauthrs/authorize]
**Body**
```
{
	"user": "qwe",
	"password":"1234",
	"redirectUri":"redirectUri",
	"appId": "app"
}
```
**Response Ok** [Status 200]
```
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhb...",
    "token_type": "Bearer",
    "expires_in": 3600,
    "scope": ""
}
```
**Response Fail** [Status 401]
```
{
    "errorCode": 0,
    "errorMessage": null,
    "userError": "Credenciales inválidas",
    "info": ""
}
```

* **Validate** Url [POST][http://localhost:8090/oauthrs/validate]
**Body**
```
{
	"user": "qwe",
	"token": "eyJ0eXAi..."
}
```
**Response Ok** [Status 200]

**Response Fail** [Status 401]
```
{
    "errorCode": 0,
    "errorMessage": "The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256",
    "userError": "Token Inválido",
    "info": ""
}
```
* **Renew** Url [POST][http://localhost:8090/oauthrs/renew]
**Body**
```
{
	"grant_type": "refresh_token",
	"refresh_token": "eyJ0eXAiOiJK...",
	"scope": ""
}
```
**Response Ok** [Status 200]
```
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJh...",
    "token_type": "Bearer",
    "expires_in": 3600,
    "scope": "",
    "refresh_token": "AyJ0wwWeriOiJKV1QiLCJhbGci..."
}
```
**Response Fail** [Status 401]
```
{
    "errorCode": 0,
    "errorMessage": "Invalid format",
    "userError": "Token Inválido",
    "info": ""
}
```

## Contributors

Javier Rodríguez  
[francisco.rodriguez@axity.com]    

## License

[MIT](https://opensource.org/licenses/MIT)