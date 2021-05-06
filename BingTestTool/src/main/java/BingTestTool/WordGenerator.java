package BingTestTool;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class WordGenerator {
    
    protected ArrayList<String> words;
    
    //Constructor
    public WordGenerator() {
        this.words = new ArrayList<>();
    }
    
    //Connects to the word generator website and gets the HTML
    protected String getHTML() {
       StringBuilder sb = new StringBuilder();
        //Connect to website
        try {
            //open connection word generator website
            URL url = new URL("https://wordcounter.net/random-word-generator");
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
    
    public void generateWords() {
        String html = this.getHTML();
        
    }
    
    //Getters
    public ArrayList<String> getWords() {
        return words;
    }
    
}
