# Simple url-shortener service
Simple realization of UrlShortener service (only add URL and redirect requests, and filesystem storage). 
Full version of [url-shortener repository](https://github.com/nikolai/urlshortener).

## Running
1. Run Spring Boot application `./mvnw spring-boot:run`
2. You need file [local JSON request file](./create-req.json) for this step. Send request `curl -X POST -d "@create-req.json" -H "Content-Type: application/json" http://localhost:8080/create`
This will receive short URL in a form of: `{"shortUrl":"http://localhost:8080/hnh"}`
3. Send request `curl -v http://localhost:8080/hnh` and you will get HTTP 301 redirection to the URL defined in `longUrl` field of create-req.json file.

## Experimental performance problems
Modify [application properties file](./src/main/resources/application.properties) for this step. Send request `curl -X POST -d "@create-req.json" -H "Content-Type: application/json" http://localhost:8080/create`
1. change property show.memory.leakage=true for showing memory leakage problem
2. change property spring.datasource.hikari.maximumPoolSize=1 for showing lack of db connections