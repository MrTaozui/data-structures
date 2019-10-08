package com01.tree;

/**
 * @author taojj .
 * 二叉树
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(0,"宋江");
        HeroNode node2 = new HeroNode(1,"林冲");
        HeroNode node3 = new HeroNode(2,"李逵");
        HeroNode node4 = new HeroNode(3,"吴用");
        HeroNode node5 = new HeroNode(4,"张顺");
        HeroNode node6 = new HeroNode(5,"卢俊义");
        BinaryTree tree = new BinaryTree();
        tree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRigtht(node3);
        node2.setLeft(node4);
        node2.setRigtht(node5);
        node3.setLeft(node6);
        System.out.println("--------前序遍历---------");
        tree.preOrder();
        System.out.println("--------中序遍历---------");
        tree.midOrder();



    }
}

class BinaryTree{
    private HeroNode root;


    public void preOrder(){
        if(root!=null){
        root.preOrder();
        }else {
            System.out.println("树为空");
        }
    }

    public void midOrder(){
        if(root!=null){
            root.midOrder();
        }else {
            System.out.println("树为空");
        }
    }




    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}


class HeroNode{

    private int no;
    private String name;
    private HeroNode left;//左子树
    private HeroNode rigtht;//右子树

    public HeroNode(int no,String name){
        this.name = name;
        this.no = no;

    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(left!=null){
            left.preOrder();
        }
        if (rigtht!=null){
            rigtht.preOrder();
        }

    }
    //中序遍历
    public void midOrder(){

        if(left!=null){
            left.preOrder();
        }
        System.out.println(this);
        if (rigtht!=null){
            rigtht.preOrder();
        }

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRigtht() {
        return rigtht;
    }

    public void setRigtht(HeroNode rigtht) {
        this.rigtht = rigtht;
    }

    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name=" + name +
                ']';
    }
}