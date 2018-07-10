#!/usr/bin/env bash

set -e

VERSION=$1

if [ -z "$VERSION" ]
then
    echo "ERROR: No version supplied! Usage: push-container.sh 1.0.0.RELEASE"
    exit 255
fi

docker push paradoxxger/mediheroes-frontend:$VERSION
docker push paradoxxger/mediheroes-backend:$VERSION
docker push paradoxxger/mediheroes-mongo:$VERSION
docker push paradoxxger/mediheroes-postgres:$VERSION
