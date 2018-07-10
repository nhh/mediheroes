#!/usr/bin/env bash

VERSION=$1
TARGET_HOST=$2

if [ -z "$TARGET_HOST" ]
then
    echo "ERROR: No target host supplied! Usage: bin/deploy-release.sh 1.0.0.RELEASE wwww.mediheroes.com"
    exit 255
fi

if [ -z "$VERSION" ]
then
    echo "ERROR: No version given! Usage: bin/deploy-release.sh 1.0.0.RELEASE wwww.mediheroes.com"
    exit 255
fi

BINARY_NAME=mediheroes-$VERSION.zip

set -e

echo "INFO: Unzipping remote artifact"
ssh root@$TARGET_HOST "cd /srv/ && unzip -o $BINARY_NAME"

echo "INFO: Pulling new images"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose pull"

echo "INFO: Shutting down application"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose down"

echo "INFO: Obtaining valid certificate for $TARGET_HOST at $VERSION"
ssh root@$TARGET_HOST "docker run -p 80:80 -v /etc/letsencrypt/:/etc/letsencrypt/ certbot/certbot certonly -n --standalone --agree-tos --force-renew --email Niklas.Hanft@outlook.com -d $TARGET_HOST"

echo "INFO: Starting up application"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose up -d"

ssh root@$TARGET_HOST "sleep 5 && docker ps"
echo "INFO: OK"
