class Solution {
    public int lengthOfLastWord(String s) {

        s=s.trim();

        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        int length = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                break;
            }
            length++;
        }

        return length;
    }
}