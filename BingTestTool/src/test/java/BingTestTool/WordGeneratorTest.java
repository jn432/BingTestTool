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
    //Testing all 4 possible inputs
    @Test
    public void testGetHTML() {
        assertFalse(test.getHTML("").isEmpty());
    }
    @Test
    public void testGetHTMLNoun() {
        assertFalse(test.getHTML("noun").isEmpty());
    }
    @Test
    public void testGetHTMLVerb() {
        assertFalse(test.getHTML("verb").isEmpty());
    }
    @Test
    public void testGetHTMLAdjective() {
        assertFalse(test.getHTML("adjective").isEmpty());
    }

    //Tests to check if we get a word from the website
    //Checking to handle all sorts of inputs
    @Test
    public void testGetWord() {
        assertFalse(this.test.generateWord().isEmpty());
    }
    
    //An empty string should return a random word
    @Test
    public void testGetWordEmptyString() {
        assertFalse(this.test.generateWord("").isEmpty());
    }
    
    //It is a little hard to check whether these are nouns, verbs or adjectives
    //A println is left in the code to check, remove at final release
    @Test
    public void testGetWordNoun() {
        assertFalse(this.test.generateWord("noun").isEmpty());
    }
    
    @Test
    public void testGetWordVerb() {
        assertFalse(this.test.generateWord("verb").isEmpty());
    }
    
    @Test
    public void testGetWordAdjective() {
        assertFalse(this.test.generateWord("adjective").isEmpty());
    }
    
    //Junk inputs should still generate a word based on design
    @Test
    public void testGetWordJunkInput() {
        assertFalse(this.test.generateWord("ajfnafbafna").isEmpty());
    }
    
}
