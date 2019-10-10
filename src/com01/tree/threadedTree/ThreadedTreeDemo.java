package com01.tree.threadedTree;

/**
 * @author taojj .
 * 线索化二叉树
 */
public class ThreadedTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(0,"宋江");
        HeroNode node2 = new HeroNode(1,"林冲");
        HeroNode node3 = new HeroNode(2,"李逵");
        HeroNode node4 = new HeroNode(3,"吴用");
        HeroNode node5 = new HeroNode(4,"张顺");
        HeroNode node6 = new HeroNode(5,"卢俊义");
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRigtht(node3);
        node2.setLeft(node4);
        node2.setRigtht(node5);
        node3.setLeft(node6);
        System.out.println("--------中序遍历---------");
        tree.midOrder();
        System.out.println("--------线索化测试---------");
        tree.midThreadedNodes();
        System.out.println(node4.getLeft());
        System.out.println(node4.getRigtht());
        System.out.println(node5.getLeft());

    }

}

//实现了线索化二叉树的功能
class ThreadedBinaryTree {
    private HeroNode root;

    private HeroNode pre = null;//前驱节点 一开始为空


    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空");
        }
    }

    public void midOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("树为空");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("树为空");
        }
    }

    //对二叉树进行中序线索化
    public void midThreadedNodes(){
        midThreadedNodes(root);
    }

    //对二叉树进行中序线索化
    //叶子节点的空指针有 2n-(n-1) = n+1 个 说明：n个节点共有2n个空指针，有n-1个连线所以2n-(n-1) = n+1

    /**
     * 线索化的时候只能拿到当前节点和 上一个节点
     *
     * @param node
     */
    private void midThreadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //先线索化左子树
        midThreadedNodes(node.getLeft());

        //处理当前节点
        if(node.getLeft()==null){//如果没有左子节点
        node.setLeft(pre);//设置前驱节点
        node.setFlagL(1);
        }
        if(pre!=null&&pre.getRigtht()==null){//前一个节点的后继节点是当前节点
            pre.setRigtht(node);//设置前驱节点的后继节点
            pre.setFlagR(1);
        }
        pre = node;//当前节点线索化完成，当前节点变成上一个节点

        //再线索化右子树
        midThreadedNodes(node.getRigtht());

    }


    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}


class HeroNode {

    private int no;
    private String name;
    private HeroNode left;//左子树
    private HeroNode rigtht;//右子树

    private int flagL;//标志 为1 时存储的是 前驱节点 为0的时候存储的是左子树

    private int flagR;//标志 为1 时存储的是 后继节点 为0的时候存储的是右子树

    public HeroNode(int no, String name) {
        this.name = name;
        this.no = no;

    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (rigtht != null) {
            rigtht.preOrder();
        }

    }

    //中序遍历
    public void midOrder() {


        if (left != null) {
            left.setFlagL(0);
            left.midOrder();
        }

        System.out.println(this);
        if (rigtht != null) {
            rigtht.midOrder();
        }


    }

    //后序遍历
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (rigtht != null) {
            rigtht.postOrder();
        }
        System.out.println(this);
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

    public int getFlagL() {
        return flagL;
    }

    public void setFlagL(int flagL) {
        this.flagL = flagL;
    }

    public int getFlagR() {
        return flagR;
    }

    public void setFlagR(int flagR) {
        this.flagR = flagR;
    }

    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name=" + name +
                ']';
    }
}