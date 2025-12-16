class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Initialize queue with all rotten oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // No fresh oranges
        if (fresh == 0) return 0;

        int minutes = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottedThisMinute = false;

            for (int s = 0; s < size; s++) {
                int[] cell = q.poll();
                int x = cell[0], y = cell[1];

                for (int[] d : dirs) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        fresh--;
                        q.offer(new int[]{nx, ny});
                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
