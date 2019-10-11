package com01.tree;

import java.util.Arrays;

/**
 * @author taojj .
 * <p>
 * 堆排序,前提是 完全二叉树的顺序存储
 * 节点下标 左子树2n+1
 * 右子树 2n+2
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3, 2, 1, 7, 1, 2, 3};
        //bulidMaxHeap(arr,0);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 构建最大堆
     *
     * @param arr
     * @param i      调整当前堆的索引  给当前节点找位置
     * @param length 需要构建的数组的长度
     */
    public static void bulidMaxHeap(int[] arr, int i, int length) {
        int tmp = arr[i];
        for (int index = 2 * i + 1; index < length; index = index * 2 + 1) {
            if (index + 1 < length && arr[index + 1] > arr[index]) {//左右子树比较
                index++;//如果右子树大 则指向右子树

            }
            if (arr[index] > tmp) {//与左右子树中大的节点比较
                arr[i] = arr[index];//把较大的移动到父节点
                i = index;//i的当前位置

            } else {
                break;//当前子树就是最大堆 则退出，因为是从子节点构建的(其他步骤体现)，所以就无需再比较
            }

            //此时的index指向 交换的节点，节点交换后， 被交换的节点需要重新构建，所以进入到下一轮
        }

        arr[i] = tmp;//找到了最终的位置 tmp 的i 位置

    }


    public static void heapSort(int[] arr) {
        for (int i = arr.length; i >= 0; i--) {
            int index = i / 2 - 1;//从第一个非叶子节点开始构建
            if (index >= 0) {//如果此节点不越界
                bulidMaxHeap(arr, index, arr.length);
            }

        }

        for (int i = arr.length - 1; i > 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];//调整最大堆，把最大的放到数组最后 然后继续构建最大堆
            arr[0] = tmp;
            bulidMaxHeap(arr, 0, i);//因为调整过后 需要0 根节点下表0 位置发生改变，数组大小发生改变所以需要重新调整

        }

    }
}
