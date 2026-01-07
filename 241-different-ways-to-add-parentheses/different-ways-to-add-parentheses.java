class Solution {
    List<Integer> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    List<Integer>[][] dp;

    public List<Integer> diffWaysToCompute(String expression) {
        parse(expression);

        int n = nums.size();
        dp = new ArrayList[n][n];

        return compute(0, n - 1);
    }

    private List<Integer> compute(int l, int r) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }

        List<Integer> res = new ArrayList<>();

        if (l == r) {
            res.add(nums.get(l));
        } else {
            for (int k = l; k < r; k++) {
                List<Integer> left = compute(l, k);
                List<Integer> right = compute(k + 1, r);

                char op = ops.get(k);
                for (int a : left) {
                    for (int b : right) {
                        if (op == '+') res.add(a + b);
                        else if (op == '-') res.add(a - b);
                        else res.add(a * b);
                    }
                }
            }
        }

        dp[l][r] = res;
        return res;
    }

    private void parse(String expr) {
        int num = 0;
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                nums.add(num);
                ops.add(c);
                num = 0;
            }
        }
        nums.add(num);
    }
}