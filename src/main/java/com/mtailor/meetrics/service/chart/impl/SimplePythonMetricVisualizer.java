package com.mtailor.meetrics.service.chart.impl;

import com.mtailor.meetrics.model.Metric;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.Base64SvgSplitter;
import com.mtailor.meetrics.service.chart.MetricVisualizer;
import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * This class is responsible for visualizing a single metric request.
 */
@Service
public class SimplePythonMetricVisualizer implements MetricVisualizer {

    private final PythonChartVisualizer pythonChartVisualizer;
    private final Base64SvgSplitter splitter;
    private final MetricProvider metricProvider;

    public SimplePythonMetricVisualizer(PythonChartVisualizer pythonChartVisualizer, Base64SvgSplitter splitter,
                                        MetricProvider metricProvider) {
        this.pythonChartVisualizer = pythonChartVisualizer;
        this.splitter = splitter;
        this.metricProvider = metricProvider;
    }

    @Override
    public String visualize(final BasicMetricRequest request) {

        String result = pythonChartVisualizer.render(new Metric("Metric from java", metricProvider.provide(request)));

        return decode(result);
    }

    private String decode(String result) {
        String pureBase64 = splitter.getPureBase64(result);
        return new String(Base64.getDecoder().decode(pureBase64));
    }

}