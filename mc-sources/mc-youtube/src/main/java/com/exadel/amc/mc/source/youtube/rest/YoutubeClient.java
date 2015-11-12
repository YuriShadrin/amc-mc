package com.exadel.amc.mc.source.youtube.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.exadel.amc.mc.engine.Initializable;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.source.youtube.data.channel.ChannelSearchResult;
import com.exadel.amc.mc.source.youtube.data.video.VideoSearchResult;

@Component
public class YoutubeClient implements Initializable<String> {

    private static final String YOUTUBE_URL = "https://content.googleapis.com/youtube/v3";
    private static final String YOUTUBE_VIDEO_BY_ID_URL = YOUTUBE_URL + "/videos?part=id,snippet,statistics,status&key={key}&id={id}";
    private static final String YOUTUBE_CHANNEL_BY_ID_URL = YOUTUBE_URL + "/channels?part=id,snippet,statistics,status&key={key}&id={id}";
    
    private String key;
    private RestTemplate rt;

    @Override
    public void init(String key) throws InitializationException {
        this.key = key;
        this.rt = new RestTemplate();
    }

    public VideoSearchResult getVideoById(String id) {
        return rt.getForObject(YOUTUBE_VIDEO_BY_ID_URL, VideoSearchResult.class, key, id);
    } 

    public ChannelSearchResult getChannelById(String id) {
        return rt.getForObject(YOUTUBE_CHANNEL_BY_ID_URL, ChannelSearchResult.class, key, id);
    }

}


