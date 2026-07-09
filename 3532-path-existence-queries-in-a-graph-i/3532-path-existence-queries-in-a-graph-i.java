class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] a=new int[n];
        int b=0;
        for(int i=1;i<n;i++){
            if(nums[i]-nums[i-1]>maxDiff){
                b++;
            }
            a[i]=b;
        }
        boolean[] ans=new boolean[queries.length];
        for(int i=0;i<queries.length;i++){
            ans[i]=a[queries[i][0]]==a[queries[i][1]];
        }
        return ans;
    }
}