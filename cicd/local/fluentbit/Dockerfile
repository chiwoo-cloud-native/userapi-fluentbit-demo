FROM amazon/aws-for-fluent-bit:stable

ADD ./cicd/local/fluentbit/fluent-bit.conf /fluent-bit/etc/
ADD ./cicd/local/fluentbit/parsers-custom.conf /fluent-bit/parsers/

COPY ./cicd/local/fluentbit/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Optional Metrics endpoint
EXPOSE 2020

# Entry point
CMD /entrypoint.sh

# docker build -t "symplesims/aws-for-fluent-bit:local" -f ./cicd/local/fluentbit/Dockerfile .

