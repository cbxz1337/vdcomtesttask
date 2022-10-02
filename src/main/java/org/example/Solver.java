package org.example;

import javafx.util.Pair;
import org.example.ratiograph.Node;
import org.example.ratiograph.RatioGraph;

public class Solver {

    private final RatioGraph graph;

    public Solver(RatioGraph ratioGraph) {
        this.graph = ratioGraph;
    }

    public String getSolution() {
        StringBuilder builder = new StringBuilder();
        if (graph.getUnknownRatios().isEmpty()) {
            System.out.println("Input contains no unknown values");
        }
        for (Pair<Node, Node> unknownValue : graph.getUnknownRatios()) {
            builder.append(getRatio(graph, unknownValue.getKey(), unknownValue.getValue())).append("\n");
        }
        return builder.toString();
    }

    private String getRatio(RatioGraph ratioGraph, Node left, Node unknownNode) {
        return ratioGraph.getEdges().stream()
                .filter(it -> it.getV().equals(left) && it.getW().equals(unknownNode))
                .findFirst()
                .map(it -> left.getNodeVal() + " " + left.getName() + " = " + it.getCoefficient() * left.getNodeVal() + " " + it.getW().getName())
                .orElse("Conversion not possible.");
    }


}
