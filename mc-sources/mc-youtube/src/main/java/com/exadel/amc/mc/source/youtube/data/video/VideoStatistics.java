package com.exadel.amc.mc.source.youtube.data.video;

import com.exadel.amc.mc.source.youtube.data.YoutubeStatistics;

public class VideoStatistics extends YoutubeStatistics {

    private Long likeCount;
    private Long dislikeCount;
    private Long favoriteCount;

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @Override
    public String toString() {
        return "VideoStatistics [viewCount=" + getViewCount() + ", commentCount=" + getCommentCount() + ", likeCount="
                + getLikeCount() + ", dislikeCount=" + getDislikeCount() + ", favoriteCount=" + getFavoriteCount()
                + "]";
    }

}
