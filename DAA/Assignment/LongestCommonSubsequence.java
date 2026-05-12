package JavaCoding.DAA.Assignment;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String String1 = "ZUBAIR";
        String String2 = "ETAOINSHR";

        int m = String1.length();
        int n = String2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (String1.charAt(i - 1) == String2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i][j - 1]);
                }
            }
        }
        System.out.println("Length of Longest Common Subsequence = " + dp[m][n]);
    }
}
