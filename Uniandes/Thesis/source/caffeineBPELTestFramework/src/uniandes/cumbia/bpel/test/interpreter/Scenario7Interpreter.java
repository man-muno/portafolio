package uniandes.cumbia.bpel.test.interpreter;

import java.util.List;

import uniandes.cumbia.bpel.test.tracers.AbstractTracer;
import uniandes.cumbia.bpel.test.tracers.ITrace;
import uniandes.cumbia.testFW.interfaces.ITracer;
import uniandes.cumbia.testFW.interfaces.annotations.CumbiaTest;

public class Scenario7Interpreter extends AbstractInterpreter
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

    public Scenario7Interpreter( )
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
            //The next element to be executed is the while
            buffer.append( "|Execution Order: 4->while: " + isElementExecuted("while", 4)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 5->invoke: " + isElementExecuted("invoke", 5)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 6->invoke: " + isElementExecuted("invoke", 6)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 7->invoke: " + isElementExecuted("invoke", 7)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 8->invoke: " + isElementExecuted("invoke", 8)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 9->invoke: " + isElementExecuted("invoke", 9)  + "\n");            
            //The next element to be executed is the Invoke   
            buffer.append( "|Execution Order: 10->invoke: " + isElementExecuted("invoke", 10)  + "\n");
            //The next element to be executed is the replyOutput
            buffer.append( "|Execution Order: 11->replyOutput: " + isElementExecuted("replyOutput", 11)  + "\n");
            
            //The process element is executed only once
            buffer.append("|HelloWorld.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("HelloWorld", "exitInit") == 1) + "\n");
            //The receiveInput element is executed only once            
            buffer.append("|receiveInput.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("receiveInput", "exitInit") == 1) + "\n");
            //The sequence element is executed only once
            buffer.append("|sequence.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("sequence", "exitInit") == 1) + "\n");
            //The receive element is executed 6 times. Because of the condition of the while. The "extra" execution is because at first
            //the variable doesn't have any numerical value, and the service starts counting from zero.
            buffer.append("|invoke.Init=>Waiting| == 6: " + (getNumberOfTimesTraced("invoke", "exitInit") == 6) + "\n");
            //The AssignElement6a54f9 element is executed only once            
            buffer.append("|AssignElement6a54f9.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("AssignElement6a54f9", "exitInit") == 1) + "\n");
            //The replyOutput element is executed only once            
            buffer.append("|replyOutput.Init=>Waiting| == 1: " + (getNumberOfTimesTraced("replyOutput", "exitInit") == 1) + "\n");
            //The input element receives the value            
            buffer.append( "|input == \"initial message\": " + isValue( "input", "initial message" )+ "\n");
            //The output element receives the value            
            buffer.append( "|output == null: " + isValue( "output", null )+ "\n");
            
            
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
        
        System.out.println(buffer);
        return buffer;

    }
}
