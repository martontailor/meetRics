package com.mtailor.meetrics.service.chart;

import com.mtailor.meetrics.model.Metric;

/**
 * Interface for python code.
 */
public interface PythonChartVisualizer {

    /**
     * Method to render SVG from metrics provided in metric param
     * @param metric contains necessary information to draw chart
     * @return Base64 encoded SVG file
     */
    String render(Metric metric);

}
