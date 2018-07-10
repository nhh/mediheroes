#!/usr/bin/env bash

if [ -z "$VERSION" ]

then
    echo "ERROR: No version given!"
    exit 255
fi

BUNDLE_PATH=/tmp/mediheroes

RELEASE_DATE=`date +%Y-%m-%d`

BINARY_NAME=mediheroes-$VERSION.zip

set -e

bin/decrypt_configuration_files

mkdir -p $BUNDLE_PATH

sed "s/\${VERSION}/${VERSION}/" docker-compose-prod.yml > $BUNDLE_PATH/docker-compose.yml

sed "s/\${RELEASE_DATE}/${RELEASE_DATE}/" docker-compose-prod.yml > $BUNDLE_PATH/docker-compose.yml

mv .env $BUNDLE_PATH/

zip -r $BINARY_NAME $BUNDLE_PATH

echo "INFO: Uploading release:"

scp $BINARY_NAME root@mediheroes.com:/srv/mediheroes/releases/

echo "INFO: Removing local bundle"

rm -rf $BUNDLE_PATH

rm $BINARY_NAME

echo "INFO: OK!"
