package com.mtailor.meetrics.data.repository;

import com.mtailor.meetrics.data.dao.MetricDAO;
import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.service.filter.MetricDAOResultFilter;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.async.SdkPublisher;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class MetricRepository {

    private static final String TABLE_NAME = "meetrics-metrics";
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<MetricDAO> metricTable;
    private final MetricDAOResultFilter filter;

    public MetricRepository(DynamoDbEnhancedAsyncClient asyncClient, MetricDAOResultFilter metricDAOResultFilter) {
        this.enhancedAsyncClient = asyncClient;
        this.filter = metricDAOResultFilter;
        this.metricTable = enhancedAsyncClient.table(TABLE_NAME, TableSchema.fromBean(MetricDAO.class));
    }

    public SdkPublisher<MetricDAO> getAllMetrics(final BasicFilter request) {
        return metricTable.scan().items()
                .filter(filter.filterEntries(request));
    }


}
