# Configuración
$APP_NAME = "springboot-app"
$DB_NAME = "postgres-db"
$MONGO_NAME = "mongo-db"
$NETWORK_NAME = "spring-network"

# Compilar la app con Maven en un contenedor
Write-Host "Compilando el proyecto con Maven en Docker..."
docker run --rm -v ${PWD}:/app -w /app maven:3.9-eclipse-temurin-21-alpine mvn clean package -DskipTests

# Verificar que el JAR se haya generado
$jarPath = Get-ChildItem -Path target -Filter *.jar | Select-Object -First 1
if (-not $jarPath) {
    Write-Error "Error: No se encontró ningún archivo JAR en la carpeta 'target'. Verifica que la compilación haya sido exitosa."
    exit 1
}

# Crear la red si no existe
if (-not (docker network ls --format "{{.Name}}" | Where-Object { $_ -eq $NETWORK_NAME })) {
    Write-Host "Creando red de Docker: $NETWORK_NAME"
    docker network create $NETWORK_NAME
}

# Construir la imagen de Spring Boot
Write-Host "Construyendo la imagen de la aplicación..."
docker build -t $APP_NAME .

# Verificar si PostgreSQL ya está en ejecución
if (docker ps -q -f name=$DB_NAME) {
    Write-Host "PostgreSQL ya está en ejecución. Deteniéndolo..."
    docker stop $DB_NAME
    docker rm $DB_NAME
}

# Iniciar contenedor de PostgreSQL
Write-Host "Iniciando PostgreSQL..."
docker run -d --name $DB_NAME --network $NETWORK_NAME -e POSTGRES_DB=ecommerce -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5433:5432 postgres:15

# Verificar si MongoDB ya está en ejecución
if (docker ps -q -f name=$MONGO_NAME) {
    Write-Host "MongoDB ya está en ejecución. Deteniéndolo..."
    docker stop $MONGO_NAME
    docker rm $MONGO_NAME
}

# Iniciar contenedor de MongoDB
Write-Host "Iniciando MongoDB..."
docker run -d --name $MONGO_NAME --network $NETWORK_NAME -p 27017:27017 mongo:6

# Iniciar la aplicación
Write-Host "Iniciando Spring..."
docker run --rm --network $NETWORK_NAME -p 8080:8080 --name $APP_NAME $APP_NAME