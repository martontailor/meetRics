package com.mtailor.meetrics.service.chart.impl;

import com.mtailor.meetrics.model.Metric;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.Base64SvgSplitter;
import com.mtailor.meetrics.service.chart.MetricVisualizer;
import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
    public Mono<String> visualize(final BasicMetricRequest request) {
        return metricProvider.provide(request).collectList().publishOn(Schedulers.boundedElastic())
                .map(metricTuples -> getDecodedChart(new Metric("Metric from mono", metricTuples)));
    }

    private String getDecodedChart(final Metric metric) {
        return decode(pythonChartVisualizer.render(metric));
    }

    private String decode(String result) {
        String pureBase64 = splitter.getPureBase64(result);
        return new String(Base64.getDecoder().decode(pureBase64));
    }

}