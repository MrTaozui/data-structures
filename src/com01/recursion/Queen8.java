package com01.recursion;

import java.util.Arrays;

/**
 * @author tjj .
 * <p>
 * 八皇后问题
 * <p>
 * 递归问题
 */
public class Queen8 {

    /**
     * 用一维数组摆放棋子 第一个代表第一行  第二个代表第二行
     * 数组中 第一个的值 代表摆放的列的位置。
     */

    public static int count = 0;
    public static void main(String[] args) {
        int[] arr = new int[8];
        setStep(arr,0);
        System.out.println(count);



    }

    /**
     * 判断第几个棋子摆放的位置是否ok
     *
     * @param arr
     * @param n
     * @return
     */
    public static boolean isOk(int[] arr, int n) {

        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(i - n) == Math.abs(arr[i] - arr[n])) {  //同一列 || 同一斜线（行，列坐标的距离相等）
                return false;
            }
        }
        return true;
    }

    /**
     * 摆放第n个棋子
     */
    public static void setStep(int[] arr, int n) {
        if(n==8){//第八个摆放完毕 一次情况 退出
            count++;
            show(arr);
            return;
        }

        for (int i = 0; i < 8; i++) {// 摆放的位置
             arr[n] = i;//摆到当前位置
            if (isOk(arr, n)) {//如果不冲突
                setStep(arr, n + 1);//摆放下一个棋子
            }
        }

    }

    public static void show(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();

    }


}
