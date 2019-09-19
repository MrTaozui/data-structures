package com01.linkList;

/**
 * @author tjj .
 */
public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleLinkList linkList = new DoubleLinkList();
        linkList.show();
        HeroNode2 node1 = new HeroNode2(1, "1", "1");
        HeroNode2 node2 = new HeroNode2(2, "2", "2");
        HeroNode2 node3 = new HeroNode2(3, "3", "3");
        HeroNode2 node4 = new HeroNode2(4, "4", "4");
        linkList.add(node1);
        linkList.add(node2);
        linkList.add(node3);
        linkList.add(node4);

        linkList.show();
        System.out.println();
        linkList.del(4);
        System.out.println(node3.next);
        linkList.show();
        System.out.println();

        //linkList.add(node3);  //错误 已经在队列中不能重新加入  这个需要校验或者控制
        linkList.add(node4);
        linkList.show();
    }
}

class HeroNode2 {
    int no;
    String name;
    String nikeName;
    HeroNode2 next; //下一个
    HeroNode2 pre;//前一个节点

    public HeroNode2(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}

//单链表
class DoubleLinkList {
    private HeroNode2 head;//头节点

    public DoubleLinkList() {
        this.head = new HeroNode2(0, "", "");
    }

    public void add(HeroNode2 e) {
        HeroNode2 curr = head;
        while (curr.next != null) {//当前节点的下一个节点是否为空
            curr = curr.next;
        }
        curr.next = e;
        e.pre = curr;
    }

    //删除指定编号
    public void del(int no) {
        HeroNode2 curr = head.next;
        boolean find = false;
        while (curr!=null){
            if(curr.no== no){
                find = true;
                break;
            }
            curr = curr.next;
        }
        if(!find){
            System.out.println("没有找到");
            return;
        }else if(curr.next==null){//删除最后一个节点
            curr.pre.next = null;
            curr.pre = null;

        }else{
            curr.pre.next = curr.next;
            curr.next.pre = curr.pre;
        }



    }

    //按照编号顺序添加
    public void sortAdd(HeroNode2 e) {


    }

    //显示链表中的值
    public void show() {
        HeroNode2 curr = head.next;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }

    }

}