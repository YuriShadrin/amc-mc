package com.exadel.amc.mc.source.youtube.data.video;

import com.exadel.amc.mc.source.youtube.data.YoutubeItem;

public class VideoItem extends YoutubeItem<VideoStatus, VideoStatistics, VideoSnippet> {

    @Override
    public String toString() {
        return "VideoItem [kind=" + getKind() + ", etag=" + getEtag() + ", id=" + getId() + ", status=" + getStatus()
                + ", statistics=" + getStatistics() + ", snippet=" + getSnippet() + "]";
    }

}
