package org.leandro.bexs.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.leandro.bexs.service.RouteService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Controller("/route")
public class RouteController {

    @Inject
    private RouteService routeService;

    @Get("/best/{routeArg}")
    @Produces(MediaType.TEXT_PLAIN)
    public String bestRoute(@NotNull final String routeArg) {
        return routeService.bestRoute(routeArg);
    }

    @Post(value = "/insert", consumes = MediaType.APPLICATION_JSON)
    public Boolean insertRoute(@NotNull @Body String routeInput) throws IOException {
        return routeService.insertRoute(routeInput);
    }
}
