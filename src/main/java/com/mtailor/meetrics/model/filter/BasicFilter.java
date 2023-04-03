package com.mtailor.meetrics.model.filter;

/**
 * Service layer representation of {@Link BasicMetricRequest}
 *
 * @param name        metric name
 * @param startTimeMs start time in milliseconds
 * @param endTimeMs   end date in milliseconds
 */
public record BasicFilter(String name,
                          long startTimeMs,
                          long endTimeMs) {
}
