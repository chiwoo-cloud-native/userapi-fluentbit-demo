cat <<EOF> /tmp/fluent-bit.conf
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
