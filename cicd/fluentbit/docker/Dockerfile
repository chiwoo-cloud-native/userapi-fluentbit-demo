FROM amazon/aws-for-fluent-bit:stable AS builder

ARG AWS_REGION="ap-northeast-2"
ARG ECS_CLUSTER_NAME="mycluster"
ARG ECS_APP_NAME="myapp"
ARG LOG_FILEPATH="/var/log/application/app.log"
ARG RETRY_LIMIT="2"

ENV AWS_REGION=$AWS_REGION
ENV ECS_CLUSTER_NAME=$ECS_CLUSTER_NAME
ENV ECS_APP_NAME=$ECS_APP_NAME
ENV LOG_FILEPATH=$LOG_FILEPATH
ENV RETRY_LIMIT=$RETRY_LIMIT

COPY cicd/fluentbit/docker/genconfig.sh /tmp/genconfig.sh
RUN chmod +x /tmp/genconfig.sh
RUN /tmp/genconfig.sh

FROM amazon/aws-for-fluent-bit:stable

ADD  cicd/fluentbit/docker/parsers/parsers-app.conf /fluent-bit/parsers/
COPY --from=builder /tmp/fluentbit-tail.conf /fluent-bit/etc/

COPY cicd/fluentbit/docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Optional Metrics endpoint
EXPOSE 2020

# Entry point
CMD /entrypoint.sh
