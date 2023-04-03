package com.mtailor.meetrics.service.provider;

import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.filter.BasicFilter;
import reactor.core.publisher.Flux;

/**
 * Provides metrics based on filter criteria.
 */
public interface MetricProvider {

    /**
     * @param request filter
     * @return flux of MetricTuples which contains data points
     */
    Flux<MetricTuple> provide(final BasicFilter request);

}
