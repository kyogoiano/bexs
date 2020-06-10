package org.leandro.bexs;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;
import org.leandro.bexs.service.RouteService;
import org.leandro.bexs.service.RouteServiceImpl;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
public class RouteControllerTest {

    @Inject
    RouteService routeService;

    @Inject
    @Client("/route")
    RxHttpClient rxHttpClient;


    @Test
    public void testBestRoute(){

        when(routeService.bestRoute("GRU-ORL")).thenReturn("RouteInput{steps=[GRU, ORL], value=56}");

        final HttpRequest<String> request = HttpRequest.GET("/best/GRU-ORL");
        final String body = rxHttpClient.toBlocking().retrieve(request);
        assertNotNull(body);
        assertEquals(body, "RouteInput{steps=[GRU, ORL], value=56}");
    }

    @Test
    public void testInsertRoute() throws IOException {
        when(routeService.insertRoute("GRU-ORL-90")).thenReturn(true);

        final HttpRequest<String> request = HttpRequest.POST("/insert", "GRU-ORL-90");

        final String body = rxHttpClient.toBlocking().retrieve(request);
        assertNotNull(body);
        assertEquals(body, "true");
    }


    @Test
    public void testInsertRouteFalse() throws IOException {
        when(routeService.insertRoute("GRU,ORL,90")).thenReturn(false);

        final HttpRequest<String> request = HttpRequest.POST("/insert", "GRU,ORL,90");

        final String body = rxHttpClient.toBlocking().retrieve(request);
        assertNotNull(body);
        assertEquals(body, "false");
    }

    @Test
    public void testInsertRouteException() throws  IOException {
        when(routeService.insertRoute("GRU-ORL-90")).thenThrow(new IOException());

        final HttpRequest<String> request = HttpRequest.POST("/insert", "GRU,ORL,90");

        final String body = rxHttpClient.toBlocking().retrieve(request);
        assertEquals(body, "false");
    }

    @MockBean(RouteServiceImpl.class)
    RouteService routeService() {
        return mock(RouteService.class);
    }
}
