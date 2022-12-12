echo -n "AWS for Fluent Bit Test Image Version "
cat /AWS_FOR_FLUENT_BIT_VERSION

echo "ecs_task_id: $(ecs_task_id) ecs_cluster: $(ecs_cluster)"
echo "ecs_task_id2: $ecs_task_id ecs_cluster2: $ecs_cluster"

cat /fluent-bit/etc/fluentbit-tail.conf

# see - https://github.com/aws/aws-for-fluent-bit/blob/mainline/entrypoint.sh
exec /fluent-bit/bin/fluent-bit -e /fluent-bit/firehose.so -e /fluent-bit/cloudwatch.so -e /fluent-bit/kinesis.so -c /fluent-bit/etc/fluentbit-tail.conf
