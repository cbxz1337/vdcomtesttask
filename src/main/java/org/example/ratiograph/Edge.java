package org.example.ratiograph;

import java.util.Objects;

public class Edge {

    private final Node V, W;
    private final double coefficient;


    public Edge(Node V, Node W, double value) {
        this.V = V;
        this.W = W;
        this.coefficient = value;
    }


    public Node getV() {
        return V;
    }

    public Node getW() {
        return W;
    }

    public double getCoefficient() {
        return coefficient;
    }


    @Override
    public int hashCode() {
        return Objects.hash(V, W, coefficient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.coefficient, coefficient) == 0 && V.equals(edge.V) && W.equals(edge.W);
    }
}
