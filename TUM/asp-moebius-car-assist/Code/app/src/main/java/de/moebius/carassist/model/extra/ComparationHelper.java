package de.moebius.carassist.model.extra;

/**
 * Created by tlamp on 24.01.2015.
 */
public class ComparationHelper{

    //supports a static set of conditions and types
    public static <T> boolean compare(Class<T> inValueTypes, T inValue1, String inCondition, T inValue2) throws Exception {
        int compareResult = 0;
        if(Double.class.isAssignableFrom(inValueTypes))
            compareResult = Double.compare((Double)inValue1, (Double)inValue2);
        else if(Long.class.isAssignableFrom(inValueTypes))
            compareResult = Long.compare((Long)inValue1, (Long)inValue2);
        else if(Integer.class.isAssignableFrom(inValueTypes))
            compareResult = Integer.compare((Integer)inValue1, (Integer)inValue2);
        else
            throw new Exception("Unsupported compare type for class " + inValueTypes.getName());

        switch (inCondition) {
            case "equals":
                return compareResult == 0;
            case "below":
                return compareResult > 0;
            case "above":
                return compareResult < 0;
            default:
                throw new Exception("Unknown condition '" + inCondition + "'");
        }
    }
}
