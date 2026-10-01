package com.demo.helloag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphAdjList {
    private Map<Vertex, List<Vertex>> adjList;
    
    public static void dfs(List<Vertex> result, GraphAdjList graph, Set<Vertex> visited, Vertex start) {
        if (visited.contains(start))
            return;

        result.add(start);
        visited.add(start);

        for (Vertex v: graph.adjList.get(start)) {
            dfs(result, graph, visited, v);
        }
    }

    public static List<Vertex> graphDfs(GraphAdjList graph, Vertex start) {
        List<Vertex> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        dfs(result, graph, visited, start);
        return result;
    }

    public static List<Vertex> graphBFS(GraphAdjList graph, Vertex start) {
        List<Vertex> result = new ArrayList<>();

        Set<Vertex> visited = new HashSet<>();
        visited.add(start);
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            result.add(current);
            for (Vertex v: graph.adjList.get(current)) {
                if (!visited.contains(v)){
                    visited.add(v);
                    queue.offer(v);
                }
            }
        }
        return result;
    }

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();

        for (Vertex[] edge: edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public void addVertex(Vertex v) {
        if (adjList.containsKey(v))
            return;
        adjList.put(v, new ArrayList<>());
    }

    public void removeVertex(Vertex v) {
        if (!adjList.containsKey(v)) 
            throw new IllegalArgumentException();

        // 1. v 对应的 edges remove
        adjList.remove(v);
        // 2. other vertex -> v remove
        for (List<Vertex> e: adjList.values()) 
            e.remove(v);
    }

    public void addEdge(Vertex v1, Vertex v2) {
        if (!adjList.containsKey(v1) || !adjList.containsKey(v2) || v1 == v2)
            return;
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1);
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        if (!adjList.containsKey(v1) || !adjList.containsKey(v2) || v1 == v2)
            throw new IllegalArgumentException();

        adjList.get(v1).remove(v2);
        adjList.get(v2).remove(v1);
    }
}