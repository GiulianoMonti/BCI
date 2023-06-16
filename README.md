# Proyecto para Global Logic BCI

Proyecto Personal Banco BCI

Api parar registrar un usuario y poder loguearse

## Tech Stack

Java, Spring, H2 database, built using Gradle



## Installation

clonar el proyecto y hacer las peticiones utilizando postman o alguna aplicacion similar


    
## Examples


para hacer este proceso mas facil puede importar desde el postman el archivo bci.postman_collection.json el cual se encuentra en el root de este mismo proyecto.


##
##

la manera manual de hacerlo es la siguiente (copiar y pegar los request en postman,detallados abajo) 
```json

localhost:8080/api/v1/sign_up
{
  "username": "global",
  "email": "global@logic.com",
  "password": "globaLLog14",
  "phones": [
    {
      "number": 54113012,
      "cityCode": 7777,
      "countryCode": "ARG"
    }
  ]
}
        
localhost:8080/api/v1/login
{
  "username": "global",
  "password": "globaLLog14"
}
```
## IMPORTANTE
Recordar agregar el JWT Token generado en nuestro sign_up cuando hagamos el login, hay un filtro de seguridad activado para esto, en el caso de que el cliente quiera podemos desactivarlo

## Used By

This project is used by the following companies:

- GlobalLogic
- BCI


## Sequence Diagram
  ![Imgur](https://i.imgur.com/l71kRFo.png)

## 🚀 GIULIANO MONTI
