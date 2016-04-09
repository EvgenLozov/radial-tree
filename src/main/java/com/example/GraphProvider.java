package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Yevhen
 */
public class GraphProvider implements Supplier<Node> {

    private static List<Node> EMPTY = new ArrayList<Node>();

    @Override
    public Node get() {
        List<Node> firstLevel = new ArrayList<Node>();

        List<Node> secondLevelOne = new ArrayList<>();
        secondLevelOne.add(new Node(EMPTY));
        secondLevelOne.add(new Node(EMPTY));

        List<Node> secondLevelTwo = new ArrayList<>();
        secondLevelTwo.add(new Node(EMPTY));
        secondLevelTwo.add(new Node(EMPTY));
        secondLevelTwo.add(new Node(EMPTY));

        firstLevel.add(new Node(EMPTY) );
        firstLevel.add(new Node(EMPTY) );
        firstLevel.add(new Node(Arrays.asList(new Node(secondLevelOne), new Node(EMPTY), new Node(EMPTY) )) );
        firstLevel.add(new Node(Arrays.asList(new Node(EMPTY), new Node(secondLevelTwo) )) );
        firstLevel.add(new Node(Arrays.asList(new Node(EMPTY), new Node(EMPTY), new Node(EMPTY) )) );

        Node root = new Node(firstLevel);

        return root;
    }
}
