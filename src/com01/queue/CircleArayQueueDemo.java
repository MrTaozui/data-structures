package com01.queue;

import java.util.Scanner;

/**
 * @author tjj .
 */
public class CircleArayQueueDemo {

    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(3);
        int key;
        while (true){
            try {


                System.out.println("请输入你的操作：");
                System.out.println("add  1");
                System.out.println("get  2");
                System.out.println("info 3");
                System.out.println("exit 4");
                Scanner sc = new Scanner(System.in);


                key = sc.nextInt();
                switch (key){
                    case 1:
                        queue.add(sc.nextInt());
                        System.out.println("添加成功！");
                        break;
                    case 2:
                        System.out.println(queue.get());
                        break;
                    case 3:
                        queue.info();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                }
            }catch (Exception ee){
                ee.printStackTrace();
            }


        }


    }

}

class CircleArrayQueue {

    private int maxSize;

    private int front;

    private int rare;

    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        //this.front = 0; //头指针指向第一个数据
        //this.rare = 0;//指向最后一个数据的下一位 最后一个数据为空 所以下一位是0
        this.arr = new int[maxSize];
    }

    public boolean isFull() {
        //return size() == maxSize-1; //方法一
        return  (rare+1)%maxSize == front;
    }

    public int size(){
        return  (rare - front + maxSize) % maxSize;
    }

    public boolean isEmpty() {
        return front == rare;
    }

    public void add(int e) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rare] = e;
        rare = (rare + 1) % maxSize;

    }

    //从队首取数据
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经空");
        }
        int crr = front;
        front = (front + 1) % maxSize;
        return arr[crr];
    }

    public void info() {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("front=" + front + "\t," + "rare=" + rare);
    }

}
