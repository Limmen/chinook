# REST-API for the Chinook database

[![Build Status](https://travis-ci.org/Limmen/chinook.svg?branch=master)](https://travis-ci.org/Limmen/chinook)

## Description

A rest-api for the Chinook database. Built with the Spring framework. Documentation for the database can be found in [doc](../../doc)

## Installation

1. `git clone https://github.com/Limmen/chinook`
2. `cd chinook/java_backend/chinook_rest/`
3. `mvn clean install` , downloads dependencies and runs tests
4. The application is packaged in a "fat jar" that includes dependencies on a tomcat server, run with: `java -jar target/chinook_rest-0.0.1-SNAPSHOT.jar`
5. The server is now up and running and accepts HTTP requests. The primary entry point is at:

   http://localhost:7777

## Resources

The whole chinook database is modelled as REST resources.
The URL for the resources are prefixed with "/resources/"

## Examples

### Request collection resources

    curl localhost:7777/resources/artists
    
Returns a JSON array of all artists

    curl localhost:7777/resources/tracks
    
Returns a JSON array of tracks

### Request single resources:

Artist:

    curl localhost:7777/resources/artists/1
    
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

Track:

    curl localhost:7777/resources/artists/1
    
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

## TODO

Currently the API is read-only. POST/PUT/DELETE is yet to be implemented.

## Documentation

[JavaDoc](javadoc/index.html)

## Copyright and license

* The repo:

The MIT License (MIT)

Copyright (c) 2016 Kim Hammar

* The database:

The MIT License (MIT)

Copyright (c) 2008-2014 Luis Rocha
