package BingTestTool;

public class AutomatedTester {
    
    protected int curScore;
    protected int maxScore;
    
    //Constructor
    public AutomatedTester() {
        this.curScore = 0;
        this.maxScore = 0;
    }
    
    //Run a test - return true if results are good, false if they are not
    //Increments curScore and maxScore on whether test passes/fails
    public boolean runTest() {
        this.curScore++;
        this.maxScore++;
        return true;
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
