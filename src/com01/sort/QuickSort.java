package com01.sort;

import java.util.Arrays;

/**
 * @author taojj .
 *
 * 注意点：有相同数和基准数相同，这种情况下的移动
 *
 * 助记说明：找个中间的基准数，l r对冲移动，
 *  left到l  rigth到r覆盖的地方就是完成的。
 *  l==r 说明完成，但是下一轮要继续比较,l r继续移动一位,就会交错
 *  重新规划大小，重新从left到r ,l到right操作
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr, int left, int right) {
        if(left>=right){ // if flag:1 //如果数组规模是1 则不用排列了
            return;
        }

        int mid = (left + right) / 2;
        int pointer = arr[mid];//基准数
        int l = left;
        int r = right;
        while (r > l) {//当l>= r 时说明左边都比基准数小，右边都比基准数大

            while (arr[l] < pointer) {//找到左边比 基准数大的 不能等于 会丢失基准数，l==mid
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
            if (arr[l] == pointer) {//如果交换过后 基准数在左边，基准数不能动 要拿着基准数作比较 右边移位 用全[1,1,1...]助记
                r--;//因为是右边交换到左边的 所以右边前进
            }
            if (arr[r] == pointer) {//如果交换过后 基准数在右边，基准数不能动 要拿着基准数作比较 左边移位
                l++;//因为是左边交换到右边的 所以左边前进
            }

        }

        //// 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        //实际意义：因为l边的都比mid 小，r边的都比mid大 如果重合，mid不需要参与比较，让l r继续移动 这样left-l ,right-r 就是覆盖完成的
        //助记：l r对向移动
        if (l == r) {//经过移位 两边如果重合 重新调整 基准数两边的数组规模
            l ++;
            r--;
        }
        //然后递归 排左边
        if(left < r) //此判断是为了防止组 规模很小的时候为1个或者经过上面的调整出现越界的情况 就不用递归排序了 //也可以在第一排加校验
        sort(arr,left,r);
        //然后递归排右边
        if(right > l)//此判断是为了防止组 规模很小的时候为1个或者经过上面的调整出现越界的情况 就不用递归排序了 //也可以在第一排加校验
        sort(arr,l,right);




    }
}

