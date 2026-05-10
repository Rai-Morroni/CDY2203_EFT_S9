# Unidos por los Animales - Gestión Veterinaria (EFT)

Este repositorio contiene la solución desarrollada para la asignatura de **Seguridad y Calidad en el Desarrollo (CDY2203)** de Duoc UC. La aplicación es un sistema de gestión veterinaria integral que cumple con estándares modernos de seguridad, arquitectura desacoplada y aseguramiento de calidad mediante pruebas automatizadas y análisis de vulnerabilidades.

## 🚀 Contexto del Proyecto

El objetivo principal es asegurar la robustez de una aplicación web compuesta por un ecosistema de microservicios y vistas, garantizando que el código sea seguro, libre de vulnerabilidades críticas y altamente probado.

- **Estudiante:** Rainiero Morroni
- **Institución:** Duoc UC
- **Semana de Entrega:** Semana 9 - Evaluación Final Transversal (EFT)

## 🛠️ Tecnologías Utilizadas

### Backend
- **Framework:** Spring Boot 3.5.14
- **Seguridad:** Spring Security con autenticación basada en **JWT (JSON Web Token)**
- **Persistencia:** Spring Data JPA con MySQL
- **Documentación:** Springdoc OpenAPI / Swagger.

### Frontend
- **Framework:** Spring Boot con **Thymeleaf** para el motor de plantillas
- **Estilos:** CSS3 funcional
- **Comunicación:** RestTemplate para consumo de APIs protegidas.

### Infraestructura, Calidad y Seguridad
- **Contenedores:** Docker para el despliegue de la base de datos MySQL 8.0
- **Análisis SAST:** SonarCloud
- **Análisis DAST:** OWASP ZAP (Zed Attack Proxy)
- **Análisis SCA:** OWASP Dependency-Check Maven
- **Pruebas y Cobertura:** JUnit 5, MockMVC y JaCoCo

## 📊 Aseguramiento de Calidad y Seguridad (DevSecOps)

Se implementó un pipeline de seguridad bajo el enfoque *Shift-Left*, logrando los siguientes resultados en las evaluaciones dinámicas, estáticas y de componentes:

### 1. Seguridad de la Aplicación
- **DAST (OWASP ZAP):** 0 vulnerabilidades de riesgo Alto detectadas. Mitigación exitosa del OWASP Top 10 (XSS, Inyecciones SQL, etc.).
- **SAST (SonarCloud):** Quality Gate en estado "Passed" con calificación A en Seguridad (0 Open Issues y 0 Hotspots pendientes).
- **SCA (Dependency-Check):** Ausencia total de vulnerabilidades críticas o altas tras la actualización centralizada a Spring Boot 3.5.14.

### 2. Cobertura de Código (JaCoCo)
Se implementó una estrategia de pruebas unitarias y de integración siguiendo el patrón **Arrange-Act-Assert**, superando la regla estricta del 60% mínimo exigido:
- **Cobertura Backend:** **65%**
- **Cobertura Frontend:** **68%**

## 📂 Estructura del Repositorio

El proyecto se encuentra estructurado de forma modular para facilitar su mantenimiento:

- `/backend`: Lógica de negocio y APIs REST protegidas (Puerto 8080).
- `/frontend`: Interfaz de usuario y consumo de servicios (Puerto 8081).
- `/database`: Configuración de infraestructura mediante `Dockerfile` y scripts `init.sql`.

## ⚙️ Instalación y Ejecución

Para evaluar la aplicación en un entorno local, sigue estos pasos en orden:

### 1. Base de Datos (Docker)
Navega a la carpeta `/database` y ejecuta los siguientes comandos para crear y levantar el contenedor con el esquema preconfigurado:

    docker build -t mysql-db .
    docker run -d -p 3306:3306 --name bd-veterinaria -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=rootpassword mysql-db

### 2. Despliegue del Backend
Navega a la carpeta `/backend` y levanta el servicio API (se ejecutará en el puerto 8080):

    ./mvnw spring-boot:run

*(Nota en Windows: Utilizar `.\mvnw.cmd spring-boot:run`)*

### 3. Despliegue del Frontend
Abre una nueva terminal, navega a la carpeta `/frontend` y levanta el servicio de vistas (se ejecutará en el puerto 8081 para evitar conflictos):

    ./mvnw spring-boot:run

Accede a la aplicación a través de tu navegador en: `http://localhost:8081`

### 4. Ejecución de Pruebas y Reportes de Cobertura
Para validar la calidad del código y generar los informes de JaCoCo en cualquiera de las capas, ejecuta:

    ./mvnw clean test jacoco:report

*Los reportes detallados podrán ser visualizados abriendo el archivo `/target/site/jacoco/index.html`.*