package BingTestTool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AutomatedTesterTest {
    
    private AutomatedTester test;
    
    @Before
    public void setUp() {
        this.test = new AutomatedTester();
    }
    
    @After
    public void tearDown() {
        this.test = null;
    }

    @Test
    public void testEvaluateResult() {
        assertTrue(test.evaluateResult("fantasia", "Fantasia (1940 film) - Wikipedia"));
    }
    
    public void testEvaluateResultMultipleWords() {
        assertTrue(test.evaluateResult("red dragon fruit", "Dragon Fruit: Nutrition, Benefits, and How to Eat It - Healthline"));
    }

}
