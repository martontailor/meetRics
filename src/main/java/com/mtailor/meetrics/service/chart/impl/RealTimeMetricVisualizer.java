package com.mtailor.meetrics.service.chart.impl;

import com.mtailor.meetrics.model.Metric;
import com.mtailor.meetrics.model.MetricTuple;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.Base64SvgSplitter;
import com.mtailor.meetrics.service.chart.MetricVisualizer;
import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import com.mtailor.meetrics.service.provider.MetricProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * This class is responsible for visualizing metric request in real time.
 */
@Service
public class RealTimeMetricVisualizer implements MetricVisualizer {
    private final PythonChartVisualizer pythonChartVisualizer;
    private final Base64SvgSplitter splitter;
    private final MetricProvider metricProvider;

    public RealTimeMetricVisualizer(PythonChartVisualizer pythonChartVisualizer, Base64SvgSplitter splitter,
                                    MetricProvider metricProvider) {
        this.pythonChartVisualizer = pythonChartVisualizer;
        this.splitter = splitter;
        this.metricProvider = metricProvider;
    }

    @Override
    public Flux<String> visualize(final BasicMetricRequest request) {
        List<MetricTuple> tuples = new ArrayList<>();
        return metricProvider.provide(request)
                .doOnNext(tuples::add)
                .publishOn(Schedulers.boundedElastic())
                .map(metricTuple -> getDecodedChart(new Metric("Flux metric", tuples)));
    }

    private String getDecodedChart(final Metric metric) {
        return decode(pythonChartVisualizer.render(metric));
    }

    private String decode(String result) {
        String pureBase64 = splitter.getPureBase64(result);
        return new String(Base64.getDecoder().decode(pureBase64));
    }
}
