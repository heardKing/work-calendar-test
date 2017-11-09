package com.calendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class News {

    @Id
    @GeneratedValue
    private Integer newID; //field

    private String title;

    private String author;

    private String description;

    //使用 title + "," + content 可以来描述当前的 News 记录.
    //即 title + "," + content 可以作为 News 的 desc 属性值
    private String content;

    public News() {
    }

    public News(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Integer getNewID() {
        return newID;
    }

    public void setNewID(Integer newID) {
        this.newID = newID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
