package uniandes.cumbia.bpel.test.interpreter;

import java.util.List;

import uniandes.cumbia.bpel.test.tracers.AbstractTracer;
import uniandes.cumbia.bpel.test.tracers.ITrace;
import uniandes.cumbia.testFW.interfaces.ITracer;
import uniandes.cumbia.testFW.interfaces.annotations.CumbiaTest;

public class Scenario2Interpreter extends AbstractInterpreter
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

    public Scenario2Interpreter( )
    {

    }

    @CumbiaTest
    public StringBuffer test( )
    {
        
        StringBuffer buffer = new StringBuffer( );
        sortByDate();
        
        try
        {
            buffer.append( "EventListenerInterpreter test\n" );

            for(ITracer iTracer : tracers) {
                if (iTracer instanceof AbstractTracer) {
                    AbstractTracer tracer = (AbstractTracer) iTracer;
                    List<ITrace> traces = tracer.getTraces();
                    for(ITrace trace : traces) {
                        //buffer.append(trace.toString() + "\n");
                    }
                }
            }
            
            
            //The fist element to be executed is the process
            buffer.append( "|Execution Order: 1->HelloWorld: " + isElementExecuted("HelloWorld", 1)  + "\n");
            //The next element to be executed is the receiveInput
            buffer.append( "|Execution Order: 2->receiveInput: " + isElementExecuted("receiveInput", 2)  + "\n");
            //The next element to be executed is the sequence
            buffer.append( "|Execution Order: 3->sequence: " + isElementExecuted("sequence", 3)  + "\n");
            //The next element to be executed is the pick
            buffer.append( "|Execution Order: 4->pick: " + isElementExecuted("pick", 4)  + "\n");
            //The next element to be executed is the onAlarm
            buffer.append( "|Execution Order: 5->onAlarm: " + isElementExecuted("onAlarm", 5)  + "\n");
            //The next element to be executed is the empty            
            buffer.append( "|Execution Order: 6->empty: " + isElementExecuted("empty", 6)  + "\n");
            //The next element to be executed is the AssignElement6a54f9   
            buffer.append( "|Execution Order: 7->AssignElement6a54f9: " + isElementExecuted("AssignElement6a54f9", 7)  + "\n");
            //The next element to be executed is the replyOutput
            buffer.append( "|Execution Order: 8->replyOutput: " + isElementExecuted("replyOutput", 8)  + "\n");
            
            //The process element is executed only once
            buffer.append("|HelloWorld.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("HelloWorld", "exitInit") == 1) + "\n");
            //The pick element is executed only once            
            buffer.append("|pick.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("pick", "exitInit") == 1) + "\n");
            //The empty element is executed only once            
            buffer.append("|empty.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("empty", "exitInit") == 1) + "\n");            
            //The sequence element is executed only once
            buffer.append("|sequence.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("sequence", "exitInit") == 1) + "\n");
            //The AssignElement6a54f9 element is executed only once            
            buffer.append("|AssignElement6a54f9.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("AssignElement6a54f9", "exitInit") == 1) + "\n");
            //The receiveInput element is executed only once            
            buffer.append("|receiveInput.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("receiveInput", "exitInit") == 1) + "\n");
            //The onAlarm element is executed only once            
            buffer.append("|onAlarm.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("onAlarm", "exitInit") == 1) + "\n");
            //The empty element is executed only once            
            buffer.append("|empty.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("empty", "exitInit") == 1) + "\n");            
            //The replyOutput element is executed only once            
            buffer.append("|replyOutput.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("replyOutput", "exitInit") == 1) + "\n");
            //The input element receives the value            
            buffer.append( "|input == \"initial message\" :" + isValue( "input", "initial message" ));
            //The output element receives the value            
            buffer.append( "|output == \"Hello initial message\" :" + isValue( "output", "Hello initial message" ));
            
            
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
        
        System.out.println(buffer);
        return buffer;

    }
}
