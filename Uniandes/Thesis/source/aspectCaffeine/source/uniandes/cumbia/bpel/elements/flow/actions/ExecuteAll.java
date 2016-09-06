package uniandes.cumbia.bpel.elements.flow.actions;

import uniandes.cumbia.bpel.elements.flow.IFlow;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;
import uniandes.cumbia.utils.ThreadPool;

public class ExecuteAll implements IAction
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes all the activities of the flow in a parallel way
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IFlow flow = (IFlow)element;
        
        ThreadPool pool = ThreadPool.getInstance( );
        for(int i=0;i<flow.getActivities( ).size( );i++)
        {
            FlowActivityExecutor ax = new FlowActivityExecutor(flow.getActivities( ).get( i ));
            pool.execute( ax );
            //System.out.println("Executing ACtivity : " + flow.getActivities( ).get( i ).getName( ));
        }
    }

}
