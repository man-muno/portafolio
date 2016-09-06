package de.moebius.carassist;

import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.moebius.carassist.model.api.Workflow;
import de.moebius.carassist.model.main.OBDTrigger;
import de.moebius.carassist.model.main.UserTrigger;
import de.moebius.carassist.obd.OBDController;
import de.moebius.carassist.obd.OBDEvent;
import de.moebius.carassist.obd.OBDListener;
import de.moebius.carassist.obd.WebOBDController;

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
public class MainActivity extends Activity {

    /**
     *
     */
    private View mView;

    private Collection<Workflow> allWorkflows;
    private Map<String, Workflow> menuItems = new HashMap<String,Workflow>();

    private boolean isWorkflowActive = false;

    /**
     * Handler used to post requests to start new activities so that the menu closing animation
     * works properly.
     */
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // get all workflows
        try {
            Workflow.context = getApplicationContext();
            allWorkflows = Workflow.getWorkflows();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //add OBD Listener for all OBDTriggers
        OBDListener listener = new OBDListener(){
            @Override
            public void handleEvent(OBDEvent inEvent) {
                if (isWorkflowActive){
                    return;
                }
                for (Workflow workflow : allWorkflows){
                    for (OBDTrigger trigger : workflow.getOBDTriggers()){
                        try {
                            if(inEvent.getSource().getOBDValue(trigger.getVariableName()) != null){
                                Object inValue = inEvent.getSource().getOBDValue(trigger.getVariableName());
                                try {
                                    if(trigger.testMatching(inValue)){
                                        startWorkflow(workflow);
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

            }
        };
        OBDController obdController = WebOBDController.getInstance();
        obdController.addListener(listener);

        //add menu options for all UserTriggers
        for (Workflow workflow : allWorkflows) {
            for (UserTrigger trigger : workflow.getUserTriggers()) {
                menuItems.put(trigger.getValue(), workflow);
            }
        }

        //enable voice commands
        getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);

        //show default view
        mView = buildView();
        setContentView(mView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link CardBuilder} class.
     */
    private View buildView() {
        return new CardBuilder(this, CardBuilder.Layout.CAPTION)
                .setText(R.string.app_name)
                .addImage(R.drawable.first_card_a)
                .getView();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||featureId == Window.FEATURE_OPTIONS_PANEL) {
            getMenuInflater().inflate(R.menu.categories, menu);
            for(int i=0; i<menu.size(); i++){
                MenuItem item = menu.getItem(i);
                int categoryId = item.getItemId();
                Menu subMenu = item.getSubMenu();

                if (getResources().getResourceEntryName(categoryId).equals(R.id.settings)){
                    getMenuInflater().inflate(R.menu.settings, subMenu);
                } else {
                    for (String command : menuItems.keySet()) {
                        if (menuItems.get(command).getCategory().equals(getResources().getResourceEntryName(categoryId)))
                            subMenu.add(Menu.NONE, Menu.NONE, Menu.NONE, command);
                    }
                }
            }
            return true;
        }
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS || featureId == Window.FEATURE_OPTIONS_PANEL) {
            final Workflow workflow = menuItems.get(item.getTitle());
            if(workflow != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startWorkflow(workflow);
                    }
                });
            }
            return super.onMenuItemSelected(featureId, item);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            openOptionsMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void startWorkflow(Workflow workflow){
        try {
            if(isWorkflowActive){
                System.out.println("Another workflow is already active!");
                return;
            }
            Intent intent = new Intent(this, WorkflowActivity.class);
            Bundle b = new Bundle();
            b.putString("workflowID", workflow.getId());
            intent.putExtras(b);
            isWorkflowActive = true;
            startActivityForResult(intent, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            isWorkflowActive = false;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
