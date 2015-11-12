package com.exadel.amc.mc.source.youtube.data.video;

import java.util.List;

import com.exadel.amc.mc.source.youtube.data.YoutubeSearchResult;

public class VideoSearchResult extends YoutubeSearchResult<VideoItem> {

    List<VideoItem> items;

    @Override
    public List<VideoItem> getItems() {
        return items;
    }

    public void setItems(List<VideoItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "VideoSearchResult [kind=" + getKind() + ", etag=" + getEtag() + ", pageInfo=" + getPageInfo()
                + ", items=" + getItems() + "]";
    }

}
