class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int lo = Integer.MAX_VALUE, hi = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            lo = Math.min(lo, e[2]);
            hi = Math.max(hi, e[2]);
        }

        if (edges.length == 0) return -1;

        // Topological order
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        int[] deg = indegree.clone();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) q.offer(i);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : graph[u]) {
                if (--deg[e[0]] == 0) q.offer(e[0]);
            }
        }

        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, graph, topo, online, k, n)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, List<int[]>[] graph, int[] topo,
                          boolean[] online, long k, int n) {

        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int u : topo) {
            if (dp[u] == INF) continue;

            // Intermediate offline nodes cannot be used.
            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                if (w < limit) continue;
                if (v != n - 1 && !online[v]) continue;

                if (dp[u] + w < dp[v]) {
                    dp[v] = dp[u] + w;
                }
            }
        }

        return dp[n - 1] <= k;
    }
}