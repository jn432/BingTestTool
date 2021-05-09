package BingTestTool;

public class AutomatedTester {
    
    protected int curScore;
    protected int maxScore;
    
    //Constructor
    public AutomatedTester() {
        this.curScore = 0;
        this.maxScore = 0;
    }
    
    //Evaluate whether the search result matches what our query is looking for
    protected boolean evaluateResult(String querry, String resultTitle) {
        //Unsure on method, discuss with team
        return false;
    }
    
    //Run a test - return true if results are good, false if they are not
    //Increments curScore and maxScore on whether test passes/fails
    public boolean runTest() {
        //generate words
        //search queries using Bing
        //compare search query to search results
        boolean result = this.evaluateResult("blah","blah");
        //add 1 if result was success, otherwise add 0
        this.curScore += (result ? 1 : 0);
        //always increment the max score
        this.maxScore++;
        return result;
    }
    
    //method to run a set amount of tests
    public void runTests(int numTests) {
        for (int i = 0; i < numTests; i++) {
            runTest();
        }
    }
    
    //Getters
    //get the score as a fraction
    public double getScore() {
        if (this.maxScore == 0) {
            return 0;
        }
        return this.curScore/this.maxScore;
    }
    
}
