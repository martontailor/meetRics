package com.mtailor.meetrics.controller;

import com.mtailor.meetrics.converter.BasicRequestMapper;
import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import com.mtailor.meetrics.service.chart.impl.ReactiveMetricVisualizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MetricVisualizerController.class)
class MetricVisualizerControllerTest {

    private static final String TEST_NAME = "TestName";
    private static final String TEST_NAME2 = "";
    private static final int START_TIME_MS = 1;
    private static final int END_TIME_MS = 999;
    @MockBean
    private ReactiveMetricVisualizer reactiveMetricVisualizer;
    @MockBean
    private BasicRequestMapper mapper;
    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @ValueSource(strings = {"/mono", "/flux"})
    public void shouldVisualizeMonoCallVisualizer(String uri) {
        //given
        BasicMetricRequest req = new BasicMetricRequest(TEST_NAME, START_TIME_MS, END_TIME_MS);
        BasicFilter filter = new BasicFilter(TEST_NAME, START_TIME_MS, END_TIME_MS);
        Mockito.when(reactiveMetricVisualizer.visualize(filter))
                .thenReturn(Flux.just("testBase64String"));
        Mockito.when(mapper.mapRequestToInnerObject(req)).thenReturn(filter);

        //when
        webTestClient.post().uri(uri)
                .body(BodyInserters.fromValue(req))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);

        //then
        Mockito.verify(reactiveMetricVisualizer).visualize(filter);
    }

    @Test
    public void shouldVisualizeMonoReturn400WhenNameIsEmpty() {
        //given
        BasicMetricRequest req = new BasicMetricRequest(TEST_NAME2, START_TIME_MS, END_TIME_MS);

        //when //then
        webTestClient.post().uri("/mono")
                .body(BodyInserters.fromValue(req))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class);
    }


}