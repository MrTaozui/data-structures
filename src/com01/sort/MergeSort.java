package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 归并排序 分治的思想
 * 主要在于分 时分解成小任务 治才是 最终操作的步骤
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{8,4,5,7,1,6,3,2,0};
        int[] tmp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,tmp);
        System.out.println(Arrays.toString(arr));



    }

    //合并排序
    public static void mergeSort(int[] arr,int left,int right,int[]tmp){//递归分解
        if(left<right) { //递归一定要有一个终止条件 当数组只有一个成员时 退出
            int mid = (left + right) / 2;
            int l = left;
            int r = mid + 1;
            mergeSort(arr,l,mid,tmp);//分左边
            mergeSort(arr,r,right,tmp);//分右边
            merge(arr,l,right,mid,tmp);//合并

        }


    }

    //合并
    public static void merge(int[] arr,int left,int right,int mid,int[] tmp){
        int l = left;
        int r = mid+1;
        int t= 0;
        while (l<=mid&&r<=right){ //有序数组合并 小的放入数组tmp
            if(arr[l]<arr[r]){
                tmp[t] = arr[l];
                l++;
                t++;
            }else {
                tmp[t] = arr[r];
                r++;
                t++;
            }
        }
        while (l<=mid){ //如果左边还有则继续插入到tmp
            tmp[t] = arr[l];
            l++;
            t++;
        }
        while (r<=right){//如果右边还有则继续插入到tmp
            tmp[t] = arr[r];
            r++;
            t++;
        }
        //tmp拷回 arr
        t = 0;
        for (int i=left;i<=right;i++){
            arr[i] = tmp[t];
            t++;
        }
        //System.out.println(Arrays.toString(tmp));

    }

}
