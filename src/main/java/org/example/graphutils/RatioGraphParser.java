package org.example.graphutils;

import javafx.util.Pair;
import org.example.ratiograph.Node;
import org.example.ratiograph.RatioGraph;

import java.util.List;
import java.util.regex.Matcher;

public class RatioGraphParser {

    private final List<String> inputLines;


    public RatioGraphParser(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    public RatioGraph getParsedGraph() {
        RatioGraph graph = new RatioGraph();
        for (String inputLine : inputLines) {
            Matcher matcher = RatioGroupValidator.getMatchedGroupsMatcher(inputLine);
            String V = matcher.group(RatioGroupValidator.LEFT_VAL_NAME);
            String W = matcher.group(RatioGroupValidator.RIGHT_VAL_NAME);
            String leftValStr = matcher.group(RatioGroupValidator.LEFT_VAL);
            String rightValStr = matcher.group(RatioGroupValidator.RIGHT_VAL);
            double leftVal = Double.parseDouble(leftValStr);
            double rightVal;
            if (rightValStr.equals(RatioGroupValidator.UNKNOWN_VALUE_SIGN)) {
                Pair<Node, Node> unknownRatio = new Pair<>(new Node(V, leftVal), new Node(W));
                graph.addUnknownRation(unknownRatio);
                continue;
            }
            rightVal = Double.parseDouble(rightValStr);
            Node src = new Node(V, leftVal);
            Node dest = new Node(W, rightVal);
            graph.addDirectedEdge(src, dest, calculateWeight(leftVal, rightVal));
            graph.addDirectedEdge(dest, src, calculateWeight(rightVal, leftVal));
        }
        graph.calculateUnknownValues();
        return graph;
    }

    private double calculateWeight(double leftVal, double rightVal) {
        return rightVal/leftVal;
    }

}
