package com.mtailor.meetrics.service.chart.impl;

import com.mtailor.meetrics.model.Metric;
import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.service.Base64SvgSplitter;
import com.mtailor.meetrics.service.chart.MetricVisualizer;
import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * This class is responsible for visualizing a single metric request.
 */
@Service
public class SimplePythonMetricVisualizer implements MetricVisualizer {

    private final PythonChartVisualizer pythonChartVisualizer;
    private final Base64SvgSplitter splitter;
    private final MetricProvider metricProvider;

    public SimplePythonMetricVisualizer(final PythonChartVisualizer pythonChartVisualizer, final Base64SvgSplitter splitter,
                                        @Qualifier("randomMetricProvider") final MetricProvider metricProvider) {
        this.pythonChartVisualizer = pythonChartVisualizer;
        this.splitter = splitter;
        this.metricProvider = metricProvider;
    }

    @Override
    public Mono<String> visualize(final BasicFilter request) {
        return metricProvider.provide(request).collectList().publishOn(Schedulers.boundedElastic())
                .map(metricTuples -> getDecodedChart(new Metric("Metric from mono", metricTuples)));
    }

    private String getDecodedChart(final Metric metric) {
        return splitter.getPureBase64(pythonChartVisualizer.render(metric));
    }

}