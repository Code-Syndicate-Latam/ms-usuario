# Microservicio de Usuarios (ms-usuario)

Este proyecto es un microservicio encargado de la gestión de usuarios, roles, permisos y detalles de usuario. Funciona como un **Resource Server**, validando tokens JWT para proteger los endpoints.

## 🚀 Tecnologías Utilizadas e Instalación

Este proyecto requiere un entorno específico. A continuación, se detallan las tecnologías y cómo instalarlas en un entorno Linux (Ubuntu/Debian).

### 1. Java 21 (LTS)
El proyecto utiliza características modernas de Java 21.
* **Instalación:**
    ```bash
    sudo apt update
    sudo apt install openjdk-21-jdk -y
    ```
* **Verificación:** `java -version` (Debe mostrar "21").

### 2. Maven (Gestor de Dependencias)
Se encarga de compilar el proyecto y descargar librerías como Spring Boot, Hibernate y Lombok.
* **Instalación:**
    ```bash
    sudo apt install maven -y
    ```
* **Verificación:** `mvn -version` (Debe usar la JVM de Java 21).

### 3. Docker (Contenedores)
Necesario para ejecutar la base de datos PostgreSQL sin instalarla en el sistema operativo base.
* **Instalación:**
    ```bash
    sudo apt install docker.io -y
    sudo systemctl start docker
    sudo usermod -aG docker $USER
    # (Requiere cerrar sesión o usar 'newgrp docker' para aplicar cambios)
    ```

### 4. Stack de Desarrollo
Las siguientes librerías se descargan automáticamente vía Maven (`pom.xml`):
* **Spring Boot 3.5.6**
* **Spring Security** (Filtros JWT)
* **Spring Data JPA** (Hibernate)
* **Lombok** (Reducción de código boilerplate)
* **PostgreSQL Driver**

---

## 🗄️ Paso 1: Configuración de la Base de Datos

El microservicio requiere una instancia de PostgreSQL corriendo. Usaremos Docker para garantizar que la configuración sea idéntica para todos los desarrolladores.

Ejecuta el siguiente comando para levantar la base de datos:

```bash
docker run -d \
  --name ms-usuario-db \
  -p 5432:5432 \
  -e POSTGRES_DB=ms-usuario-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=admin \
  -v ms_usuario_data:/var/lib/postgresql/data \
  postgres:15
```

## 🔧 Paso 2: Configuración del Proyecto

Verifica que el archivo src/main/resources/application.properties coincida con la configuración de Docker.

```properties
    # Configuración del Servidor
    server.port=8082

    # Conexión a Base de Datos (Debe coincidir con el Docker)
    spring.datasource.url=jdbc:postgresql://localhost:5432/ms-usuario-db
    spring.datasource.username=postgres
    spring.datasource.password=admin
    spring.datasource.driver-class-name=org.postgresql.Driver

    # Configuración JWT
    jwt.secret=EstaEsUnaClaveSecretaMuySeguraYLoSuficientementeLargaParaHS256
    jwt.expiration=86400000
```
## ▶️ Paso 3: Compilación y Ejecución

Para evitar errores de caché o versiones antiguas, recomendamos una instalación limpia.

### 1. Compilar y empaquetar el proyecto:

```Bash
    mvn clean package -DskipTests
```
*(Esperar a ver el mensaje "BUILD SUCCESS").*

### 2. Ejecutar el microservicio:

```Bash
    java -jar target/*.jar
```
*Verificar que está corriendo: La terminal debe mostrar logs y detenerse en: Tomcat started on port(s): 8080 (http)*

## 🕵️‍♂️ Paso 4: Pruebas de Humo (Smoke Test)

Abre una nueva terminal y verifica que el servicio responde:

```Bash
    curl -v http://localhost:8082
```
Si recibes un 401 Unauthorized o 403 Forbidden, ¡El servicio funciona y la seguridad está activa!

Si recibes "Connection Refused", verifica que el puerto sea el 8080.

## 🔒 Seguridad y Pruebas (JWT)

Este microservicio valida el token en cada petición.
Cómo probar con Postman

1. Genera un token HS256 en jwt.io usando la misma jwt.secret del application.properties.

2. Realiza un GET a http://localhost:8082/api/v1/usuarios (ajusta según tus endpoints).

3. En la pestaña Authorization, elige Bearer Token y pega tu JWT.

## 📚 Documentación

1. Swagger UI: http://localhost:8082/swagger-ui.html

2. Docs JSON: http://localhost:8082/v3/api-docs