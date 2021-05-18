package BingTestTool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BingParserTest {
    
    private BingParser test;

    //Create and destroy object before and after the test
    @Before
    public void setUp() throws Exception {
        this.test = new BingParser();
    }

    @After
    public void tearDown() throws Exception {
        this.test = null;
    }

    
    //Encode tests
    @Test
    public void testEncodeURL() {
        this.test.setSearchTerm("purple");
        assertEquals("One Word", "https://www.bing.com/search?q=purple", this.test.encodeURLTest());
    }
    
    @Test
    public void testEncodeURLMultipleWords() {
        this.test.setSearchTerm("purple monkey dishwasher");
        assertEquals("Two Words", "https://www.bing.com/search?q=purple+monkey+dishwasher", this.test.encodeURLTest());
    }
    
    @Test
    public void testEncodeURLMultipleAlphanumeric() {
        this.test.setSearchTerm("pepe's ducks");
        assertEquals("Alphanumeric Symbols", "https://www.bing.com/search?q=pepe%27s+ducks", this.test.encodeURLTest());
    }
    
    //Connect tests
    @Test
    public void testGetWebpageHTML() {
        this.test.setSearchTerm("fantasia");
        String result = this.test.getWebpageHTMLTest();
        assertFalse("Get HTML: fantasia", result.isEmpty());
    }
    
    @Test
    public void testGetWebpageHTMLMultipleTerms() {
        this.test.setSearchTerm("martin luther king");
        String result = this.test.getWebpageHTMLTest();
        assertFalse("Get HTML: martin luther king", result.isEmpty());
    }
    
    @Test
    public void testGetWebpageHTMLAlphanumeric() {
        this.test.setSearchTerm("c++ for dummies");
        String result = this.test.getWebpageHTMLTest();
        assertFalse("Get HTML: c++ for dummies", result.isEmpty());
    }
    
    //HTML scraping tests
    @Test
    public void testGetSearchTitles() {
        this.test.setSearchTerm("youtube");
        assertFalse("Search: youtube", this.test.getSearchTitles().isEmpty());
    }
    
    @Test
    public void testGetSearchTitlesMultipleTerms() {
        this.test.setSearchTerm("university of western sydney");
        assertFalse("Search: university of western sydney", this.test.getSearchTitles().isEmpty());
    }
    
    @Test
    public void testGetSearchTitlesAlphanumeric() {
        this.test.setSearchTerm("C# test driven development");
        assertFalse("Search: C# test driven development", this.test.getSearchTitles().isEmpty());
    }
    
    //This one should return no search results, junk input
    @Test
    public void testGetSearchTitlesJunkInput() {
        this.test.setSearchTerm("bafbanavnealvmeavmafnemoeavmaomakalvalnvjanv");
        assertTrue("Search: <junk input>", this.test.getSearchTitles().isEmpty());
    }
    
    //This one should also be empty, no search term
    @Test
    public void testGetSearchTitlesEmptyString() {
        this.test.setSearchTerm("");
        assertTrue("Search: <empty>", this.test.getSearchTitles().isEmpty());
    }
    
}
