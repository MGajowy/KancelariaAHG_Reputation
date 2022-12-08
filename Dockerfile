FROM openjdk:11

COPY target/reputation-*.jar app.jar

CMD ["java" , "-jar" , "app.jar" ]
