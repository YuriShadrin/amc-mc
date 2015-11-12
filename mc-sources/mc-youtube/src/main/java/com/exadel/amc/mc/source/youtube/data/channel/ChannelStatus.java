package com.exadel.amc.mc.source.youtube.data.channel;

import com.exadel.amc.mc.source.youtube.data.YoutubeStatus;

public class ChannelStatus extends YoutubeStatus {
    private Boolean isLinked;
    private String longUploadsStatus;

    public Boolean getIsLinked() {
        return isLinked;
    }

    public void setIsLinked(Boolean isLinked) {
        this.isLinked = isLinked;
    }

    public String getLongUploadsStatus() {
        return longUploadsStatus;
    }

    public void setLongUploadsStatus(String longUploadsStatus) {
        this.longUploadsStatus = longUploadsStatus;
    }

    @Override
    public String toString() {
        return "ChannelStatus [privacyStatus=" + getPrivacyStatus() + ", isLinked=" + getIsLinked()
                + ", longUploadsStatus=" + getLongUploadsStatus() + "]";
    }

}
