package BingTestTool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AutomatedTesterTest {
    
    private AutomatedTester test;
    private BingParser bp;
    
    @Before
    public void setUp() {
        this.test = new AutomatedTester();
        this.bp = new BingParser();
    }
    
    @After
    public void tearDown() {
        this.test = null;
        this.bp = null;
    }

    //basic tests for evaluating results
    //1 word
    @Test
    public void testEvaluateResult() {
        String searchTerm = "fantasia";
        bp.setSearchTerm(searchTerm);
        assertTrue("Evaluate: fantasia", test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //2 words
    @Test
    public void testEvaluateResultTwoWords() {
        String searchTerm = "dragon fruit";
        bp.setSearchTerm(searchTerm);
        assertTrue("Evaluate: dragon fruit", test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //3 words
    @Test
    public void testEvaluateResultThreeWords() {
        String searchTerm = "red dragon fruit";
        bp.setSearchTerm(searchTerm);
        assertTrue("Evaluate: red dragon fruit", test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //redundancy
    @Test
    public void testEvaluateResultRedundancy() {
        String searchTerm = "uow wollongong";
        bp.setSearchTerm(searchTerm);
        assertTrue("Evaluate: red dragon fruit", test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //tests for automatic testing
    //1 word
    @Test
    public void testRunTest() {
        assertTrue("Full Test 1 word", test.runTest(1,5));
    }
    
    //2 words
    @Test
    public void testRunTestTwoWords() {
        assertTrue("Full Test 2 words", test.runTest(2,5));
    }
    
    //3 words
    @Test
    public void testRunTestThreeWords() {
        assertTrue("Full Test 3 words", test.runTest(3,5));
    }

}
