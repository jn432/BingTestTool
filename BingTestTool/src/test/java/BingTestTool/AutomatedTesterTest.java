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

    //basic test
    @Test
    public void testEvaluateResult() {
        String searchTerm = "fantasia";
        bp.setSearchTerm(searchTerm);
        assertTrue(test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //2 words
    @Test
    public void testEvaluateResultTwoWords() {
        String searchTerm = "dragon fruit";
        bp.setSearchTerm(searchTerm);
        assertTrue(test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }
    
    //3 words
    @Test
    public void testEvaluateResultThreeWords() {
        String searchTerm = "red dragon fruit";
        bp.setSearchTerm(searchTerm);
        assertTrue(test.evaluateResultTest(searchTerm, bp.getSearchTitles().get(0)));
    }

}
