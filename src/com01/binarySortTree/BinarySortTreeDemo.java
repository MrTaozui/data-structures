package com01.binarySortTree;

/**
 * @author taojj .
 *
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BST bst = new BST();
        for (int i:arr){
            bst.add(new Node(i));
        }
        bst.fixOrder();

    }
}


class BST{
    private Node root;

    public void add(Node node){
        if(this.root==null){
            this.root = node;
        }else {
            this.root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void fixOrder(){
        if(this.root==null){
            System.out.println("树为空");
            return;
        }
        this.root.fixOrder();
    }



}


class Node{
    private int value;//值

    private Node left;//左子节点

    private Node right;//右子节点


    public Node(int value){
        this.value = value;

    }

    /**
     * 为当前节点（树）添加新节点
     * @param node
     */
    public void add(Node node){
        if(node.getValue()<this.value){//如果待添加节点的值小与当前节点 往左子树添加
            if(this.left==null){
            this.left = node;
            }
            else {
                this.left.add(node);//往左子树添加
            }
        }else {
            if(this.right==null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }


    }


    //当前树的中序遍历

    /**
     * 根据二叉排序树的性质 中序遍历就是排序的 因为就拿三个节点的树来说  比当前树根节点小的
     * 都在左边，比根节点大的 都在右边  而中序遍历的顺序为左 当前 右  -> 小 中 大 这种性质从叶子节点
     * 延申到整个树，所以这种遍历方式就是有序的
     */
    public void fixOrder(){

        if(this.left!=null){
            this.left.fixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.fixOrder();
        }


    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}