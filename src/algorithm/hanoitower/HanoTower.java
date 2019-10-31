package algorithm.hanoitower;

/**
 * @author taojj .
 * <p>
 * 汉诺塔
 * <p>
 * 每个n+1 问题 都是n的问题 再加上一个问题
 * <p>
 * 归并问题
 * 分-小规模直接递归解出-合并
 */
public class HanoTower {

    public static void main(String[] args) {
        move(2, 'A', 'B', 'C');

    }

    /**
     * 移动的方法
     * 使用递归的方式，分两种情况
     * num=1 时直接移动
     * num>=2 时分两层1-> num-1和第num 层
     * 把上层移到b ，再把下层移到c
     * 再把上层从b 移到c
     *
     * @param num 塔的层数
     * @param a   第一棵柱子
     * @param b   第二棵柱子
     * @param c   第三棵柱子
     *
     * 从第一根移到第三根
     */
    public static void move(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
            move(num - 1, a, c, b);//上层借助c移到b   b相当于第三根
            System.out.println(a + "->" + c);//下层从a到c
            move(num - 1, b, a, c);//上层从b 到c
        }


    }
}
