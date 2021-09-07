# Servidor Web con Framework IoC AREP 

En este laboratorio se busco construir un servidor(tipo Apache) usando solo Java y librerías para manejo de la red, que soporta múltiples solicitudes seguidas (no concurrentes). El servidor está desplegado en HEROKU y retorna todos los archivos solicitados, incluyendo páginas html e imágenes. También, el servidor cuenta con un framework IoC para la construcción de aplicaciones web a partir de POJOS.




## Compilar programa
- Para construir el programa y ejecutar todas las fases de un repositorio maven
```
mvn clean install
``` 
- Para correr los tests
```
mvn test
```
- Para generar la documentación
```
mcn javadoc:javadoc o mvn javadoc:jar (generar jar)
```

## Ejecutar Programa
Para probar el programa localmente, ejecutar y dirigirse a
```
http://localhost:35000
```

## Algunos de sus usos

Este servidor tiene la capacidad de entender recursos .html , .js ,.png , .css. Algunos ejemplos se pueden ver localmente en 
### LOCAL

* *HTML* ``` http://localhost:35000/index.html ```
* *PNG* ``` http://localhost:35000/imagen1.png ```
* *SIN RECURSO* ``` http://localhost:35000 ```

### HEROKU

Usando POJO Controller se puede acceder a:

### LOCAL
* ``` http://localhost:35000/nspapp/sum``` muestra el resultado de una suma.
* ```http://localhost:35000/nspapp/sentence``` muestra una frase en pantalla.

### HEROKU 


## Enlace Heroku
[![Clientes y Servicios ](https://www.herokucdn.com/deploy/button.png)](https://clientesyservicios-arep.herokuapp.com/index.html)

## Licencia
Ver licencia en LICENCE.txt para más detalles.