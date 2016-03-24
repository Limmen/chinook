#!/bin/bash

cd java_backend/chinook_rest;
mvn install -DskipTests;
#unit tests only
mvn test;
