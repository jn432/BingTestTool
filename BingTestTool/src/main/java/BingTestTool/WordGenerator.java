package BingTestTool;

import java.io.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordGenerator {
    
    //Connects to the word generator website and gets the HTML
    private String getHTML() {
       StringBuilder sb = new StringBuilder();
        //Connect to website
        try {
            //open connection word generator website
            URL url = new URL("https://randomword.com/");
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
            System.err.println("Malformed URL Exception: " + e);
        }
        catch (IOException e) {
            System.err.println("I/O Exception: " + e);
        }
        
        return sb.toString();
    }
    
    //Uses randomword.com to generate a random word
    public String generateWord() {
        //Get html of website and put it as a Document for jsoup to parse
        Document doc = Jsoup.parse(this.getHTML());
        //get the randomly generated word in the document
        String word =  doc.select("#random_word").first().text();
        return word;
    }   
}
