package de.moebius.carassist.model.api;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import de.moebius.carassist.R;
import de.moebius.carassist.model.main.OBDTransition;
import de.moebius.carassist.model.main.OBDTrigger;
import de.moebius.carassist.model.main.UserTransition;
import de.moebius.carassist.model.main.UserTrigger;

public class Workflow implements Target{
    private static final String workflowRootDirectory = "./data/workflows";
    private String id;
    private String category;
    private Map<String, WFActivity> activities = new HashMap<String, WFActivity>();
    private WFActivity currentActivity = null;

    public static Context context;

    private List<Trigger> triggers = new LinkedList<Trigger>();

    private String startActivityName = null;

//    public Workflow (InputStream inputStream) {
//        parseXml(inputStream);
//    }
//
//    private void parseXml(InputStream inputStream) {
//        try {
//            //read xml file
//            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            final Document document = dBuilder.parse(inputStream);
//            document.getDocumentElement().normalize();
//
//            this.id = document.getDocumentElement().getAttribute("id");
//            System.out.println("Workflow id :" + this.id);
//
//            NodeList nodeList = document.getChildNodes();
//            for (int i = 0; i < nodeList.getLength(); i++){
//                final Node node = nodeList.item(i);
//                final String nodeName = node.getNodeName();
//                final Element element = (Element) node;
//
//                if(nodeName.equals("start")){
//                    final String activityName = element.getAttribute("name");
//                    this.startActivityName = activityName;
//                } else if(nodeName.equals("triggers")){
//                    this.triggers = this.extractTriggers(element.getChildNodes());
//                } else if(nodeName.equals("activities")) {
//                    for(int j=0; j<element.getChildNodes().getLength();j++ ) {
//                        Element activityNode = (Element)element.getChildNodes().item(j);
//                        final String activityId = activityNode.getAttribute("id");
//                        final String activityType = activityNode.getAttribute("type");
//                        final String activityText = activityNode.getAttribute("text");
//                        final String activityParameter = activityNode.getAttribute("parameter");
//                        final String activityImg = activityNode.getAttribute("img");
//
//                        List<Transition> transitions = null;
//                        for(int k=0; k<activityNode.getChildNodes().getLength();k++ ) {
//                            Element innerNode = (Element) activityNode.getChildNodes().item(k);
//                            if (innerNode.getNodeName().equals("transitions")) {
//                                transitions = this.extractTransitions(innerNode.getChildNodes());
//                            } else {
//                                throw new Exception("Unknown inner node type '" + innerNode.getNodeName() + "'");
//                            }
//                        }
//                        final WFActivity activity = new WFActivity(activityId, activityType, activityText, activityParameter, activityImg, transitions);
//                        this.activities.put(activityId, activity);
//                    }
//                } else {
//                    throw new Exception("Unknown node type '" + nodeName + "'");
//                }
//
//            }
//            //Check completion
//            if(this.id == null){
//                throw new Exception("Workflow does not have an ID.");
//            }
//            if(this.startActivityName == null){
//                throw new Exception("Workflow file does not contain a start node.");
//            }
//            if(this.activities.size() == 0){
//                throw new Exception("Workflow file does not contain activities.");
//            }
//            if(this.triggers == null){
//                throw new Exception("Workflow file does not contain a set of triggers.");
//            }
//
//            this.resetWorkflow();
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public Workflow (XmlResourceParser inParser) {
        parseXml(inParser);
    }

    private void parseXml(XmlResourceParser document) {
        try {
            int eventType = -1;
            WFActivity parseActivity = null;
            while(eventType != XmlResourceParser.END_DOCUMENT) {
                document.next();
                eventType = document.getEventType();
                if (document.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = document.getName();
                    System.out.println("Tag name: " + tagName);
                    switch (tagName) {
                        case "workflow":
                            this.id = document.getIdAttribute();
                            System.out.println("Workflow id :" + this.id);
                            this.category = document.getAttributeValue(null, "category");
                            System.out.println("Workflow category :" + this.category);
                            break;
                        case "start":
                            final String activityName = document.getAttributeValue(null, "name");
                            System.out.println("Start activity :" + activityName);
                            this.startActivityName = activityName;
                            break;
                        case "triggers":
                            System.out.println("Skip triggers");
                            break;
                        case "trigger":
                            final Boolean userTrigger = Boolean.valueOf(document.getAttributeValue(null, "user"));

                            Trigger trigger;
                            if(userTrigger) {
                                final String triggerValue = document.getAttributeValue(null, "value");
                                trigger = new UserTrigger(triggerValue);

                                System.out.println("User trigger");
                                System.out.println("Value: " + triggerValue);
                            } else {
                                final Class<?> triggerValueType = Transition.reflectType(document.getAttributeValue(null, "valueType"));
                                final Object triggerValue =  Transition.reflectValue(triggerValueType, document.getAttributeValue(null, "value"));
                                final String triggerCondition = document.getAttributeValue(null, "condition");
                                final String triggerVariableName = document.getAttributeValue(null, "variableName");
                                trigger = new OBDTrigger(triggerValueType, triggerValue, triggerCondition, triggerVariableName);

                                System.out.println("OBD trigger");
                                System.out.println("Value: " + triggerValue);
                                System.out.println(triggerCondition + ", " + triggerVariableName);
                            }
                            triggers.add(trigger);

                            break;
                        case "activities":
                            System.out.println("Skip activities");
                            break;
                        case "activity":
                            final String activityId = document.getAttributeValue(null, "id");
                            final String activityType = document.getAttributeValue(null, "type");
                            final String activityText = document.getAttributeValue(null, "text");
                            final String activityParameter = document.getAttributeValue(null, "parameter");
                            final String activityImg = document.getAttributeValue(null, "img");

                            System.out.println("Activity");
                            System.out.println(activityId);
//                            System.out.println(activityType);
//                            System.out.println(activityText);
//                            System.out.println(activityParameter);
//                            System.out.println(activityImg);

                            final WFActivity activity = new WFActivity(activityId, activityType, activityText, activityParameter, activityImg);
                            this.activities.put(activityId, activity);
                            parseActivity = activity;
                            break;

                        case "transitions":
                            System.out.println("Skip transitions");
                            break;
                        case "transition":
                            final Boolean userTransition = Boolean.valueOf( document.getAttributeValue(null, "user"));
                            final String transitionTargetType =  document.getAttributeValue(null, "targetType");
                            final String transitionTargetID =  document.getAttributeValue(null, "targetID");

                            System.out.println("Transition");
                            System.out.println(userTransition);
                            System.out.println(transitionTargetID);
                            System.out.println(transitionTargetID);

                            Transition transition;
                            if(userTransition) {
                                final Class<?> transitionValueType = String.class;
                                final String transitionValue =  document.getAttributeValue(null, "value");
                                transition = new UserTransition(transitionValue, transitionTargetType, transitionTargetID);
                            } else {
                                final Class<?> transitionValueType = Transition.reflectType( document.getAttributeValue(null, "valueType"));
                                final Object transitionValue =  Transition.reflectValue(transitionValueType,  document.getAttributeValue(null, "value"));
                                final String transitionVariableName =  document.getAttributeValue(null, "variableName");
                                final String transitionCondition =  document.getAttributeValue(null, "condition");
                                transition = new OBDTransition(transitionValueType, transitionValue, transitionTargetType, transitionTargetID, transitionCondition, transitionVariableName);
                            }
                            parseActivity.addTransition(transition);
                            break;
                    }
                }

            }
            //Check completion
            if (this.id == null) {
                throw new Exception("Workflow does not have an ID.");
            }
            if (this.startActivityName == null) {
                throw new Exception("Workflow file does not contain a start node.");
            }
            if (this.activities.size() == 0) {
                throw new Exception("Workflow file does not contain activities.");
            }
            if (this.triggers == null) {
                throw new Exception("Workflow file does not contain a set of triggers.");
            }

            this.resetWorkflow();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Transition> extractTransitions(NodeList inTransitionList) throws Exception {
        List<Transition> outTransitions = new LinkedList<Transition>();
        for (int j = 0; j < inTransitionList.getLength(); j++){
            Node transitionNode = inTransitionList.item(j);
            Element transitionElement = (Element) transitionNode;

            final Boolean userTransition = Boolean.valueOf(transitionElement.getAttribute("user"));
            final String transitionTargetType = transitionElement.getAttribute("targetType");
            final String transitionTargetID = transitionElement.getAttribute("targetID");
            Transition transition;
            if(userTransition) {
                final Class<?> transitionValueType = String.class;
                final String transitionValue = transitionElement.getAttribute("value");
                transition = new UserTransition(transitionValue, transitionTargetType, transitionTargetID);
            } else {
                final Class<?> transitionValueType = Transition.reflectType(transitionElement.getAttribute("valueType"));
                final Object transitionValue =  Transition.reflectValue(transitionValueType, transitionElement.getAttribute("value"));
                final String transitionVariableName = transitionElement.getAttribute("variableName");
                final String transitionCondition = transitionElement.getAttribute("condition");
                transition = new OBDTransition(transitionValueType, transitionValue, transitionTargetType, transitionTargetID, transitionCondition, transitionVariableName);
            }
            outTransitions.add(transition);
        }
        return outTransitions;
    }

    public void resetWorkflow() throws Exception{
        this.currentActivity = this.getActivity(this.startActivityName);
    }

    private WFActivity getActivity(String inActivityName) throws Exception {
        WFActivity activity = this.activities.get(inActivityName);
        if(activity == null){
            throw new Exception("Workflow does not contain activity with name " + inActivityName);
        }
        return activity;
    }

    //returns null if end is reached
    public WFActivity getCurrentActivity(){
        return this.currentActivity;
    }

    public Target followTransition(Transition inTransition) throws Exception {
        if(!this.currentActivity.hasTransition(inTransition)){
            throw new Exception("The current activity does not have the given transition");
        }
        Class<Target> targetType = inTransition.getTargetType();
        String targetID = inTransition.getTargetID();
        if(targetID.equals("end")){
            return null;
        }
        if(Workflow.class.isAssignableFrom(targetType)){
            return Workflow.getWorkflow(targetID);
        } else if(WFActivity.class.isAssignableFrom(targetType)) {
            WFActivity activity = getActivity(targetID);
            this.currentActivity = activity;
            return this.currentActivity;
        } else {
            throw new Exception("Target class not yet implemented. Target class: " + targetType.getName());
        }
    }

    public String getId(){
        return this.id;
    }
    public String getCategory(){
        return this.category;
    }

    public List<UserTrigger> getUserTriggers() {
        List<UserTrigger> userTriggers = new LinkedList<UserTrigger>();
        for(Trigger trigger : this.triggers){
            if(trigger instanceof UserTrigger){
                userTriggers.add((UserTrigger)trigger);
            }
        }
        return userTriggers;
    }
    public List<OBDTrigger> getOBDTriggers() {
        List<OBDTrigger> obdTriggers = new LinkedList<OBDTrigger>();
        for(Trigger trigger : this.triggers){
            if(trigger instanceof OBDTrigger){
                obdTriggers.add((OBDTrigger) trigger);
            }
        }
        return obdTriggers;
    }

    /*
    Should not be used usually. Just for preview and direct jump ins.
     */
    public void setCurrentActivity(WFActivity inActivity){
        this.currentActivity = inActivity;
    }

    //returns a list of all (including the current) WFActivities that can be reached straight (without branches)
    public List<WFActivity> previewNextActivities() throws Exception {
        List<WFActivity> previewList = new LinkedList<>();
        WFActivity tempActivity = this.currentActivity;
        previewList.add(tempActivity);
        while(tempActivity.getUserTransitions().size() == 1 && tempActivity.getOBDTransitions().isEmpty()){
            //there is just a forward transition
            String tempId = tempActivity.getUserTransitions().get(0).getTargetID();
            if(tempId.equals("end")){
                break;
            }
            tempActivity = this.getActivity(tempId);
            previewList.add(tempActivity);
        }
        return previewList;
    }

    private List<Trigger> extractTriggers(NodeList inTriggerList) throws Exception {
        List<Trigger> outTrigger = new LinkedList<Trigger>();
        for (int j = 0; j < inTriggerList.getLength(); j++){
            Node triggerNode = inTriggerList.item(j);
            Element triggerElement = (Element) triggerNode;

            final Boolean userTrigger = Boolean.valueOf(triggerElement.getAttribute("user"));

            Trigger trigger;
            if(userTrigger) {
                final String triggerValue = triggerElement.getAttribute("value");
                trigger = new UserTrigger(triggerValue);
            } else {
                final Class<?> triggerValueType = Transition.reflectType(triggerElement.getAttribute("valueType"));
                final Object triggerValue =  Transition.reflectValue(triggerValueType, triggerElement.getAttribute("value"));
                final String triggerCondition = triggerElement.getAttribute("condition");
                final String triggerVariableName = triggerElement.getAttribute("variableName");
                trigger = new OBDTrigger(triggerValueType, triggerValue, triggerCondition, triggerVariableName);
            }
            outTrigger.add(trigger);
        }
        return outTrigger;
    }

    private static Map<String, Workflow> workflows = null;
    public static Workflow getWorkflow(String inWorkflowId) throws Exception {
        obtainWorkflows();
        Workflow outWorkflow = workflows.get(inWorkflowId);
        if(outWorkflow == null){
            throw new Exception("No workflow existent with id " + inWorkflowId);
        }
        return outWorkflow;
    }
    public static Collection<Workflow> getWorkflows() throws Exception {
        obtainWorkflows();
        return workflows.values();

    }
    private static void obtainWorkflows() {
        if(workflows == null) {
            workflows = new HashMap<String, Workflow>();
            final File rootDirectory = new File(workflowRootDirectory);
            File resDirectory = context.getDir("xml",0);
            for (Field field : R.xml.class.getDeclaredFields()){
                if (field.getName().startsWith("moebius_")) {
                    InputStream inputStream = context.getResources().openRawResource((context.getResources().getIdentifier(field.getName(), "xml", context.getPackageName())));
//                    try {
//                        byte[] b = new byte[inputStream.available()];
//                        inputStream.read(b);
//                        System.out.println(new String(b));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    InputStreamReader inputreader = new InputStreamReader(context.getResources().openRawResource((context.getResources().getIdentifier(field.getName(), "xml", context.getPackageName()))));
//                    BufferedReader buffreader = new BufferedReader(inputreader);
//                    String line;
//                    StringBuilder text = new StringBuilder();
//
//                    try {
//                        while (( line = buffreader.readLine()) != null) {
//                            text.append(line);
//                            text.append('\n');
//                        }
//                    } catch (IOException e) {
//
//                    }
                    //System.out.println(text.toString());


                    Workflow tempWorkflow = new Workflow(context.getResources().getXml(context.getResources().getIdentifier(field.getName(), "xml", context.getPackageName())));
                    workflows.put(tempWorkflow.getId(), tempWorkflow);
                }
            }
        }
    }
}
