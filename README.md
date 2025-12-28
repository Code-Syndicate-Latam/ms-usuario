# Microservicio de Usuarios (ms-usuario)

Este proyecto es un microservicio encargado de la gestión de usuarios, roles, permisos y detalles de usuario. Funciona como un **Resource Server**, validando tokens JWT para proteger los endpoints.

## 🚀 Tecnologías Utilizadas

*   **Java 21**
*   **Spring Boot 3.5.6**
*   **Spring Security** (con filtro JWT personalizado)
*   **Spring Data JPA** (Hibernate)
*   **PostgreSQL** (Base de datos)
*   **Lombok**
*   **Docker** (Para la base de datos)
*   **Maven**

## ⚙️ Pre-requisitos

*   Java JDK 21 instalado.
*   Maven instalado.
*   Docker instalado y corriendo.

## 🗄️ Configuración de la Base de Datos

El proyecto utiliza PostgreSQL. Puedes levantar una instancia rápidamente utilizando Docker con el siguiente comando:

```bash
docker run -d \
  --name ms-usuario-db \
  -p 5432:5432 \
  -e POSTGRES_DB=db_user \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=admin \
  -v ms_usuario_data:/var/lib/postgresql/data \
  postgres:15
```

Este comando:
*   Crea un contenedor llamado `ms-usuario-db`.
*   Expone el puerto `5432`.
*   Configura el usuario `postgres` y contraseña `admin`.
*   Crea un volumen persistente `ms_usuario_data` para no perder los datos.

## 🔧 Configuración del Proyecto

El archivo de configuración se encuentra en `src/main/resources/application.properties`.

### Variables Clave
*   **Puerto del Servidor:** `8082`
*   **Base de Datos:** Conectada a `localhost:5432/db_user`.
*   **JWT Secret:** La clave secreta utilizada para validar la firma de los tokens entrantes.

```properties
# Ejemplo de configuración JWT
jwt.secret=EstaEsUnaClaveSecretaMuySeguraYLoSuficientementeLargaParaHS256
jwt.expiration=86400000
```

> **Nota:** Asegúrate de que la `jwt.secret` coincida con la del servicio que genera los tokens (Auth Server).

## ▶️ Ejecución

1.  Clona el repositorio.
2.  Asegúrate de que la base de datos Docker esté corriendo.
3.  Ejecuta el proyecto con Maven:

```bash
mvn spring-boot:run
```

El servicio estará disponible en: `http://localhost:8082`

## 🔒 Seguridad (JWT)

Este microservicio actúa como un **Resource Server**. No genera tokens (no tiene endpoint de login), pero valida que las peticiones entrantes tengan un token válido.

### Cómo probar con Postman
1.  Genera un token JWT válido (puedes usar [jwt.io](https://jwt.io/) para simularlo).
    *   **Algoritmo:** HS256.
    *   **Secret:** Usa la misma clave que está en `application.properties`.
    *   **Payload:** Asegúrate de que el campo `sub` (subject) sea un email que exista en tu base de datos local.
2.  En Postman, realiza una petición a un endpoint protegido (ej. `GET /api/v1/usuarios`).
3.  En la pestaña **Authorization**, selecciona **Bearer Token** y pega el token generado.

## 📚 Documentación API (Swagger)

Una vez iniciada la aplicación, puedes consultar la documentación interactiva de la API en:

`http://localhost:8082/swagger-ui.html`
`http://localhost:8082/v3/api-docs`

## 📂 Estructura del Proyecto

*   `controller`: Controladores REST (Endpoints).
*   `entities`: Entidades JPA (Modelos de BD).
*   `repository`: Interfaces de acceso a datos (DAO).
*   `service`: Lógica de negocio.
*   `jwt`: Utilidades y filtros para seguridad JWT.
*   `config`: Configuraciones de Spring (Security, Swagger, etc).
