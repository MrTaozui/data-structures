package com01.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author tjj .
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);
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


class ArrayQueue {

    private int maxSize;

    private int front;

    private int rare;

    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1; //头指针指向第一个数据的前面一位
        this.rare = -1;//指向最后一个数据
        this.arr = new int[maxSize];
    }

    public boolean isFull() {
        return rare == maxSize-1;
    }

    public boolean isEmpty() {
        return rare == front;
    }

    public void add(int e) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[++rare] = e;

    }

    //从队首取数据
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经空");
        }
        return arr[++front];
    }

    public void info(){
        for (int i : arr){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.println("front="+front+"\t,"+"rare="+rare);
    }


}