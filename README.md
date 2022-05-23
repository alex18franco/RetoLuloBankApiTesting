# Introducción 
Este proyecto contiene la automatización de 4 casos de prueba con el objetivo de verificar el funcionamiento de los métodos principales del api https://dummy.restapiexample.com que corresponden a las funcionalidades de consultar empleados, consultar empleado según su identificación, registrar nuevos empleados y eliminar empleados existentes.

GET
http://dummy.restapiexample.com/api/v1/employees
POST
http://dummy.restapiexample.com/api/v1/create
GET
http://dummy.restapiexample.com/api/v1/employee/1
DELETE
http://dummy.restapiexample.com/api/v1/delete/2

# Software utilizado
Para realizar el proyecto se utilizaron las herramientas de
1.  Gestor de dependencias apache-maven-3.8.5
2.  Java versión: 11.0.15
3.  Framework Cucumber
4.  Framework Serenity BDD en su última versión 3.2.5
5.  Patrón de diseño ScreenPlay BDD

![image](https://user-images.githubusercontent.com/57577125/169853389-abf8854d-c59d-4e49-893a-7997a28e8259.png)

6.  Librería Hamcrest para realizar las validaciones de los escenarios

# Como ejecutar el proyecto
Se debe tener instalado en el ambiente en dónde se va a realizar la ejecución:
1.  La versión 11 de java
2.  Apache Maven
3.  Se abre una ventana de cmd en la raiz de la carpeta RetoLuloBankApiTesting\LuloBankApiRest 
4.  Ejecutar el comando sin comillas "mvn clean verify -e serenity:aggregate" para generar el informe de serenity report y ver el resumen de la ejecución

# Como veo el resultado de las pruebas
1. Se debe ejecutar el comando del paso 4 del apartado anterior
2. Ejecutar el archivo index.html de la ruta RetoLuloBankApiTesting/RetoLuloBankApiTesting/LuloBankApiRest/target/site/serenity/index.html
![image](https://user-images.githubusercontent.com/57577125/169853808-81395dfe-87c2-45ec-8496-58e885801f8c.png)

# Observaciones
Se observa que la api es muy inestable en algunas ocasiones no responde correctamente (mensaje de respuesta "Too Many Requests"), entonces puede salir alguna excepción al momento de la ejecución, que se visualizara en el archivo index.html

# Referencias
1. http://dummy.restapiexample.com/   --Documentación del api utilizada
2. https://cucumber.io/docs/cucumber/configuration/   --Documentación de Cucumber para manejo de datos por modelos
