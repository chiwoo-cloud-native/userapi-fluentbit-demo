FROM amazon/aws-for-fluent-bit:stable AS builder

ARG ECS_AWS_REGION="ap-northeast-2"
ARG ECS_CLUSTER_NAME
ARG ECS_APP_NAME
ARG LOG_FILEPATH="/var/log/application/app.log"

ENV ECS_AWS_REGION=$ECS_AWS_REGION
ENV ECS_CLUSTER_NAME=$ECS_CLUSTER_NAME
ENV ECS_APP_NAME=$ECS_APP_NAME
ENV LOG_FILEPATH=$LOG_FILEPATH

# ADD  cicd/fluentbit/docker/conf/fluent-bit.conf /fluent-bit/etc/

COPY cicd/fluentbit/docker/genconfig.sh /tmp/genconfig.sh
RUN chmod +x /tmp/genconfig.sh
RUN /tmp/genconfig.sh

FROM amazon/aws-for-fluent-bit:stable

ADD  cicd/fluentbit/docker/parsers/parsers-app.conf /fluent-bit/parsers/
COPY --from=builder /tmp/fluent-bit.conf /fluent-bit/etc/

# Entrypoint
# CMD /entrypoint.sh
