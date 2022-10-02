package org.example.ratiograph;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class RatioGraph {

    private final Set<Edge> edges = new HashSet<>();

    private final Set<Pair<Node, Node>> nonConnectedNodes = new HashSet<>();


    public void addDirectedEdge(Node src, Node dest, double weight) {
        edges.add(new Edge(src, dest, weight));
    }


    public void calculateUnknownValues() {
        Map<Edge, Double> collect = getEdges().stream()
                .collect(Collectors.toMap(key -> key, Edge::getCoefficient));
        for (Edge edge : collect.keySet()) {
            for (Edge edge1 : collect.keySet()) {
                if (hasSameSrcAndDifferentDest(edge, edge1)) {
                    addDirectedEdge(edge.getW(),
                            edge1.getW(),
                            Math.max(edge.getCoefficient(),
                                    edge1.getCoefficient()) / Math.min(edge.getCoefficient(),
                                    edge1.getCoefficient()));
                }
            }
        }
    }


    public Set<Edge> getEdges() {
        return this.edges;
    }

    public void addUnknownRation(Pair<Node, Node> unknownRatio) {
        this.nonConnectedNodes.add(unknownRatio);
    }

    public Set<Pair<Node, Node>> getUnknownRatios() {
        return nonConnectedNodes;
    }

    private static boolean hasSameSrcAndDifferentDest(Edge edge, Edge edge1) {
        return edge.getV().equals(edge1.getV()) && !edge.getW().equals(edge1.getW());
    }
}
