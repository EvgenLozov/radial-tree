package com.example.operations;

import com.example.Node;

import java.util.List;

/**
 * @author Yevhen
 */
public interface SameLevelNodeOperation {

    void eval(List<Node> nodes, double levelRadius, double nextLevelRadius );

}
