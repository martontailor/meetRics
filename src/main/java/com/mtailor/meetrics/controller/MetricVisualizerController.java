package com.mtailor.meetrics.controller;

import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.chart.impl.SimplePythonMetricVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricVisualizerController {

    private final static Logger logger = LoggerFactory.getLogger(MetricVisualizerController.class);
    private final SimplePythonMetricVisualizer simplePythonMetricVisualizer;

    public MetricVisualizerController(SimplePythonMetricVisualizer simplePythonMetricVisualizer) {
        this.simplePythonMetricVisualizer = simplePythonMetricVisualizer;
    }

    @PostMapping(value = "/test", produces = "image/svg+xml")
    public ResponseEntity<String> visualize(@RequestBody BasicMetricRequest request) {
        return ResponseEntity.ok(simplePythonMetricVisualizer.visualize(request));
    }

    @GetMapping(value = "/test", produces = "image/svg+xml")
    public ResponseEntity<String> visualize() {
        return ResponseEntity.ok(simplePythonMetricVisualizer.visualize(null));
    }

}