package com.mtailor.meetRics.service.impl;

import com.mtailor.meetRics.model.Metric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePythonMetricVisualizerTest {

    private SimplePythonMetricVisualizer simplePythonMetricVisualizer;

    @BeforeEach
    public void setUp() {
        simplePythonMetricVisualizer = new SimplePythonMetricVisualizer( null);
    }

    @Test
    public void testVisualizeShouldCallRFunction() {
        //given

        //when
        simplePythonMetricVisualizer.visualize(Metric.builder().build());

        //then
    }

}