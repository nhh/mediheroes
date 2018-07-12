#!/usr/bin/env bash

VERSION=$1

if [ -z "$VERSION" ]
then
    echo "ERROR: No version supplied! Usage: build-container.sh 1.0.0.RELEASE"
    exit 255
fi

BUNDLE_PATH=mediheroes-$VERSION

scp -rp $BUNDLE_PATH root@mediheroes.com:/srv/releases/
