package com.mtailor.meetrics.service.provider.impl;

import com.mtailor.meetrics.data.repository.MetricRepository;
import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component("DbMetricProvider")
public class DbMetricProvider implements MetricProvider {

    private static final Logger logger = LoggerFactory.getLogger(DbMetricProvider.class);
    private final MetricRepository repository;

    public DbMetricProvider(MetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<MetricTuple> provide(BasicFilter request) {
        return Flux.from(repository.getAllMetrics(request))
                .map(element -> new MetricTuple(element.getValue(), element.getTimestamp()));
    }
}
