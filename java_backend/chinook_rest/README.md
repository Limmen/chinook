# REST-API for the Chinook database

[![Build Status](https://travis-ci.org/Limmen/chinook.svg?branch=master)](https://travis-ci.org/Limmen/chinook)

## Description

A rest-api for the Chinook database. Built with the Spring framework. Documentation for the database can be found in [doc](../../doc)

Table of Contents
=================

  * [REST-API for the Chinook database](#rest-api-for-the-chinook-database)
    * [Description](#description)
    * [Installation](#installation)
      * [Docker](#docker)
        * [Bild Spring Docker image with maven](#bild-spring-docker-image-with-maven)
        * [Build Postgres Docker image](#build-postgres-docker-image)
        * [Start Postgres container](#start-postgres-container)
        * [Start the Spring container linked](#start-the-spring-container-linked)
        * [Stop the containers](#stop-the-containers)
        * [Delete containers](#delete-containers)
        * [Watch all running containers](#watch-all-running-containers)
        * [Watch logs of running containers](#watch-logs-of-running-containers)
    * [Resources](#resources)
    * [Examples](#examples)
      * [Request collection resources](#request-collection-resources)
      * [Request single resources:](#request-single-resources)
        * [Artist:](#artist)
        * [Track:](#track)
      * [Add resources](#add-resources)
      * [Delete resources](#delete-resources)
      * [Update single resource](#update-single-resource)
      * [Update collection resource](#update-collection-resource)
      * [Request filtered resource](#request-filtered-resource)
      * [Sort resources on given parameter](#sort-resources-on-given-parameter)
        * [Ascending order](#ascending-order)
        * [Descending order](#descending-order)
      * [Error responses](#error-responses)
        * [Invalid path:](#invalid-path)
        * [Requesting a resource that doesn't exists:](#requesting-a-resource-that-doesnt-exists)
        * [Using a HTTP method (verb) that is not supported:](#using-a-http-method-verb-that-is-not-supported)
        * [Using a media type that is not supported:](#using-a-media-type-that-is-not-supported)
        * [Invalid query string:](#invalid-query-string)
    * [Documentation](#documentation)
    * [Copyright and license](#copyright-and-license)


## Installation

1. `$ git clone https://github.com/Limmen/chinook`
2. `$ cd chinook/java_backend/chinook_rest/`
3. `$ mvn clean install` , downloads dependencies and runs tests
4. The application is packaged in a "fat jar" that includes dependencies on a tomcat server, run with: `java -jar target/chinook_rest-0.0.1-SNAPSHOT.jar`
5. The server is now up and running and accepts HTTP requests. The primary entry point is at:

   http://localhost:7777
   
### Docker

#### Bild Spring Docker image with maven

    $ mvn package docker:build

#### Build Postgres Docker image

    $ sudo docker build -t chinook_rest-postgres src/main/docker/postgres/

#### Start Postgres container

    $ sudo docker run --name chinook_rest-postgres -d chinook_rest-postgres
    
#### Start the Spring container linked

    $ docker run --name chinook_rest --link chinook_rest-postgres:postgres -p 7777:7777 -d chinook_rest

#### Stop the containers

    $ docker stop chinook_rest

    $ docker stop chinook_rest-postgres

#### Delete containers

    $ docker rm chinook_rest
    
    $ docker rm chinook_rest-postgres

#### Watch all running containers 

    $ docker ps -a
    
#### Watch logs of running containers

    $ docker logs chinook_rest
    
    $ docker logs chinook_rest-postgres

## Resources

The whole chinook database is modelled as REST resources.
The URL for the resources are prefixed with "/resources/"

![Chinook database model](./../../doc/data_model.png?raw=true "Chinook database model")

## Examples

### Request collection resources

    $ curl localhost:7777/resources/artists
    
Returns a JSON array of all artists

    $ curl localhost:7777/resources/tracks
    
Returns a JSON array of tracks

### Request single resources:

#### Artist:

    $ curl localhost:7777/resources/artists/1
    
Returns artist with id 1:

    {
        "artist" : {
            "artistId" : 1,
            "name" : "AC/DC"
        },
        "_links" : {
            "self" : {
                "href" : "http://localhost:7777/resources/artists/1"
            }
        }
    }

#### Track:

    $ curl localhost:7777/resources/tracks/1
    
Returns track with id 1:

    {
      "track" : {
        "trackId" : 1,
        "name" : "For Those About To Rock (We Salute You)",
        "albumId" : 1,
        "mediaTypeId" : 1,
        "genreId" : 1,
        "composer" : "Angus Young, Malcolm Young, Brian Johnson",
        "milliseconds" : 343719,
        "bytes" : 11170334,
        "unitPrice" : 0.99
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:7777/resources/tracks/1"
        },
        "album" : {
          "href" : "http://localhost:7777/resources/albums/1"
        },
        "mediatype" : {
          "href" : "http://localhost:7777/resources/mediatypes/1"
        },
        "genre" : {
          "href" : "http://localhost:7777/resources/genres/1"
        }
      }
    }

### Add resources

    $ curl -H "Content-Type: application/json" -X POST -d '{"name":"newArtist"}' http://localhost:7777/resources/artists
    
Returns the newly created resource: 
    
    {
      "artist" : {
        "artistId" : 276,
        "name" : "newArtist"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:7777/resources/artists/276"
        }
      }
    }

### Delete resources

    $ curl -X DELETE  http://localhost:7777/resources/artists/276

Returns the deleted resource:

    
    {
      "artist" : {
        "artistId" : 276,
        "name" : "newArtist"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:7777/resources/artists/276"
        }
      }
    }

### Update single resource

    $ curl -H "Content-Type: application/json" -X PUT -d '{"name":"updatedName"}' http://localhost:7777/resources/artists/276
    
Returns the updated resource:
    
    {
      "artist" : {
        "artistId" : 276,
        "name" : "updatedName"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:7777/resources/artists/276"
        }
      }
    }

### Update collection resource

    $ curl -H "Content-Type: application/json" -X PUT -d '[{"name":"testNames1"}, {"name":"testNames2"}]' http://localhost:7777/resources/artists

Returns the updates resource:

    {
      "artists" : [ {
        "artist" : {
          "artistId" : 1,
          "name" : "testNames1"
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:7777/resources/artists/1"
          }
        }
      }, {
        "artist" : {
          "artistId" : 2,
          "name" : "testNames2"
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:7777/resources/artists/2"
          }
        }
      } ]
    }
    
### Request filtered resource

    $ curl localhost:7777/resources/artists?name=AC/DC
    
Returns entrys that fulfills the filter:

    {
      "artists" : [ {
        "artist" : {
          "artistId" : 1,
          "name" : "AC/DC"
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:7777/resources/artists/1"
          }
        }
      } ]
    }

Spaces encoding in URL:
     
    $ curl localhost:7777/resources/tracks?composer=Philip%20Glass
    
Returns:
    
    {
      "tracks" : [ {
        "track" : {
          "trackId" : 3503,
          "name" : "Koyaanisqatsi",
          "albumId" : 347,
          "mediaTypeId" : 2,
          "genreId" : 10,
          "composer" : "Philip Glass",
          "milliseconds" : 206005,
          "bytes" : 3305164,
          "unitPrice" : 0.99
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:7777/resources/tracks/3503"
          },
          "album" : {
            "href" : "http://localhost:7777/resources/albums/347"
          },
          "mediatype" : {
            "href" : "http://localhost:7777/resources/mediatypes/2"
          },
          "genre" : {
            "href" : "http://localhost:7777/resources/genres/10"
          }
        }
      } ]
    }

### Sort resources on given parameter

#### Ascending order

    $ curl localhost:7777/resources/artists?sort=+artistId

#### Descending order

    $ curl localhost:7777/resources/artists?sort=-artistId

### Error responses

#### Invalid path:

    $ curl localhost:7777/wrong_path

Returns a JSON response on the following format:
    
    {
        "timestamp":1459003058410,
        "status":404,
        "error":"Not Found",
        "message":"No message available",
        "path":"/wrong_path"
    }

#### Requesting a resource that doesn't exists:

    $ curl localhost:7777/resources/artists/999999

Returns a JSON response on the following format:

    {
        "timestamp":1461499523041,
        "status":404,
        "error":"Not Found",
        "exception":"org.springframework.dao.EmptyResultDataAccessException",
        "message":"Resource not Found",
        "path":"/resources/artists/999999"
    }

#### Using a HTTP method (verb) that is not supported:

    $ curl -X POST  http://localhost:7777/

Returns a JSON response on the following format:

    {
        "timestamp":1461499887935,
        "status":405,
        "error":"Method Not Allowed",
        "exception":"org.springframework.web.HttpRequestMethodNotSupportedException",
        "message":"Request method 'POST' not supported",
        "path":"/"
    }

#### Using a media type that is not supported:

    $ curl -H "Content-Type: text/xml" -X POST -d '<XML>data</XML>' http://localhost:7777/resources/artists

Returns a JSON response on the following format:
    
    {
        "timestamp":1461500261831,
        "status":415,
        "error":"Unsupported Media Type",
        "exception":"org.springframework.web.HttpMediaTypeNotSupportedException",
        "message":"Content type 'text/xml' not supported",
        "path":"/resources/artists"
    }
    
#### Invalid query string:

    $ curl localhost:7777/resources/artists?sort=-not_found_parameter

Returns a JSON response on the following format:
    
    {
        "timestamp":1461593056398,
        "status":400,
        "error":"Bad Request",
        "exception":"limmen.business.services.exceptions.SortException",
        "message":"Invalid Query String",
        "path":"/resources/artists"
    }
## Documentation

[JavaDoc](javadoc/index.html)

## Copyright and license

* The repo:

The MIT License (MIT)

Copyright (c) 2016 Kim Hammar

* The database:

The MIT License (MIT)

Copyright (c) 2008-2014 Luis Rocha
