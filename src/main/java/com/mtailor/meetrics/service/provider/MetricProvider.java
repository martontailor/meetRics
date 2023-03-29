package com.mtailor.meetrics.service.provider;

import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.request.BasicMetricRequest;

import java.util.List;

/**
 * Provides metrics based on filter criteria.
 */
public interface MetricProvider {

    /**
     * @param request filter
     * @return list of MetricTuples which contains data points
     */
    List<MetricTuple> provide(final BasicMetricRequest request);

}
