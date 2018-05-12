#!/usr/bin/env bash

set -e

docker build -f docker/frontend/Dockerfile -t paradoxxger/mediheroes-frontend:latest .
docker build -f docker/backend/Dockerfile -t paradoxxger/mediheroes-backend:latest .
