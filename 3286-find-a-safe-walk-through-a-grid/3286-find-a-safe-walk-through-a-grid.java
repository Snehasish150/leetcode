class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m=grid.size(), n=grid.get(0).size();
        int[][] dis=new int[m][n];
        for(int[] row:dis)Arrays.fill(row, Integer.MAX_VALUE);
        int[][] dirs={{0,1},{1,0},{0,-1},{-1,0}};
        Deque<int[]>q=new ArrayDeque<>();
        q.offerFirst(new int[]{0,0});
        dis[0][0]=grid.get(0).get(0);
        while(!q.isEmpty()){
            int[] curr=q.pollFirst();
            int cx=curr[0], cy=curr[1];
            if(cx==m-1 && cy==n-1) return true;
            for(int[] d:dirs){
                int nx=cx+d[0], ny=cy+d[1];
                if(nx>=0 && nx<m && ny>=0 && ny<n){
                    int cost=dis[cx][cy]+grid.get(nx).get(ny);
                    if(cost>=health) continue;
                    if(cost<dis[nx][ny]){
                        dis[nx][ny]=cost;
                        if(grid.get(nx).get(ny)==0) q.offerFirst(new int[]{nx,ny});
                        else q.offerFirst(new int[]{nx,ny});
                    }
                }
            }
        }
        return false;
    }
}