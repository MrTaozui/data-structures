package com01.search;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 斐波那契查找 （黄金比例查找方法）数组是有序的
 * 理解说明：斐波那契 查找 就是 按照mid 左边/数组length 未黄金比例的
 * 查找方法，因为 通过观察斐波那契数列可以看到 fi(k)/fi(k+1) 越来越接近黄金比例所以使用
 * 斐波那契数列，通过斐波那契数列来找到分割点
 *
 * 所以首先，根据数组的长度 在斐波那契 数组中找到对应的长度（假设我们按照斐波那契方法来查找，则fib那契
 * 数组就存储了 我们要查找的每次mid的规律） 假设要查找的数组长度是8  对应的mid的查找规律对于fib那契数组
 * 就为 1 1 2 3 5 8 先从mid = 5位置开始找， 假设arr[mid]< findval  则从左半部分 长度为5的数组里面继续
 * 查找按照黄金比例查找
 * 所以构建fib那契数 数组是关键
 * 总结 斐波那契数组存储了 黄金比例查找mid 的值
 * 注意一个规律 数组长度是fiArr[k] 左边数组长度是fiArr[k-1] 查找的
 * 右边数组的长度是fiArr[k-2] 如果  fiArr[n]<findArr.length <fiArr[n+1]
 * 重新构建一个数组长度为fiArr[n+1] 把原数组的值拷贝进去，其余的用高位值填充
 *
 *
 * 可以理解成折半查找  这时的fib那契数列 就存了 每次查找mid 的值为 上一步的一半
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(search(arr,7));


    }



    public static int[] genFibArr(int length){
        int[] arr = new int[length];
        arr[0] = 1;
        arr[1] = 1;
        for (int k = 2;k<length;k++){
            arr[k] =  arr[k-1]+ arr[k-2];

        }
        return  arr;
    }


    //递归查找
    public static int search(int[] arr,int findvalue){
        int[] fibArr = genFibArr(arr.length);//构成fibo 数列
        int index = 0;
        for (int i = 0; i <=fibArr.length ; i++) {
            if(fibArr[i]>=arr.length){
                index = i;
                break;
            }

        }

        int[] newArr = Arrays.copyOf(arr,fibArr[index]);//构建新数组

        for(int i=arr.length;i<newArr.length;i++){
            newArr[i]=arr[arr.length-1];
        }
       // System.out.println(Arrays.toString(newArr));
        return fibonacciSearch(newArr,fibArr,findvalue,0,newArr.length-1);

    }

    //查找的逻辑。查看fib数组 表 来确定 要查找的mid的位置 就是左边分割长度多少，右边分割长度多少
    public  static int fibonacciSearch(int[] arr,int[] fibArr ,int findValue,int left,int right){
        int result = -1;
        if(left>right){
            return -1;
        }
        int index = 1;//注意开始的位置，说明 长度为1的时候，左边一个 右边没有

        int length = right -left +1;
        while (true){//找到fib表中自己的长度
            if(length<=fibArr[index]){
                break;
            }
            index+=1;
        }
        //System.out.println(index);

        int mid = left + fibArr[index-1] -1;//左边长度减一 确定长度为 fibArr[i]的数组中 找到 mid的位置
        if(arr[mid] > findValue){
            right = mid-1;
            result = fibonacciSearch(arr,fibArr,findValue,left,right);
            if(result!=-1){
                return result;
            }

        }else if(arr[mid]<findValue){
            left = mid +1;
            result = fibonacciSearch(arr,fibArr,findValue,left,right);
            if(result!=-1){
                return result;
            }
        }else {
            if(mid>arr.length-1){
                return arr.length-1;
            }else {
                return mid;
            }
        }


        return  result;
    }

}
