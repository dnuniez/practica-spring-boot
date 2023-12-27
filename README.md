# practica-spring-boot
Practica en Spring Boot de microservicio para enriquecer albumes de fotos

#Propósito
La funcionalidad de la práctica cubre los siguientes puntos:
Un endpoint que ejecuta un algoritmo que enriquezca unos datos obtenidos a través de un API (2 endpoints), para posteriormente guardarlos en una base de
datos en memoria H2.
Otro endpoint que ejecuta un algoritmo que enriquezca unos datos obtenidos a través de un API (2 endpoints) sin posibilidad de utilizar base de datos y los
devuelva en la petición.
Otro endpoint con un GET de la base de datos en memoria H2.
Encuanto al origen de los datos, tenemos “albums” (https://jsonplaceholder.typicode.com/albums) y por otra las “photos” (https://jsonplaceholder.typicode.com/photos).

#Diseño y desarrollo
De cara a la eficiencia en el tiempo de ejecución, el algoritmo está desarrollado de tal forma que se lea una única vez de los dos endpoint de datos. Después se almacenan en estructura de ArrayList (no tenemos predefinido un número exacto de elementos), aprovechando también que los datos tienen un orden ya definido en el origen de datos. Se realiza mediante bucle recorriendo las estructuras el enriquecimiento y se guarda en H2 o se devuelve en la respuesta.

#Pruebas y revisión de calidad de código
Se realizan test unitarios con Junit y Mockito, garantizando un mínimo del 90% de cobertura, y también se realiza la revisión automática de código con Sonarlint. El entorno de desarrollo utilizado ha sido Eclipe. 
También se ha probado el microservicio mediante ThunderClient, se pueden invocar los siguientes endpoint una vez levantada la aplicación:
http://localhost:8080/api/enrichAndSaveAlbums
http://localhost:8080/api/enrichAndGetAlbums
http://localhost:8080/api/getAlbums

