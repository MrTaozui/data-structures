package algorithm.dynamic;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 动态规划之0-1背包问题
 *思考：
 * 有两个增长指标背包容量和物品数量，一个物品价值衡量指标
 *
 * 目的是两个增加量先后增长的时候 计算当前最优的值
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数

        int[][]v = new int[n+1][m+1]; //增加一行 一列 方便操作，实际意义就是0个物品  0价值


        int[][] path = new int[n+1][m+1];//商品放入情况

        for (int i = 0; i < v.length; i++) {//第一列置为0
            v[i][0] = 0;
        }
        for (int j = 0; j < v[0].length; j++) {//第一行置为0
             v[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {//物品在增加
            for (int j = 1; j <= m ; j++) {//背包重量在增加
                /**
                 * 随着物品数量增加， 在当前物品下，背包数量逐渐增加，
                 * 每次都尝试先放入当前物品n+1，这种情况下，背包会正好放入当前物品，物品重量=背包容量的情况（超重也是一样说明放不下）
                 * 再跟同等容量下（1...n）个物品放入的情况比较，哪种价值最高，因为是从第一个物品开始放入的，所以会得到
                 * 当前条件下最优的情况。
                 * 如果背包容量继续增加，就会查找上次放入的剩余背包重量下的最优的情况（是那个条件下的最后一中情况v[n][剩余重量]）
                 */

                if(w[i-1]>j){//第i 个物品的重量w[i-1] 大于当前的容量么
                    v[i][j] = v[i-1][j];  //当前物品放不下 则看同等容量上个物品的放入的价值
                }else {//当前物品放得下 当前物品的价值+剩余容量的可放入物品的价值 val[i-1] + v[i-1][j-w[i-1]]
                    //v[i][j] = Math.max(val[i-1] + v[i-1][j-w[i-1]],v[i-1][j]);
                    if(val[i-1] + v[i-1][j-w[i-1]]>v[i-1][j]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1; //第i个物品在当前背包容量下放入
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }

        }

        System.out.println("------价值总览表-------");
        for (int[] i:v){
            System.out.println(Arrays.toString(i));
        }
        System.out.println("------放入物品的情况-------");

        int i = n;//path 行下标
        int j = m;//path 列下标
        while (i>0&&j>0){//因为最后的情况是最优的 所以从后面查找
            if(path[i][j]==1){
                System.out.println("放入第"+i+"个物品");
                j = j - w[i-1];// 缩小规模考虑递归
            }
            i--;
        }
        System.out.println("------path 打印-------");
        for (int[]  p :path){
            System.out.println(Arrays.toString(p));
        }

        System.out.println("------递归结果打印-------");
        dacPaht(n,m,path,w);
    }

    /**
     *
     * @param i path 行下标
     * @param j path 列下标
     * @param path
     * @param w 物品重量标
     *
     *  想象成不断缩小的物品和背包重量规模
     */
    private static void dacPaht(int i,int j,int[][] path,int w[]){
        if(i<0||j<0){
            return;
        }
        if(path[i][j]==1){
            System.out.println("放入第"+i+"个物品");
            j = j - w[i-1];// 缩小规模考虑递归
        }
        i--;
        dacPaht(i,j,path,w);

    }


}
