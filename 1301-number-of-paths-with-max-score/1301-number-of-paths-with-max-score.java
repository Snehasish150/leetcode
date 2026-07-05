class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n=board.size();
        int[][][] dp=new int[n][n][2];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0]=-1;
            }
        }
        dp[n-1][n-1][0]=0;
        dp[n-1][n-1][1]=1;
        int mod=(int) 1e9+7;
        int[][] dirs={{1,0},{0,1},{1,1}};
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==n-1 && j==n-1) continue;
                if(board.get(i).charAt(j)=='X') continue;
                for(int[] d:dirs){
                    int r=i+d[0], c=j+d[1];
                    if(r<n && c<n && dp[r][c][0] !=-1){
                        if(dp[r][c][0]>dp[i][j][0]){
                            dp[i][j][0]=dp[r][c][0];
                            dp[i][j][1]=dp[r][c][1];
                        } else if(dp[r][c][0]==dp[i][j][0]){
                            dp[i][j][1]=(dp[i][j][1]+dp[r][c][1])%mod;
                        }
                    }
                }
                if(dp[i][j][0]!=-1 &&board.get(i).charAt(j)!='E'){
                    dp[i][j][0]+=board.get(i).charAt(j)-'0';
                }

            }
        }
        return dp[0][0][0]==-1 ? new int[]{0,0} : new int[]{dp[0][0][0],dp[0][0][1]};
    }
}