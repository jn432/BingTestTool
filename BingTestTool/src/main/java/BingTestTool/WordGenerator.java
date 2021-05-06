package BingTestTool;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordGenerator {
    
    //Connects to the word generator website and gets the HTML
    protected String getHTML() {
       StringBuilder sb = new StringBuilder();
        //Connect to website
        try {
            //open connection word generator website
            URL url = new URL("https://randomword.com/noun");
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
    
    public String generateWord() {
        //Get html of website and put it as a Document for jsoup to parse
        Document doc = Jsoup.parse(this.getHTML());
        //get the randomly generated word in the document
        String word =  doc.select("#random_word").first().text();
        System.out.println(word);
        return word;
    }
}
