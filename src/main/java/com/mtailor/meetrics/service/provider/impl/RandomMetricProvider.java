package com.mtailor.meetrics.service.provider.impl;

import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Only for testing purposes.
 * Provides random metricTuples.
 */
@Component
public class RandomMetricProvider implements MetricProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomMetricProvider.class);

    private Function<Integer, Long> getTimeInMs = (sec) -> LocalDateTime.now().plusSeconds(sec).toInstant(ZoneOffset.UTC).toEpochMilli();

    @Override
    public Flux<MetricTuple> provide(final BasicFilter request) {
        return getRandomMetrics();
    }

    private Flux<MetricTuple> getRandomMetrics() {

        List<MetricTuple> metricTuples = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int randomValue = (int) Math.floor(Math.random() * 100);
            metricTuples.add(new MetricTuple(randomValue, getTimeInMs.apply(i)));
        }

        return Flux.fromIterable(metricTuples).doOnNext(e -> LOGGER.info(e.toString())).delayElements(Duration.ofSeconds(1));
    }

}
