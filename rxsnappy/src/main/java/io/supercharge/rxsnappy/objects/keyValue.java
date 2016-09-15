package io.supercharge.rxsnappy.objects;

import java.io.Serializable;

/**
 * Created by omri on 15/09/2016.
 */
public class keyValue implements Serializable {

    public keyValue(){}
    private Object key;
    private Object value;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public keyValue(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
