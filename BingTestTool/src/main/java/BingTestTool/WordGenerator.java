//Code structure built similarly to BingParser

package BingTestTool;

import java.io.*;
import java.net.*;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordGenerator {
    
    //Connects to the word generator website and gets the HTML
    private String getHTML() {
       StringBuilder sb = new StringBuilder();
        //Connect to website
        try {
            //open connection word generator website
            //vocabulary used to reduce range of words, removing medical terms
            //or other very obscure words, improves testing for 2+ words
            URL url = new URL("https://randomword.com/vocabulary");
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
    
    //adds an extra letter to the word to simulate typing error
    public String createError(String word) {
        Random rng = new Random();
        //pick a random location within the String
        int randomNumber = rng.nextInt(word.length());
        //add a duplicate letter at that location
        //eg. random number is 4, word is pipeline, returns pipelline
        String mistakeWord = word.substring(0, randomNumber)
            + word.charAt(randomNumber)
            + word.substring(randomNumber, word.length());
        return mistakeWord;
    }
}
