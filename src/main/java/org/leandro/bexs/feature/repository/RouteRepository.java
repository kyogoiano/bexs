package org.leandro.bexs.feature.repository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.leandro.bexs.model.RouteInput;

import javax.inject.Named;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@Named
public class RouteRepository {


    private static final String COMMA_DELIMITER = ",";
    @Getter
    private final static Set<RouteInput> routeInputs = new HashSet<>();

    public Set<RouteInput> readFile() throws FileNotFoundException {
        try (final Scanner scanner = new Scanner(new File("input-file.txt"))) {
            while (scanner.hasNextLine()) {
                routeInputs.add(getRecordFromLine(scanner.nextLine(), COMMA_DELIMITER));
            }
        }
        return routeInputs;
    }

    public RouteInput getRecordFromLine(final String line, final String delimiter) {
        final RouteInput routeInput = new RouteInput();
        try (final Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimiter);
            while (rowScanner.hasNext()) {
                routeInput.getSteps().add(rowScanner.next());
            }
            routeInput.setValue(Integer.parseInt(routeInput.getSteps().get(routeInput.getSteps().size() -1)));
            routeInput.getSteps().remove(routeInput.getSteps().size() -1);
        }
        log.debug("route read: {}", routeInput.toString());
        return routeInput;
    }

    public boolean insertRecordOnFile(final String line) throws IOException {
        Files.write(Paths.get("input-file.txt"), (System.lineSeparator()+line).getBytes(), StandardOpenOption.APPEND);
        return true;
    }
}
