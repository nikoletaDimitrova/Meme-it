package com.vmware.talentboost.demo.entity;


import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "memes")
public class Meme{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "description_pic")
    private String description;

    @Column(name = "date_posted")
    private LocalDateTime datePosted = LocalDateTime.now();

    public Meme() {
    }

    public Meme(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getPictureUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPictureUrl(String pictureUrl) {
        this.url = pictureUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

}
