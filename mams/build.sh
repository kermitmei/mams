#!/usr/bin/env bash

VERSION=0.0.8
gradle mamsLib
mvn install:install-file -DgroupId=com.california -DartifactId=mams -Dversion=$VERSION -Dfile=/Users/kermit/Projects/mams/mams/build/libs/mams-$VERSION.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=/Users/kermit/Projects/tmp/public-maven-repo  -DcreateChecksum=true

