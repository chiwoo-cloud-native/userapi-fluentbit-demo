FROM amazon/aws-for-fluent-bit:stable

ADD ./cicd/dev/docker/fluent-bit.conf /fluent-bit/etc/
ADD ./cicd/dev/docker/parsers-custom.conf /fluent-bit/parsers/

COPY ./cicd/dev/docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Optional Metrics endpoint
# EXPOSE 2020

# Entrypoint
CMD /entrypoint.sh
