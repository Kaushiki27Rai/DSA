class Solution {

    static final int NEG = -1000000000;
    int n, budget;
    int[] present, future;
    List<Integer>[] tree;
    int[][] dp0, dp1, dp2;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.n = n;
        this.present = present;
        this.future = future;
        this.budget = budget;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : hierarchy) tree[e[0] - 1].add(e[1] - 1);

        dp0 = new int[n][budget + 1];
        dp1 = new int[n][budget + 1];
        dp2 = new int[n][budget + 1];

        dfs(0);

        int ans = 0;
        for (int c = 0; c <= budget; c++) {
            ans = Math.max(ans, dp0[0][c]);
            ans = Math.max(ans, dp1[0][c]);
        }
        return ans;
    }

    void dfs(int u) {
        Arrays.fill(dp0[u], NEG);
        Arrays.fill(dp1[u], NEG);
        Arrays.fill(dp2[u], NEG);

        dp0[u][0] = 0;

        int costFull = present[u];
        int costDisc = present[u] / 2;
        int profitFull = future[u] - costFull;
        int profitDisc = future[u] - costDisc;

         if (costFull <= budget)
          dp1[u][costFull] = profitFull;

         if (costDisc <= budget)
           dp2[u][costDisc] = profitDisc;

         for (int v : tree[u]) {
            dfs(v);

            int[] ndp0 = new int[budget + 1];
            int[] ndp1 = new int[budget + 1];
            int[] ndp2 = new int[budget + 1];
            Arrays.fill(ndp0, NEG);
            Arrays.fill(ndp1, NEG);
            Arrays.fill(ndp2, NEG);

            for (int c1 = 0; c1 <= budget; c1++) {
                if (dp0[u][c1] != NEG)
                    merge(ndp0, dp0[u][c1], dp0[v], dp1[v], c1);

                if (dp1[u][c1] != NEG)
                    merge(ndp1, dp1[u][c1], dp0[v], dp2[v], c1);

                if (dp2[u][c1] != NEG)
                    merge(ndp2, dp2[u][c1], dp0[v], dp2[v], c1);
            }

            dp0[u] = ndp0;
            dp1[u] = ndp1;
            dp2[u] = ndp2;
        }
    }

    void merge(int[] res, int base, int[] noBuy, int[] buy, int used) {
        for (int c = 0; c + used <= budget; c++) {
            if (noBuy[c] != NEG)
                res[c + used] = Math.max(res[c + used], base + noBuy[c]);
            if (buy[c] != NEG)
                res[c + used] = Math.max(res[c + used], base + buy[c]);
        }
    }
}
