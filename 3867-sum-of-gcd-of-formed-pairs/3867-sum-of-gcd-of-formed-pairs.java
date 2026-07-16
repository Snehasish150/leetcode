class Solution {
    public long gcdSum(int[] nums) {
        int n=nums.length;
        int max=nums[0];
        List<Integer>list =new ArrayList<>();
        for(int i=0;i<n;i++){
            max=Math.max(max,nums[i]);
            int gcd=getgcd(max,nums[i]);
            list.add(gcd);
        }
        Collections.sort(list);
        long sum=0;
        int i=0,j=n-1;
        while(i<j){
            int curr=getgcd(list.get(j), list.get(i));
            sum+=curr;
            i++;
            j--;
        }
        return sum;
    }
    private int getgcd(int a, int b){
        if(a==0){
            return b;
        }
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
}