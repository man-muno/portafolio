package de.moebius.carassist.model.api;

/**
 * Created by tlamp on 11.01.2015.
 */
public abstract class Transition<T> extends Trigger<T>{

    private Class<? extends Target> targetType;

    private String targetID;

    public Transition(Class<T> inTransitionValueType, T inTransitionValue, String inTransitionTargetType, String inTransitionTargetID) throws Exception {
        super(inTransitionValueType, inTransitionValue);
        if(inTransitionTargetType.equals("workflow")){
            this.targetType = Workflow.class;
        } else if(inTransitionTargetType.equals("activity")){
            this.targetType = WFActivity.class;
        } else {
            throw new Exception("Unknown target type " + inTransitionTargetType);
        }
        this.targetID = inTransitionTargetID;
    }

    public String getTargetID() {
        return targetID;
    }

    public Class<? extends Target> getTargetType() {
        return targetType;
    }

    public boolean equals(Transition inTransition){
        if(!this.getTargetType().equals(inTransition.getTargetType())){
            return false;
        }
        if(!this.getTargetID().equals(inTransition.getTargetID())){
            return false;
        }
        return super.equals(inTransition);
    }
}
