package com.mtailor.meetRics.service.chart;

import com.mtailor.meetRics.model.Metric;

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
