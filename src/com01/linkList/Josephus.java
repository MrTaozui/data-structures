package com01.linkList;

/**
 * @author tjj .
 *
 * 约瑟夫 问题
 *
 * 一定要按照实际逻辑来操作
 */
public class Josephus {

    public static void main(String[] args) {
        Student stu1 = new Student(1);
        Student stu2 = new Student(2);
        Student stu3 = new Student(3);
        Student stu4 = new Student(4);
        Student stu5 = new Student(5);
        CircleLinkList list =new CircleLinkList();
//        list.add(stu1);
//        list.show();
//        list.add(stu2);
//        list.add(stu3);
//        list.add(stu4);
//        list.add(stu5);
//        list.show();
//        System.out.println(list.getCurrent().no);

        list.josephusProblem(5,2,2);
    }
}

class Student{
    public int no; //学号

    public Student next;//下一个

    public Student(int no){
        this.no = no;
    }
}



class CircleLinkList{

    private Student first;//第一个  环的特性

    private Student current;

    public void add(Student stu){
        if (first==null){
            first = stu;
            first.next = first;
            current = first;
        }else {
                current.next = stu; //插入到当前节点的下一个
                stu.next = first;//被插入节点的下一个是 第一个节点 相当于插入到末尾
                current = stu;//当前节点是stu;

        }
    }

    public Student getCurrent(){
        Student curr = null;

        if(first!=null){
            curr = first;
            while (curr.next!=first){
                    curr = curr.next;
            }
        }
        return curr;

    }

    public void show(){
        if (first==null){
            System.out.println("环形链表为空");
            return;
        }
        Student curr = first;
        do {
            System.out.println(curr.no);
            curr = curr.next;
        }while (curr!=first);

    }

    /**
     *
     * @param stuNo 学生的数量
     * @param starNo 开始的数量
     * @param num 数的数
     */
    public void josephusProblem(int stuNo,int starNo,int num){
        CircleLinkList list = new CircleLinkList();
        for (int i = 1; i <= stuNo; i++) {
            Student stu = new Student(i);
            list.add(stu);
        }
        Student first = list.getFirst();
        Student current = list.getCurrent(); //需要拿到待删除指针的上一个指针 这样才能删除后面的指针
        while (starNo-1>0){
            first = first.next;
            current = current.next;
            starNo--;
        }
        while (true){
            if (first==current){
                System.out.println(current.no);
                break;
            }

            for (int i=0;i<num-1;i++){
                first = first.next;
                current = current.next;
            }
            System.out.println(first.no);
            first = first.next;
            current.next = first;
        }


    }


    public Student getFirst() {
        return first;
    }

    public void setFirst(Student first) {
        this.first = first;
    }

}