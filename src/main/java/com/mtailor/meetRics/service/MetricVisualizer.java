package com.mtailor.meetRics.service;

import com.mtailor.meetRics.model.Metric;

/**
 * This interface is used to create a visual representation of metrics.
 */
public interface MetricVisualizer {

    Object visualize(final Metric metrics);

}
