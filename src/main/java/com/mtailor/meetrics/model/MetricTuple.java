package com.mtailor.meetrics.model;

/**
 * This record will be represented as SVG chart.
 * @param value of datapoint
 * @param timeInMs of datapoint
 */
public record MetricTuple(double value, long timeInMs) {
}
