package de.moebius.carassist.model.main;

import de.moebius.carassist.model.api.Transition;
import de.moebius.carassist.model.extra.ComparationHelper;

/**
 * Created by tlamp on 24.01.2015.
 */
public class OBDTransition<T> extends Transition<T> {

    private String condition;

    private String variableName;

    public OBDTransition(Class<T> inValueType, T inValue, String inTargetType, String inTargetID, String inCondition, String inVariableName) throws Exception {
        super(inValueType, inValue, inTargetType, inTargetID);
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
        return ComparationHelper.compare(this.getTypeClass(), this.getValue(), this.condition, inValue);
    }
}
