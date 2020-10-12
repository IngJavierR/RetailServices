# Api Manager Netflix OSS

_Proyecto Api Manager basado en la suite de Netflix OSS (Zuul y Eureka)

## Comenzando 🚀

_Proyecto Api Manager basado en la suite de NetflixOSS_

* [Zuul](https://github.com/Netflix/zuul/wiki), para el ruteo de peticiones a los distintos endpoints registrados
* [Eureka](https://github.com/Netflix/eureka/wiki), para el descubrimiento de endpoints que en conjunto con Docker y Kubernetes proporcionan escalabilidad

### Pre-requisitos 📋

_Para la instalación se necesita:_

* [Maven 3.5+](https://maven.apache.org/download.cgi) 
* [Java 8+](https://www.java.com/es/download/)
* [Docker](https://docs.docker.com/install/) *opcional


### Instalación 🔧

_El proyecto fue realizado con maven para compilar ejecutar dentro de /ApiManagerNetflixOSS:_


```
mvn clean package
```

_Si se cuenta con Docker ejecutar_

```
docker build -t apimanager .
docker run -d -p 8000:8000 apimanager
```

_Si **No** se cuenta con Docker ejecutar_

```
java -jar target/gateway-1.0-SNAPSHOT.jar
```

_La aplicación por default levanta en el puerto 8000_

La url del sitio web de eureka es:  
http://localhost:8000/  

Los servicios para discovery deberán a puntar a la url:  
http://localhost:8000/eureka  


## Contributors

Javier Rodríguez  
[francisco.rodriguez@axity.com]    

## License

[MIT](https://opensource.org/licenses/MIT)