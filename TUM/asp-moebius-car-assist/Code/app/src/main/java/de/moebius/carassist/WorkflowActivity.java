package de.moebius.carassist;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.moebius.carassist.model.api.Target;
import de.moebius.carassist.model.api.Transition;
import de.moebius.carassist.model.api.WFActivity;
import de.moebius.carassist.model.api.Workflow;
import de.moebius.carassist.model.main.OBDTransition;
import de.moebius.carassist.model.main.UserTransition;
import de.moebius.carassist.obd.OBDController;
import de.moebius.carassist.obd.OBDEvent;
import de.moebius.carassist.obd.OBDListener;
import de.moebius.carassist.obd.WebOBDController;
import de.moebius.carassist.view.CardGenerator;

/**
 * An {@link Activity} showing a tuggable "Hello World!" card.
 * <p/>
 * The main content view is composed of a one-card {@link CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 *
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class WorkflowActivity extends Activity {

    private CardScrollView mCardScroller;
    private View mView;
//    private GestureDetector mGestureDetector;

    private Workflow workflow;
    private OBDListener listener = null;
    private OBDController obdController;

    private boolean isSubWorkflowActive = false;

    private List<Pair<WFActivity, View>> reachableActivitiesViews = new LinkedList<Pair<WFActivity, View>>();
    private Map<String, Transition> menuItems = new HashMap<String,Transition>();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // create gestureDetector
//        mGestureDetector = createGestureDetector(this);

        //enable voice commands
        getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);

        //get workflow
        Bundle b = getIntent().getExtras();
        String workflowID = b.getString("workflowID");
        try {
            obdController = WebOBDController.getInstance();
            workflow = Workflow.getWorkflow(workflowID);
            workflow.resetWorkflow();
            updateView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateView() throws Exception{
        reachableActivitiesViews.clear();
        List<WFActivity> activities = workflow.previewNextActivities();

        // generate entries for scrollview
        for (WFActivity a : activities){
            //check if text has to be changed by inserting an obd value
            if (a.getParameter() != null){
                Object obdValue = obdController.getOBDValue(a.getParameter());
                a.setParameterValue(obdValue.toString());
            }
            // generate view
            View view = CardGenerator.getView(a, getApplicationContext());
            // add entry to hashmap
            reachableActivitiesViews.add(new Pair<WFActivity, View>(a, view));
        }

        // create scroll view
        mCardScroller = new CardScrollView(this){
//            @Override
//            public boolean dispatchGenericMotionEvent(MotionEvent event) {
//                boolean superReturnValue = super.dispatchGenericMotionEvent(event);
//                if(this.getSelectedItemPosition() == this.getCount()-1)
//                    return false;
//                else
//                    return superReturnValue;
//            }
        };
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return reachableActivitiesViews.size();
            }

            @Override
            public Object getItem(int position) {
                return reachableActivitiesViews.get(position).first;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return reachableActivitiesViews.get(position).second;
            }

            @Override
            public int getPosition(Object item) {
                for (Pair<WFActivity, View> pair : reachableActivitiesViews){
                    if (pair.first.equals(item)){
                        return reachableActivitiesViews.indexOf(pair);
                    }
                }
                return AdapterView.INVALID_POSITION;
            }

        });

        // change menu items and listener according to selected card (selected means that user scrolled to and stopped at that card)
        mCardScroller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final WFActivity currentActivity = reachableActivitiesViews.get(i).first;

                //add (and remove old) OBD Listener for all OBDTransitions
                if(listener != null) {
                    obdController.removeListener(listener);
                }
                listener = new OBDListener() {
                    @Override
                    public void handleEvent(OBDEvent inEvent) {
                        for (OBDTransition t : currentActivity.getOBDTransitions()){
                            try {
                                if(inEvent.getSource().getOBDValue(t.getVariableName()) != null){
                                    Object inValue = inEvent.getSource().getOBDValue(t.getVariableName());
                                    try {
                                        if(t.testMatching(inValue)){
                                            performTransition(t);
                                        }
                                    } catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                obdController.addListener(listener);
                menuItems.clear();

                //add menu options for all UserTransitions
                for (UserTransition t : currentActivity.getUserTransitions()){
                    menuItems.put(t.getValue(), t);
                }

                // rebuild voice menu with new menu items
                getWindow().invalidatePanelMenu(WindowUtils.FEATURE_VOICE_COMMANDS);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // remove menu items
                menuItems.clear();

                // remove obd listener
                if(listener != null) {
                    obdController.removeListener(listener);
                }
            }
        });

        mCardScroller.activate();
        setContentView(mCardScroller);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            menu.clear();
            for (String command : menuItems.keySet()){
                menu.add(Menu.NONE, Menu.NONE, Menu.NONE, command);
            }
            return true;
        }
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            menu.clear();
            for (String command : menuItems.keySet()){
                menu.add(Menu.NONE, Menu.NONE, Menu.NONE, command);
            }
            return true;
        }
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            Transition t = menuItems.get(item.getTitle());
            performTransition(t);
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void performTransition(Transition transition){
        // set state of workflow to currently shown transition
        WFActivity selectedActivity = (WFActivity)mCardScroller.getSelectedItem();
        workflow.setCurrentActivity(selectedActivity);

        // perform transition
        if(isSubWorkflowActive){
            System.out.println("A sub-workflow is currently active!");
            return;
        }
        try {
            Target target = workflow.followTransition(transition);
            if(target == null){ //end of workflow
                finish();
            } else if (target instanceof WFActivity){   //next activity

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            updateView();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else if (target instanceof Workflow){     //push sub-workflow
                startSubWorkflow((Workflow)target);
            } else {
                //TODO other actions (call hotline, launch google maps, ...)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSubWorkflow(Workflow workflow){
        try {
            Intent intent = new Intent(this, WorkflowActivity.class);
            Bundle b = new Bundle();
            b.putString("workflowID", workflow.getId());
            intent.putExtras(b);
            isSubWorkflowActive = true;
            startActivityForResult(intent, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            isSubWorkflowActive = false;
            Transition showNext = menuItems.get("Show next step");
            if (showNext != null){
                performTransition(showNext);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            openOptionsMenu();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    private GestureDetector createGestureDetector(Context context) {
//        GestureDetector gestureDetector = new GestureDetector(context);
//        //Create a base listener for generic gestures
//        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
//            @Override
//            public boolean onGesture(Gesture gesture) {
//                if (mCardScroller.getSelectedItemPosition() == reachableActivitiesViews.size() - 1
//                        && gesture == Gesture.SWIPE_RIGHT) {
//                    try {
//                        java.lang.Process p = java.lang.Runtime.getRuntime().exec("input keyevent " + Integer.toString(KeyEvent.KEYCODE_DPAD_CENTER) + "\n");
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });
//        return gestureDetector;
//    }
//
//    /*
//     * Send generic motion events to the gesture detector
//     */
//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//        if (mGestureDetector != null) {
//            return mGestureDetector.onMotionEvent(event);
//        }
//        return false;
//    }
}
