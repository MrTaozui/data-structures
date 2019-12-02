package algorithm.floyd;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 弗罗伊徳算法求最短路径问题
 * 跟dijkstra 算法的区别是，此算法每个点
 * 都是出发顶点，求每个点到其他顶点的最短路径。
 *
 * 理解：通过每个点都是中间顶点，逐渐跟不能连通的顶点建立联系，通过中间顶点
 * 就是互相连通的了，最后就可以迭代出最短路径。
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertex,matrix);
        graph.floyd();
        graph.show();


    }




}

class Graph {
    private char[] vertax;
    private int[][] dis;//距离
    private int[][] pre;//前驱顶点
    private int len;

    public Graph(char[] vertax, int[][] dis) {
        this.vertax = vertax;
        this.len = vertax.length;
        this.dis = dis;
        this.pre = new int[len][len];
        for (int i = 0; i < len; i++) {//初始化每个顶点到其他顶点是自己
            Arrays.fill(pre[i], i);
        }

    }

    /**
     * 弗洛伊德算法求最短路径
     */
    public void floyd() {

        for (int i = 0; i < len; i++) {//中间顶点
            for (int j = 0; j < len; j++) {//出发顶点
                for (int k = 0; k < len; k++) {//结束顶点
                    int disSum = dis[j][i] + dis[i][k];//通过中间顶点建立联系的距离
                    if (disSum < dis[j][k]) {
                        dis[j][k] = disSum;
                        pre[j][k] = pre[i][k];//此句的意思是：从j到K的路径，是经过ik到达的，所以j到k的前驱是ik顶，而ik的前驱也是这样的方式得到的
                    }

                }

            }

        }
    }

    public void show() {
        System.out.println("------------距离表------------");
        for (int[] d : dis) {
            for (int i = 0; i < d.length; i++) {
                System.out.printf("%5d", d[i]);
            }
            System.out.println();
        }
        System.out.println("------------前驱表------------");
        for (int i = 0; i < len; i++) {
            System.out.printf("%5c", vertax[i]);
        }
        System.out.println();
        for (int[] p : pre) {
            for (int i = 0; i < p.length; i++) {
                System.out.printf("%5d", p[i]);
            }
            System.out.println();
        }

    }
}