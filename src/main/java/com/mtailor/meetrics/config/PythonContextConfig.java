package com.mtailor.meetrics.config;

import com.mtailor.meetrics.service.chart.PythonChartVisualizer;
import com.mtailor.meetrics.service.chart.impl.SimplePythonMetricVisualizer;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Configuration
public class PythonContextConfig {

    public static final String PYTHON_OPTION_FORCE_IMPORT_SITE_KEY = "python.ForceImportSite";
    public static final String PYTHON_OPTION_EXECUTABLE_KEY = "python.Executable";
    public static final String PYTHON = "python";
    public static final String PYTHON_CONTEXT = "firstPythonContext";
    public static final String PYTHON_CLASS_NAME = "ChartVisualizer";
    public static final String PYTHON_SOURCE = "pythonSource";
    @Value("${python.venv}")
    private String pythonVenv;
    @Value("${python.name}")
    private String pythonFileName;

    @Bean(name = PYTHON_CONTEXT)
    public Context context(@Qualifier(PYTHON_SOURCE) final Source source) {
        Context context1 = Context.newBuilder()
                .allowAllAccess(true)
                .allowExperimentalOptions(true)
                .option(PYTHON_OPTION_FORCE_IMPORT_SITE_KEY, Boolean.TRUE.toString())
                .option(PYTHON_OPTION_EXECUTABLE_KEY, pythonVenv)
                .build();

        context1.eval(source);
        return context1;
    }

    @Bean(PYTHON_SOURCE)
    public Source pythonSource() {
        InputStreamReader code = new InputStreamReader(
                Objects.requireNonNull(SimplePythonMetricVisualizer.class.getClassLoader()
                        .getResourceAsStream(pythonFileName)));

        Source source;
        try {
            source = Source.newBuilder(PYTHON, code, pythonFileName).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return source;
    }

    @Bean
    public PythonChartVisualizer pythonChartVisualizer(@Qualifier(PYTHON_CONTEXT) final Context context) {
        return context.getPolyglotBindings()
                .getMember(PYTHON_CLASS_NAME)
                .newInstance()
                .as(PythonChartVisualizer.class);
    }

}
