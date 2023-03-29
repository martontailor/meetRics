package com.mtailor.meetrics.service.chart;

import com.mtailor.meetrics.model.request.BasicMetricRequest;

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
    String visualize(final BasicMetricRequest request);

}
