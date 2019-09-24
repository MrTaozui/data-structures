package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 注意点：基准数 要参与比较，以为后 也要参与比较
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr, int left, int right) {
//        if(left==right){ // if flag:1 //如果数组规模是1 则不用排列了
//            return;
//        }

        int mid = (left + right) / 2;
        int pointer = arr[mid];//基准数
        int l = left;
        int r = right;
        while (r > l) {//当l>= r 时说明左边都比基准数小，右边都比基准数大

            while (arr[l] < pointer) {//找到左边比 基准数大的 不能等于 会丢失基准数
                l++;
            }
            while (arr[r] > pointer) {//找到右边比基准数小的
                r--;
            }
            if(l>=r){//如果 重合说明已经是排放好的了
                break;
            }

            //交换位置
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            if (arr[l] == pointer) {//如果交换过后 基准数在左边，基准数不能动 要拿着基准数作比较 右边移位
                r--;
            }
            if (arr[r] == pointer) {//如果交换过后 基准数在右边，基准数不能动 要拿着基准数作比较 左边移位
                l++;
            }

        }

        if (l == r) {//经过移位 两边如果重合 重新调整 基准数两边的数组
            l --;
            r++;
        }
        //然后递归 排左边
        if(left < l) //此判断是为了防止组 规模很小的时候为1个 就不用递归排序了 (if flag:1 作用相同)
        sort(arr,left,l);
        //然后递归排右边
        if(right > r)//此判断是为了防止组 规模很小的时候为1个 就不用递归排序了 (if flag:1 作用相同)
        sort(arr,r,right);




    }
}
