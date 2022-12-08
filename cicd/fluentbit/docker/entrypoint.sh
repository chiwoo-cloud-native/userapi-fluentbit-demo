echo -n "AWS for Fluent Bit Test Image Version "
cat /AWS_FOR_FLUENT_BIT_VERSION

sed -i "s#{aws_region}#$ECS_AWS_REGION#g" /fluent-bit/etc/fluent-bit.conf
sed -i "s#{cluster_name}#$ECS_CLUSTER_NAME#g" /fluent-bit/etc/fluent-bit.conf
sed -i "s#{app_name}#$ECS_APP_NAME#g" /fluent-bit/etc/fluent-bit.conf
sed -i "s#{log_filepath}#$LOG_FILEPATH#g" /fluent-bit/etc/fluent-bit.conf

cat /fluent-bit/etc/fluent-bit.conf

# see - https://github.com/aws/aws-for-fluent-bit/blob/mainline/entrypoint.sh
exec /fluent-bit/bin/fluent-bit -e /fluent-bit/firehose.so -e /fluent-bit/cloudwatch.so -e /fluent-bit/kinesis.so -c /fluent-bit/etc/fluent-bit.conf
