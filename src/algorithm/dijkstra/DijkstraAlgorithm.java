package algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 迪杰斯特拉算法
 * 解决：最短路径问题：如：五个邮差从G点出发，到达A,B,C,D,E,F各个顶点的最小距离
 *
 * 解决步骤：从出发点开始 对图进行广度优先遍历，
 * 不断更新三个重要的数据 已经访问数组already(只记录当前已经广度优先遍历的节点)，
 * 前驱数组pre，距离出发节点长度数组dis
 *
 *
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex,matrix);
        graph.dijkstra(6);

    }
}

/**
 * 图
 */
class Graph{
    public char[] vertex;
    public int[][] edge;
    public VisitedRecord vr;

    public Graph(char[] vertex,int[][] edge){
        this.vertex = vertex;
        this.edge = edge;
    }

    /**
     * 对此图从index 节点开始进行迪杰斯特拉算法，或者
     * 寻找最短路径
     * @param index
     */
    public void dijkstra(int index){
        this.vr = new VisitedRecord(vertex.length,index);
        while (index != -1){
        for (int i=0;i<edge[index].length;i++){//对当前节点进行广度优先遍历
            int distnace = edge[index][i] + vr.dis[index];
            if(distnace < vr.dis[i]){
                vr.updataDis(i,distnace);//更新距离
                vr.updataPre(i,index);//更新前驱
            }
        }
        index = vr.getNoAlready();
            if(index!=-1){
                vr.updatAlready(index);
            }
        }
        System.out.println(Arrays.toString(vr.dis));

    }
}

/**
 * 访问记录表 提供三个数组
 */
class VisitedRecord{
    //用此值代表各个节点见尚未遍历 标记距离，所以到触发顶点的值为N
    private static final int N = 65535;
    //已经访问数组
    public int[] already;
    //前驱数组
    public int[] pre;
    //距离数组
    public int[] dis;

    /**
     *
     * @param len 节点数组的长度
     * @param index 开始访问的节点
     */
    public VisitedRecord(int len,int index){
        this.already = new int[len];
        this.pre = new int[len];
        this.dis = new int[len];

        this.already[index] = 1;//当前节点标记为已经访问，或者开始访问
        Arrays.fill(dis,N);//初始化距离
        this.dis[index] = 0;//当前节点已经遍历过，到自己的距离为0

    }
    //更新当前节点为已经访问
    public void updatAlready(int index){
        this.already[index] = 1;
    }
    //更新index的前驱数组pre
    public void updataPre(int index,int pre){
        this.pre[index] = pre;
    }
    //更新当前节点index 到触发节点的距离distance
    public void updataDis(int index,int distance){
        this.dis[index] = distance;
    }

    //获取下一个未访问的节点
    public int getNoAlready(){
        for (int i=0;i<already.length;i++){
            if(already[i]==0 && dis[i] < 65535){//未访问，且被上次遍历访问过
                return i;
            }
        }
        return -1;
    }


}
