package algorithm.bsearchnorecur;

/**
 * @author taojj .
 * <p>
 * 二分查找的非递归方式
 */
public class BSearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {0,1,3,5,7,8,9};
        System.out.println(search(arr,0));

    }

    /**
     * @param arr   被查找的数组
     * @param value 查找的值
     * @return 下标
     */
    public static int search(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (value < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        return -1;
    }
}
