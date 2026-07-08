class Solution {
    static long[] pow10=new long[100001];
    static int MOD=1000000007;
    static{
        pow10[0]=1;
        for(int i=1;i<=100000;i++){
            pow10[i]=(pow10[i-1]*10)%MOD;
        }
    }
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n=s.length();
        long[] sum=new long[n+1];
        long[] x=new long[n+1];
        int[] cnt=new int[n+1];
        for(int i=0;i<n;i++){
            int d=s.charAt(i)-'0';
            sum[i+1]=sum[i]+d;
            cnt[i+1]=cnt[i]+(d>0 ?1:0);
            if(d>0){
                x[i+1]=(x[i]*10+d)%MOD;
            } else{
                x[i+1]=x[i];
            }
        }
        int m=queries.length;
        int[] res=new int[m];
        for(int i=0;i<m;i++){
            int l=queries[i][0];
            int r=queries[i][1]+1;
            int length=cnt[r]-cnt[l];
            long curX=(x[r]-(x[l]*pow10[length])%MOD+MOD)%MOD;
            long curSum=(sum[r]-sum[l])%MOD;
            res[i]=(int)((curX*curSum)%MOD);
        }
        return res;
    }
}