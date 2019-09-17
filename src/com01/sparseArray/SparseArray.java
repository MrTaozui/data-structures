package com01.sparseArray;

/**
 * @author tjj .
 * <p>
 * 稀疏数组 可以用来压缩 多个相同数值得数组
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] sourceArr = new int[11][11];
        sourceArr[1][2] = 7;
        sourceArr[3][4] = 8;
        sourceArr[5][6] = 9;
        System.out.println("-----------------原始数组-----------------");
        for (int[] arr : sourceArr) {
            for (int i : arr)
                System.out.printf("%d\t", i);
            System.out.println();
        }
        /****************************生成稀疏数组********************************/
        //统计有效数字
        int num = 0;
        for (int[] arr : sourceArr) {
            for (int i : arr) {
                if (i != 0) {
                    num++;
                }

            }
        }
        //  第一行  行数 |列数|有效个数  其他行    行数 | 列数 | 有效数值
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = num;

        int count = 1;
        for(int i=0;i<sourceArr.length;i++){
            for (int j = 0; j <sourceArr[i].length ; j++) {
                if (sourceArr[i][j]!=0){
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = sourceArr[i][j];
                    count ++;
                }
            }

        }
        System.out.println("-----------------稀疏数组-----------------");
        for (int[] arr : sparseArray) {
            for (int i : arr)
                System.out.printf("%d\t", i);
            System.out.println();
        }

        /****************************还原数组********************************/
        int [][] recoverArr = new int[sparseArray[0][0]][sparseArray[0][1]];

        for(int i=1;i<sparseArray.length;i++){
            recoverArr[sparseArray[i][0]][sparseArray[i][1]] =  sparseArray[i][2];

        }

        System.out.println("-----------------还原数组-----------------");
        for (int[] arr : recoverArr) {
            for (int i : arr)
                System.out.printf("%d\t", i);
            System.out.println();
        }


    }


}
