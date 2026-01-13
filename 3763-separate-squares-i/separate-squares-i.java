class Solution {
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        // Determine search range
        for (int[] sq : squares) {
            low = Math.min(low, sq[1]);
            high = Math.max(high, sq[1] + sq[2]);
        }

        // Binary search
        for (int iter = 0; iter < 100; iter++) { // enough for precision
            double mid = (low + high) / 2.0;

            double below = 0.0;
            double above = 0.0;

            for (int[] sq : squares) {
                double y = sq[1];
                double l = sq[2];
                double top = y + l;
                double area = l * l;

                if (mid <= y) {
                    // entire square above
                    above += area;
                } else if (mid >= top) {
                    // entire square below
                    below += area;
                } else {
                    // split square
                    below += (mid - y) * l;
                    above += (top - mid) * l;
                }
            }

            if (below < above) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}