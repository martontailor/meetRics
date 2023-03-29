package com.mtailor.meetrics.model.request;

/**
 * Basic metric request record for incoming API requests.
 * @param name of the metric
 * @param startTimeMs interval start time in ms
 * @param endTimeMs interval end time in ms
 */
public record BasicMetricRequest(String name, long startTimeMs, long endTimeMs) {
}
