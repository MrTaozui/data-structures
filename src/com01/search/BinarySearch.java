package com01.search;

/**
 * @author taojj .
 *
 * 二分法查找
 */
public class BinarySearch {


    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch(arr,0,0,arr.length-1));

    }

    public static int binarySearch(int[] arr,int findVal,int left,int rigth){
        int index = -1;
        if(left > rigth){
            return index;

        }
        int mid  = (left + rigth)/2;

        if(arr[mid]==findVal){
            return mid;
        }else if(findVal<arr[mid]){
            return binarySearch(arr,findVal,left,mid-1);

        }else {
            return binarySearch(arr,findVal,mid+1,rigth);
        }

    }
}
