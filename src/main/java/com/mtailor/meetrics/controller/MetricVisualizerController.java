package com.mtailor.meetrics.controller;

import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.chart.impl.RealTimeMetricVisualizer;
import com.mtailor.meetrics.service.chart.impl.SimplePythonMetricVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class MetricVisualizerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MetricVisualizerController.class);
    private final SimplePythonMetricVisualizer simplePythonMetricVisualizer;
    private final RealTimeMetricVisualizer realTimeMetricVisualizer;

    public MetricVisualizerController(SimplePythonMetricVisualizer simplePythonMetricVisualizer,
                                      RealTimeMetricVisualizer realTimeMetricVisualizer) {
        this.simplePythonMetricVisualizer = simplePythonMetricVisualizer;
        this.realTimeMetricVisualizer = realTimeMetricVisualizer;
    }

    @PostMapping(value = "/test", produces = "image/svg+xml")
    public Mono<String> visualize(@RequestBody BasicMetricRequest request) {
        return simplePythonMetricVisualizer.visualize(request);
    }

    @GetMapping(value = "/test", produces = "image/svg+xml")
    public Mono<String> visualize() {
        return simplePythonMetricVisualizer.visualize(null);
    }
    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping(value = "/flux", produces = "text/event-stream")
    public Flux<String> visualizeFlux() {
        return realTimeMetricVisualizer.visualize(null);
    }

}
