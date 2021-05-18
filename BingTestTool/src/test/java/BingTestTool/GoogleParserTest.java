package BingTestTool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoogleParserTest {
    
    private GoogleParser test;

    //Create and destroy object before and after the test
    @Before
    public void setUp() throws Exception {
        this.test = new GoogleParser();
    }

    @After
    public void tearDown() throws Exception {
        this.test = null;
    }

    
    //Encode tests
    @Test
    public void testEncodeURL() {
        this.test.setSearchTerm("automated test oracle");
        assertEquals("Google encode url", "https://www.google.com/search?q=automated+test+oracle", this.test.encodeURLTest());
    }
    
    //Connect tests
    @Test
    public void testGetWebpageHTML() {
        this.test.setSearchTerm("c++ for dummies");
        String result = this.test.getWebpageHTMLTest();
        assertFalse("Get HTML Google: c++ for dummies", result.isEmpty());
    }
    
    //HTML scraping tests
    @Test
    public void testGetSearchTitles() {
        this.test.setSearchTerm("c++ for dummies");
        assertFalse("Search google: c++ for dummies", this.test.getSearchTitles().isEmpty());
    }
    
    //This one should return no search results, junk input
    @Test
    public void testGetSearchTitlesJunkInput() {
        this.test.setSearchTerm("bafbanavnealvmeavmafnemoeavmaomakalvalnvjanv");
        assertTrue("Search google: <junk input>", this.test.getSearchTitles().isEmpty());
    }
    
    //This one should also be empty, no search term
    @Test
    public void testGetSearchTitlesEmptyString() {
        this.test.setSearchTerm("");
        assertTrue("Search google: <empty>", this.test.getSearchTitles().isEmpty());
    }
    
    //Checking if we are scraping the correct data
    @Test
    public void testScrapeLink() {
        this.test.setSearchTerm("c++ for dummies");
        assertEquals("Search google link", "https://www.dummies.com/programming/cpp/",
            this.test.getSearchTitles().get(0));
    }
    
    //Checking the 4th result
    @Test
    public void testScrapeLink2() {
        this.test.setSearchTerm("c++ for dummies");
        assertEquals("Search google link", "https://www.amazon.com/C-Dummies-Stephen-R-Davis/dp/0470317264",
            this.test.getSearchTitles().get(3));
    }
    
    //testing different queries
    @Test
    public void testScrapeLinkUoW() {
        this.test.setSearchTerm("uow wollongong");
        assertEquals("Search google link", "https://www.uow.edu.au/",
            this.test.getSearchTitles().get(0));
    }
    
    @Test
    public void testScrapeLinkUoWStudent() {
        this.test.setSearchTerm("uow students");
        assertEquals("Search google link", "https://www.uow.edu.au/student/",
            this.test.getSearchTitles().get(0));
    }
    
}
