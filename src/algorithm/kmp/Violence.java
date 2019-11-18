package algorithm.kmp;

/**
 * @author taojj .
 * <p>
 * 暴力匹配方式
 */
public class Violence {
    public static void main(String[] args) {

        String str1 = "helloworld123";
        String str2 = "world";
        System.out.println(violence(str1,str2));

    }


    /**
     * 暴力匹配
     *找出target中的 str
     * @param target 目标串
     * @param str    子串
     * @return
     */
    public static int violence(String target, String str) {
        int i = 0;
        int j = 0;
        int matchNum = 0;//匹配到的数值

        while (j < str.length() && i < target.length()) {
            if(str.charAt(j)==target.charAt(i)){
                i++;
                j++;
                matchNum++;
            }else {
                j=0;
                i++;
            }
            if(matchNum==str.length()){
                return i-matchNum;
            }
        }

        return -1;

    }
}
