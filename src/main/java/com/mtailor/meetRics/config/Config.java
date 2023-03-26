package com.mtailor.meetRics.config;

import com.mtailor.meetRics.service.PythonChartVisualizer;
import com.mtailor.meetRics.service.impl.SimplePythonMetricVisualizer;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class Config {

    @Bean(name = "firstPythonContext")
    public Context context() {
        Context context1 = Context.newBuilder()
                .allowAllAccess(true)
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                //TODO Should be extracted to env var
                .option("python.Executable", "/Users/Marton_Szabo1/dev/python/bin/graalpy")
                .build();

        InputStreamReader code = new InputStreamReader(SimplePythonMetricVisualizer.class.getClassLoader()
                .getResourceAsStream("first.py"));

        Source source;
        try {
            source = Source.newBuilder("python", code, "first.py").build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        context1.eval(source);
        return context1;
    }

    @Bean
    public PythonChartVisualizer pythonChartVisualizer(@Qualifier("firstPythonContext") final Context context) {
        return context.getPolyglotBindings()
                .getMember("ChartVisualizer")
                .newInstance()
                .as(PythonChartVisualizer.class);
    }

}
