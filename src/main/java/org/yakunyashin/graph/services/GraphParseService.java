package org.yakunyashin.graph.services;

import org.yakunyashin.ratiograph.RatioGraph;

public interface GraphParseService<T> {

    RatioGraph<T> getParsedGraph();
}
