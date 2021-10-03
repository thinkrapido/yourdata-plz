
# Yourdata example project

## OpenApi

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## How to create development environment

### How to provision the database

*(Linux-based system)*

* First, install postgresql docker container `docker-compose -f docker/development.yml`
  * Prerequisite: Install docker-compose 

* Change to folder `liquibase`
* `cp liquibase.example.properties liquibase.properties`
* Provide API KEY in file `liquibase.properties`
    * you must obtain an api key from [https://hub.liquibase.com](https://hub.liquibase.com) to proceed
* Enter command `./liquibase register-changelog` and follow instructions
* Enter command `./liquibase update`

### How to ingest data set into the database

data set needs to be pushed to the server via file upload.

Send per POST method to `http://localhost:8080/api/v1/ingest`
file parameter with file name of csv data set via `form-data`.

See: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ingest-controller/handleFileUpload)

File content is of format *(Header is mandatory)*:

```
osm_id,ort,plz,bundesland
1104550,Aach,78267,Baden-Württemberg
1255910,Aach,54298,Rheinland-Pfalz
62564,Aachen,52062,Nordrhein-Westfalen
62564,Aachen,52064,Nordrhein-Westfalen
62564,Aachen,52066,Nordrhein-Westfalen
...
```

