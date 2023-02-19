# Spring Boot URL Shortener #
This project demonstrates the use of Spring Boot 2.0 and Redis to build a URL shortener api.

### Prerequisites
  * Redis running locally
  * host: localhost
  * port: 6379

### Swagger-ui
'http://localhost:8081/swagger-ui/index.html'

### Execution

```
java -jar spring-url-shortner.jar
```

### API Details
#### Create Short URL:
`http://localhost:8081/api/rest/url

Request body:
```JSON
{
    "url": "https://github.com"
}
```
Response body:
```JSON
{
     "id": "ee30c62f",
     "url": "https://github.com",
     "created": "2023-02-19T15:28:51.6437993"
}
```
Response codes:

| HTTP Status | Description           |
|-------------|-----------------------|
| 200         | successful operation  |
| 500         | internal server error |

#### Retrieve Original URL:
`http://localhost:8085/api/rest/url/{id}`

Response body:
```JSON
{
    "id": "94717296",
    "url": "https://www.gmail.com",
    "created": "2018-12-02T14:11:26.887"
}
```
Response codes:

| HTTP Status | Description           |
|-------------|-----------------------|
| 200         | successful operation  |
| 404         | not found             |
| 500         | internal server error |