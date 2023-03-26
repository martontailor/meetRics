package com.mtailor.meetRics.service.impl;

import com.mtailor.meetRics.model.Metric;
import com.mtailor.meetRics.service.MetricVisualizer;
import com.mtailor.meetRics.service.PythonChartVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;

@Service
public class SimplePythonMetricVisualizer implements MetricVisualizer {

    private static final Logger logger = LoggerFactory.getLogger(SimplePythonMetricVisualizer.class);
    public static final int PURE_BASE64_PART = 1;
    public static final String SVG_DELIMITER = ",";

    private final PythonChartVisualizer pythonChartVisualizer;

    public SimplePythonMetricVisualizer(PythonChartVisualizer pythonChartVisualizer) {
        this.pythonChartVisualizer = pythonChartVisualizer;
    }

    @Override
    public String visualize(Metric metrics) {
        double[] values = new double[]{1.9, 23.33, 55.44};

        String result = pythonChartVisualizer.render(Metric.builder()
                .name("Metric From Java")
                .values(values)
                .earliest(LocalDate.now().toEpochDay())
                .latest(LocalDate.now().plusDays(3).toEpochDay())
                .build());

        return getDecodedString(result);
    }

    private String getDecodedString(String result) {
        result = getPureBase64(result);
        logger.debug("Python call finished:" + result);
        byte[] decodedBytes = Base64.getDecoder().decode(result);
        return new String(decodedBytes);
    }

    private String getPureBase64(final String input) {
        String[] parts = input.split(SVG_DELIMITER);
        return parts[PURE_BASE64_PART];
    }

}