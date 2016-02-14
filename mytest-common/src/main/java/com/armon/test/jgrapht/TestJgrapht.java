/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2015 All Rights Reserved.
 *
 * Author: guangming
 * Create Date: 2015年9月25日 下午2:33:02
 */

package com.armon.test.jgrapht;

import java.util.Set;

import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph.CycleFoundException;
import org.jgrapht.graph.DefaultEdge;

/**
 * @author guangming
 *
 */
public class TestJgrapht {

    /**
     * @param args
     * @throws CycleFoundException
     */
    public static void main(String[] args) throws CycleFoundException {
        DirectedAcyclicGraph<MyVertex, DefaultEdge> g1 = new DirectedAcyclicGraph<MyVertex, DefaultEdge>(DefaultEdge.class);
        MyVertex v1 = new MyVertex(1);
        MyVertex v2 = new MyVertex(2);
        MyVertex v3 = new MyVertex(3);
        MyVertex v4 = new MyVertex(4);
        g1.addVertex(v1);
        g1.addVertex(v2);
        g1.addVertex(v3);
        g1.addVertex(v4);
        g1.addDagEdge(v1, v2);
        g1.addDagEdge(v1, v3);
        g1.addDagEdge(v2, v4);
        g1.addDagEdge(v3, v4);

        Set<DefaultEdge> v1out = g1.outgoingEdgesOf(v1);
        System.out.println(v1out.size());
        for (DefaultEdge ege : v1out) {
            System.out.println(g1.getEdgeTarget(ege).getA());
        }

        System.out.println();

        Set<DefaultEdge> v4in = g1.incomingEdgesOf(v4);
        System.out.println(v4in.size());
        for (DefaultEdge ege : v4in) {
            System.out.println(g1.getEdgeSource(ege).getA());
        }
    }
}
