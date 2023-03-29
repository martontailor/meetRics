package com.mtailor.meetrics.service.chart.impl;

import com.mtailor.meetrics.model.Metric;
import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.service.Base64SvgSplitter;
import com.mtailor.meetrics.service.chart.MetricVisualizer;
import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;

/**
 * This class is responsible for visualizing a single metric request.
 */
@Service
public class SimplePythonMetricVisualizer implements MetricVisualizer {

    private final PythonChartVisualizer pythonChartVisualizer;
    private final Base64SvgSplitter splitter;

    public SimplePythonMetricVisualizer(PythonChartVisualizer pythonChartVisualizer, Base64SvgSplitter splitter) {
        this.pythonChartVisualizer = pythonChartVisualizer;
        this.splitter = splitter;
    }

    @Override
    public String visualize(Metric metrics) {

        long firstTimeInMs = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        long secondTimeInMs = LocalDateTime.now().plusSeconds(1).toInstant(ZoneOffset.UTC).toEpochMilli();
        long thirdTimeInMs = LocalDateTime.now().plusSeconds(2).toInstant(ZoneOffset.UTC).toEpochMilli();
        long fourthTimeInMs = LocalDateTime.now().plusSeconds(3).toInstant(ZoneOffset.UTC).toEpochMilli();
        long fifthTimeInMs = LocalDateTime.now().plusSeconds(5).toInstant(ZoneOffset.UTC).toEpochMilli();

        List<MetricTuple> metricTuples = List.of(
                new MetricTuple(22, firstTimeInMs),
                new MetricTuple(33, secondTimeInMs),
                new MetricTuple(55, thirdTimeInMs),
                new MetricTuple(25, fourthTimeInMs),
                new MetricTuple(17, fifthTimeInMs)
        );

        String result = pythonChartVisualizer.render(new Metric("Metric from java", metricTuples));

        return decode(result);
    }

    private String decode(String result) {
        String pureBase64 = splitter.getPureBase64(result);
        return new String(Base64.getDecoder().decode(pureBase64));
    }

}