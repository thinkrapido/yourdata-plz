
# Yourdata example project

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

*(tbd)*