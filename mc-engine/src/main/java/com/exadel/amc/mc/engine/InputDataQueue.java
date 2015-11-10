package com.exadel.amc.mc.engine;

import java.util.List;

import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;

/**
 * Queue of input data items. 
 */
public interface InputDataQueue extends Initializable<Source> {

    /**
     * Initializes the queue.
     * @param source - source.
     */
    @Override
    void init(Source source) throws InitializationException;

    /**
     * Returns next data item from the queue or <code>null</code> if
     * queue is empty. Immediately after return, object is removed
     * from the queue.
     * 
     * @return next object identifier.
     */
    InputDataItem nextItem();

    /**
     * Returns size of the queue.
     */
    int size();

    /**
     * Returns list of queue items.
     */
    List<InputDataItem> getRemainedItems();
}
