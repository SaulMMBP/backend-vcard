# VCard Backend project

This is a funny project in colaboration with my Frontend friend [La mushasha Cinthia](https://github.com/CinthiaSR)

This is an application that allows you to create custom bussiness/contact cards with a QR.

## DB

![Database Diagram](./db/vcard_db_diagram.png)

## REST API

### Dependencies

-   Spring Framework
-   Springboot
-   Spring Web
-   Spring Hateoas
-   Spring JPA
-   Spring Security
-   Spring Resource Server
-   MySQL Driver
-   Lombok
-   Okapi Barcode

### Endpoints

| Método | Path                          | Descripción                                                        |
| ------ | ----------------------------- | ------------------------------------------------------------------ |
| GET    | /general-information/{userId} | Obtiene los totales de contactos y tarjetas creadas por el usuario |

# References

You can find the amazing Frontend at [CinthiaSR/Qr_Card](https://github.com/CinthiaSR/Qr_Card)
