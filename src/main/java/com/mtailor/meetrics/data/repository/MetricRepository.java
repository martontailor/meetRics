package com.mtailor.meetrics.data.repository;

import com.mtailor.meetrics.data.dao.MetricDAO;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

@Repository
public class MetricRepository {

    public static final String TABLE_NAME = "meetrics-metrics";
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<MetricDAO> customerDynamoDbAsyncTable;

    public MetricRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.customerDynamoDbAsyncTable = enhancedAsyncClient.table(TABLE_NAME, TableSchema.fromBean(MetricDAO.class));
    }

    public PagePublisher<MetricDAO> getAllCustomer() {
        return customerDynamoDbAsyncTable.scan();
    }

}
