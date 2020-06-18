FROM amazoncorretto:11

COPY target/blogger-*.jar /app/blogger.jar

CMD ["sh", "-c", "java -jar /app/blogger.jar"]
EXPOSE 8080