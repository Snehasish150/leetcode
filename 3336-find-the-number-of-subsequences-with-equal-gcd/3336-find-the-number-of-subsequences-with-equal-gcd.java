class Solution {
    public int subsequencePairCount(int[] nums) {
        int a=1000000007;
        int m=0;
        for(int num:nums) m=Math.max(m,num);
        int[][] dp=new int[m+1][m+1];
        dp[0][0]=1;
        for(int num:nums){
            int[][] ndp=new int[m+1][m+1];
            for(int j=0;j<=m;j++){
                int div1=j==0? num:gcd(j,num);
                for(int k=0;k<=m;k++){
                    if(dp[j][k]==0) continue;
                    int div2=k==0? num:gcd(k,num);
                    ndp[j][k]=(ndp[j][k]+dp[j][k])%a;
                    ndp[div1][k]=(ndp[div1][k]+dp[j][k])%a;
                    ndp[j][div2]=(ndp[j][div2]+dp[j][k])%a;
                }
            }
            dp=ndp;
        }
        int ans=0;
        for(int i=1;i<=m;i++){
            ans=(ans+dp[i][i])%a;

        }
        return ans;
    }
    private int gcd(int c,int b){
        return b==0?c:gcd(b,c%b);
    }
}