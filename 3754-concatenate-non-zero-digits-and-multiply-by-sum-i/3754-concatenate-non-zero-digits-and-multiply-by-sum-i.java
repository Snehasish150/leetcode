class Solution {
    public long sumAndMultiply(int n) {
       String s = String.valueOf(n);

        long x = 0;
        long sum = 0;

        for (char c : s.toCharArray()) {
            int d = c - '0';
            if (d != 0) {
                x = x * 10 + d;
                sum += d;
            }
        }
        return x*sum;
    }
}