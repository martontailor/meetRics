package com.mtailor.meetrics.service.provider.impl;

import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Provides random metricTuples.
 */
@Component
public class RandomMetricProvider implements MetricProvider {

    private Function<Integer, Long> getTimeInMs = (sec) -> LocalDateTime.now().plusSeconds(sec).toInstant(ZoneOffset.UTC).toEpochMilli();

    @Override
    public List<MetricTuple> provide(BasicMetricRequest request) {
        return getRandomMetrics();
    }

    private List<MetricTuple> getRandomMetrics() {

        List<MetricTuple> metricTuples = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int randomValue = (int) Math.floor(Math.random() * 100);
            metricTuples.add(new MetricTuple(randomValue, getTimeInMs.apply(i)));
        }

        return metricTuples;
    }

}
