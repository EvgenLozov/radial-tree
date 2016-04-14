package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.VectorAlgebra.rotateVector;
import static com.example.VectorAlgebra.tangentAngle;

/**
 * @author Yevhen
 */
public class RadialTree {

    double radius = 1;

    public void eval(Node root)
    {
        root.setVector(new Vector(0, 0));

        double firstLevelAngle = 2 * Math.PI / root.getChilds().size();

        for (int i = 0; i < root.getChilds().size(); i++)
            root.getChilds().get(i).setVector(VectorAlgebra.create(radius, firstLevelAngle * i));

        eval(Arrays.asList(root), 1);


    }

    private void eval(List<Node> nodes, int level )
    {
        List<Node> children = new ArrayList<>();
        for (Node node : nodes) {
            children.addAll(node.getChilds());
        }

        for (Node node : nodes) {
            for (Node child : node.getChilds()) {
                putInChildrenCoord(child, children, level);
            }
        }

        if (children.isEmpty())
            return;

        eval(children, level+1);
    }

    private void putInChildrenCoord(Node parent, List<Node> sameLevelNodes, int level)
    {
        List<Vector> withChildren = sameLevelNodes.stream().filter(node -> !node.getChilds().isEmpty())
                .map(Node::getVector).collect(Collectors.toList());

        double rightLimitAngle = getBisecLimit(parent.getVector(), withChildren, false, level);
        double leftLimitAngle = getBisecLimit(parent.getVector(), withChildren, true, level);

        double segment = rightLimitAngle + leftLimitAngle;


        double childAngle = segment/(parent.getChilds().size() + 1);

        Vector rightLimitVector = VectorAlgebra.rotateVector( parent.getVector(), -rightLimitAngle);

        for (int i = 0; i < parent.getChilds().size(); i++) {
            Vector vector = VectorAlgebra.rotateVector(rightLimitVector, childAngle * (i+1));
            vector = VectorAlgebra.multiply(vector, ((double)level + 1)/level);

            parent.getChilds().get(i).setVector(vector );

        }

    }

    public double getBisecLimit(Vector parent, List<Vector> withChildren, boolean direction, int level)
    {
        double tangentAngle = tangentAngle(radius * level, radius * (level+1) ) * Math.pow((1 -  0.05 ), level);

        Optional<Double> bisecAngle = VectorAlgebra.getNeighborAngle(parent, withChildren, direction);

        if (!bisecAngle.isPresent())
            return tangentAngle/2;

        return Math.min(tangentAngle/2, bisecAngle.get()/2 );
    }


}
