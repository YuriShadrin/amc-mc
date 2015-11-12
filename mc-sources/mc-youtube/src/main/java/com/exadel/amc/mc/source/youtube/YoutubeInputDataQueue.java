package com.exadel.amc.mc.source.youtube;

import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.engine.impl.DefaultInputDataQueue;

@Component
public class YoutubeInputDataQueue extends DefaultInputDataQueue {

    @Override
    public void init(Source source) throws InitializationException {
        super.init(source);
        sources.add(new InputDataItem("z4SMuD5hATQ", "V", false));
        sources.add(new InputDataItem("BJeXncr9n5A", "V", false));
        sources.add(new InputDataItem("Tj75Arhq5ho", "V", false));
        sources.add(new InputDataItem("z4SMuD5hATQ", "V", false));
        sources.add(new InputDataItem("BJeXncr9n5A", "V", false));
        sources.add(new InputDataItem("Tj75Arhq5ho", "V", false));
        sources.add(new InputDataItem("z4SMuD5hATQ", "V", false));
        sources.add(new InputDataItem("BJeXncr9n5A", "V", false));
        sources.add(new InputDataItem("Tj75Arhq5ho", "V", false));
        sources.add(new InputDataItem("z4SMuD5hATQ", "V", false));
        sources.add(new InputDataItem("BJeXncr9n5A", "V", false));
        sources.add(new InputDataItem("Tj75Arhq5ho", "V", false));
    }

}
