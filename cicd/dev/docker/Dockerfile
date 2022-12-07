FROM amazoncorretto:17
# CMD ["./mvnw", "clean", "package"]

ARG JAR_FILE_PATH=target/*.jar
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE}"

RUN mkdir -p /data/app
RUN mkdir -p /var/log/myapp
VOLUME ["/var/log/myapp"]

WORKDIR /data/app

COPY ${JAR_FILE_PATH} springApp.jar

RUN ls -al /data/ /data/app

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "springApp.jar"]
