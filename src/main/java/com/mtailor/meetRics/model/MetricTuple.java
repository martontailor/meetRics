package com.mtailor.meetRics.model;

/**
 * This record will be represented as SVG chart.
 * @param value of datapoint
 * @param timeInMs of datapoint
 */
public record MetricTuple(double value, long timeInMs) {
}
