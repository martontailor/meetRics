package com.mtailor.meetRics.controller;

import com.mtailor.meetRics.model.Metric;
import com.mtailor.meetRics.service.impl.SimplePythonMetricVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricVisualizerController {

    private final static Logger logger = LoggerFactory.getLogger(MetricVisualizerController.class);
    private final SimplePythonMetricVisualizer simplePythonMetricVisualizer;

    public MetricVisualizerController(SimplePythonMetricVisualizer simplePythonMetricVisualizer) {
        this.simplePythonMetricVisualizer = simplePythonMetricVisualizer;
    }

    @GetMapping(value = "/test", produces = "image/svg+xml")
    public ResponseEntity<String> visualize() {
        logger.info("Incoming request");
        return ResponseEntity.ok(simplePythonMetricVisualizer.visualize(Metric.builder().build()));
    }

}
