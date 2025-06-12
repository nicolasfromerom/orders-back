# Pedidos - Proyecto Spring Boot

Este proyecto es una aplicación desarrollada con **Spring Boot**, que utiliza **PostgreSQL** como base de datos relacional y **MongoDB** como base de datos no relacional. A continuación, se detallan los pasos para levantar el proyecto utilizando el archivo `start-app.ps1`.

## Requisitos previos

1. **Docker** instalado.
2. Puertos disponibles:
    - `8080` para la aplicación.
    - `5432` en el contenedor, y expuesto en el `5433` para PostgreSQL. 
    - `27017` para MongoDB.

## Configuración del proyecto

El archivo `application.properties` ya está configurado para conectarse a las bases de datos con los siguientes parámetros:

- **PostgreSQL**:
    - URL: `r2dbc:postgresql://postgres-db:5432/ecommerce`
    - Usuario: `postgres`
    - Contraseña: `password`
- **MongoDB**:
    - URI: `mongodb://mongo-db:27017/ecommerce`
- Puerto de la aplicación: `8080`

## Pasos para levantar el proyecto

### 1. Ejecutar el script `start-app.ps1`

Ejecuta el siguiente comando en PowerShell desde la raíz del proyecto para compilar, construir e iniciar la aplicación junto con las bases de datos:

```powershell
.\start-app.ps1
```

### 2. Crear una orden

Para crear una nueva orden, puedes enviar una solicitud `POST` al endpoint localhost:8080/api/orders. A continuación, se muestra un ejemplo del cuerpo de la solicitud en formato JSON:

```json
{
    "customerId": 1,
    "productId": 1,
    "quantity": 1
}
```
### 3. Editar una orden

Para crear una nueva orden, puedes enviar una solicitud `PUT` al endpoint localhost:8080/api/orders/{id}. Reemplaza `{id}` con el ID de la orden que deseas editar. A continuación, se muestra un ejemplo del cuerpo de la solicitud en formato JSON:

```json
{
    "customerId": 1,
    "productId": 1,
    "quantity": 2
}
``` 
### 4. Consultar una orden
Para consultar una orden específica, puedes enviar una solicitud `GET` al endpoint `localhost:8080/api/orders/{id}`. Reemplaza `{id}` con el ID de la orden que deseas consultar.

### 5. Consultar todas las órdenes
Para consultar todas las órdenes, puedes enviar una solicitud `GET` al endpoint `localhost:8080/api/orders`.

### 6. Eliminar una orden
Para eliminar una orden específica, puedes enviar una solicitud `DELETE` al endpoint `localhost:8080/api/orders/{id}`. Reemplaza `{id}` con el ID de la orden que deseas eliminar.