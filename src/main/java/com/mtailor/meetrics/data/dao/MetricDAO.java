package com.mtailor.meetrics.data.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public void setId(String id) {
        this.id = id;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
