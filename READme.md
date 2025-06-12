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