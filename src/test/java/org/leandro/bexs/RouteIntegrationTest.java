package org.leandro.bexs;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.leandro.bexs.service.RouteService;

import javax.inject.Inject;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RouteIntegrationTest {
    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    RouteService routeService;

    static RxHttpClient rxHttpClient;

    @BeforeAll
    public void setup() throws FileNotFoundException {
        routeService.readInputFile();
        rxHttpClient = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL());
    }

    @Test
    public void testGetBest() {
        final HttpRequest<String> request = HttpRequest.GET("/route/best/GRU-ORL");

        assertEquals("RouteInput{steps=[GRU, ORL], value=56}", rxHttpClient.toBlocking().retrieve(request));
    }


    @Test
    public void testInsertRoute() {
        final HttpRequest<String> request = HttpRequest.POST("/route/insert", "GRU-ORL-90");

        assertEquals("true", rxHttpClient.toBlocking().retrieve(request));
    }

}
