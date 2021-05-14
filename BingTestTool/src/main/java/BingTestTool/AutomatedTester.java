package BingTestTool;

import java.util.ArrayList;

public class AutomatedTester {
    
    private int curScore;
    private int maxScore;
    private int testsPassed;
    private int totalTests;
    
    //Constructor
    public AutomatedTester() {
        this.curScore = 0;
        this.maxScore = 0;
        this.testsPassed = 0;
        this.totalTests = 0;
    }
    
    //Evaluate whether the search result matches what our query is looking for
    private boolean evaluateResult(String query, SearchResult result) {
        //Unsure on method, discuss with team
        //returns true if link, title or description contains the word
        //otherwise it returns false
        return result.getLink().toLowerCase().contains(query.toLowerCase()) || 
                result.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                result.getDescription().toLowerCase().contains(query.toLowerCase());
    }
    
    //Run a test - return true if results are good, false if they are not
    //Increments curScore and maxScore on whether test passes/fails
    public boolean runTest() {
        //generate words
        WordGenerator wordGen = new WordGenerator();
        String word = wordGen.generateWord();
        
        //search queries using Bing
        BingParser bp = new BingParser(word);
        ArrayList<SearchResult> results = bp.getSearchTitles();
        
        //SCORING SECTION - VERY BASIC
        int score = 0;
        
        //compare search query to search results
        for (SearchResult result : results) {
            boolean test = this.evaluateResult(word, result);
            //SCORING SECTION - VERY BASIC
            //add 1 if result was success, otherwise add 0
            score += (test ? 1 : 0);
        }
        
        //add points
        this.curScore += score;
        this.maxScore += results.size();
        
        //Counting number of tests passed
        //add 1 if test scored at least 50%
        this.testsPassed += ((score * 2 >= results.size()) ? 1 : 0);
        //always increment the max score
        this.totalTests++;
        
        //return true if tests passed
        return (score * 2 >= results.size());
        
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
    
    public double getTestsPassedFraction() {
        if (this.totalTests == 0) {
            return 0;
        }
        return this.testsPassed/this.totalTests;
    }
    
    public boolean evaluateResultTest(String query, SearchResult result) {
        return evaluateResult(query, result);
    }
}
