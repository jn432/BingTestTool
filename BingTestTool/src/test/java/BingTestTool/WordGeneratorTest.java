package BingTestTool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordGeneratorTest {
    
    private WordGenerator test;
    
    @Before
    public void setUp() {
        this.test = new WordGenerator();
    }
    
    @After
    public void tearDown() {
        this.test = null;
    }

    //Test to check if we can get html from the website
    @Test
    public void testGetHTML() {
        assertFalse(test.getHTML().isEmpty());
    }

    //Test to check if we get at least 1 word
    @Test
    public void testGetWords() {
        assertTrue(this.test.getWords().size() > 0);
    }
}
