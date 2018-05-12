FROM node:10.0.0-alpine as node

WORKDIR /mediheroes

COPY ./frontend/package.json /mediheroes/

RUN npm install

COPY ./frontend /mediheroes/

RUN npm run build --prod --environment prod

FROM nginx:alpine

COPY --from=node /mediheroes/dist/ /usr/share/nginx/html

ADD ./docker/frontend/mediheroes.com.conf /etc/nginx/conf.d/default.conf
