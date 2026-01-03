class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
         int empty = 0;
        int drank = 0;

        drank += numBottles;
        empty += numBottles;

        while (empty >= numExchange) {
            empty -= numExchange;   
            numExchange++;          
            drank++;                
            empty++;                
        }

        return drank;
    }
}