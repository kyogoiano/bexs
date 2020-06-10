package org.leandro.bexs;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.runtime.Micronaut;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.leandro.bexs.service.RouteService;
import picocli.CommandLine;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
@CommandLine.Command(name = "bexs", description = "bexs route app")
public class Application implements Runnable {

    @Inject
    RouteService routeService;

    public static void main(String[] args) {

        Micronaut.run(Application.class);
        PicocliRunner.run(Application.class, args);
    }

    @SneakyThrows
    @Override
    public void run() {
        routeService.readInputFile();
        log.info("Please enter the route: ");
        final Scanner in = new Scanner(System.in);
        final String bestRoute = routeService.bestRoute(in.nextLine());
        log.info("Best route: {}", bestRoute);
    }
}
