package com.mtailor.meetrics.model.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Basic metric request record for incoming API requests.
 *
 * @param name        of the metric
 * @param startTimeMs interval start time in ms
 * @param endTimeMs   interval end time in ms
 */
@Validated
public record BasicMetricRequest(@NotBlank(message = "Metric name cannot be empty") String name,
                                 long startTimeMs,
                                 long endTimeMs) {
}
