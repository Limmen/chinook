# JavaScript Frontend for consuming REST-API for the chinook database

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


## Description

A React application for consuming a REST-API for the chinook database.
The application was initiated with a template from: [generator-react-webpack](https://github.com/newtriks/generator-react-webpack)

Tools used:

  * Webpack
    * Module bundler. Bundles all necessary javascript files to a bundle dist/assets/app.js

  * Babel
    * JavaScript compiler that transforms EcmaScript2015 code to JavaScript code that is compatible with the browser. Enables us to write the application in ES2015.
  
  * Mocha
    * JavaScript test framework.
  
  * Karma
    * JavaScript test runner. Allows for executing code in real browsers.
  
  * ESLint
    * JavaScript linting utility (a type of static analysis).


## Screenshots

![Screen 1](./screenshots/screen_1.png?raw=true "Screen 1")

![Screen 2](./screenshots/screen_2.png?raw=true "Screen 2")

## Installation

1. `git clone https://github.com/Limmen/chinook`
2. `cd chinook/react_frontend/chinook_consumer/`
3. `npm install`
4. `npm start`

## Docker

### Build application image (node) with docker

```bash
$ docker build -t chinook_consumer .
```

### Start application container

```bash
$ docker run --name chinook_consumer -p 3000:3000 -d chinook_consumer
```

### Stop the container

```bash
$ docker stop chinook_consumer
```

### Delete container

```bash
$ docker rm chinook_consumer
```

### Watch all running containers 

```bash
$ docker ps -a
```

###  Watch logs of running container

```bash
$ docker logs chinook_consumer
```

## Generating new components with yeoman
```bash
$ yo react-webpack:component my/namespaced/components/name
```

The above command will create a new component, as well as its stylesheet and a basic testcase.

## Generating new stateless functional components with yeoman
```
$ yo react-webpack:component my/namespaced/components/name --stateless
```

## Usage
The following commands are available in the project:
```bash
# Start for development
$ npm start # or
$ npm run serve

# Start the dev-server with the dist version
$ npm run serve:dist

# Just build the dist version and copy static files
$ npm run dist

# Run unit tests
$ npm test

# Lint all files in src (also automatically done AFTER tests are run)
$ npm run lint

# Clean up the dist directory
$ npm run clean

# Just copy the static assets
$ npm run copy

# run  for deployment without dev-server
$ npm run deploy
```
### Naming Components
Uppercase for component file naming e.g. [Component.js](https://github.com/petehunt/ReactHack/tree/master/src/components).

### Modules
Each component is a module and can be required using the [Webpack](http://webpack.github.io/) module system. [Webpack](http://webpack.github.io/) uses [Loaders](http://webpack.github.io/docs/loaders.html) which means you can also require CSS and a host of other file types. Read the [Webpack documentation](http://webpack.github.io/docs/home.html) to find out more.

### Running Tests
`npm test` or `node node_modules/.bin/mocha`

## Copyright and license

* The repo:

The MIT License (MIT)

Copyright (c) 2016 Kim Hammar

* The database:

The MIT License (MIT)

Copyright (c) 2008-2014 Luis Rocha
