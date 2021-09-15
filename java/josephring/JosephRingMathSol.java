package josephring;

/**
 * 约瑟夫环:数学 + 迭代解法
 * 复杂度分析
 * <p>
 * 时间复杂度：O(n)，需要求解的函数值有 n 个。
 * <p>
 * 空间复杂度：O(1)，只使用常数个变量。
 */
class JosephRingMathSol {
    public static int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }
}