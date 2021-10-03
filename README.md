
# Yourdata example project

## How to use the application

After ingesting the dataset, you will see up to 30 results when querying.

Querying is done with this command:

```
http://localhost:<8080 or 9000>/api/v1/plz?search=<query-string>
```

The query string can have any characters. Hence, only digits make sense.

You can also search for partial zip codes starting from left.

In Germany zip codes are in a 5-digit form.

## How to Run the Application

After starting the database container, the database is empty.

Please ingest the data set first. *(See below)*

### In Development Mode

```
> docker-compose -f docker/development.yml up -d
> gradle bootRun
```

The application is running at port `8080`.

### In Production Mode

```
> gradle bootBuildImage --imageName=yourdata/plz-server
> docker-compose -f docker/development.yml up -d
```

The application is running at port `9000`.

## Creating Docker Container

* Create docker image `gradle bootBuildImage --imageName=yourdata/plz-server`

## OpenApi Documentation

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## How to ingest data set into the database

The data set needs to be pushed to the server via file upload.

Send per POST method to `http://localhost:8080/api/v1/ingest`
file parameter with file name of csv data set via `form-data`.

See: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ingest-controller/handleFileUpload)

You can find a sample data set in the folder <project>/data.

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

