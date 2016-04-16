package com.example;

import com.example.operations.ChildrenCoordinateDetermination;
import org.junit.Test;

import static org.junit.Assert.*;

public class RadialTreeTest {

    @Test
    public void testName() throws Exception {
        Node root = new GraphProvider().get();

        double radius = 1;
        RadialTree radialTree = new RadialTree(radius, new ChildrenCoordinateDetermination());

        radialTree.eval(root);


    }
}