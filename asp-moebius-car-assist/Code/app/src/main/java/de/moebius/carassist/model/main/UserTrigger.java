package de.moebius.carassist.model.main;

import de.moebius.carassist.model.api.Trigger;

/**
 * Created by tlamp on 24.01.2015.
 */
public class UserTrigger extends Trigger<String> {

    public UserTrigger(String inValue){
        super(String.class, inValue);
    }

    @Override
    public boolean testMatching(String inValue) {
        if(!this.isActive()){
            return false;
        }
        return this.getValue().equals(inValue);
    }

}
