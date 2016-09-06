package de.moebius.carassist.model.main;

import de.moebius.carassist.model.api.Trigger;
import de.moebius.carassist.model.extra.ComparationHelper;

/**
 * Created by tlamp on 24.01.2015.
 */
public class OBDTrigger<T> extends Trigger<T> {

    private String condition;

    private String variableName;

    public OBDTrigger(Class<T> inValueType, T inValue, String inCondition, String inVariableName){
        super(inValueType, inValue);
        this.condition = inCondition;
        this.variableName = inVariableName;
    }

    public String getCondition() {
        return condition;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public boolean testMatching(T inValue) throws Exception {
        if(!this.isActive()){
            return false;
        }
        return ComparationHelper.compare(this.getTypeClass(), this.getValue(), this.condition, inValue);
    }
}
