FROM amazon/aws-for-fluent-bit:stable

ARG ECS_AWS_REGION="ap-northeast-2"
ARG ECS_CLUSTER_NAME
ARG ECS_APP_NAME
ARG LOG_FILEPATH="/var/log/application/app.log"

ENV ECS_AWS_REGION=$ECS_AWS_REGION
ENV ECS_CLUSTER_NAME=$ECS_CLUSTER_NAME
ENV ECS_APP_NAME=$ECS_APP_NAME
ENV LOG_FILEPATH=$LOG_FILEPATH

ADD ./cicd/local/fluentbit/fluent-bit.conf /fluent-bit/etc/
ADD ./cicd/local/fluentbit/parsers-app.conf /fluent-bit/parsers/
COPY ./cicd/local/fluentbit/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Entrypoint
CMD /entrypoint.sh

# RUN mkdir -p /var/log/myapp
VOLUME ["/var/log/myapp"]
