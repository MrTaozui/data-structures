package com01.tree;

/**
 * @author taojj .
 * <p>
 * 完全二叉树的顺序存储
 * <p>
 * 用数组顺序存储完全二叉树 假设节点下标为n （从0开始）
 * 则做孩子为2n+1 右孩子为2n+2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();

    }
}


class ArrBinaryTree {

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }



    public void preOrder(){
        if(arr==null||arr.length==0){
            System.out.println("树为空");
            return;
        }
        preOrder(0);
    }

    //前序遍历
    private void preOrder(int index) {
        System.out.println(arr[index]);
        if (index * 2 + 1 < arr.length) {//还有左子树
            preOrder(index * 2 + 1);//遍历左子树

        }
        if(index * 2 + 2 < arr.length){//还有右孩子
            preOrder(index * 2 + 2);//遍历右子树
        }


    }


    //后序遍历
    public void postOrder(){
        if(arr==null||arr.length==0){
            System.out.println("树为空");
            return;
        }
        postOrder(0);
    }

    //后序遍历
    private void postOrder(int index) {
        if (index * 2 + 1 < arr.length) {//还有左子树
            postOrder(index * 2 + 1);//遍历左子树

        }
        if(index * 2 + 2 < arr.length){//还有右孩子
            postOrder(index * 2 + 2);//遍历右子树
        }
        System.out.println(arr[index]);

    }



}

