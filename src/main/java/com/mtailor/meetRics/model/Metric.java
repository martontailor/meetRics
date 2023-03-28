package com.mtailor.meetRics.model;

import java.util.List;

/**
 * Dataholder object for passing metric to Python code.
 */
public record Metric(String name, List<MetricTuple> metrics) {
}
