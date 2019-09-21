package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int [] arr = new int[]{5,3,8,1,8,32,0,5,1,5,7,93,6453};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for (int i=arr.length;i>1;i--){
            for (int j=0;j<i-1;j++){
                if (arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;

                }
            }

        }

    }
}


