package de.moebius.carassist.model.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import de.moebius.carassist.model.main.OBDTransition;
import de.moebius.carassist.model.main.OBDTrigger;
import de.moebius.carassist.model.main.UserTransition;
import de.moebius.carassist.model.main.UserTrigger;

public class WFActivity implements Target{

    private String ID;

    private String type;

    private String text;

    private String parameter;

    private String parameterValue;

    private String image;

    private List<Transition> transitions;

    protected WFActivity(String inID, String inType, String inText, String inParameter, String inImage){
        this.ID = inID;
        this.type = inType;
        this.text = inText;
        this.parameter = inParameter;
        this.image = inImage;
        this.transitions = new LinkedList<Transition>();
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        String returnText = text;
        if (parameter != null) {
            returnText = returnText.replace("%s", parameterValue);
        }
        return returnText;
    }

    public void setText(String newText) { text = newText; }

    public String getParameter() {
        return parameter;
    }

    public String getImage() {
        return image;
    }
    public void addTransition(Transition inTransition) {
        this.transitions.add(inTransition);
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<UserTransition> getUserTransitions() {
        List<UserTransition> userTransitions = new LinkedList<UserTransition>();
        for(Transition transition : this.transitions){
            if(transition instanceof UserTransition){
                userTransitions.add((UserTransition) transition);
            }
        }
        return userTransitions;
    }

    public boolean hasTransition(Transition inTransition) {
        for(Transition transition : this.transitions){
            if(transition.equals(inTransition)){
                return true;
            }
        }
        return false;
    }
    public List<OBDTransition> getOBDTransitions() {
        List<OBDTransition> obdTransitions = new LinkedList<OBDTransition>();
        for(Transition transition : this.transitions){
            if(transition instanceof OBDTransition){
                obdTransitions.add((OBDTransition) transition);
            }
        }
        return obdTransitions;
    }


    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}
