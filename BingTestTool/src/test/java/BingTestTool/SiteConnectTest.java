package BingTestTool;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SiteConnectTest {
    
    //may need to create objects later
    @BeforeAll
    public void setUp() {
    }
    
    @AfterAll
    public void tearDown() {
    }
    
    //Encode tests
    @Test
    public void testEncodeURL() {
        assertEquals("https://www.bing.com/search?q=purple", SiteConnect.encodeURL("https://www.bing.com/search?q=", "purple"));
        assertEquals("https://www.bing.com/search?q=purple+monkey+dishwasher", SiteConnect.encodeURL("https://www.bing.com/search?q=", "purple monkey dishwasher"));
        assertEquals("https://www.bing.com/search?q=pepe%27s+ducks", SiteConnect.encodeURL("https://www.bing.com/search?q=", "pepe's ducks"));
        assertEquals("https://www.bing.com/search?q=c%2B%2B", SiteConnect.encodeURL("https://www.bing.com/search?q=", "c++"));
    }

    //Connect tests
    @Test
    public void testGetWebpageHTML() {
        //single term search
        String result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "purple");
        assertFalse(result.isEmpty());
        //multiple terms
        result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "purple monkey dishwasher");
        assertFalse(result.isEmpty());
        //alphanumeric symbols
        result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "pepe's ducks");
        assertFalse(result.isEmpty());
        result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "c++");
        assertFalse(result.isEmpty());
    }
    
    //HTML scraping tests
    @Test
    public void testParse() {
        //Should return some results
        String result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "uow");
        assertFalse(SiteConnect.parseHTML(result, "#b_results > li.b_algo > h2 > a").isEmpty());
        //Should return where the quote came from
        result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "I have a dream.");
        assertFalse(SiteConnect.parseHTML(result, "#b_results > li.b_algo > h2 > a").isEmpty());
        //Should return empty, junk search
        result = SiteConnect.getWebpageHTML("https://www.bing.com/search?q=", "bafbanavnealvmeavmafnemoeavmaomakalvalnvjanv");
        assertTrue(SiteConnect.parseHTML(result, "#b_results > li.b_algo > h2 > a").isEmpty());
    }
    
}
