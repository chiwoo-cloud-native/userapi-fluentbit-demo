echo -n "AWS for Fluent Bit Test Image Version "
cat /AWS_FOR_FLUENT_BIT_VERSION

cat <<EOF> /tmp/fluentbit-tail.conf
[SERVICE]
    flush               5
    daemon              off
    log_Level           info
    parsers_file        /fluent-bit/parsers/parsers-app.conf

[INPUT]
    Name                tail
    Tag                 application
    Path                $LOG_FILEPATH
    Parser              logback-json
    Read_from_Head      False
    Refresh_Interval    10
    Rotate_Wait         30
    Skip_Long_Lines     On
    Skip_Empty_Lines    On

[OUTPUT]
    Name                cloudwatch
    Match               application
    region              $AWS_REGION
    log_group_name      /ecs/$ECS_CLUSTER_NAME/$ECS_APP_NAME
    log_stream_prefix   $ECS_APP_NAME-
    auto_create_group   true
    retry_limit         $RETRY_LIMIT

EOF

cp /tmp/fluentbit-tail.conf /fluent-bit/etc/

# see - https://github.com/aws/aws-for-fluent-bit/blob/mainline/entrypoint.sh
exec /fluent-bit/bin/fluent-bit -e /fluent-bit/firehose.so -e /fluent-bit/cloudwatch.so -e /fluent-bit/kinesis.so -c /fluent-bit/etc/fluentbit-tail.conf
