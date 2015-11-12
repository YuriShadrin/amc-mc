package com.exadel.amc.mc.source.youtube.data;

import java.util.List;

public abstract class YoutubeSearchResult<T extends YoutubeItem<?, ?, ?>> {

    private String kind;
    private String etag;
    private PageInfo pageInfo;

    public abstract List<T> getItems();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
