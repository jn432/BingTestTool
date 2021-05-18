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

    //Tests to check if we get a word from the website
    @Test
    public void testGetWord() {
        assertFalse(this.test.generateWord().isEmpty());
    }
    
    //Tests to check if we can generate a word with an error
    @Test
    public void testCreateError() {
        String word = "Pipeline";
        assertNotEquals("Word error", word, this.test.createError(word));
    }
    
    //Full test
    @Test
    public void testGenerateWordWithError() {
        String word = this.test.generateWord();
        assertNotEquals("Generate error", word, this.test.createError(word));
    }
}
