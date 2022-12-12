cat <<EOF> /tmp/fluent-bit.conf
[SERVICE]
    flush               5
    daemon              off
    log_Level           info
    parsers_file        /fluent-bit/parsers/parsers-app.conf

[INPUT]
    Name                tail
    Path                $LOG_FILEPATH
    Parser              logback-json
    Read_from_Head      False
    Refresh_Interval    10
    Rotate_Wait         30
    Skip_Long_Lines     On
    Skip_Empty_Lines    On

[OUTPUT]
    Name cloudwatch
    Match   **
    region us-east-1
    log_group_name fluent-bit-cloudwatch
    log_stream_prefix from-fluent-bit-
    auto_create_group true

EOF

cp /tmp/fluent-bit.conf /fluent-bit/etc/
