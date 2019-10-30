package com01.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author taojj .
 * <p>
 * 图中的节点关系表示方式：邻接矩阵，邻接表
 */
public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs = {"A", "B", "C", "D", "E"};
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.showEdges();
        graph.dfs();
    }
}


/**
 * 无向图
 */
class Graph {
    //顶点
    private List<String> vertexs = new ArrayList<>();
    //边，用邻接矩阵表示
    private int[][] edges;
    //此顶点是否被访问过
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        isVisited = new boolean[5];
    }

    //添加顶点
    public void addVertex(String vertex) {
        vertexs.add(vertex);
    }

    //添加边
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;

    }

    /**
     * 对当前节点进行深度优先遍历
     *
     * @param isVisited
     * @param vertex
     */

    private void dfs(boolean[] isVisited, int vertex) {
        //打印此节点
        System.out.print(getVertexById(vertex) + "=>");
        isVisited[vertex] = true;//此节点设置为被访问

         int neighbor = getFirstNeighbor(vertex);//获取第一个邻接节点

         while (neighbor!=-1){//如果有临接节点就继续访问
         if(!isVisited[neighbor]){//如果此节点没有被访问
             dfs(isVisited,neighbor);//进行深度优先遍历
         }
         neighbor = getNexNeighbor(vertex,neighbor);
         }

    }

    /**
     * 深度优先遍历
     *
     * 为了防止出现顶点之间不连通
     * 可能为多个子图 所以要每个节点
     * 都要深度优先遍历
     */
    public void dfs(){
        for (int i = 0; i < vertexs.size(); i++) {
            if(!isVisited[i])
            dfs(isVisited,i);
        }
    }


    /**
     * 广度优先遍历
     * @param isVisited
     * @param vertex
     */
    public void bfs(boolean[] isVisited, int vertex){
        

    }

    /**
     * 获取节点vertex的第一个邻接节点
     *
     * @param vertex 节点id
     * @return 第一个邻接节点的id
     */
    private int getFirstNeighbor(int vertex) {
        for (int i = 0; i < edges[vertex].length; i++) {
            if (edges[vertex][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取当前节点的下一个邻接节点
     *
     * @param vertex 当前节点
     * @param index  上一个邻接节点的坐标
     * @return
     */
    private int getNexNeighbor(int vertex, int index) {
        for (int i = index + 1; i < edges[vertex].length; i++) {
            if (edges[vertex][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取顶点
     *
     * @param index
     * @return
     */
    private String getVertexById(int index) {
        return vertexs.get(index);
    }

    /**
     * 打印边
     */
    public void showEdges() {
        for (int[] edgs : edges) {
            System.out.println(Arrays.toString(edgs));
        }
    }


}