package BingTestTool;

import java.io.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordGenerator {
    
    //Connects to the word generator website and gets the HTML
    protected String getHTML(String type) {
       StringBuilder sb = new StringBuilder();
        //Connect to website
        try {
            //open connection word generator website
            URL url = new URL("https://randomword.com/" + type);
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
    
    //Uses randomword.com to generate a random noun, verb or adjective
    //other inputs will call the function with no arguments
    public String generateWord(String type) {
        if (!(type.equals("noun") || type.equals("verb") || type.equals("adjective"))) {
            return generateWord();
        }
        //Get html of website and put it as a Document for jsoup to parse
        Document doc = Jsoup.parse(this.getHTML(type));
        //get the randomly generated word in the document
        String word =  doc.select("#random_word").first().text();
        System.out.println(word);
        return word;
    }
    
    //Uses randomword.com to generate a random word
    public String generateWord() {
        //Get html of website and put it as a Document for jsoup to parse
        Document doc = Jsoup.parse(this.getHTML(""));
        //get the randomly generated word in the document
        String word =  doc.select("#random_word").first().text();
        System.out.println(word);
        return word;
    }   
}
