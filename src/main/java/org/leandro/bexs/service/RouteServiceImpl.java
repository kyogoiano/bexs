package org.leandro.bexs.service;

import lombok.extern.slf4j.Slf4j;
import org.leandro.bexs.feature.RouteFeature;
import org.leandro.bexs.model.RouteInput;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Named
public class RouteServiceImpl implements RouteService {

    @Inject
    private RouteFeature routeFeature;
    
    private static final String HYPHEN_DELIMITER = "-";

    public String bestRoute(final String routeArg) {
        final String[] routeArgArray = routeArg.split(HYPHEN_DELIMITER, 2);
        final String from = routeArgArray[0];
        final String to = routeArgArray[1];

        final RouteInput bestRoute = routeFeature.calculateBestRoute(from, to);
        if(Objects.isNull(bestRoute)){
            log.info("No routes stored are compatibles!");
            return "No routes stored are compatibles!";
        }
        log.info("Best route: {}", bestRoute.toString());
        return bestRoute.toString();
    }


    public boolean insertRoute(final String line) throws IOException {
        //validate if this line is valid!

        final RouteInput routeInput = routeFeature.getRecordFromLine(line, HYPHEN_DELIMITER);
        if(routeFeature.validate(routeInput, line, HYPHEN_DELIMITER)){
            if(routeFeature.insertRoute(routeInput.buildLine(HYPHEN_DELIMITER))){
                routeFeature.insertRouteOnMem(routeInput);
                return true;
            }
        }
        return false;
    }

    public void readInputFile() throws FileNotFoundException {
        routeFeature.readInputFile();
    }
}
