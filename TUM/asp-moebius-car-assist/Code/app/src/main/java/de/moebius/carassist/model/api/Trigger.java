package de.moebius.carassist.model.api;

import java.util.Date;

/**
 * Created by tlamp on 24.01.2015.
 */
public abstract class Trigger<T> {
    private Class<T> typeClass;
    private T value;
    private long activeFromTimestamp;

    public Trigger(Class<T> inTransitionValueType, T inTransitionValue){
        this.typeClass = inTransitionValueType;
        this.value = inTransitionValue;
        this.activeFromTimestamp = 0;
    }

    public T getValue() {
        return value;
    }

    public Class<T> getTypeClass() {
        return typeClass;
    }

    public abstract boolean testMatching(T inValue) throws Exception;

    protected static Class<?> reflectType(final String inType) throws ClassNotFoundException {
        return Class.forName(inType);
    }
    protected static Object reflectValue(Class<?> inType, String inValue) throws Exception {

        Object response = null;
        if (Boolean.class.isAssignableFrom(inType))
            response = Boolean.parseBoolean(inValue);

        if (Integer.class.isAssignableFrom(inType))
            response = Integer.parseInt(inValue);

        if (String.class.isAssignableFrom(inType))
            response = inValue;

        if (Double.class.isAssignableFrom(inType))
            response = Double.parseDouble(inValue);

        if (Long.class.isAssignableFrom(inType))
            response = Long.parseLong(inValue);
        return response;
    }

    public boolean isActive(){
        return this.activeFromTimestamp < (new Date()).getTime();
    }

    public void deactivateTriggerFor15Min(){
        this.activeFromTimestamp = (new Date()).getTime() + 15*60*1000;
    }

    public boolean equals(Trigger inTrigger){
        if(!this.getTypeClass().equals(inTrigger.getTypeClass())){
            return false;
        }
        if(!this.getValue().equals(inTrigger.getValue())){
            return false;
        }
        return true;
    }
}
