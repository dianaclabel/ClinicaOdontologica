
# Proyecto Final Backend

## Sistema de reserva de turnos 

---

Se desea implementar un sistema que permita administrar la reserva de turnos para una clínica odontológica. Esta necesita informatizar su operatoria. Por lo cual, te solicitan un sistema que debe cumplir con los siguientes requerimientos:

* **Administración de datos de odontólogos:** listar, agregar, modificar y eliminar odontólogos. Registrar apellido, nombre y matrícula de los mismos.

* **Administración de datos de los pacientes:** listar, agregar, modificar y eliminar pacientes. Al registrar un paciente los datos que se le solicitan son:
    * Apellido
    * Nombre
    * DNI
    * Fecha de alta 
    * Domicilio 
        * Calle
        * Número
        * Localidad
        * Provincia

    Además, le agregaremos un ID autoincremental tanto a los pacientes como a los domicilios.

    Se pide utilizar H2 como base de datos, aplicar el patrón DAO y testear con JUnit. Tener en cuenta que el modelado de clases debe contar con al menos dos clases: **PACIENTE** y **DOMICILIO**, con la consideración de que los pacientes podrán tener solo un domicilio.

    Crear solo una clase de servicio, PacienteService, y crear por cada entidad un DAO, es decir, DomicilioDAOH2 y PacienteDAOH2. Al guardar y buscar un paciente en PacienteDAOH2 deberás invocar el guardar y buscar de DomicilioDAOH2.

* **Registrar turno**: se tiene que poder permitir asignar a un paciente un turno con un odontólogo a una determinada fecha y hora. 


**Requerimientos técnicos:**
---

La aplicación debe ser desarrollada en capas:
* **Capa de entidades de negocio:** son las clases Java de nuestro negocio modelado a través del paradigma orientado a objetos.

* **Capa de acceso a datos (Repository)**: son las clases que se encargarán de acceder a la base de datos.

* **Capa de datos (base de datos)**: es la base de datos de nuestro sistema modelado a través de un modelo entidad-relación. Utilizaremos la base H2 por su practicidad. 
* **Capa de negocio**: son las clases service que se encargan de desacoplar el acceso a datos de la vista.

* **Capa de presentación**: son las pantallas web que tendremos que desarrollar utilizando el framework de Spring Boot MVC con los controladores y alguna de estas dos opciones: HTML+JavaScript para la vista.

**APIS:**
---

* Listar todos los odontólogos:
    
    Método: GET.

        PATH: /api/odontologos/all

* Buscar un odontólogo: 

    Método: GET.
        
        PATH: /api/odontologos/{id} 

* Guardar odontólogo:

    Método: POST

        PATH: /api/odontologos

* Eliminar odontólogo:
    
    Método: DELETE.

        PATH: /api/odontologos/{id} 

* Actualizar odontólogo:

    Método: PUT

        PATH: /api/odontologos
---

* Listar todos los pacientes:
    
    Método: GET.

        PATH: /api/pacientes/all

* Buscar un paciente: 

    Método: GET.
        
        PATH: /api/pacientes/{id} 


* Guardar paciente:

    Método: POST

        PATH: /api/pacientes

* Eliminar paciente:
    
    Método: DELETE.

        PATH: /api/pacientes/{id} 

* Actualizar paciente:

    Método: PUT

        PATH: /api/pacientes

---

* Listar todos los turnos:
    
    Método: GET.

        PATH: /api/turnos/all


* Guardar turno:

    Método: POST

        PATH: /api/turnos





**Vistas:**
---

Sección para listar los Turnos registrados
![Captura de pantalla 2024-09-13 024434](https://github.com/user-attachments/assets/ff8b1bb0-393e-4aca-b6d8-0462b795ca55)

Sección para listar los Pacientes registrados
![Captura de pantalla 2024-09-13 024443](https://github.com/user-attachments/assets/e8313fa4-165d-4598-85af-b37ce4d27cf0)

Sección para listar los Odontólogos registrados
![Captura de pantalla 2024-09-13 024451](https://github.com/user-attachments/assets/41c0dd50-c21d-4d05-b65e-38b6cd8d0c4e)

Modal para registrar un turno 
![Captura de pantalla 2024-09-13 024355](https://github.com/user-attachments/assets/4e6ba106-efe5-408a-8554-ff77d327a3f9)

Validaciones de campos vacios en el Modal para registrar un turno
![Captura de pantalla 2024-09-13 024321](https://github.com/user-attachments/assets/cd02e7dc-93cb-4dc2-80b8-b3f8d5e5af8c)

Validaciones de campos vacios en el Modal para registrar un paciente
![Captura de pantalla 2024-09-13 024046](https://github.com/user-attachments/assets/6ee50928-3699-4508-a322-18a62b47aaee)

Modal para registrar un paciente
![Captura de pantalla 2024-09-13 023725](https://github.com/user-attachments/assets/a53881b3-8c3b-41f8-b064-320a55ffbda9)

Modal para registrar un nuevo odontólogo
![Captura de pantalla 2024-09-13 024136](https://github.com/user-attachments/assets/4eaf38bf-cf98-49b5-9278-0b40866219a6)

Validaciones de campos vacios en el Modal para registrar un odontólogo
![Captura de pantalla 2024-09-13 024148](https://github.com/user-attachments/assets/838a5ce5-bf1c-45d5-b79d-74d24f426301)


**Realizado por:**
---
[@dianaclabel](https://github.com/dianaclabel) - Diana Clabel H. <br>
[@mysticBel](https://github.com/mysticBel) -  Maribel Maza A.  <br> <br>
Proyecto Final para la cursada de Backend Java, Digital House.



Muchas gracias.