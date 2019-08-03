# Proyecto Microservicios Retail Services

## Compilación
```bash
cd api-manager/
mvn clean package

cd order-service/
mvn clean package

cd product-service/
mvn clean package

cd user-service/
mvn clean package
```

Levantar BD  
Usuario: postgres  
```
docker run --name postgresdb -p 5432:5432 -e POSTGRES_DB=unbound -e POSTGRES_PASSWORD=postgres123 -d postgres
```

Actualizar BD
```
cd liquibase/
liquibase --changeLogFile="changesets/db.changelog-master.xml" update
```

Levantar servicios  
```
cd api-manager/
java -jar target/*.jar

cd order-service/
java -jar order-web/target/*.jar

cd product-service/
java -jar product-web/target/*.jar

cd user-service/
java -jar user-web/target/*.jar
```
Verificar Instalación
```
http://localhost:8000/
```
Probar
Desde Api Manager  
http://localhost:8000/api/orders/order/test  
http://localhost:8000/api/products/product/test  
http://localhost:8000/api/users/user/test  

Directo a los servicios  
http://localhost:8200/order/test  
http://localhost:8300/product/test  
http://localhost:8100/user/test  
