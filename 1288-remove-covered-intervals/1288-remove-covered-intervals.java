class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b) ->(a[0]==b[0]?b[1]-a[1]:a[0]-b[0]));
        int count=0,max_end=-1;
        for(int[] interval:intervals){
            if(interval[1]>max_end){
                count++;
                max_end=interval[1];
            }
        }
        return count;
    }
}