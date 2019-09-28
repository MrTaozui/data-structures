package com01.hashtable;

/**
 * @author taojj .
 *
 * 哈希表（散列表）
 *  用一个数组，来存储一个链表
 *  id 根据散列的方法 定位到
 *  数组所在的下标从而定位到存储的
 *  链表，然后从链表中查找
 *  从而加快查找速度
 *
 *
 */
public class HashTableDemo {


    public static void main(String[] args) {
        HashTable table = new HashTable();
        Emp emp1 = new Emp(1,"tom");
        Emp emp2 = new Emp(2,"jerry");
        Emp emp3 = new Emp(8,"rose");
        table.add(emp1);
        table.add(emp2);
        table.add(emp3);
        table.showInfo();

    }
}


class Emp {
    int id;
    String name;
    Emp next;

    public Emp(int id,String name){
        this.id = id;
        this.name = name;

    }
}

class EmpLinkList{
    private Emp head = new Emp(0,null);//不存数据

    public int size(){
        int size = 0;
        Emp curr = head;
        while (true){
            if(curr.next==null){
                return size;
            }
            size++;
            curr = curr.next;

        }
    }

    public void add(Emp emp){
        Emp tmp = head;
        while (true){
            if (tmp.next==null){
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = emp;
    }

    public void list(int no){
        System.out.println("链表"+no+"信息为：");
        Emp tmp = head.next;
        while (tmp!=null){
            System.out.printf("\tid%d\t 姓名%s\n",tmp.id,tmp.name);
            tmp = tmp.next;
        }
    }

    //find 等方法省略

}

class HashTable{

    private int size = 7;


    private EmpLinkList[]  hashTable;

    public  HashTable(){
        hashTable = new EmpLinkList[this.size];
        for (int i=0;i<size;i++){
            hashTable[i] = new EmpLinkList();
        }

    }
    //hash散列的方法 (这里是简单的一种)
    private int hashFunc(int id){
        return id%this.size;

    }

    public void add(Emp emp){
        int linkNO = hashFunc(emp.id);
        hashTable[linkNO].add(emp);
    }

    public void showInfo(){
        for (int i = 0;i<hashTable.length;i++){
            hashTable[i].list(i);
        }
    }

    //find 等方法省略


}