package uniandes.cumbia.bpel.elements.assign.actions;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;

import uniandes.cumbia.bpel.elements.assign.IAssign;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;
import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.assign.to.ITo;
import uniandes.cumbia.bpel.engine.utils.xpath.BPELContext;
import uniandes.cumbia.bpel.engine.utils.xpath.BPELXPathCompiler;
import uniandes.cumbia.bpel.engine.utils.xpath.Compiler;
import uniandes.cumbia.bpel.engine.utils.xpath.ParseException;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathExpression;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathParser;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ExecuteNext implements IAction
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
     * Executes the next copy of an assign. If all the copies have been executed, the activity must end
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IAssign assign = ( IAssign )element;
        if(assign.getNextCopyToExecute( ) < assign.getCopies( ).size( ) && assign.getNextCopyToExecute( ) != -1)
        {
            ICopy copy = assign.getCopies( ).get( assign.getNextCopyToExecute( ) );
            ITo to = copy.getTo( );
            IFrom from = copy.getFrom( );
            Object fromElement = null;
            Object toElement = null;
            
            switch( from.getType( ) )
            {
                case IFrom.PARTNER_LINK_VARIANT:
                    fromElement =  from.getEndPointReference( ).equalsIgnoreCase( IFrom.MY_ROLE_NAME ) ? from.getChannel( ).getMyRole( ) : from.getChannel( ).getPartnerRole( );
                    break;
                case IFrom.LITERAL_VARIANT:
                    fromElement = from.getLiteralValue( );// TODO Revisar los tipos de los literales
                    break;
                case IFrom.EXPRESSION_VARIANT:
                {
                    Compiler compiler = new BPELXPathCompiler( );
                    XPathParser parser = new XPathParser( new StringReader( from.getExpression()));
                    parser.setCompiler( compiler );
                    try
                    {
                        XPathExpression expression = ( XPathExpression )parser.parseExpression( );
                        BPELContext context = new BPELContext( assign.getParentProcess( ));
                        fromElement = expression.compute( context );
                        //System.out.println("From element obtenido " + fromElement);
                    }
                    catch( ParseException e )
                    {
                        e.printStackTrace( );
                        throw new RuntimeException("Problems calculating the expression for the From element");
                    }
                    break;
                }

                case IFrom.VARIABLE_VARIANT:
                {
                    fromElement = from.getVariable( );
                    JXPathContext context = JXPathContext.newContext( fromElement );
                    String partName = from.getProperty( );

                    if( !partName.equals( "" ) )
                    {
                        fromElement = context.getValue( partName );
                        context = JXPathContext.newContext( fromElement );
                    }

                    String fromQuery = from.getQuery( );

                    if( !fromQuery.equals( "" ) )
                    {
                        fromElement = context.getValue( fromQuery );
                    }
                }

                case IFrom.PROPERTY_VARIANT:
                {
                    //fromValueId = utils.getVariableIdGenerator( ).getId( WorkspaceConstants.PROPERTY_NAME + WorkspaceConstants.FROM_NAME );
                    // TODO NO SUPPORTED YET
                }
            }

            switch (to.getType( ))
            {
                case ITo.PARTNER_LINK_VARIANT:
                {
                    String partnerLinkName = to.getChannel( ).getName( );
                    toElement = to.getChannel( ).getPartnerRole( );
                }
                
                case ITo.EXPRESSION_VARIANT:
                {
                    Compiler compiler = new BPELXPathCompiler( );
                    XPathParser parser = new XPathParser( new StringReader( to.getExpresion( ) ) );
                    parser.setCompiler( compiler );
                    try
                    {
                        XPathExpression expression = ( XPathExpression )parser.parseExpression( );
                        BPELContext context = new BPELContext( assign.getParentProcess( ));
                        toElement = expression.compute( context );
                    }
                    catch( ParseException e )
                    {
                        e.printStackTrace( );
                    }
                }
                
                case ITo.VARIABLE_VARIANT:
                {
                    String variableName = to.getData( ).getName( );
                    //System.out.println( "VARIALBE NAME " + variableName + to.getData( ));
                    //JXPathContext context = JXPathContext.newContext(to.getVariable( ));
                    String partName = to.getPart( );

                    if( partName!=null)
                    {
                        try
                        {
                            //Object toElementAux = context.getValue( partName );
                            //context = JXPathContext.newContext( toElementAux );
                            toElement = to.getData( ).getPart(partName);
                        }
                        catch( JXPathException e )
                        {
                            e.printStackTrace( );
                            throw new RuntimeException("Problems calculating the expression for the To element");
                        }
                    }
                    String toQuery = to.getQuery( );
                    if( toQuery != null )
                    {/*
                        try
                        {
                            Object toElementAux = context.getValue( toQuery );
                            if( toElementAux != null )
                                toElement = toElementAux;
                        }
                        catch( JXPathException e )
                        {
                            e.printStackTrace( );
                            throw new RuntimeException("Problems calculating the expression for the To element");
                        }
                    */}
                }
                
                case ITo.PROPERTY_VARIANT:
                {
                    //toValueId = utils.getVariableIdGenerator( ).getId( WorkspaceConstants.PROPERTY_NAME + WorkspaceConstants.TO_NAME );
                    // TODO NO SUPPORTED YET
                }
            }
            

            String toElementClassName = toElement.getClass( ).getName( );
            int index = toElementClassName.lastIndexOf( "." );
            String className = toElementClassName.substring( index + 1 );

            try
            {
                //System.out.println("Method parameters: " + "set" + className +  new Class[]{ fromElement.getClass( ) } );
                Method method = toElement.getClass( ).getMethod( "set" + className, new Class[]{ fromElement.getClass( ) } );
                method.invoke( toElement, new Object[]{ fromElement } );
                //System.out.println( "ASIGNO LOS ELEMENTOS SIGUIENTES to element " + toElement + "---- from element" + fromElement );
                to.getData( ).addObject( "payload", toElement );
                assign.copyExecuted( );
            }
            catch( SecurityException e )
            {
                e.printStackTrace( );
            }
            catch( NoSuchMethodException e )
            {

                e.printStackTrace( );
            }
            catch( IllegalArgumentException e )
            {

                e.printStackTrace( );
            }
            catch( IllegalAccessException e )
            {

                e.printStackTrace( );
            }
            catch( InvocationTargetException e )
            {

                e.printStackTrace( );
            }


        }
        else
            assign.finalizedAssign();
        
        

        

        List<ICopy> copies = assign.getCopies( );

        for( int i = 0; i < copies.size( ); i++ )
        {}
    }
}
