package uniandes.cumbia.bpel.elements.runtime;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.While.IWhile;
import uniandes.cumbia.bpel.elements.assign.IAssign;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;
import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.assign.to.ITo;
import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.bpel.elements.conditional.Else.IElse;
import uniandes.cumbia.bpel.elements.conditional.If.IIf;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.empty.IEmpty;
import uniandes.cumbia.bpel.elements.exit.IExit;
import uniandes.cumbia.bpel.elements.flow.IFlow;
import uniandes.cumbia.bpel.elements.invoke.IInvoke;
import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.receive.IReceive;
import uniandes.cumbia.bpel.elements.receive.Receive;
import uniandes.cumbia.bpel.elements.reply.IReply;
import uniandes.cumbia.bpel.elements.runtime.BpelRuntime.BpelElementType;
import uniandes.cumbia.bpel.elements.sequence.ISequence;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPick;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPoint;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingReceive;
import uniandes.cumbia.bpel.elements.wait.IWait;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.runtime.IExpressionEvaluator;

public class BpelExpressionEvaluator implements IExpressionEvaluator
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The model instance of the process
     */
    private ModelInstance instanceSpace;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the expression evaluator
     */
    public BpelExpressionEvaluator( ModelInstance instanceSpace )
    {
        super( );
        this.instanceSpace = instanceSpace;
    }

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Returns the elements of the instance that satisfy the path or expression provided
     * 
     * @param expression The path or expression to evaluate
     * @return The list of elements that satisfy the expression or path
     */
    public List getElement( String expression )
    {
        ArrayList<IBasicElement> target = new ArrayList<IBasicElement>( );
        if(expression.startsWith( "*" ))
        {
            String noStar = expression.split( "\\*" )[ 1 ];
            String[] targetPath = noStar.split( "\\|" );
            BpelElementType type = parseType( targetPath[0] );
            getAllElementsInProcess(type,target);
        }
        else
        {
            String[] s = expression.split( "\\|" );
            String[] targetPath = s[ 1 ].split( ":" );
            BpelElementType type = parseType( s[ 0 ] );
            //System.out.println("Searching expression: " + expression);
            //System.out.println( "Searching type: " + type );
            int posInTargetPath = 0;
            if( type.equals( BpelElementType.Process ) )
            {
                target.add( ( IProcess )instanceSpace.getRoot( ) );
                // //System.out.println("Process added");
            }
            else if( type != null && ( ( IProcess )instanceSpace.getRoot( ) ).getName( ).equals( targetPath[ posInTargetPath ] ) )
            {
                getElements( ( IProcess )instanceSpace.getRoot( ), targetPath, target, posInTargetPath, type );
            }
        }
        return target;
    }


    /**
     * Searches for the elements inside the process
     * @param expression
     * @param process
     * @param resp
     * @param posInTargetPath
     * @param type
     */
    private void getElements( IProcess process, String[] targetPath, ArrayList<IBasicElement> resp, int posInTargetPath, BpelElementType type )
    {
        if( type.equals( BpelElementType.Data ) )
        {
            searchData( process.getData( ), targetPath[ posInTargetPath+1 ], resp );
        }
        else if( type.equals( BpelElementType.Channel ) )
        {
            searchChannels( process.getChannels( ), targetPath[ posInTargetPath ], resp );
        }
        else if( type.equals( BpelElementType.StartingPick ) )
        {
            searchStartingPoints( process.getStartingPoints( ), targetPath[ posInTargetPath+1 ], resp );
        }
        else if( type.equals( BpelElementType.StartingReceive ) )
        {
            searchStartingPoints( process.getStartingPoints( ), targetPath[ posInTargetPath+1 ], resp );
        }
        else
        {
            for(int i=0;i<process.getStartingPoints( ).size( );i++)
                searchInsideStartingPick(process.getStartingPoints( ).get( i ), targetPath, posInTargetPath + 1, resp );
            for( int i = 0; i < process.getProcessElements( ).size( ); i++ )
                searchElements( process.getProcessElements( ).get( i ), targetPath, posInTargetPath + 1, resp );
        }
    }

    private void searchInsideStartingPick( IStartingPoint startingPoint, String[] targetPath, int posInTargetPath, ArrayList<IBasicElement> resp )
    {
        if(startingPoint instanceof IStartingPick && startingPoint.getElementName( ).equals( targetPath[posInTargetPath] ))
            searchElements( ((IStartingPick)startingPoint).getActivity( ), targetPath, posInTargetPath+1, resp );
    }

    private void searchChannels( List<IChannel> channels, String elementName, ArrayList<IBasicElement> resp )
    {
        for( int i = 0; i < channels.size( ); i++ )
            if( channels.get( i ).getName( ).equals( elementName ) )
                resp.add( channels.get( i ) );
    }

    private void searchStartingPoints( List<IStartingPoint> startingPoints, String elementName, ArrayList<IBasicElement> resp )
    {
        //System.out.println("Searching starting point: " + elementName);
        for( int i = 0; i < startingPoints.size( ); i++ )
            if( startingPoints.get( i ).getName( ).equals( elementName ) )
                resp.add( startingPoints.get( i ) );
        //System.out.println("Searched: " + resp.size( ));
    }

    private void searchData( List<IData> data, String elementName, ArrayList<IBasicElement> resp )
    {
        //System.out.println("Searching data: " + elementName);
        for( int i = 0; i < data.size( ); i++ )
        {
            //System.out.println("Searching data: " + data.get(i).getName( ));
            if( data.get( i ).getName( ).equals( elementName ) )
                resp.add( ( IBasicElement )data.get( i ) );
        }
        //System.out.println("Data found: " + resp.size( ));
    }

    /**
     * 
     * @param expression
     * @param element
     * @param resp
     */
    private void searchElements( IBasicElement element, String[] targetPath, int posInTargetPath, ArrayList resp )
    {
        // //System.out.println( "Searching for element " + targetPath[posInTargetPath] + " in element " + element.getName( ) );
        if( element instanceof ISequence && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            ISequence sequence = ( ISequence )element;
            for( int i = 0; i < sequence.getActivities( ).size( ); i++ )
            {
                searchElements( sequence.getActivities( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else if( element instanceof IFlow && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IFlow flow = ( IFlow )element;
            for( int i = 0; i < flow.getActivities( ).size( ); i++ )
            {
                searchElements( flow.getActivities( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else if( element instanceof IStartingPick && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IStartingPick )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IOnMessage && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IOnMessage )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IPick && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IPick pick = ( IPick )element;
            for( int i = 0; i < pick.getOnAlarms( ).size( ); i++ )
            {
                searchElements( pick.getOnAlarms( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
            for( int i = 0; i < pick.getOnMessages( ).size( ); i++ )
            {
                searchElements( pick.getOnMessages( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else if( element instanceof IConditional && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IConditional conditional = ( IConditional )element;
            searchElements( ( ( IConditional )element ).getIf( ), targetPath, posInTargetPath + 1, resp );
            List<IElseIf> elseIfs = conditional.getElseIfs( );
            for( int i = 0; i < elseIfs.size( ); i++ )
            {
                searchElements( ( IElseIf )elseIfs.get( i ), targetPath, posInTargetPath + 1, resp );
            }
            searchElements( conditional.getElse( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IOnAlarm && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IOnAlarm onAlarm = (IOnAlarm) element;
            searchElements( onAlarm.getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IOnMessage&& targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IOnMessage onMessage = (IOnMessage) element;
            searchElements( onMessage.getActivity( ), targetPath, posInTargetPath + 1, resp );            
        }
        else if( element instanceof IIf && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IIf )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IElseIf && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IElseIf )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IElse && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IElse )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IWhile && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IWhile )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IAssign && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            List<ICopy> copies = ( ( IAssign )element ).getCopies( );
            for( int i = 0; i < copies.size( ); i++ )
            {
                searchElements( ( ICopy )copies.get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else if( element instanceof ICopy && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( ICopy )element ).getFrom( ), targetPath, posInTargetPath + 1, resp );
            searchElements( ( ( ICopy )element ).getTo( ), targetPath, posInTargetPath + 1, resp );
        }
        else if( element instanceof IStartingPick && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            searchElements( ( ( IStartingPick )element ).getActivity( ), targetPath, posInTargetPath + 1, resp );
        }
        else
        {
            if( targetPath[ posInTargetPath ].equals( element.getName( ) ) )
                resp.add( element );
        }
    }

    /**
     * Parse the type specified
     * 
     * @param type The type to parsing
     * @return The type parsing
     */
    private BpelElementType parseType( String type )
    {
        BpelElementType res = null;
        if( type.equalsIgnoreCase( IAssign.TYPE_ASSIGN ) )
        {
            res = BpelElementType.Assign;
        }
        else if( type.equalsIgnoreCase( ICopy.TYPE_COPY ) )
        {
            res = BpelElementType.Copy;
        }
        else if( type.equalsIgnoreCase( IFrom.TYPE_FROM ) )
        {
            res = BpelElementType.From;
        }
        else if( type.equalsIgnoreCase( ITo.TYPE_TO ) )
        {
            res = BpelElementType.To;
        }
        else if( type.equalsIgnoreCase( IConditional.TYPE_CONDITIONAL ) )
        {
            res = BpelElementType.Conditional;
        }
        else if( type.equalsIgnoreCase( IElse.TYPE_ELSE ) )
        {
            res = BpelElementType.Else;
        }
        else if( type.equalsIgnoreCase( IElseIf.TYPE_ELSE ) )
        {
            res = BpelElementType.ElseIf;
        }
        else if( type.equalsIgnoreCase( IIf.TYPE_IF ) )
        {
            res = BpelElementType.If;
        }
        else if( type.equalsIgnoreCase( IEmpty.TYPE_EMPTY ) )
        {
            res = BpelElementType.Empty;
        }
        else if( type.equalsIgnoreCase( IExit.TYPE_EXIT ) )
        {
            res = BpelElementType.Exit;
        }
        else if( type.equalsIgnoreCase( IFlow.TYPE_FLOW ) )
        {
            res = BpelElementType.Flow;
        }
        else if( type.equalsIgnoreCase( IInvoke.TYPE_INVOKE ) )
        {
            res = BpelElementType.Invoke;
        }
        else if( type.equalsIgnoreCase( IPick.TYPE_PICK ) )
        {
            res = BpelElementType.Pick;
        }
        else if( type.equalsIgnoreCase( IOnAlarm.TYPE_ON_ALARM ) )
        {
            res = BpelElementType.OnAlarm;
        }
        else if( type.equalsIgnoreCase( IOnMessage.TYPE_ON_MESSAGE ) )
        {
            res = BpelElementType.OnMessage;
        }
        else if( type.equalsIgnoreCase( IProcess.TYPE_PROCESS ) )
        {
            res = BpelElementType.Process;
        }
        else if( type.equalsIgnoreCase( IReceive.TYPE_RECEIVE ) )
        {
            res = BpelElementType.Receive;
        }
        else if( type.equalsIgnoreCase( IReply.TYPE_REPLY ) )
        {
            res = BpelElementType.Reply;
        }
        else if( type.equalsIgnoreCase( ISequence.TYPE_SEQUENCE ) )
        {
            res = BpelElementType.Sequence;
        }
        else if( type.equalsIgnoreCase( IStartingReceive.TYPE_STARTING_RECEIVE ) )
        {
            res = BpelElementType.StartingReceive;
        }
        else if( type.equalsIgnoreCase( IStartingPick.TYPE_STARTING_PICK ) )
        {
            res = BpelElementType.StartingPick;
        }
        else if( type.equalsIgnoreCase( IWait.TYPE_WAIT ) )
        {
            res = BpelElementType.Wait;
        }
        else if( type.equalsIgnoreCase( IWhile.TYPE_WHILE ) )
        {
            res = BpelElementType.While;
        }
        else if( type.equalsIgnoreCase( IChannel.TYPE_CHANNEL) )
        {
            res = BpelElementType.Channel;
        }
        else if( type.equalsIgnoreCase( IData.TYPE_DATA) )
        {
            res = BpelElementType.Data;
        }
        return res;
    }
    
    

    private void getAllElementsInProcess( BpelElementType type, ArrayList<IBasicElement> target )
    {
        IProcess process = ( IProcess )instanceSpace.getRoot( );
        if( type.equals( BpelElementType.Process ) )
        {
            target.add( process );
        }
        else if( type.equals( BpelElementType.StartingPick ) )
        {
            searchStartingPoints(process.getStartingPoints( ),type,target);
        }
        else if( type.equals( BpelElementType.StartingReceive ) )
        {
            searchStartingPoints(process.getStartingPoints( ),type,target);
        }
        else
        {
            for(IBasicElement activity : process.getProcessElements( ))
            {
                searchElements(activity,type,target);    
            }
            
        }
    }


    private void searchStartingPoints( List<IStartingPoint> startingPoints, BpelElementType type,ArrayList<IBasicElement> target  )
    {
        for(IStartingPoint startingPoint : startingPoints)
        {
            if(type.equals( BpelElementType.StartingPick ) && startingPoint instanceof IStartingPick)
            {
                target.add( startingPoint );
            }
            else if(type.equals( BpelElementType.StartingReceive) && startingPoint instanceof IStartingReceive)
            {
                target.add( startingPoint );
            }
        }
    }
    

    private void searchElements( IBasicElement activity, BpelElementType type, ArrayList<IBasicElement> resp )
    {
        // //System.out.println( "Searching for element " + targetPath[posInTargetPath] + " in element " + element.getName( ) );
        if( activity instanceof ISequence)
        {
            if(type.equals( BpelElementType.Sequence ))
            {
                resp.add( activity );
            }
            ISequence sequence = ( ISequence )activity;
            for( int i = 0; i < sequence.getActivities( ).size( ); i++ )
            {
                searchElements( sequence.getActivities( ).get( i ), type, resp );
            }
        }
        else if( activity instanceof IFlow )
        {
            if(type.equals( BpelElementType.Flow ))
            {
                resp.add( activity );
            }
            IFlow flow = ( IFlow )activity;
            for( int i = 0; i < flow.getActivities( ).size( ); i++ )
            {
                searchElements( flow.getActivities( ).get( i ), type, resp );
            }
        }
        else if( activity instanceof IOnMessage  )
        {
            if(type.equals( BpelElementType.OnMessage ))
            {
                resp.add( activity );
            }
            searchElements( ( ( IOnMessage )activity ).getActivity( ), type, resp );
        }
        else if( activity instanceof IPick  )
        {
            if(type.equals( BpelElementType.Pick))
            {
                resp.add( activity );
            }
            IPick pick = ( IPick )activity;
            for( int i = 0; i < pick.getOnAlarms( ).size( ); i++ )
            {
                searchElements( pick.getOnAlarms( ).get( i ), type, resp );
            }
            for( int i = 0; i < pick.getOnMessages( ).size( ); i++ )
            {
                searchElements( pick.getOnMessages( ).get( i ), type, resp );
            }
        }
        else if( activity instanceof IConditional  )
        {
            if(type.equals( BpelElementType.Conditional ))
            {
                resp.add( activity );
            }
            IConditional conditional = ( IConditional )activity;
            searchElements( ( ( IConditional )activity ).getIf( ), type, resp );
            List<IElseIf> elseIfs = conditional.getElseIfs( );
            for( int i = 0; i < elseIfs.size( ); i++ )
            {
                searchElements( ( IElseIf )elseIfs.get( i ), type, resp );
            }
            searchElements( conditional.getElse( ), type, resp );
        }
        else if( activity instanceof IOnAlarm  )
        {
            if(type.equals( BpelElementType.OnAlarm))
            {
                resp.add( activity );
            }
            IOnAlarm onAlarm = (IOnAlarm) activity;
            searchElements( onAlarm.getActivity( ), type, resp );
        }
        else if( activity instanceof IIf  )
        {
            if(type.equals( BpelElementType.If ))
            {
                resp.add( activity );
            }
            searchElements( ( ( IIf )activity ).getActivity( ), type, resp );
        }
        else if( activity instanceof IElseIf  )
        {
            if(type.equals( BpelElementType.ElseIf ))
            {
                resp.add( activity );
            }
            searchElements( ( ( IElseIf )activity ).getActivity( ), type, resp );
        }
        else if( activity instanceof IElse  )
        {
            if(type.equals( BpelElementType.Else))
            {
                resp.add( activity );
            }
            searchElements( ( ( IElse )activity ).getActivity( ), type, resp );
        }
        else if( activity instanceof IWhile  )
        {
            if(type.equals( BpelElementType.While ))
            {
                resp.add( activity );
            }
            searchElements( ( ( IWhile )activity ).getActivity( ), type, resp );
        }
        else if( activity instanceof IAssign  )
        {
            if(type.equals( BpelElementType.Assign ))
            {
                resp.add( activity );
            }
            List<ICopy> copies = ( ( IAssign )activity ).getCopies( );
            for( int i = 0; i < copies.size( ); i++ )
            {
                searchElements( ( ICopy )copies.get( i ), type, resp );
            }
        }
        else if( activity instanceof ICopy  )
        {
            if(type.equals( BpelElementType.Copy ))
            {
                resp.add( activity );
            }
/*            searchElements( ( ( ICopy )activity ).getFrom( ), type, resp );
            searchElements( ( ( ICopy )activity ).getTo( ), type, resp );*/
        }
        else if(activity instanceof IReceive)
        {
            if(type.equals( BpelElementType.Receive ))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IReply)
        {
            if(type.equals( BpelElementType.Reply ))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IInvoke)
        {
            if(type.equals( BpelElementType.Invoke))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IExit)
        {
            if(type.equals( BpelElementType.Exit))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IEmpty)
        {
            if(type.equals( BpelElementType.Empty ))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IWait)
        {
            if(type.equals( BpelElementType.Wait))
            {
                resp.add( activity );
            }
        }
        else if(activity instanceof IAssign)
        {
            if(type.equals( BpelElementType.Assign))
            {
                resp.add( activity );
            }
        }

    }
}
