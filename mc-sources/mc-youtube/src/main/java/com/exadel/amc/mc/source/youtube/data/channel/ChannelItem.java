package com.exadel.amc.mc.source.youtube.data.channel;

import com.exadel.amc.mc.source.youtube.data.YoutubeItem;

public class ChannelItem extends YoutubeItem<ChannelStatus, ChannelStatistics, ChannelSnippet> {

    @Override
    public String toString() {
        return "ChannelItem [id=" + getId() + ", kind=" + getKind() + ", etag=" + getEtag() + ", status=" + getStatus()
                + ", statistics=" + getStatistics() + ", snippet=" + getSnippet() + "]";
    }

}
