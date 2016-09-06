package uniandes.cumbia.bpel.test.interpreter;

import java.util.List;

import uniandes.cumbia.bpel.test.tracers.AbstractTracer;
import uniandes.cumbia.bpel.test.tracers.ITrace;
import uniandes.cumbia.testFW.interfaces.ITracer;
import uniandes.cumbia.testFW.interfaces.annotations.CumbiaTest;

public class Scenario1_1Interpreter extends AbstractInterpreter
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

    public Scenario1_1Interpreter( )
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
            //The next element to be executed is the pick
            buffer.append( "|Execution Order: 2->pick: " + isElementExecuted("pick", 2)  + "\n");
            //The next element to be executed is the empty
            buffer.append( "|Execution Order: 3->empty: " + isElementExecuted("empty", 3)  + "\n");
            //The next element to be executed is the sequence            
            buffer.append( "|Execution Order: 4->sequence: " + isElementExecuted("sequence", 4)  + "\n");
            //The next element to be executed is the AssignElement6a54f9   
            buffer.append( "|Execution Order: 5->AssignElement6a54f9: " + isElementExecuted("AssignElement6a54f9", 5)  + "\n");
            //The next element to be executed is the replyOutput
            buffer.append( "|Execution Order: 6->replyOutput: " + isElementExecuted("replyOutput", 6)  + "\n");
            
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
            //The replyOutput element is executed only once            
            buffer.append("|replyOutput.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("replyOutput", "exitInit") == 1) + "\n");
            //The input element receives the value            
            buffer.append( "|input == \"initial message\" :" + isValue( "input", "initial message" ));
            //The output element receives the value            
            buffer.append( "|output == \"Hello initial message\" :" + isValue( "output", "Hello initial message" ));
            //The input element never receives the value            
            buffer.append( "|input != \"not supposed to work\" : " + !isValue( "input", "not supposed to work" )+ "\n");
            //The output element never receives the value            
            buffer.append( "|output != \"not supposed to work\" : " + !isValue( "output", "not supposed to work" )+ "\n");            
            
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
        
        System.out.println(buffer);
        return buffer;

    }
}
