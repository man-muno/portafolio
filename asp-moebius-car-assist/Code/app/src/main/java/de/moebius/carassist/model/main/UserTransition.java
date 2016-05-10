package de.moebius.carassist.model.main;

import de.moebius.carassist.model.api.Transition;

/**
 * Created by tlamp on 24.01.2015.
 */
public class UserTransition extends Transition<String> {

    public UserTransition(String inValue, String inTargetType, String inTargetID) throws Exception {
        super(String.class, inValue, inTargetType, inTargetID);
    }

    @Override
    public boolean testMatching(String inValue) {
        return this.getValue().equals(inValue);
    }
}
