package BingTestTool;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class BingConnect {
    
    protected String searchTerm;
    
    //constructor that takes a String for the search terms
    public BingConnect(String searchTerm) {
        this.searchTerm =searchTerm;
    }
    
    //default constructor
    public BingConnect() {
        this.searchTerm = "";
    }
    
    
    //Encode the string that will be used to Bing search
    protected String encodeURL() {
        String encoded = URLEncoder.encode(this.searchTerm, StandardCharsets.UTF_8);
        return "https://www.bing.com/search?q=" + encoded;
    }
    
    //Connect to webpage and get HTML data
    protected String getWebpageHTML() {
        
        StringBuilder sb = new StringBuilder();
        
        //Connect to the webpage
        try {
            //open connection to that page
            URL url = new URL(this.encodeURL());
            URLConnection con = url.openConnection();
            InputStream stream = con.getInputStream();
            
            //append every line of webpage into StringBuilder
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    
    //parse html string, looking for a structure matching the selector using jsoup
    //examples that worked during testing
    //Google - "a > h3"
    //Bing - "#b_results > li.b_algo > h2 > a"
    public ArrayList<String> parseHTML() {
        ArrayList<String> results = new ArrayList<>();
        
        //Parse html and find all elements matching the selector
        Document doc = Jsoup.parse(this.getWebpageHTML());
        Elements elements = doc.select("#b_results > li.b_algo > h2 > a");
        
        //print out all links
        elements.forEach(element -> {
            String str = element.text() + " - " + element.attr("href");
            System.out.println(str);
            results.add(str);
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
    
}
