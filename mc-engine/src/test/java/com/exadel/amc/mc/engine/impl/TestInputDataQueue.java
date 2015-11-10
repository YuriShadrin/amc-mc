package com.exadel.amc.mc.engine.impl;

import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component("testInputDataQueue")
public class TestInputDataQueue extends DefaultInputDataQueue {

    @Override
    public void init(Source source) throws InitializationException {

        super.init(source);
        
        for (int i=0; i< 100; i++) {
            sources.add(new InputDataItem("" + i, "AAA" + i, false));
        }
    }

}
