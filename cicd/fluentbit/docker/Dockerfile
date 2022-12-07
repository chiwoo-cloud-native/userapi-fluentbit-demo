FROM amazon/aws-for-fluent-bit:stable

ADD ./cicd/fluentbit/docker/conf/fluent-app.conf /fluent-bit/etc/
ADD ./cicd/fluentbit/docker/parsers/parsers-app.conf /fluent-bit/parsers/

# COPY ./cicd/fluentbit/docker/entrypoint.sh /entrypoint.sh
# RUN chmod +x /entrypoint.sh
# Optional Metrics endpoint
# EXPOSE 2020

# Entry point
# CMD /entrypoint.sh
