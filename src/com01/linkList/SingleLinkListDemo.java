package com01.linkList;

import java.util.Stack;

/**
 * @author tjj .
 */
public class SingleLinkListDemo {
    public static void main(String[] args) {
        SingleLinkList linkList = new SingleLinkList();
        linkList.show();
        HeroNode node1 = new HeroNode(1, "1", "1");
        HeroNode node2 = new HeroNode(2, "2", "2");
        HeroNode node3 = new HeroNode(3, "3", "3");
        HeroNode node4 = new HeroNode(4, "4", "4");
//        linkList.add(node1);
//        linkList.add(node2);

        linkList.sortAdd(node3);
        linkList.sortAdd(node2);
        linkList.sortAdd(node1);
        linkList.sortAdd(node4);

        linkList.show();
        linkList.del(4);
        linkList.show();
        System.out.println("反转打印");
        linkList.reverseShow();




    }


}

class HeroNode {
    int no;
    String name;
    String nikeName;
    HeroNode next; //下一个

    public HeroNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}

//单链表
class SingleLinkList {
    private HeroNode head;//头节点

    public SingleLinkList() {
        this.head = new HeroNode(0, "", "");
    }

    public void add(HeroNode e) {
        HeroNode curr = head;
        while (curr.next != null) {//当前节点的下一个节点是否为空
            curr = curr.next;
        }
        curr.next = e;
    }

    //删除指定编号
    public HeroNode del(int no) {
        HeroNode result = null;
        HeroNode curr = head;
        boolean fined = false;
        while (curr.next != null) {
            if (curr.next.no == no) {
                fined = true;
                break;
            }
            curr = curr.next;
        }
        if (!fined) {
            System.out.println("没有找到！");
        } else {
            result = curr.next;
            curr.next = curr.next.next;//从中删除
        }
        return result;
    }

    //按照编号顺序添加
    public void sortAdd(HeroNode e) {
        HeroNode curr = head;
        while (curr.next != null) {
            if (e.no <= curr.next.no){
                break;
            }
            curr = curr.next;
        }
        e.next = curr.next;
        curr.next = e;

    }

    //显示链表中的值
    public void show() {
        HeroNode curr = head.next;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }

    }
    //反转打印 不能破坏链表结构

    public void reverseShow(){
        Stack<HeroNode> stack = new Stack<>();
        HeroNode curr = this.head.next;
        while (curr!=null){
            stack.push(curr);
            curr = curr.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

}

