package net.jiangchen.blog.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "blogs")
public class BlogPO {
    @Id
    String bid;
    String title;
    String tags;
    String categories;
    String date;
    int uid;
    String author;

    public BlogPO(){

    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public String getAuthor() {
        return author;
    }

    public String getBid() {
        return bid;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String[] getTags() {
        return tags.split("/");
    }

    public String[] getCategories() {
        return categories.split("/");
    }
}
