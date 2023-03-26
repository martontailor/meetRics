package com.mtailor.meetRics.service.impl;

import com.mtailor.meetRics.model.Metric;
import com.mtailor.meetRics.service.Base64SvgSplitter;
import com.mtailor.meetRics.service.MetricVisualizer;
import com.mtailor.meetRics.service.PythonChartVisualizer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;

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
        double[] values = new double[]{1.9, 23.33, 55.44, 11.33, 3, 100};

        String result = pythonChartVisualizer.render(Metric.builder()
                .name("Metric From Java")
                .values(values)
                .earliest(LocalDate.now().toEpochDay())
                .latest(LocalDate.now().plusDays(values.length).toEpochDay())
                .build());

        return decode(result);
    }

    private String decode(String result) {
        String pureBase64 = splitter.getPureBase64(result);
        return new String(Base64.getDecoder().decode(pureBase64));
    }

}