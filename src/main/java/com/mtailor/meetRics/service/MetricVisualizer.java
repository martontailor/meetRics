package com.mtailor.meetRics.service;

import com.mtailor.meetRics.model.Metric;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface is used to create a visual representation of metrics.
 */
public interface MetricVisualizer {

    Object visualize(final Metric metrics);

}
