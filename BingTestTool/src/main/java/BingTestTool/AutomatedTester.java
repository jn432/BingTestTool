package BingTestTool;

import java.util.ArrayList;

public class AutomatedTester {

    private int curScore;
    private int maxScore;
    private int testsPassed;
    private int totalTests;
    
    private String testResults;

    //Constructor
    public AutomatedTester() {
        this.curScore = 0;
        this.maxScore = 0;
        this.testsPassed = 0;
        this.totalTests = 0;
        this.testResults = "";
    }

    //Evaluate whether the search result matches what our query is looking for
    private boolean evaluateResult(String query, SearchResult result) {
        boolean testResult = true;
        GoogleParser gp = null;
        //split the search terms into an array of Strings
        String[] words = query.toLowerCase().split(" ");
        //for each word in the query
        for (String word : words) {
            //search for it in the link, title and description
            //if it is not found, change testResult to false and first test ends
            if (!(result.getLink().toLowerCase().contains(word)
                    || result.getTitle().toLowerCase().contains(word)
                    || result.getDescription().toLowerCase().contains(word))) {
                testResult = false;
                break;
            }
        }

        //if words do not appear in the link, title and description,
        //it means testResult is currently false
        //check if google search returns has the link
        if (!testResult) {
            //create GoogleParser object if it has not been created yet
            if (gp == null) {
                gp = new GoogleParser(query);
            }

            //get links from first page of google search    
            ArrayList<String> googleLinks = gp.getSearchTitles();
            //if the search result link appears, set testResult to true
            //and break the loop
            for (String link : googleLinks) {
                if (link.equals(result.getLink())) {
                    testResult = true;
                    System.out.println("found result in google");
                    break;
                }
            }            
        }
        //return the results of the tests
        //will return true if search results has all the words or
        //if the link appeared within googles search results first page
        return testResult;
    }

    //Run a test - return true if results are good, false if they are not
    //Increments curScore and maxScore on whether test passes/fails
    public boolean runTest(int numWords, int maxResults, boolean addError) {
        //generate words for search query
        WordGenerator wordGen = new WordGenerator();
        String searchTerm = "";
        String searchTermSent = "";
        for (int i = 0; i < numWords; i++) {
            //generate a word
            String word = wordGen.generateWord();
            
            //if we are generating errors, add a mistake to the word
            if (addError) {
                searchTermSent += wordGen.createError(word) + " ";
            }
            else {
                searchTermSent += word + " ";
            }
            searchTerm += word + " ";
        }

        System.out.println("Search query: " + searchTermSent);

        //search queries using Bing
        BingParser bp = new BingParser(searchTermSent);
        ArrayList<SearchResult> results = bp.getSearchTitles();

        //initialise points and maxPoints
        int points = 0;
        int maxPoints = 0;

        //compare search query to search results, with a maximum number stated
        int max = Math.min(maxResults, results.size());
        for (int i = 0; i < max; i++) {
            //compare search Term with the ith search result
            boolean test = this.evaluateResult(searchTerm, results.get(i));
            //SCORING SECTION
            //add points if result was success, otherwise add 0
            //search results higher on the list have larger weights
            points += (test ? (max - i) : 0);
            maxPoints += (max - i);
        }
        
        //Pass if the test scored over 50%
        boolean testPassed = (points * 2 >= maxPoints);
        
        //Add test result to the String
        testResults += "Query: " + searchTermSent +
                "\t\tResult: " + (testPassed ? "PASS" : "FAIL") + 
                "\tScore: " + points + "/" + maxPoints + "\n";

        //add points total
        this.curScore += points;
        this.maxScore += maxPoints;

        //Counting number of tests passed
        //add 1 if test passed
        this.testsPassed += (testPassed ? 1 : 0);
        //always increment the max score
        this.totalTests++;

        //return result of test
        return testPassed;

    }
    
    public String getSummary() {
        return "Total score: " + this.curScore + "/" + this.maxScore +
                "\nTests passed: " + this.testsPassed + "/" + this.totalTests + "\n";
    }

    //returns the results of the test as a String
    public String getTestResult() {
        return testResults;
    }

    //access to private for test purposes
    public boolean evaluateResultTest(String query, SearchResult result) {
        return evaluateResult(query, result);
    }
}
