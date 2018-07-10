#!/usr/bin/env bash

VERSION=$1

if [ -z "$VERSION" ]
then
    echo "ERROR: No version supplied! Usage: build-container.sh 1.0.0.RELEASE"
    exit 255
fi

BUNDLE_PATH=mediheroes/

RELEASE_DATE=`date +%Y-%m-%d`

BINARY_NAME=mediheroes-$VERSION.zip

set -e

bin/decrypt_configuration_files

mkdir -p $BUNDLE_PATH

sed "s/\${VERSION}/$VERSION/" docker-compose-prod.yml > $BUNDLE_PATH/docker-compose.yml

mv .env $BUNDLE_PATH

zip -r $BINARY_NAME $BUNDLE_PATH

echo "INFO: Uploading release:"

scp $BINARY_NAME root@mediheroes.com:/srv/$BINARY_NAME

echo "INFO: Removing local bundle"

rm -rf $BUNDLE_PATH

rm $BINARY_NAME

echo "INFO: OK!"
