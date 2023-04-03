package com.mtailor.meetrics.service.filter;

import com.mtailor.meetrics.data.dao.MetricDAO;
import com.mtailor.meetrics.model.filter.BasicFilter;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class MetricDAOResultFilter {

    /**
     * This method is responsible for filtering results from java code.
     * Dynamo db enhanced client filtering happens after db entities are fetched.
     * {@Link software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest.Builder#filterExpression Documentation}
     * Using the filter expression does not reduce the cost of the scan, since it is applied
     * <em>after</em> the database has found matching items.
     */
    public Predicate<MetricDAO> filterEntries(BasicFilter request) {
        return element -> request.name().equalsIgnoreCase(element.getMetricName()) &&
                request.startTimeMs() <= element.getTimestamp() &&
                request.endTimeMs() >= element.getTimestamp();
    }

}
