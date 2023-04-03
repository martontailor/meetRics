package com.mtailor.meetrics.service.chart;

import com.mtailor.meetrics.model.filter.BasicFilter;
import reactor.core.CorePublisher;

/**
 * This interface is used to create a visual representation of metrics.
 */
public interface MetricVisualizer {

    /**
     * Visualizes the given parameter
     *
     * @param request filter request
     * @return base64 encoded svg
     */
    CorePublisher<String> visualize(final BasicFilter request);

}
