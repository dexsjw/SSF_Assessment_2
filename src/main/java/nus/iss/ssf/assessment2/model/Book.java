package nus.iss.ssf.assessment2.model;

import jakarta.json.JsonObject;

public class Book {

    private String bookTitle;
    private String workId;
    private String url;
    private String description;
    private String excerpt;
    private boolean cached;

    // methods
    public static Book create(JsonObject jo) {
        final Book b = new Book();
        b.setBookTitle(jo.getString("title"));
        b.setWorkId(jo.getString("key"));
        return b;
    }

    // setters and getters
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }
    
    public void setWorkId(String workId) {
        this.workId = workId;
    }
    
    public String getWorkId() {
        return this.workId;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getExcerpt() {
        return this.excerpt;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public boolean getCached() {
        return this.cached;
    }
}
