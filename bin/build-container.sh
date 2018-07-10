#!/usr/bin/env bash

set -e

VERSION=$1

if [ -z "$VERSION" ]
then
    echo "ERROR: No version supplied! Usage: build-container.sh 1.0.0.RELEASE"
    exit 255
fi

docker build -f docker/frontend/Dockerfile -t paradoxxger/mediheroes-frontend:$VERSION .
docker build -f docker/backend/Dockerfile -t paradoxxger/mediheroes-backend:$VERSION .
docker build -f docker/mongo/Dockerfile -t paradoxxger/mediheroes-mongo:$VERSION .
docker build -f docker/postgres/Dockerfile -t paradoxxger/mediheroes-postgres:$VERSION .
