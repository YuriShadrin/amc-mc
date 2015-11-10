package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.ConnectorException;
import com.exadel.amc.mc.engine.exception.InitializationException;

/**
 * Connector gets data from a particular source. 
 *
 * @param <D> - in accordance to the source, structure of return data can be different.
 */
public interface Connector<D extends MetricsData> extends Initializable<Source> {

    @Override
    void init(Source source) throws InitializationException;

    /**
     * Returns metrics data from the managed source for the given object.
     * @param dataItem - input data item.
     * @return metrics data.
     * @throws ConnectorException in case something was wrong. 
     */
    D getMetricsData(InputDataItem dataItem) throws ConnectorException;

}
