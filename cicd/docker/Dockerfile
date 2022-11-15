FROM amazon/aws-for-fluent-bit:stable

ADD ./cicd/docker/fluentbit/conf-app.conf /fluent-bit/etc/
ADD ./cicd/docker/fluentbit/parser-app.conf /fluent-bit/parsers/

# RUN ls -al /fluent-bit/etc
# RUN ls -al /fluent-bit/parsers

# docker build -t "symplesims/user-service" -f ./cicd/docker/Dockerfile .
# docker build --build-arg LAMBDA_TASK_ROOT="/var/task/" -t "symplesims/user-service" -f ./cicd/docker/Dockerfile .
