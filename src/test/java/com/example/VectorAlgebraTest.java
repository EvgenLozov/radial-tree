package com.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VectorAlgebraTest {

    @Test
    public void testAngle() throws Exception {
        Vector vector = new Vector(1, 0);


        VectorAlgebra.angle(vector, new Vector(-1,0));


    }

    @Test
    public void getNeighbor() throws Exception {
        List<Vector> candidates = new ArrayList<Vector>();
        candidates.add(new Vector(-1, -1));
        candidates.add(new Vector(0, -1));
        candidates.add(new Vector(1, 0));


        VectorAlgebra.getNeighbor(new Vector(1, 0), candidates, true);


    }
}