package org.leandro.bexs.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RouteService {
    String bestRoute(final String routeArg);
    boolean insertRoute(final String line) throws IOException;
    void readInputFile() throws FileNotFoundException;
}
