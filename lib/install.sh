#!/bin/sh

mvn install:install-file -Dfile=fit-10.20.jar -DgroupId=com.garmin -DartifactId=fit -Dversion=10.20 -Dpackaging=jar
