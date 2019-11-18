package algorithm.kmp;

import java.util.Arrays;

/**
 * @author taojj .
 * <p>
 * KMP算法
 */
public class KMP {

    public static void main(String[] args) {
        String str = "abaabbabaab";//ABCDABD
        System.out.println(Arrays.toString(buildNext(str)));

    }


    /**
     * 构建next 数组
     *
     * @param str
     * @return
     */
    private static int[] buildNext(String str) {

        int[] next = new int[str.length()];
        next[0] = 0;
        /**
         * j代表前缀索引 i 代表后缀索引
         *
         * 前缀的匹配的开头总是从j = 0开始的
         * 后缀的开头 是从前缀的后一位开始的 i = j+1
         *
         * 用后缀来匹配前缀
         */
        for (int i = 1, j = 0; i < str.length(); i++) {
            /**
             * while  回溯
             */
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1]; // next[i] 中保存的是 以当前字母str[i] 为后缀匹配到得长度
            }

            //如果后缀字符等于前缀字符
            if (str.charAt(j) == str.charAt(i)) {
                j++;//前缀字符索引后移，匹配下一个
            }
            next[i] = j;//next 保存匹配的个数 匹配的个数正好与j 有这个关系
        }
        return next;

    }




}
