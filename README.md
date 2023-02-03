# Proyecto para Global Logic BCI

Proyecto Personal Banco BCI

Api parar registrar un usuario y poder loguearse

## Tech Stack

Java, Spring, H2 database, built using Gradle



## Installation

clonar el proyecto y hacer las peticiones utilizando postman o alguna aplicacion similar


    
## Examples
enviar los siguientes post request

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

## Used By

This project is used by the following companies:

- GlobalLogic


## 🚀 GIULIANO MONTI
