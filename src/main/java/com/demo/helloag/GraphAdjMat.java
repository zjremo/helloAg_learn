package com.demo.helloag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GraphAdjMat {
    private List<Integer> vertices;
    private List<List<Integer>> edges;

    public GraphAdjMat(int[] vertices, int[][]edges) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int val: vertices) {
            this.vertices.add(val);
        }
        
        for (int[] e: edges) {
            assert e.length == 2;
            addEdge(e[0], e[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v1 > size() || v2 < 0 || v2 > size() || v1 == v2) {
            return;
        }

        edges.get(v1).set(v2, 1);
        edges.get(v2).set(v1, 1);
    }

    public void removeEdge(int v1, int v2) {
        if (v1 < 0 || v1 > size() || v2 < 0 || v2 > size() || v1 == v2) {
            return;
        }

        edges.get(v1).set(v2, 0);
        edges.get(v2).set(v1, 0);
    }

    public void addVertic(int v) {
        vertices.add(v);

        // Step1: currrent vertic -> v
        edges.forEach(e -> e.add(0));

        // Step2: add v -> current vertic
        List<Integer> e = new ArrayList<>();
        IntStream.range(0, size()).forEach(i -> e.add(0));

        // Step3； merge into edges
        edges.add(e);
    }

    public void removeVertic(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        vertices.remove(index);
        edges.remove(index);
        for (List<Integer> e: edges) 
            e.remove(index);
    }
}