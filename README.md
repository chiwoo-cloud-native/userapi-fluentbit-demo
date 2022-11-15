# spring-cloudwatch-metrics
spring-cloudwatch-metrics

## Git
```
git clone https://github.com/chiwoo-cloud-native/userapi-fluentbit-demo.git

cd userapi-fluentbit-demo
git config --local user.email <your.email>
git config --local user.name <your.name>
git config --local -l
```

## Build
```
mvn clean package
```

## Run
```
mvn spring-boot:run
```

## Build Image

### app
```
docker build -t "symplesims/userapi-fluentbit" -f ./cicd/docker/app/Dockerfile .
docker run --rm -p 8000:8080 -e "SPRING_PROFILES_ACTIVE=default" --name userapi-fluentbit symplesims/userapi-fluentbit
```

### flunt-bit
```
docker build -t "symplesims/user-service" -f ./cicd/docker/Dockerfile .
```

## cURL

### user-add

```curl
curl --location --request POST 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "apple.orchard",
    "nick": "apple",
    "email": "apple@orchard.com",
    "age": 22
}'
```

### user-find
```
curl --location --request GET 'http://localhost:8080/api/v1/users/query' 
```

### user-get
```
curl --location --request GET 'http://localhost:8080/api/v1/users/apple.orchard'
```

### user-modify
```
curl --location --request PUT 'http://localhost:8080/api/v1/users/apple.orchard' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "apple.orchard",
    "nick": "apple",
    "email": "apple@gmail.com",
    "age": 18
}'
```
