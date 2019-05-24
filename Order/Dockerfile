#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#RUN pwd
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","build/*","order.Application"]


FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar

#TODO: try and build here?
#RUN gradle build 

COPY . app
EXPOSE 80:80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/build/libs/gs-rest-service-0.1.0.jar"]

#ENTRYPOINT ["java", "-jar", "/build/libs/gs-rest-service-0.1.0.jar"]