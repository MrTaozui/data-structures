package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 * 希尔排序  缩小增量排序
 * 不断分组然后调整组的容量
 * 让每个元素 都在其他组中参与比较
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        /**
         * 找规律 然后推导
         */
//
//        //第一轮分了五组 每组2个元素 组之间进行插入排序
//        for(int i=5;i<arr.length;i++){
//            for (int j= i-5;j>=0;j-=5){ //从下标0开始与前面的元素进行比较
//                if(arr[j]>arr[j+5]){//后面大于前面 交换
//                    int temp = arr[j];
//                    arr[j] = arr[j+5];
//                    arr[j+5] = temp;
//                }
//            }
//
//        }
        for (int i = arr.length / 2; i > 0; i /= 2) {//分组
            for (int j = i; j < arr.length; j++) {//待插入的开始下标位置 (i之前下标的元素都是有序的，所以从i开始插入比较)
                for (int l = j - i; l >= 0; l -= i) {//挨个与前面已经有序的元素比较然后插入 j - i此组中的第一个元素
                    if (arr[l] > arr[l + i]) {//此组中的 后面的元素 与前面的比较
                        int temp = arr[l];
                        arr[l] = arr[l+i];
                        arr[l+i] = temp;
                    }

                }


            }


        }


    }
}
