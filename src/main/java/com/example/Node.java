package com.example;

import java.util.List;

/**
 * @author Yevhen
 */
public class Node {

    List<Node> childs;
    Vector vector;

    public Node(List<Node> childs) {
        this.childs = childs;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public List<Node> getChilds() {
        return childs;
    }

    @Override
    public String toString() {
        return vector.toString();
    }
}
