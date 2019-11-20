package algorithm.prim;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 普里姆算法解决修路的 最短路径问题
 *
 * 其实也就是最小生成树的问题：
 * 思考：从一个节点出发到达其他节点 形式上就是一个树
 */
public class Prim {

    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {Integer.MAX_VALUE,5,7,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,2},
                {5,Integer.MAX_VALUE,Integer.MAX_VALUE,9,Integer.MAX_VALUE,Integer.MAX_VALUE,3},
                {7,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,8,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,9,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,8,Integer.MAX_VALUE,Integer.MAX_VALUE,5,4},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,4,5,Integer.MAX_VALUE,6},
                {2,3,Integer.MAX_VALUE,Integer.MAX_VALUE,4,6,Integer.MAX_VALUE},};


        Graph graph = new Graph(data,weight);
        //graph.showGraph();
        //graph.getMTree(0);
        graph.getMTree(1);
    }


}


/**
 * 图 记录了路径权值和 城市节点之间的连通关系
 */
class Graph{

    int vertxs;//节点数
    char[] data;//节点
    int[][] edge;//边

    public Graph(char[] data,int[][] edge){
        this.data = data;
        this.vertxs = data.length;
        this.edge = edge;
    }


    /**
     * 获取最小生成树
     * n 是开始节点
     * 生成思路 从一个节点A开始（标记已经被访问） 先找所有连通的边中权值最小的顶点B，
     * B标记被访问， 然后从已经访问过的A,B 中找到 未访问过的节点中权值最小的边
     * 以此类推
     */
    public void getMTree(int n){
        int[] isVisited = new int[vertxs];//被访问过的节点
        isVisited[n] = 1;
        for (int i = 0; i < vertxs-1; i++) {//n 个顶点需要 n-1 条边就能完成 下面的操作每次选取出来一条边
            int minWeight = Integer.MAX_VALUE;//不连通用最大值表示
            int s = -1;//记录开始节点
            int e = -1;//记录结束节点

            for (int j = 0; j <vertxs; j++) {//代表已经访问的 顶点
                for (int k = 0; k <vertxs; k++) {//代表未访问的节点
                    if(isVisited[j]==1&&isVisited[k]==0&&edge[j][k]<minWeight){//怎样找出访问的节点和未访问的节点，从此看出
                        minWeight = edge[j][k];
                        s= j;
                        e = k;
                    }

                }

            }
            isVisited[e] = 1;//标记已经被访问
            System.out.printf("找到的边：<%C,%C> 权值：%d\n",data[s],data[e],minWeight);
        }


    }

    /**
     * 因为每次处理逻辑重复，但是规模在变化，可以考虑用递归实现
     * @param isVis
     * @param notVis
     */
    public  void getMTreeRecursion(int[] isVis,int notVis[]){

    }

    public void showGraph(){
        for (int[] e : edge){
            System.out.println(Arrays.toString(e));
        }
    }



}