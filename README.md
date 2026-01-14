 # Dentify – Backend  
 
## 📌 Descripción

Backend para la gestión integral de una clínica odontológica. El sistema está orientado a facilitar la **administración de pacientes**, la **gestión de odontólogos** y la **reserva inteligente de turnos**, priorizando escalabilidad, reglas de negocio claras y buenas prácticas profesionales.

El proyecto es **personal**, pero fue diseñado con una visión **productiva y comercial**, pensando en su uso real por clínicas odontológicas.

Actualmente funciona como una **API REST**. La integración con frontend está planificada a corto plazo.  

## 🛠️ Tecnologías

* **Java 17**
* **Spring Boot 4.0.0**
* **Spring Data JPA**
* **Hibernate**
* **Spring Security (BCrypt)**
* **MySQL** / **H2** (testing)
* **Maven**
* **Lombok**
* **Swagger (OpenAPI + Swagger UI)**
* **Postman (colección de endpoints)**
  
  
## ✨ Features

* CRUD completo de **Pacientes**
* CRUD completo de **Turnos (Appointments)**
* Gestión de **Odontólogos**
* Manejo de **roles**:

  * Odontólogo
  * Secretario
* Encriptación de contraseñas con **BCrypt**
* Arquitectura **MVC**
* Uso de **DTOs (request / response)**
* Validaciones avanzadas de disponibilidad horaria
* Manejo de errores mediante **ResponseEntity** y códigos HTTP
* Documentación automática de la API con **Swagger**
  
## ⚙️ Proceso de desarrollo 

Proyecto desarrollado de forma individual, siguiendo un enfoque incremental y profesional:

1. Seguridad básica con encriptado de contraseñas (BCrypt)
2. Diseño de entidades y relaciones
3. Implementación de endpoints REST
4. Reglas de negocio críticas (agenda y horarios)

Se aplicaron buenas prácticas como:

* Patrón **MVC**
* Uso de **DTOs**
* **Inyección de dependencias**
* Separación clara de capas (controller, service, repository)
  

## 📚 Qué aprendí

* Implementación de **encriptación de contraseñas** con BCrypt
* Uso de **Swagger annotations** para documentar APIs REST
* Diseño de reglas de negocio reales.
* Manejo avanzado de fechas y horarios en Java
* Organización profesional de un proyecto Spring Boot

El mayor desafío técnico fue el cálculo dinámico de **bloques horarios disponibles**, considerando:

* Vigencia del horario
* Día de la semana
* Existencia del odontólogo
* Turnos previamente reservados
* Estado activo del schedule
  

## 🚀 Posibles mejoras

* Completar CRUDs en todas las entidades
* Implementar autenticación **JWT (stateless)**
* Historial clínico del paciente (notas, evolución)
* Mapeo de dientes afectados
* Generación de **PDFs**
* Contenerización con **Docker**
* Integración con frontend
  

## ▶️ Cómo correr el proyecto

### Requisitos

* **Java 17**
* **Maven**
* **MySQL** (opcional, se puede usar H2)


### Configuración de base de datos (MySQL)

En el archivo `application.properties`:

```properties
spring.application.name=odontologia

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.datasource.url=jdbc:mysql://localhost:3306/odontologica?useSSL=false&serverTimezone=UTC
spring.datasource.username=admin
spring.datasource.password=admin
```

> ⚠️ Asegurarse de que la base de datos `odontologica` exista previamente.


### Ejecución

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

La aplicación se levanta por defecto en:  


```
http://localhost:8080
```

 ### ⚠️ Consideraciones importantes

El sistema está diseñado para funcionar con **dos roles primarios**:

- **ODONTOLOGO**
- **SECRETARIO**

Para un uso correcto de la aplicación, **deben existir usuarios asociados a estos roles**.

####  Flujo esperado de uso
1. Crear usuarios con sus credenciales y asignarles uno de los roles definidos  
3. Iniciar sesión  
4. Acceder a las funcionalidades según el rol  

####  Reglas de negocio a tener en cuenta
- Para **obtener bloques horarios disponibles**, es obligatorio que:
  - El **odontólogo exista**
  - Existan **horarios (schedules)** previamente creados y activos para dicho odontólogo

- Para **listar pacientes asociados a un odontólogo**, es necesario que:
  - Existan tanto el **odontólogo** como los **pacientes**
  - Existan **turnos (appointments)** creados que relacionen al odontólogo con los pacientes

- Los **appointments** son la entidad central que vincula:
  - Odontólogo
  - Paciente
  - Fecha y bloque horario

Estas validaciones aseguran **coherencia de datos** y reflejan un **flujo realista de funcionamiento** dentro de una clínica odontológica.  


 

### Documentación de la API (Swagger)

Una vez levantada la aplicación, acceder a:

```
http://localhost:8080/swagger-ui/index.html
```

La documentación se genera automáticamente mediante **Swagger/OpenAPI annotations**.



### Pruebas con Postman

El proyecto incluye una **colección de Postman** para probar todos los endpoints.

📎  https://rzz-matias18-7061175.postman.co/workspace/Matias-Rodriguez's-Workspace~ab5a65d3-1bae-4284-83bf-2262438b3e42/collection/49727979-57d9cdf6-c5c2-4e27-b076-035d15abd4fa?action=share&source=copy-link&creator=49727979  





## 🏗️ Arquitectura

Estructura basada en MVC:

* `controller`
* `service`

  * `interfaces`
  * `impl`
* `repository`
* `model`
* `dto`

  * `request`
  * `response`
* `config`

Esta arquitectura favorece la **mantenibilidad**, **escalabilidad** y **claridad del código**.
