package uniandes.cumbia.bpel.elements.conditional.actions;

import java.io.StringReader;

import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.bpel.engine.utils.xpath.BPELContext;
import uniandes.cumbia.bpel.engine.utils.xpath.ParseException;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathExpression;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathParser;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class EvaluateElseIf implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Evaluates the condition of a else-if element
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IConditional conditional = (IConditional)element;
        uniandes.cumbia.bpel.engine.utils.xpath.BPELXPathCompiler compiler= new uniandes.cumbia.bpel.engine.utils.xpath.BPELXPathCompiler();
        if(conditional.getPosElseIf( ) < conditional.getElseIfs( ).size( ))
        {
            XPathParser parser= new XPathParser(new StringReader(conditional.getElseIfs( ).get( conditional.getPosElseIf() ).getCondition( )));
            parser.setCompiler(compiler);
            try 
            {
                XPathExpression expressionXPath= (XPathExpression) parser.parseExpression();
                BPELContext context= new  BPELContext(conditional.getParentProcess( ));
                Boolean eval= (Boolean) expressionXPath.compute(context); 
                
                if(eval.booleanValue())
                    conditional.executeElseIfActivity();
                else
                    conditional.evaluateNextElseIf();
            } 
            catch (ParseException e) 
            {                       
                e.printStackTrace();
            }
        }
        else
            conditional.evaluateElse();
    }

}
