FROM amazoncorretto:17
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=target/*.jar
ARG SPRING_PROFILES_ACTIVE

RUN mkdir -p /data/app
RUN mkdir -p /data/logs
WORKDIR /data/app

COPY ${JAR_FILE_PATH} springApp.jar

RUN ls -al /data/ /data/app

ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE}"
ENTRYPOINT ["java", "-jar", "springApp.jar"]
