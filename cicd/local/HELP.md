# local
docker-compose 를 통해 container 내부의 애플리케이션 로그를 외부의 특정 경로로 적재하는 예제 입니다. 

## Build
```
mvn clean package -DskipTests=true
```

## Build Image
- App
```
docker build -t "symplesims/userapi:latest" -f ./cicd/local/docker/Dockerfile .
```

- Fluent-Bit Tail
```
docker build -t "symplesims/aws-for-fluent-bit:local" -f ./cicd/local/fluentbit/Dockerfile .
```

## Config for fluent-bit

```
mkdir -p /tmp/lima/myapp
```

## Run
```
docker-compose -f ./cicd/local/docker-compose.yaml up
# docker-compose -f ./cicd/local/docker-compose.yaml up -d 
```

## stop
```
docker-compose -f ./cicd/local/docker-compose.yaml down
```

## prune
```
docker container prune
docker image prune
```
