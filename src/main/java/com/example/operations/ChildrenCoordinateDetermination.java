package com.example.operations;

import com.example.Node;
import com.example.Vector;
import com.example.VectorAlgebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.VectorAlgebra.tangentAngle;

/**
 * @author Yevhen
 */
public class ChildrenCoordinateDetermination implements SameLevelNodeOperation {

    @Override
    public void eval(List<Node> nodes, double levelRadius, double nextLevelRadius ) {

        for (Node node : nodes)
            putInChildrenCoord(node, nodes, levelRadius, nextLevelRadius);

    }

    private void putInChildrenCoord(Node parent, List<Node> sameLevelNodes,  double levelRadius, double nextLevelRadius)
    {
        List<Vector> withChildren = sameLevelNodes.stream().filter(node -> !node.getChilds().isEmpty())
                .map(Node::getVector).collect(Collectors.toList());

        double rightLimitAngle = getBisecLimit(parent.getVector(), withChildren, false, levelRadius, nextLevelRadius);
        double leftLimitAngle = getBisecLimit(parent.getVector(), withChildren, true, levelRadius, nextLevelRadius);

        double segment = rightLimitAngle + leftLimitAngle;


        double childAngle = segment/(parent.getChilds().size() + 1);

        Vector rightLimitVector = VectorAlgebra.rotateVector(parent.getVector(), -rightLimitAngle);

        for (int i = 0; i < parent.getChilds().size(); i++) {
            Vector vector = VectorAlgebra.rotateVector(rightLimitVector, childAngle * (i+1));
            vector = VectorAlgebra.multiply(vector, nextLevelRadius/levelRadius);

            parent.getChilds().get(i).setVector(vector );

        }

    }

    public double getBisecLimit(Vector parent, List<Vector> withChildren, boolean direction,  double levelRadius, double nextLevelRadius)
    {
        double tangentAngle = tangentAngle(levelRadius, nextLevelRadius ) * 0.3;

        Optional<Double> bisecAngle = VectorAlgebra.getNeighborAngle(parent, withChildren, direction);

        if (!bisecAngle.isPresent())
            return tangentAngle/2;

        return Math.min(tangentAngle/2, bisecAngle.get()/2 );
    }
}
