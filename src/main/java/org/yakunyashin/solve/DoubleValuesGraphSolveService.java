package org.yakunyashin.solve;

import javafx.util.Pair;
import org.yakunyashin.ratiograph.RatioGraph;

import java.text.DecimalFormat;

public class DoubleValuesGraphSolveService implements GraphSolveService {

    public static final String SINGLE_SPACE = " ";
    public static final String NEW_LINE = "\n";
    private final RatioGraph<Double> graph;

    public DoubleValuesGraphSolveService(RatioGraph<Double> ratioGraph) {
        this.graph = ratioGraph;
    }

    public String getSolution() {
        StringBuilder builder = new StringBuilder();
        if (graph.getUnknownRatios().isEmpty()) {
            System.out.println(INPUT_CONTAINS_NO_UNKNOWN_VALUES_MSG);
        }
        for (Pair<RatioGraph.Node<Double>, RatioGraph.Node<Double>> unknownValue : graph.getUnknownRatios()) {
            builder.append(getSolvedUnknownRatioString(unknownValue.getKey(),
                    unknownValue.getValue())).append(NEW_LINE);
        }
        return builder.toString();
    }

    private String getSolvedUnknownRatioString(RatioGraph.Node<Double> left, RatioGraph.Node<Double> unknownNode) {
        DecimalFormat format = new DecimalFormat("0.#");
        return graph.getEdges().stream()
                .filter(it -> it.getSrc().equals(left) && it.getDest().equals(unknownNode))
                .findFirst()
                .map(it -> format.format(left.getNodeVal()) + SINGLE_SPACE + left.getName()
                        + " = " + format.format(it.getWeight() * left.getNodeVal())
                        + SINGLE_SPACE + it.getDest().getName())
                .orElse(CONVERSION_NOT_POSSIBLE_MSG);
    }


}
