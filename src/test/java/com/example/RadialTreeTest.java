package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class RadialTreeTest {

    @Test
    public void testName() throws Exception {
        Node root = new GraphProvider().get();

        RadialTree radialTree = new RadialTree();

        radialTree.eval(root);


    }
}