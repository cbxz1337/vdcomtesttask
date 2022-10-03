package org.yakunyashin.graph.services;

import javafx.util.Pair;
import org.yakunyashin.ratiograph.RatioGraph;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.yakunyashin.ratiograph.RatioGraph.Node;
public class RegexpGraphParseService implements GraphParseService<Double>{

    private static final String REGEX = "(?<a>[0-9]+([,.][0-9]?)?)\\s+(?<V>[A-Za-z]+)\\s*=\\s*(?<b>[0-9]+([,.][0-9]?)?|\\?)\\s+(?<W>[A-Za-z]+)$";
    public static final String LEFT_VAL_NAME = "V";
    public static final String RIGHT_VAL_NAME = "W";
    public static final String LEFT_VAL = "a";
    public static final String RIGHT_VAL = "b";

    public static final String UNKNOWN_VALUE_SIGN = "?";


    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private final List<String> inputLines;


    public RegexpGraphParseService(List<String> inputLines) {
        this.inputLines = inputLines;
    }
    @Override
    public RatioGraph<Double> getParsedGraph() {
        RatioGraph<Double> graph = new RatioGraph<>();
        for (String inputLine : inputLines) {
            Matcher matcher = PATTERN.matcher(inputLine);
            if (!matcher.find()) {
                throw new IllegalArgumentException(String.format("Cannot validate line %s", inputLine));
            };
            String V = matcher.group(LEFT_VAL_NAME);
            String W = matcher.group(RIGHT_VAL_NAME);
            String leftValStr = matcher.group(LEFT_VAL);
            String rightValStr = matcher.group(RIGHT_VAL);
            double leftVal = Double.parseDouble(leftValStr);
            double rightVal;
            if (rightValStr.equals(UNKNOWN_VALUE_SIGN)) {
                Pair<RatioGraph.Node<Double>, RatioGraph.Node<Double>> unknownRatio = new Pair<>(new Node<>(V, leftVal), new Node<>(W));
                graph.addUnknownRation(unknownRatio);
                continue;
            }
            rightVal = Double.parseDouble(rightValStr);
            Node<Double> src = new Node<>(V, leftVal);
            Node<Double> dest = new Node<>(W, rightVal);
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
