package org.yakunyashin.ratiograph;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public final class RatioGraph<T> {

    private final Set<Edge<T>> edges = new HashSet<>();

    private final Set<Pair<Node<T>, Node<T>>> nonConnectedNodes = new LinkedHashSet<>();


    public void addDirectedEdge(Node<T> src, Node<T> dest, double weight) {
        edges.add(new Edge<T>(src, dest, weight));
    }


    public void calculateUnknownValues() {
        Map<Edge<T>, Double> collect = getEdges().stream()
                .collect(Collectors.toMap(key -> key, Edge::getWeight));
        for (Edge<T> edge : collect.keySet()) {
            for (Edge<T> edge1 : collect.keySet()) {
                if (hasSameSrcAndDifferentDest(edge, edge1)) {
                    addDirectedEdge(edge.getDest(),
                            edge1.getDest(),
                            Math.max(edge.getWeight(),
                                    edge1.getWeight()) / Math.min(edge.getWeight(),
                                    edge1.getWeight()));
                }
            }
        }
    }


    public Set<Edge<T>> getEdges() {
        return this.edges;
    }

    public void addUnknownRation(Pair<Node<T>, Node<T>> unknownRatio) {
        this.nonConnectedNodes.add(unknownRatio);
    }

    public Set<Pair<Node<T>, Node<T>>> getUnknownRatios() {
        return nonConnectedNodes;
    }

    private boolean hasSameSrcAndDifferentDest(Edge<T> edge, Edge<T> edge1) {
        return edge.getSrc().equals(edge1.getSrc()) && !edge.getDest().equals(edge1.getDest());
    }

    public static class Node<T> {
        private final String name;
        private T nodeVal;

        public String getName() {
            return this.name;
        }

        public Node(String name) {
            this.name = name;
        }

        public Node(String name, T nodeVal) {
            this.name = name;
            this.nodeVal = nodeVal;
        }

        public T getNodeVal() {
            return nodeVal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, nodeVal);
        }
    }

    public static class Edge<T> {
        private final Node<T> src, dest;
        private final double weight;


        public Edge(Node<T> V, Node<T> W, double value) {
            this.src = V;
            this.dest = W;
            this.weight = value;
        }


        public Node<T> getSrc() {
            return src;
        }

        public Node<T> getDest() {
            return dest;
        }

        public double getWeight() {
            return weight;
        }


        @Override
        public int hashCode() {
            return Objects.hash(src, dest, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return Double.compare(edge.weight, weight) == 0 && src.equals(edge.src) && dest.equals(edge.dest);
        }
    }

}
