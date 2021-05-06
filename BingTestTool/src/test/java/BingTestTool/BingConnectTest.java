package BingTestTool;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BingConnectTest {
    
    private BingConnect test;

    //Create and destroy object before and after the test
    @Before
    public void setUp() throws Exception {
        this.test = new BingConnect();
    }

    @After
    public void tearDown() throws Exception {
    }

    
    //Encode tests
    @Test
    public void testEncodeURL() {
        this.test.setSearchTerm("purple");
        assertEquals("https://www.bing.com/search?q=purple", this.test.encodeURL());
    }
    
    @Test
    public void testEncodeURLMultipleWords() {
        this.test.setSearchTerm("purple monkey dishwasher");
        assertEquals("https://www.bing.com/search?q=purple+monkey+dishwasher", this.test.encodeURL());
    }
    
    @Test
    public void testEncodeURLMultipleAlphanumeric() {
        this.test.setSearchTerm("pepe's ducks");
        assertEquals("https://www.bing.com/search?q=pepe%27s+ducks", this.test.encodeURL());
    }
    
    //Connect tests
    
    @Test
    public void testGetWebpageHTML() {
        this.test.setSearchTerm("fantasia");
        String result = this.test.getWebpageHTML();
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testGetWebpageHTMLMultipleTerms() {
        this.test.setSearchTerm("martin luther king");
        String result = this.test.getWebpageHTML();
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testGetWebpageHTMLAlphanumeric() {
        this.test.setSearchTerm("c++ for dummies");
        String result = this.test.getWebpageHTML();
        assertFalse(result.isEmpty());
    }
    
    //HTML scraping tests
    @Test
    public void testParse() {
        this.test.setSearchTerm("youtube");
        assertFalse(this.test.parseHTML().isEmpty());
    }
    
    @Test
    public void testParseMultipleTerms() {
        this.test.setSearchTerm("university of western sydney");
        assertFalse(this.test.parseHTML().isEmpty());
    }
    
    @Test
    public void testParseAlphanumeric() {
        this.test.setSearchTerm("C# test driven development");
        assertFalse(this.test.parseHTML().isEmpty());
    }
    
    //This one should return nothing, junk input
    @Test
    public void testParseJunk() {
        this.test.setSearchTerm("bafbanavnealvmeavmafnemoeavmaomakalvalnvjanv");
        assertTrue(this.test.parseHTML().isEmpty());
    }
    
    //This one should also be empty, no search term
    @Test
    public void testParseEmpty() {
        this.test.setSearchTerm("");
        assertTrue(this.test.parseHTML().isEmpty());
    }

}
