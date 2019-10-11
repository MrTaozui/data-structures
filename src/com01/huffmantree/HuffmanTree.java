package com01.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author taojj .
 * 赫夫曼树：所有叶子节点的带权路径和最小的树 WPL最小的
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = genHufMan(arr);
        preOrder(root);

    }

    /**
     * 生成赫夫曼树
     *
     * @param arr
     */
    public static Node genHufMan(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int i : arr) {
            Node node = new Node(i);
            list.add(node);
        }
        Node parent = null;//最后返回的节点
        while (list.size() > 1) { //当一个节点时 就停止
            Collections.sort(list);//先排序
            Node leftNode = list.get(0);//左子树
            Node rigthNode = list.get(1);//右子树
            parent = new Node(leftNode.getValue()+rigthNode.getValue());//构建父节点
            parent.setLeftNode(leftNode);
            parent.setRightNode(rigthNode);
            list.remove(leftNode);
            list.remove(rigthNode);
            list.add(parent);
        }
        return parent;

    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空");
            return;
        }
        root.preOrder();

    }


}

class Node implements Comparable<Node> {
    private int value;//权值

    private Node leftNode;//左子树

    private Node rightNode;//右子树

    public Node(int value) {
        this.value = value;
    }

    //前序遍历当前子树 单个节点右左右子节点 堪称一棵树（可能左右子节点为空）
    public void preOrder() {
        System.out.println(this);
        if (this.getLeftNode() != null) {//前序遍历左子树
            this.getLeftNode().preOrder();
        }
        if (this.getRightNode() != null) {//前序遍历右子树
            this.getRightNode().preOrder();
        }

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//升序排列
    }
}
