package com.mtailor.meetrics.service.impl;

import com.mtailor.meetrics.service.chart.impl.SimplePythonMetricVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePythonMetricVisualizerTest {

    private SimplePythonMetricVisualizer simplePythonMetricVisualizer;

    @BeforeEach
    public void setUp() {
        simplePythonMetricVisualizer = new SimplePythonMetricVisualizer(null, null, null);
    }

    @Test
    public void testVisualizeShouldCallRFunction() {
        //given

        //when
//        simplePythonMetricVisualizer.visualize(Metric.builder().build());

        //then
    }

}