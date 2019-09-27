package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 * 插入排序
 * <p>
 * 把数组看成两个部分 一个是前半部分 已经排序好的 一个是 后半部分
 * 还未排序好的  把未排序的 插入到已排序的合适的位置
 */
public class InsertSort {

    public static void main(String[] args) {
        int [] arr = new int[]{5,3,8,1,8,32,0,5,1,5,7,93,6453};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) { //i 是待插入的当前索引
            int insertIndex = i;//要插入的地方
            int isnertNum = arr[i];//待插入的数   因为数组是移动的 所以 要保存此值

            for (int j = i - 1; j >= 0; j--) {//依次与前面的数比较
                if (isnertNum < arr[j]) { //用到保存值得地方
                    arr[j + 1] = arr[j];//往后移
                    insertIndex = j;
                } else {
                    break;
                }
            }
            arr[insertIndex] = isnertNum;

        }

    }
}
