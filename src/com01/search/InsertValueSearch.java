package com01.search;

/**
 * @author taojj .
 *
 * 插值查找
 *
 * 说明：插值查找适用于 有序数组分布均的数组
 * 因为 根据计算mid的公式可以看出 要查找的值在数组中索引 位置所占大概的比例
 * */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(insertValueSearch(arr,8,0,arr.length-1));
    }

    public static int insertValueSearch(int[] arr,int findVal,int left, int rigth){
        if(left>rigth){
            return -1;
        }
        int mid = left + (findVal-arr[left])/(arr[rigth]-arr[left])*(rigth-left);
        if(mid>arr.length|| mid<0){
            return -1;
        }
        if(arr[mid]==findVal){
            return mid;
        }else if(findVal<arr[mid]){
            return insertValueSearch(arr,findVal,left,mid-1);

        }else {
            return insertValueSearch(arr,findVal,mid+1,rigth);
        }



    }



}
