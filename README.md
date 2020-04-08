# Simple url-shortener service
Simple realization of UrlShortener service (only add URL and redirect requests, and filesystem storage). 
Full version of [url-shortener repository](https://github.com/nikolai/urlshortener).

## Running
1. Run Spring Boot application `./mvnw spring-boot:run`
2. You need file [local JSON request file](./create-req.json) for this step. 
Send request `curl -X POST -d "@create-req.json" -H "Content-Type: application/json" http://localhost:8080/create`
This will receive short URL in a form of: `{"shortUrl":"http://localhost:8080/hnh"}`
3. Send request `curl -v http://localhost:8080/hnh` and you will get HTTP 301 redirection to the URL defined in `longUrl` field of create-req.json file.

## Dynamic configuration
1. Configuration described below can be changed on the fly with /config context.
Just send HTTP POST to localhost:8080/config with JSON payload like:
```json
{
   "configs" : {
       "internStrings":"true",
       "workThreadSleepTimeMs":"35"
   }
}
```

## Experimental performance problems
Modify [application properties file](./src/main/resources/application.properties) for this step. Send request `curl -X POST -d "@create-req.json" -H "Content-Type: application/json" http://localhost:8080/create`
Some useful properties for performance problems demonstration:
 1. show.memory.leakage - shows OutOfMemoryError
 2. show.bad.synchronization - unnecessary synchronizations 
 3. show.deadlock - shows classical deadlock with number of client threads >1
 4. spring.datasource.hikari.maximumPoolSize=1 - to show the lack of database connections
 5. server.tomcat.max-threads=1 - to show the lack of working threads
 6. internStrings=true - to eliminate string pollution problem
 7. showOldGenEating - to show bad OldGen utilisation
 8. workThreadSleepTimeMs - emulates work delays with sleeping