FROM amazon/aws-for-fluent-bit:stable

ADD ./cicd/local/docker/fluentbit-tail/fluent-bit.conf /fluent-bit/etc/
ADD ./cicd/local/docker/fluentbit-tail/parsers-custom.conf /fluent-bit/parsers/

COPY ./cicd/local/docker/fluentbit-tail/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Optional Metrics endpoint
EXPOSE 2020

# Entry point
CMD /entrypoint.sh

# docker build -t "symplesims/aws-for-fluent-bit:local" -f ./cicd/local/docker/fluentbit-tail/Dockerfile .

