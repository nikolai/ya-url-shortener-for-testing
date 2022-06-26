# Simple url-shortener service
Simple realization of UrlShortener service (only add URL and redirect requests, and filesystem storage). 
Full version of [url-shortener repository](https://github.com/nikolai/urlshortener).

## Running
1. Run Spring Boot application `./mvnw spring-boot:run`
2. You need file [local JSON request file](./create-req.json) for this step. 
Send request `curl -X POST -d "@create-req.json" -H "Content-Type: application/json" http://localhost:8080/create`
This will receive short URL in a form of: 
```
{
    "shortUrl":"http://localhost:8080/hnh",
    "secretKey": "abc"
}
```
3. Send GET request with secretKey to `http://localhost:8080/admin/abc` and you will get usage statistics like that
```
{
    "creationDate": "2022-06-26T22:06:11.876+00:00",
    "usageCount": 1
}
```
4. Send DELETE request to `http://localhost:8080/admin/abc` to delete previously created redirection.