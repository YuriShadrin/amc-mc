package com.exadel.amc.mc.source.youtube;

import com.exadel.amc.mc.engine.MetricsData;

public class YoutubeMetricsData implements MetricsData {

    @Override
    public int getSpentLimits() {
        return 1;
    }

}
