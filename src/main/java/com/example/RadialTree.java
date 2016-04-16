package com.example;

import com.example.operations.SameLevelNodeOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.VectorAlgebra.tangentAngle;

/**
 * @author Yevhen
 */
public class RadialTree {

    double radius;
    SameLevelNodeOperation operation;

    public RadialTree(double radius, SameLevelNodeOperation operation) {
        this.radius = radius;
        this.operation = operation;
    }

    public void eval(Node root)
    {
        root.setVector(new Vector(0, 0));

        double firstLevelAngle = 2 * Math.PI / root.getChilds().size();

        for (int i = 0; i < root.getChilds().size(); i++)
            root.getChilds().get(i).setVector(VectorAlgebra.create(radius, firstLevelAngle * i));

        List<Node> nodes = root.getChilds();
        int level = 0;
        while (!nodes.isEmpty())
        {
            level++;
            operation.eval(nodes,  level * radius, (level + 1) * radius);
            nodes = getChildrenOfNodes(nodes);
        }

    }

    private List<Node> getChildrenOfNodes(List<Node> nodes)
    {
        List<Node> children = new ArrayList<>();
        for (Node node : nodes) {
            children.addAll(node.getChilds());
        }

        return children;
    }



}
