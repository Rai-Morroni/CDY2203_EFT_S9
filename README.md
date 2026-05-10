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

El proyecto incluye un archivo `docker-compose.yaml` que orquesta la Base de Datos, el Backend y el Frontend de manera automatizada. 

Para evaluar la aplicación en un entorno local, asegúrate de tener Docker Desktop ejecutándose y sigue estos pasos:

### Despliegue Automatizado
Abre una terminal en la raíz del proyecto (donde se encuentra el archivo `docker-compose.yaml`) y ejecuta el siguiente comando:

    docker-compose up -d --build

Este comando se encargará de:
1. Levantar el contenedor de **MySQL 8.0** en el puerto `3306` e inyectar el script de datos iniciales.
2. Construir y levantar el contenedor del **Backend** en el puerto `8080`.
3. Construir y levantar el contenedor del **Frontend** en el puerto `8081`.

### Accesos
Una vez que los contenedores estén en estado *healthy*, accede a la aplicación a través de tu navegador en:

* **Frontend (Interfaz Gráfica):** `http://localhost:8081`
* **Backend (API REST):** `http://localhost:8080`

### Ejecución Manual y Reportes de Cobertura (Opcional)
Si deseas levantar los servicios manualmente usando Maven o generar los informes de JaCoCo, puedes abrir una terminal en la carpeta `/backend` o `/frontend` y ejecutar:

    ./mvnw clean test jacoco:report