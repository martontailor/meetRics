package com.mtailor.meetRics.service.chart;

import com.mtailor.meetRics.model.Metric;

/**
 * This interface is used to create a visual representation of metrics.
 */
public interface MetricVisualizer {

    /**
     * Visualizes the given parameter
     * @param metrics
     * @return base64 encoded svg
     */
    String visualize(final Metric metrics);

}
