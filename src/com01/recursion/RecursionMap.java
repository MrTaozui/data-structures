package com01.recursion;

/**
 * @author tjj .
 * <p>
 * 迷宫问题  主要是递归回溯
 * 总结：可以理解为标记可走的点
 * <p>
 * 说明：递归的理解应该从方法的底层调用机制理解，当一个方法A调用另一个方法B时，开辟
 * 另一个栈帧，当前的方法A处于等待阶段，当被调用的方法B执行完成之后，就会返回到A的
 * 等待位置，A继续往下执行 这个就是回溯
 * <p>
 * ex:  A->b->c->d               A<-b<-c<-d
 */
public class RecursionMap {


    /**
     * 说明  地图用数组表示 0值处表示未走过  1值处事障碍物 2 值处表示可以走通
     * 3 值处表示 不可走通
     */
    public static void main(String[] args) {
        //初始化地图
        int[][] map = new int[7][8];
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[6][i] = 1;
        }

        for (int i = 0; i < 7; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }

        for (int i = 1; i < 3; i++) {
            map[3][i] = 1;
        }

       // map[1][2]=1; //设置障碍物 可以看出 赋值3的回溯
        //map[2][2]=1; //设置障碍物

        setWay(map,1,1);

        show(map);


    }

    /**
     * 标记可行点  到 终点map[5][6]标记完成结束
     * 先定一个行走路线  下 - 右 - 上 - 左 的方向
     * @param map 地图
     * @param x  横坐标
     * @param y 纵坐标
     * @return 是否为可行的点
     */
    public static boolean setWay(int[][]map,int x , int y) {
        if(map[5][6]==2){//如果(5,6)是可行的则 返回
            return true;
        }

        if(map[x][y]>0){//此点标记过了或者是墙  1 2 3
            return false;

        }

        map[x][y] = 2; //假设此地点是可行的
        if(setWay(map,x+1,y)){//下走是可行的 则此点可以通
            return true;
        }else if(setWay(map,x,y+1)){//右
            return true;
        }
        else if(setWay(map,x-1,y)){//上
            return true;
        }
        else if(setWay(map,x,y-1)){//左
            return true;
        }
        //此点不可行
        map[x][y] = 3; //递归调用 返回处 就会发生回溯

      return false;

    }

    /**
     * 打印地图
     * @param map
     */
    public static void show(int[][] map) {
        for (int[] l : map) {
            for (int i : l) {
                System.out.printf("%d\t", i);
            }

            System.out.println();
        }

    }


}
