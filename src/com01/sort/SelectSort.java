package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 选择排序: 每次从后面的数组中选择一个
 * 最小/最大的 放入到数组中的合适的位置
 */
public class SelectSort {
    public static void main(String[] args) {
        int [] arr = new int[]{5,3,8,1,8,32,0,5,1,5,7,93,6453};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int[] arr){

        for (int i=0;i<arr.length-1;i++){
            int min = arr[i];
            int minIndex = i;
            for (int j=i+1;j< arr.length;j++){//找出最小的
                if (min>arr[j]){
                    minIndex = j;
                    min = arr[minIndex];
                }
            }
            //交换
            arr[minIndex] = arr[i];
            arr[i] = min;





        }

    }
}
