package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.DataSaverException;
import com.exadel.amc.mc.engine.exception.InitializationException;

/**
 * Saver saves metrics data to a storage (filesystem, database, etc.). 
 *
 * @param <D> - data which received from connector, structure of data can be different
 * for different sources.
 * 
 * @see Connector
 */
public interface DataSaver<D extends MetricsData> extends Initializable<Source> {

    @Override
    void init(Source source) throws InitializationException;

    void saveMetricsData(InputDataItem dataItem, D data) throws DataSaverException;

}
