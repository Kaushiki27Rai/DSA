class Solution {
    public char kthCharacter(int k) {
        int size = 1;
        while (size < k) {
            size <<= 1; // double
        }

        int shift = 0;

        while (k > 1) {
            int half = size / 2;
            if (k > half) {
                k -= half;
                shift++;
            }
            size = half;
        }

        return (char) ('a' + (shift % 26));
    }
}