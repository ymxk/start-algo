package josephring;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 约瑟夫环:数学 + 迭代解法
 * 复杂度分析
 * <p>
 * 时间复杂度：O(n)，需要求解的函数值有 n 个。
 * <p>
 * 空间复杂度：O(1)，只使用常数个变量。
 *
 * @author keno
 * @link https://zh.wikipedia.org/wiki/%E7%BA%A6%E7%91%9F%E5%A4%AB%E6%96%AF%E9%97%AE%E9%A2%98
 */
public class JosephRingMathSol {
    /**
     * @param n 数组长度
     * @param m 跳过几个
     * @return 幸存者
     */
    public static int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            // (当前index + m) % 上一轮剩余数字的个数
            ans = (ans + m) % i;
//            System.out.println(ans);
        }
        return ans;
    }

    public static int lastRemainingNormal(int n, int m) {
        List<Integer> array = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            array.add(i);
        }
        removeArray(array, m - 1);
        return 0;
    }

    public static int removeArray(List<Integer> array, int index) {
        if (array.size() == 1) {
            return array.get(0);
        }
        if (array.size() <= index) {
            array = padArray(array);
        }
        array.remove(index);
        int size = array.size();
        Set<Integer> dups = findDuplicateByFrequency(array);
        for (Integer dup : dups) {
            for (int i = 0; i < size - 1; i++) {
                if (array.get(i).equals(dup)) {
                    array.remove(i);
                }
            }
        }
        return removeArray(array, index);
    }


    public static <T> Set<T> findDuplicateByFrequency(List<T> list) {
        return list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
    }


    public static List<Integer> padArray(List<Integer> array) {
        List<Integer> pads = new ArrayList<>(array.size() * 2);
        pads.addAll(array);
        pads.addAll(array);
        return pads;
    }


    public static void main(String[] args) {
        System.out.println(lastRemainingNormal(5, 3));
//        System.out.println(lastRemaining(5, 3));
    }
}