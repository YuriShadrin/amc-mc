package com.exadel.amc.mc.engine.conf;

import java.util.List;

import com.exadel.amc.mc.engine.Initializable;
import com.exadel.amc.mc.engine.exception.InitializationException;

public interface InstanceConfiguration extends Initializable<String> {

    @Override
    void init(String instanceId) throws InitializationException;    

    List<Source>getSources();
}
