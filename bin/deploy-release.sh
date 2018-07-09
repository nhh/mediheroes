#!/usr/bin/env bash

TARGET_HOST=$1

if [ -z "$TARGET_HOST" ]
then
    echo "ERROR: No target host supplied!"
    exit 255
fi

if [ -z "$VERSION" ]
then
    echo "ERROR: No version given!"
    exit 255
fi

BINARY_NAME=mediheores-$VERSION.zip

set -e

echo "INFO: Copying artifact to local host"
scp root@144.76.45.199:/home/mediheroes/releases/$BINARY_NAME .

echo "INFO: Copying artifact to target host"
scp $BINARY_NAME root@$TARGET_HOST:/srv/
rm $BINARY_NAME

echo "INFO: Unzipping remote artifact"
ssh root@$TARGET_HOST "cd /srv && unzip -o $BINARY_NAME"

echo "INFO: Pulling new images"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose pull"

echo "INFO: Shutting down application"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose down"

echo "INFO: Obtaining valid certificate for $1"
ssh root@$TARGET_HOST "docker run -p 80:80 -v /etc/letsencrypt/:/etc/letsencrypt/ certbot/certbot certonly -n --standalone --agree-tos --force-renew --email Niklas.Hanft@outlook.com -d $1"

echo "INFO: Starting up application"
ssh root@$TARGET_HOST "cd /srv/mediheroes && docker-compose up -d --scale app=5"

ssh root@$TARGET_HOST "sleep 5 && docker ps"
echo "INFO: OK"
