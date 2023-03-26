package com.mtailor.meetRics.service;

import com.mtailor.meetRics.model.Metric;

public interface PythonChartVisualizer {

    String render(Metric data);

}
