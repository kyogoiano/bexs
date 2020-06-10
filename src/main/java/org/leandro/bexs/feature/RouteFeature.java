package org.leandro.bexs.feature;

import lombok.extern.slf4j.Slf4j;
import org.leandro.bexs.feature.repository.RouteRepository;
import org.leandro.bexs.model.RouteInput;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Named
public class RouteFeature {

    @Inject
    private RouteRepository routeRepository;

    public RouteInput calculateBestRoute(final String from, final String to) {
        final Set<RouteInput> validRoutes = new HashSet<>();
        RouteRepository.getRouteInputs().forEach(routeInput -> {
            if(routeInput.getSteps().get(0).equals(from)){
                log.debug("Analysing route!");
                routeInput.getSteps().stream().filter( step -> step.equals(to)).findFirst().ifPresent( step-> {
                    log.debug("possible route found! from {}, to {}", from, to);
                    validRoutes.add(routeInput);
                });
            }
        });

        if(validRoutes.isEmpty()){
            return null;
        }

        return validRoutes.stream().sorted(Comparator.comparing(RouteInput::getValue)).collect(Collectors.toList()).get(0);
    }

    public boolean validate(final RouteInput routeInput, final String line, final String delimiter) {
        log.debug("route read from line input: {}", routeInput.toString());
        final String parsedLine = routeInput.buildLine(delimiter);
        return parsedLine.equals(line);
    }

    public boolean insertRoute(final String line) throws IOException {
        return routeRepository.insertRecordOnFile(line);
    }

    public RouteInput getRecordFromLine(final String line, final String delimiter) {
        return routeRepository.getRecordFromLine(line, delimiter);
    }

    public void insertRouteOnMem(final RouteInput routeInput) {
        RouteRepository.getRouteInputs().add(routeInput);
    }

    public void readInputFile() throws FileNotFoundException {
        routeRepository.readFile();
    }
}
