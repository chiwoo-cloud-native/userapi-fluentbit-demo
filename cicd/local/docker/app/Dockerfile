FROM amazoncorretto:17
# CMD ["./mvnw", "clean", "package"]
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE}"

RUN mkdir -p /data/app

WORKDIR /data/app

COPY ./target/*.jar springApp.jar

# RUN ls -al /data/app

ENTRYPOINT ["java", "-jar", "springApp.jar"]

# docker build -t "symplesims/userapi" -f ./cicd/local/docker/Dockerfile .
# docker run --rm -p 8080:8080 --name userapi symplesims/userapi:latest
# docker run --rm -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" --name userapi symplesims/userapi:latest
# docker run --rm -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prd" --name userapi symplesims/userapi:latest
# docker build --build-arg LAMBDA_TASK_ROOT="/var/task/" -t "symplesims/userapi" -f ./cicd/local/docker/Dockerfile .

