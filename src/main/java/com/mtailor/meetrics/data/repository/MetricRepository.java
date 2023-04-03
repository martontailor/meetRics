package com.mtailor.meetrics.data.repository;

import com.mtailor.meetrics.data.dao.MetricDAO;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.async.SdkPublisher;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.function.Predicate;

@Repository
public class MetricRepository {

    public static final String TABLE_NAME = "meetrics-metrics";
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<MetricDAO> customerDynamoDbAsyncTable;

    public MetricRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.customerDynamoDbAsyncTable = enhancedAsyncClient.table(TABLE_NAME, TableSchema.fromBean(MetricDAO.class));
    }

    public SdkPublisher<MetricDAO> getAllMetrics(final BasicMetricRequest request) {
        return customerDynamoDbAsyncTable.scan().items()
                .filter(filterForMetricName(request));
    }

    /**
     * This method is responsible for filtering results from java code.
     * Dynamo db enhanced client filtering happens after db entities are fetched.
     * {@Link software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest.Builder#filterExpression Documentation}
     * Using the filter expression does not reduce the cost of the scan, since it is applied
     * <em>after</em> the database has found matching items.
     */
    private static Predicate<MetricDAO> filterForMetricName(BasicMetricRequest request) {
        return e -> request.name().equalsIgnoreCase(e.getMetricName());
    }


}
