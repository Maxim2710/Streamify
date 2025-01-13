FROM amazoncorretto:21-alpine

EXPOSE 8084

WORKDIR /streamify/notifications

COPY ./target/*.jar .

CMD java -jar *.jar