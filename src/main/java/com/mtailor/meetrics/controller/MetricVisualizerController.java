package com.mtailor.meetrics.controller;

import com.mtailor.meetrics.converter.BasicRequestMapper;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.chart.impl.ReactiveMetricVisualizer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class MetricVisualizerController {

    private final ReactiveMetricVisualizer reactiveMetricVisualizer;
    private BasicRequestMapper mapper;

    public MetricVisualizerController(final ReactiveMetricVisualizer reactiveMetricVisualizer,
                                      final BasicRequestMapper mapper) {
        this.reactiveMetricVisualizer = reactiveMetricVisualizer;
        this.mapper = mapper;
    }

    @PostMapping(value = "/mono", produces = "image/svg+xml")
    public Mono<String> visualize(@Valid @RequestBody BasicMetricRequest request) {
        return reactiveMetricVisualizer.visualize(mapper.mapRequestToInnerObject(request)).next();
    }

    @PostMapping(value = "/flux", produces = "text/event-stream")
    public Flux<String> visualizeFlux(@Valid @RequestBody BasicMetricRequest request) {
        return reactiveMetricVisualizer.visualize(mapper.mapRequestToInnerObject(request));
    }

}
