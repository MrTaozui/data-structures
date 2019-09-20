package com01.stack;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * @author tjj .
 *
 * 数组栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        //stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}

class ArrayStack{
    //栈需要栈底和栈顶  把数组的第一个作为栈底

    private  int maxSize;

    private int top;

    private int[] arr;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.top = -1;
        this.arr = new int[maxSize];
    }

    public boolean isEmpty(){
        return this.top == -1;
    }

    public boolean isFull(){
        return this.top == maxSize-1;
    }

    public void push(int e){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        arr[top] = e;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空!");
        }
        return arr[top--];
    }

}
