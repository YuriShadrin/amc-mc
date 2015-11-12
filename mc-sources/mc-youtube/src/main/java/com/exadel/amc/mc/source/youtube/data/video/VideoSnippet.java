package com.exadel.amc.mc.source.youtube.data.video;

import com.exadel.amc.mc.source.youtube.data.YoutubeSnippet;

public class VideoSnippet extends YoutubeSnippet {

    private String channelId;
    private String channelTitle;
    private String categoryId;
    private String liveBroadcastContent;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    @Override
    public String toString() {
        return "VideoSnippet [title=" + getTitle() + ", description=" + getDescription() + ", publishedAt="
                + getPublishedAt() + ", localized=" + getLocalized() + ", thumbnails=" + getThumbnails() + ", tags="
                + getTags() + ", channelId=" + getChannelId() + ", channelTitle=" + getChannelTitle() + ", categoryId="
                + getCategoryId() + ", liveBroadcastContent=" + getLiveBroadcastContent() + "]";
    }

}
