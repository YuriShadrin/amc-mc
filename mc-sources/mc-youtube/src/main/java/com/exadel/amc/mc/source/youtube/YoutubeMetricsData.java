package com.exadel.amc.mc.source.youtube;

import com.exadel.amc.mc.engine.MetricsData;
import com.exadel.amc.mc.source.youtube.data.YoutubeSearchResult;

public class YoutubeMetricsData implements MetricsData {

    private YoutubeSearchResult<?>ysr;
    
    @Override
    public int getSpentLimits() {
        return 1;
    }

    public YoutubeSearchResult<?> getYoutubeSearchResult() {
        return ysr;
    }
    
    public void setYoutubeSearchResult(YoutubeSearchResult<?>ysr) {
        this.ysr = ysr;
    }
    
}
