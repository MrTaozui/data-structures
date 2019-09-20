package com01.stack;

/**
 * @author tjj .
 */
public class Calculator {

    public static void main(String[] args) {
        //1 + ( 2 + 3 ) * 4  中缀表达式 转后缀表达式 （逆波兰表达式）
        String ex = "1 + ( 2 + 8 ) / 2";
        String resv = getResBolan(ex);
        System.out.println(resv); // 1 2 3 + 4 * +
        // 21
        System.out.println(getRreult(resv));

    }


    public static String getResBolan(String expression){ //为了方便表达式字符之间 用空格隔开
        ArraySk opSk = new ArraySk(10);
        String[] sarr = expression.split(" ");
        String result = "";

        for (int i = 0;i<sarr.length;i++){
            if(isNo(sarr[i])){
                result+=" "+sarr[i];
            }else {
                if(opSk.isEmpty()){
                    opSk.push(sarr[i]);
                }else {

                    String opstr = sarr[i];
                    if (opstr.equals("(")){
                        opSk.push(opstr);
                        continue;
                    }

                    if(opstr.equals(")")){
                        while (true){
                            String pop = opSk.pop();
                            if(!pop.equals("(")){
                            result+= " "+pop;
                            }
                            if(pop.equals("(")){
                                break;
                            }
                        }
                    }else {
                        int level1 = getPiror(opstr);
                        String  seek = opSk.seek();
                        int  seekLevel = getPiror(seek);
                        if(level1<seekLevel){
                           String newpop =  opSk.pop();
                           result+=" "+newpop;
                           opSk.push(opstr);
                        }else {
                            opSk.push(opstr);
                        }

                    }


                }
            }

        }
        while (!opSk.isEmpty()){
            result +=" " + opSk.pop();
        }

        return result;
    }

    /**
     * 根据逆波兰表达式 得出结果
     * @param niBoExp
     * @return
     */
    public static int getRreult(String niBoExp){
        String arr[] = niBoExp.split(" ");
        ArraySk stack = new ArraySk(10);
        int result = 0;
        for (int i = 0;i<arr.length;i++){
            String str = arr[i];
            if(stack.isEmpty()){
                stack.push(str);
                continue;
            }

            if (isNo(str)){
                stack.push(str);
            }else {
               String opNumStr1 = stack.pop();
               String opNumStr2 = stack.pop();
               Integer opNum2 = Integer.parseInt(opNumStr1);
               Integer opNum1 = Integer.parseInt(opNumStr2);
               result = getCal(opNum1,opNum2,str.charAt(0));
               stack.push(String.valueOf(result));
            }

        }
        result = Integer.parseInt(stack.pop());
        return result;

    }


    public static int getCal(int num1,int num2,char op){
        int result = 0;

        switch (op){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
        }

    return result;

    }

    public static boolean isNo(String ch){
        try {
            Integer.parseInt(ch);
        }catch (Exception e){
            return false;
        }
        return  true;
    }

    public static int getPiror(String sign){
        if(sign =="*"|| sign=="/" ){
            return 2;
        }
        //else if(sign =="+"|| sign=="-" )
            return 1;

    }





}

class ArraySk{
    //栈需要栈底和栈顶  把数组的第一个作为栈底

    private  int maxSize;

    private int top;

    private String[] arr;

    public ArraySk(int maxSize){
        this.maxSize = maxSize;
        this.top = -1;
        this.arr = new String[maxSize];
    }

    public boolean isEmpty(){
        return this.top == -1;
    }

    public boolean isFull(){
        return this.top == maxSize-1;
    }

    public void push(String e){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        arr[top] = e;
    }

    public String pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空!");
        }
        return arr[top--];
    }

    public String seek(){
        return arr[top];
    }

}


