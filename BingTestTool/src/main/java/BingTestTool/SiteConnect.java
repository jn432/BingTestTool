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


public class SiteConnect {
    
    //Encode the string to comply with URL standards and return the search link
    public static String encodeURL(String link, String searchTerm) {
        String encoded = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
        return link + encoded;
    }
    
    //Connect to webpage and get HTML data
    public static String getWebpageHTML(String link, String searchTerm) {
        //encode the link to be used
        String encoded = encodeURL(link, searchTerm);
        
        StringBuilder sb = new StringBuilder();
        
        //Connect to the webpage
        try {
            //open connection to that page
            URL url = new URL(encoded);
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
    public static ArrayList<String> parseHTML(String html, String selector) {
        ArrayList<String> results = new ArrayList<>();
        
        //Parse html and find all elements matching the selector
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select(selector);
        
        //print out all links
        elements.forEach(element -> {
            String str = element.text() + " - " + element.attr("href");
            System.out.println(str);
            results.add(str);
        });
        return results;
    }
    
}
