# userapi-fluentbit-demo
The 'userapi-fluentbit-demo' project practices an example of ingesting application logs to cloudwatch using fluentbit. 


## Project Layout
```
.
├── cicd
├── src
│   ├── main
│   │   ├── kotlin
│   │   └── resources
│   └── test
│       └── kotlin
│           └── demo
├── target
├── README.md
└── pom.xml
```

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
# by maven plugin
mvn spring-boot:run -Dspring-boot.run.profiles=default

# by jar
java -jar -Dspring.profiles.active=default ./target/userapi-fluentbit-demo-1.0.0-SNAPSHOT.jar
```

## Runtime Environment

- [Local](./cicd/local/HELP.md) 환경 컨테이너 서비스 구성 / 실행 
- [Cloudwatch](./cicd/cloudwatch/HELP.md) 환경 컨테이너 서비스 구성 / 실행 



## Test by cURL
- Refer to [Postman](https://www.postman.com/downloads/) [collection](./cicd/postman/userapi-fluentbit-demo.postman-collection.json)

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

## Appendix

- [Maven Standard Directory Layout](https://www.baeldung.com/maven-directory-structure)
- [Kotlin Plugin for Maven](https://kotlinlang.org/docs/maven.html)
- [Get started with Kotlin](https://kotlinlang.org/docs/getting-started.html)
- [Atomic Kotlin](https://www.atomickotlin.com/atomickotlin/)
- [Fluent Bit Configuration](https://docs.fluentbit.io/manual/administration/configuring-fluent-bit/classic-mode/configuration-file)
- [Fluent Bit Pipeline](https://docs.fluentbit.io/manual/concepts/data-pipeline)
- [Amazon ECS 커스텀 로거 fluentbit 구성](https://docs.aws.amazon.com/ko_kr/prescriptive-guidance/latest/patterns/create-a-custom-log-parser-for-amazon-ecs-using-a-firelens-log-router.html)
- [Amazon ECS FireLens Examples](https://github.com/aws-samples/amazon-ecs-firelens-examples)
- [AWS Firelens – 사용자 지정 로그 라우팅 소개](https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/developerguide/using_firelens.html)
- [AWS Firelens – 컨테이너 로그 통합 관리 기능 출시](https://aws.amazon.com/ko/blogs/korea/announcing-firelens-a-new-way-to-manage-container-logs/)

