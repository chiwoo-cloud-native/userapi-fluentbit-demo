FROM amazoncorretto:17
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=target/*.jar
ARG SPRING_PROFILES_ACTIVE

RUN mkdir /app
WORKDIR app
COPY ${JAR_FILE_PATH} springApp.jar

ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE}"
ENTRYPOINT ["java", "-jar", "springApp.jar"]

# docker build -t "symplesims/userapi-fluentbit" -f ./cicd/docker/app/Dockerfile .
# docker run --rm -p 8000:8080 --name userapi-fluentbit symplesims/userapi-fluentbit
# docker run --rm -p 8000:8080 -e "SPRING_PROFILES_ACTIVE=dev" --name userapi-fluentbit symplesims/userapi-fluentbit
# docker run --rm -p 8000:8080 -e "SPRING_PROFILES_ACTIVE=prd" --name userapi-fluentbit symplesims/userapi-fluentbit
# docker build --build-arg LAMBDA_TASK_ROOT="/var/task/" -t "symplesims/user-service" -f ./cicd/docker/Dockerfile .
