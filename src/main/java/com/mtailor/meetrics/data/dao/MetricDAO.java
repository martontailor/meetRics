package com.mtailor.meetrics.data.dao;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class MetricDAO {

    private String id;
    private String metricName;
    private Long timestamp;
    private Double value;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("MetricId")
    public String getId() {
        return id;
    }

    public String getMetricName() {
        return metricName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getValue() {
        return value;
    }

    public MetricDAO setId(String id) {
        this.id = id;
        return this;
    }

    public MetricDAO setMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }

    public MetricDAO setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MetricDAO setValue(Double value) {
        this.value = value;
        return this;
    }
}
