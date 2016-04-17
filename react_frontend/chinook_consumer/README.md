# JavaScript Frontend for consuming REST-API for the chinook database

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
  
*
  * JavaScript linting utility (a type of static analysis).

## Screenshot

TODO

## Installation

1. `git clone https://github.com/Limmen/chinook`
2. `cd chinook/react_frontend/chinook_consumer/`

## Generating new components with yeoman
```bash
yo react-webpack:component my/namespaced/components/name
```

The above command will create a new component, as well as its stylesheet and a basic testcase.

## Generating new stateless functional components with yeoman
```
yo react-webpack:component my/namespaced/components/name --stateless
```

## Usage
The following commands are available in your project:
```bash
# Start for development
npm start # or
npm run serve

# Start the dev-server with the dist version
npm run serve:dist

# Just build the dist version and copy static files
npm run dist

# Run unit tests
npm test

# Lint all files in src (also automatically done AFTER tests are run)
npm run lint

# Clean up the dist directory
npm run clean

# Just copy the static assets
npm run copy
```
### Naming Components
Uppercase for component file naming e.g. [Component.js](https://github.com/petehunt/ReactHack/tree/master/src/components).

### Modules
Each component is a module and can be required using the [Webpack](http://webpack.github.io/) module system. [Webpack](http://webpack.github.io/) uses [Loaders](http://webpack.github.io/docs/loaders.html) which means you can also require CSS and a host of other file types. Read the [Webpack documentation](http://webpack.github.io/docs/home.html) to find out more.

### Running Tests
`npm test` or `node node_modules/.bin/mocha`

## TODO

Most stuff. Currently the application just consists of one page with a table for displaying artists from the chinook database.

## Copyright and license

* The repo:

The MIT License (MIT)

Copyright (c) 2016 Kim Hammar

* The database:

The MIT License (MIT)

Copyright (c) 2008-2014 Luis Rocha
