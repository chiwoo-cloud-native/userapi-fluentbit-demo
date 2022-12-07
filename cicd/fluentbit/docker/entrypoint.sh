echo -n "AWS for Fluent Bit Test Image Version "
cat /AWS_FOR_FLUENT_BIT_VERSION

# sed -i "s/TASK_ID/$TASK_ID/g" /fluent-bit/etc/fluent-bit.conf
# sed -i "s/TASK_DEFINITION/$FAMILY:$REV/g" /fluent-bit/etc/fluent-bit.conf
# sed -i "s/ECS_CLUSTER/$ECS_CLUSTER/g" /fluent-bit/etc/fluent-bit.conf

# see - https://github.com/aws/aws-for-fluent-bit/blob/mainline/entrypoint.sh
exec /fluent-bit/bin/fluent-bit -e /fluent-bit/firehose.so -e /fluent-bit/cloudwatch.so -e /fluent-bit/kinesis.so -c /fluent-bit/etc/fluent-bit.conf

