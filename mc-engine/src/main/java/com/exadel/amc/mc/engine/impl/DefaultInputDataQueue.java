package com.exadel.amc.mc.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.InputDataQueue;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component("defaultInputDataQueue")
public class DefaultInputDataQueue implements InputDataQueue {

    protected int cursor;
    protected List<InputDataItem>sources;
    
    @Override
    public void init(Source source) throws InitializationException {
        cursor = 0;
        sources = new ArrayList<>();
    }

    @Override
    public InputDataItem nextItem() {
        if (cursor >= sources.size()) {
            return null;
        }
        return sources.get(cursor++);
    }

    @Override
    public int size() {
        return sources.size() - cursor;
    }

    @Override
    public List<InputDataItem> getRemainedItems() {
        return null;
    }

}
