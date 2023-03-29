package com.mtailor.meetrics.model;

import java.util.List;

/**
 * Record for passing metrics and metric name to Python code.
 * @param name of metrics
 * @param metrics tuple of value-times
 */
public record Metric(String name, List<MetricTuple> metrics) {
}
