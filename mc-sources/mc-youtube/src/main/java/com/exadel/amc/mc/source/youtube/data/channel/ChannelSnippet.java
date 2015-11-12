package com.exadel.amc.mc.source.youtube.data.channel;

import com.exadel.amc.mc.source.youtube.data.YoutubeSnippet;

public class ChannelSnippet extends YoutubeSnippet {

    @Override
    public String toString() {
        return "ChannelSnippet [title=" + getTitle() + ", description=" + getDescription() + ", publishedAt="
                + getPublishedAt() + ", localized=" + getLocalized() + ", thumbnails=" + getThumbnails() + ", tags="
                + getTags() + "]";
    }

}
