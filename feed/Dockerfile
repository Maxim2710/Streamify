FROM amazoncorretto:21-alpine

EXPOSE 8083

WORKDIR /streamify/feed

COPY ./target/*.jar .

CMD java -jar *.jar