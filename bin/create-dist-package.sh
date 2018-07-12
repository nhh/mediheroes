#!/usr/bin/env bash

VERSION=$1
PASSWORD=$2

if [ -z "$VERSION" ]
then
    echo "ERROR: No version supplied! Usage: build-container.sh 1.0.0.RELEASE"
    exit 255
fi

BUNDLE_PATH=mediheroes-$VERSION

RELEASE_DATE=`date +%Y-%m-%d`

BINARY_NAME=mediheroes-zip

set -e

bin/decrypt_configuration_files $PASSWORD

mkdir -p $BUNDLE_PATH

sed "s/\${VERSION}/$VERSION/" docker-compose-prod.yml > $BUNDLE_PATH/docker-compose.yml

mv .env $BUNDLE_PATH
