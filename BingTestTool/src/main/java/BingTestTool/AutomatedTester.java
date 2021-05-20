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
    public boolean runTest(int numWords, int maxResults) {
        //generate words for search query
        WordGenerator wordGen = new WordGenerator();
        String searchTerm = "";
        for (int i = 0; i < numWords; i++) {
            searchTerm += wordGen.generateWord() + " ";
        }

        System.out.println("Search query: " + searchTerm);

        //search queries using Bing
        BingParser bp = new BingParser(searchTerm);
        ArrayList<SearchResult> results = bp.getSearchTitles();

        //SCORING SECTION - VERY BASIC
        int points = 0;
        int maxPoints = Math.min(maxResults, results.size());

        //compare search query to search results, with a maximum number stated
        for (int i = 0; i < maxPoints; i++) {
            //compare search Term with the ith search result
            boolean test = this.evaluateResult(searchTerm, results.get(i));
            //SCORING SECTION - VERY BASIC
            //add 1 if result was success, otherwise add 0
            points += (test ? 1 : 0);
        }

        //add points
        this.curScore += points;
        this.maxScore += maxPoints;

        //Counting number of tests passed
        //add 1 if test scored at least 50%
        this.testsPassed += ((points * 2 >= maxPoints) ? 1 : 0);
        //always increment the max score
        this.totalTests++;

        //return true if tests passed
        return (points * 2 >= maxPoints);

    }

    //Similar to runTest, except this one introduces an error for every word
    public boolean runTestWithError(int maxResults) {
        //generate words for search query
        WordGenerator wordGen = new WordGenerator();
        String searchTerm = wordGen.generateWord();
        String searchTermMistake = wordGen.createError(searchTerm);

        System.out.println("Search query: " + searchTermMistake);

        //search queries using Bing
        BingParser bp = new BingParser(searchTermMistake);
        ArrayList<SearchResult> results = bp.getSearchTitles();

        //SCORING SECTION - VERY BASIC
        int points = 0;
        int maxPoints = Math.min(maxResults, results.size());

        //compare search query to search results, with a maximum number stated
        for (int i = 0; i < maxPoints; i++) {
            //compare search Term with no errors with the ith search result
            boolean test = this.evaluateResult(searchTerm, results.get(i));
            //SCORING SECTION - VERY BASIC
            //add 1 if result was success, otherwise add 0
            points += (test ? 1 : 0);
        }

        //add points
        this.curScore += points;
        this.maxScore += maxPoints;

        //Counting number of tests passed
        //add 1 if test scored at least 50%
        this.testsPassed += ((points * 2 >= maxPoints) ? 1 : 0);
        //always increment the max score
        this.totalTests++;

        //return true if tests passed
        return (points * 2 >= maxPoints);

    }

    //Getters
    //get the score as a fraction
    public double getScore() {
        if (this.maxScore == 0) {
            return 0;
        }
        return this.curScore / this.maxScore;
    }

    public double getTestsPassedFraction() {
        if (this.totalTests == 0) {
            return 0;
        }
        return this.testsPassed / this.totalTests;
    }

    //access to private for test purposes
    public boolean evaluateResultTest(String query, SearchResult result) {
        return evaluateResult(query, result);
    }
}
