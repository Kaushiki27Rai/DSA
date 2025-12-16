class Solution {
    private static final int MOD = 1_000_000_007;

    public int specialTriplets(int[] nums) {
        int n = nums.length;

        Map<Integer, Long> leftFreq = new HashMap<>();
        Map<Integer, Long> rightFreq = new HashMap<>();

        for (int x : nums) {
            rightFreq.put(x, rightFreq.getOrDefault(x, 0L) + 1);
        }

        long ans = 0;

        for (int j = 0; j < n; j++) {
            int mid = nums[j];

            // remove mid from right side
            rightFreq.put(mid, rightFreq.get(mid) - 1);
            if (rightFreq.get(mid) == 0) {
                rightFreq.remove(mid);
            }

            int target = mid * 2;

            long leftCount = leftFreq.getOrDefault(target, 0L);
            long rightCount = rightFreq.getOrDefault(target, 0L);

            ans = (ans + leftCount * rightCount) % MOD;

            // add mid to left side
            leftFreq.put(mid, leftFreq.getOrDefault(mid, 0L) + 1);
        }

        return (int) ans;
    }
}
