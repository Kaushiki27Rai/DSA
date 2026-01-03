class Solution {
    private static final int MOD = 1_000_000_007;
    public int numOfWays(int n) {
        long dpA = 6; // ABC pattern
        long dpB = 6; // ABA pattern

        for (int i = 2; i <= n; i++) {
            long newA = (2 * dpA + 2 * dpB) % MOD;
            long newB = (2 * dpA + 3 * dpB) % MOD;
            dpA = newA;
            dpB = newB;
        }

        return (int) ((dpA + dpB) % MOD);
    }
}