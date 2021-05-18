package BingTestTool;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class GoogleParser {
    
    private String searchTerm;
    
    //constructor that takes a String for the search terms
    public GoogleParser(String searchTerm) {
        this.searchTerm =searchTerm;
    }
    
    //default constructor
    public GoogleParser() {
        this.searchTerm = "";
    }
    
    
    //Encode the string that will be used to Google search
    private String encodeURL() {
        String encoded = URLEncoder.encode(this.searchTerm, StandardCharsets.UTF_8);
        return "https://www.google.com/search?q=" + encoded;
    }
    
    //Connect to webpage and get HTML data
    private String getWebpageHTML() {
        
        StringBuilder sb = new StringBuilder();
        
        //Connect to the webpage
        try {
            //open connection to that page
            URL url = new URL(this.encodeURL());
            URLConnection con = url.openConnection();
            //this line is needed compared to the Bing version
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream stream = con.getInputStream();
            
            //append every line of webpage into StringBuilder
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (MalformedURLException e) {
            System.err.println("Malformed URL Exception: " + e);
        }
        catch (IOException e) {
            System.err.println("I/O Exception: " + e);
        }
        
        return sb.toString();
    }
    
    //parse html string, looking for a structure matching the selector using jsoup
    //examples that worked during testing
    //Google - "a > h3"
    //Bing - "#b_results > li.b_algo > h2 > a"
    public ArrayList<String> getSearchTitles() {
        ArrayList<String> results = new ArrayList<>();
        
        //Parse html and find all web link search results
        Document doc = Jsoup.parse(this.getWebpageHTML());
        Elements elements = doc.select("a > h3");
        
        //parse the search results and add it to list
        elements.forEach(element -> {
            //get the link
            String link = element.parent().attr("href");
            
            //remove the /url?q= at the beginning
            link = link.replace("/url?q=", "");
            //remove the &sa=.... at the end
            link = link.split("&sa=")[0];
            
            results.add(link);
        });
        return results;
    }
    
    //Setters
    
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    //Getters
    
    public String getSearchTerm() {
        return this.searchTerm;
    }
    
    //public methods used for testing, remove on final build
    //done this way to enforce public/private within this class
    public String encodeURLTest() {
        return this.encodeURL();
    }
    
    public String getWebpageHTMLTest() {
        return this.getWebpageHTML();
    }
    
}
