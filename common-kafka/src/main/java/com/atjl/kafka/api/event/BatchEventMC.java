package com.atjl.kafka.api.event;

import com.atjl.kafka.domain.OffsetRange;

import java.io.Serializable;
import java.util.*;

/**
 * RawData
 * @since 1.0
 */
public class BatchEventMC extends BatchEvent implements Serializable{
	private static final long serialVersionUID = -8992590730225150331L;

    public BatchEventMC(){
        super();
    }

	/**
     * key:partition
     * value:
     *     List of OffsetRange
     */
    private Map<Integer,OffsetRange> offsetMap;


    public Map<Integer, OffsetRange> getOffsetMap() {
        return offsetMap;
    }

    public void setOffsetMap(Map<Integer, OffsetRange> offsetMap) {
        this.offsetMap = offsetMap;
    }




}
