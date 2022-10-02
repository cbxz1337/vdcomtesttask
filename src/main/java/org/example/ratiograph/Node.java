package org.example.ratiograph;

import java.util.Objects;

public class Node {

    private final String name;
    private Double nodeVal;

    public String getName() {
        return this.name;
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Double nodeVal) {
        this.name = name;
        this.nodeVal = nodeVal;
    }

    public Double getNodeVal() {
        return nodeVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
