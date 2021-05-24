//Wrapper class
//Simple class that does not need exhaustive testing in TTD

package BingTestTool;

public class SearchResult {
    
    private String link;
    private String title;
    private String description;
    
    //Constructor taking weblink, title and description as Strings
    public SearchResult(String link, String title, String description) {
        this.link = link;
        this.title = title;
        this.description = description;
    }
    
    //Default constructor
    public SearchResult() {
        
    }
    
    //Setters
    public void setLink(String link) {
        this.link = link;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    //Getters
    public String getLink() {
        return this.link;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
