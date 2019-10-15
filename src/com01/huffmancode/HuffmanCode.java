package com01.huffmancode;

import java.util.*;

/**
 * @author taojj .
 *
 * 赫夫曼编码  前缀编码
 */
public class HuffmanCode {

    private static Map<Byte,String> huffmanCodeMap = new HashMap<>();//哈夫曼编码表

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        //String str = "ab"; //会有问题 因为最后一步的处理01  会变成1
        //Map<Byte, String> huffmanTable = genHuffmanCode(str);
        //huffmanTable.forEach((k,v)-> System.out.println(k+":"+v));
        byte[] zip = zip(str);
        // 10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
        //
        System.out.println(Arrays.toString(zip));


        System.out.println(unzip(zip,huffmanCodeMap));
//        System.out.println(Integer.parseInt("00101",2));
//        System.out.println(Integer.toBinaryString(5));


    }

    //生成哈夫曼树
    public static Node genhuffmanTree(byte[] bytes){
        Map<Byte,Node> nodeMap = new HashMap<>();
        for (byte byt:bytes){
            if(nodeMap.get(byt)!=null){
                Node node = nodeMap.get(byt);
                node.setWeight(node.getWeight()+1);

            }else {
                Node node = new Node();
                node.setCode(byt);
                node.setWeight(1);
                nodeMap.put(byt,node);
            }

        }
        //生成哈夫曼树

        List<Node> list = new ArrayList<>();
        Set<Byte> nodeKeySet = nodeMap.keySet();
        for (byte key:nodeKeySet){
            list.add(nodeMap.get(key));
        }
        //list.forEach( x->System.out.println(x));
        while (list.size()>1) {
            Collections.sort(list);
            Node left = list.get(0);//左子树
            Node right = list.get(1);//右子树
            Node parent = new Node();
            parent.setWeight(left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            list.add(parent);
            list.remove(left);
            list.remove(right);
        }
        return list.get(0);

    }

    /**
     * 生成哈夫曼编码
     * @param str
     * @return
     */

    public static  Map<Byte,String> genHuffmanCode(String str){
        byte[] bytes = str.getBytes();
        Node root = genhuffmanTree(bytes);
        StringBuilder code = new StringBuilder();
        genHufManCode(root,"",code);
        return huffmanCodeMap;

    }

    /**
     * 生成哈夫曼编码表
     * @param node 当前节点
     * @param path 过来的路径 parent -left-node 0  parent -right-node 1
     * @param parentCode
     *
     * 过来的路径为当前节点的编码
     */
    public static void genHufManCode(Node node,String path,StringBuilder parentCode){
        if (node==null){
            return;
        }
        StringBuilder curentCode = new StringBuilder(parentCode);//当前的路径编码
        curentCode.append(path);
        if (node.getCode()==null){//不为叶子节点（构建哈夫曼树的时候的性质）
            genHufManCode(node.getLeft(),"0",curentCode);//向左构建
            genHufManCode(node.getRight(),"1",curentCode);//向右构建

        }else {//叶子节点

            huffmanCodeMap.put(node.getCode(),curentCode.toString());
        }

    }

    // huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
    //二进制转为字符串是补码的形式展示的，所以 原码推导为上步骤 补码结果是-40 ，但是-88按照字符串打印还是补码，即二进制展示是补码展示
    public static byte[] zip(String str){
        Map<Byte, String> huffmanTable = genHuffmanCode(str);
        byte[] bytes = str.getBytes();
        StringBuilder strCode = new StringBuilder();

        for (byte byt:bytes){
            strCode.append(huffmanTable.get(byt));
        }
//"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
       // System.out.println(strCode.toString());
        int length = (strCode.length()+7)/8;//长度不足8的倍数的时候 补足
        byte[] result = new byte[length];

        for (int i=0;i<strCode.length();i+=8){
            String unitCode;
            if(i+8>strCode.length()){
                unitCode = strCode.substring(i);
            }else {
                unitCode = strCode.substring(i, i + 8);
            }
            result[i/8] = (byte) Integer.parseInt(unitCode, 2);//转二进制

        }
        return result;

    }

    /**
     *
     * @param byt 需要转码的字符
     * @param flag 是否需要补高位 因为6是0000 0110转过来的 所以需要补足回去 true 需要 false 不需要
     */
    public static String byteToBitString(byte byt,boolean flag){
        String str=null;
        int tmp = byt;
        if(flag){
            str=  Integer.toBinaryString(tmp|=256);
            str = str.substring(str.length()-8);//截取后8位

        }else {
            str = Integer.toBinaryString(tmp);
        }
        //System.out.println(str);
       return str;

    }



    /**
     * 解压
     * @param bytes 待解压字节数组
     * @param huffmanTable 哈夫曼编码表
     * @return
     */
    public static String unzip(byte[] bytes,Map<Byte,String> huffmanTable){
        StringBuilder decode = new StringBuilder();
        List<Byte> result =new ArrayList<>();//中间转码存储
        Map<String,Byte> table = new HashMap<>();


        for (Map.Entry<Byte,String> entry:huffmanTable.entrySet()){//反转哈夫曼编码表
            table.put(entry.getValue(),entry.getKey());
        }

        //把byte 翻译成哈夫曼后的字节码
        for (int i=0;i<bytes.length-1;i++){
           String decodeStr =  byteToBitString(bytes[i],true);
            decode.append(decodeStr);
        }
        String decodeStr = byteToBitString(bytes[bytes.length-1],false);//最后一位不需要调 因为以前压缩的时候 8位一个 剩余的变成了一个
        decode.append(decodeStr);


        for (int i = 0; i <decode.length(); ) {//转换成原字符
            int k=1;
            while (true){
                Byte strByt = table.get(decode.substring(i,i+k));
                if(strByt==null){
                    k++;
                }else {
                    result.add(strByt);
                    i+=k;
                    break;
                }
            }

        }

        byte[] resultByts = new byte[result.size()];//构造返回结果
        for (int i = 0; i <result.size() ; i++) {
            resultByts[i] = result.get(i);

        }
        return new String(resultByts);


    }


}



class Node implements Comparable<Node>{
    private Node left;//左子树

    private Node right;//右子树

    private int weight;//权重

    private Byte code;//节点的字节码

    //当前子树的前序遍历
    public void preOder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOder();
        }
        if(this.right!=null){
            this.right.preOder();
        }
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.getWeight();
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", code=" + code +
                '}';
    }
}