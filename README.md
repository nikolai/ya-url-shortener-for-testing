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
Some useful properties for performance problems demonstration:
0. show.memory.leakage
0. show.bad.synchronization - unnecessary synchronizations 
0. show.deadlock
0. spring.datasource.hikari.maximumPoolSize=1 - to show the lack of database connections
0. server.tomcat.max-threads=1 - to show the lack of working threads
0. internStrings=true - to eliminate string pollution problem