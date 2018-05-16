FROM openjdk:10-jdk as build

WORKDIR /srv/mediheroes

COPY . /srv/mediheroes

RUN ./gradlew build

FROM openjdk:10-jre

COPY --from=build /srv/mediheroes/build/libs/mediheroes-*.jar ./srv/mediheroes/mediheroes.jar

WORKDIR /srv/mediheroes

ENV JAVA_OPTS="-xmx1024m -xms256m"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -server  -Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom -jar mediheroes.jar" ]
