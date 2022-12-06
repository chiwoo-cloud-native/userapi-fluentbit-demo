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
mvn clean package -DskipTests=true
```

## Run

- run by maven plugin 
```
mvn spring-boot:run -Dspring-boot.run.profiles=default
```

- run by executable jar
```
java -jar -Dspring.profiles.active=default ./target/userapi-fluentbit-demo.jar
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

### aws-for-fluent-bit 이미지 

- [aws-for-fluent-](https://github.com/aws/aws-for-fluent-bit) 깃허브 프로젝트

- [entrypoint.sh](https://github.com/aws/aws-for-fluent-bit/blob/mainline/entrypoint.sh) 컨테이너 런처는 `fluent-bit.conf` 설정 파일로 고정 되어 있습니다.
```
exec /fluent-bit/bin/fluent-bit -e /fluent-bit/firehose.so -e /fluent-bit/cloudwatch.so -e /fluent-bit/kinesis.so -c /fluent-bit/etc/fluent-bit.conf
```

- [fluent-bit.conf](https://github.com/aws/aws-for-fluent-bit/blob/mainline/fluent-bit.conf) 설정 파일은 INPUT, OUTPUT 항목이 고정 되어 있습니다.  
특히 24224 포트는 firelens 서비스 포트 입니다.

```
[INPUT]
    Name        forward
    Listen      0.0.0.0
    Port        24224

[OUTPUT]
    Name cloudwatch
    Match   **
    region us-east-1
    log_group_name fluent-bit-cloudwatch
    log_stream_prefix from-fluent-bit-
    auto_create_group true
```

### ECS 로그 적재 Workflow
아래 ECS Task 구성은 nginx 애플리케이션과 fluentbit 에이전트가 sidecar 로 구성되어 있으며, 내부적으로 awsfirelens 가 메시지 채널 역할을 담당 합니다.  
다음은 애플리케이션 로그 적재 흐름 입니다.  
ecs 애플리케이션 > stdout > awsfirelens > fluentbit > cloudwatch log-group 
```
{
	"family": "firelens-example-cloudwatch",
	"taskRoleArn": "arn:aws:iam::XXXXXXXXXXXX:role/ecs_task_iam_role",
	"executionRoleArn": "arn:aws:iam::XXXXXXXXXXXX:role/ecs_task_execution_role",
	"containerDefinitions": [		
		{
			 "essential": true,
			 "image": "nginx",
			 "name": "app",
			 "logConfiguration": {
				"logDriver":"awsfirelens",
				"options": {
					"Name": "cloudwatch",
					"region": "us-west-2",
					"log_group_name": "/aws/ecs/containerinsights/$(ecs_cluster)/application",
					"auto_create_group": "true",
					"log_stream_name": "$(ecs_task_id)",
					"retry_limit": "2"
				}
			},
			"memoryReservation": 100
		},
		{
			"essential": true,
			"image": "906394416424.dkr.ecr.us-east-1.amazonaws.com/aws-for-fluent-bit:stable",
			"name": "log_router",
			"firelensConfiguration": {
				"type": "fluentbit"
			},
			"logConfiguration": {
				"logDriver": "awslogs",
				"options": {
					"awslogs-group": "firelens-container",
					"awslogs-region": "us-west-2",
					"awslogs-create-group": "true",
					"awslogs-stream-prefix": "firelens"
				}
			},
			"memoryReservation": 50
		},
	]
}
```

aws-for-fluent-bit 이 컨테이너 런타임으로 실행 할때, [CloudWatch Logs 를 위한 옵션 바인딩](https://github.com/aws/amazon-cloudwatch-logs-for-fluent-bit#usage) 은 대략 아래와 같이 설정 됩니다.  

```
./fluent-bit -e ./cloudwatch.so \
  -o cloudwatch \
  -p "region=us-west-2" \
  -p "log_group_name=fluent-bit-cloudwatch" \
  -p "log_stream_name=testing" \
  -p "auto_create_group=true"
  -c /fluent-bit/etc/fluent-bit.conf
```
