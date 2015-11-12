package com.exadel.amc.mc.source.youtube.data;

import java.util.Date;
import java.util.List;

import com.exadel.amc.mc.source.youtube.rest.YoutubeDataAdapter;
import com.google.gson.annotations.JsonAdapter;

public class YoutubeSnippet {

    private String title;
    
    private String description;
    
    @JsonAdapter(YoutubeDataAdapter.class)
    private Date publishedAt;
    private Localized localized;
    private Thumbnails thumbnails;
    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
