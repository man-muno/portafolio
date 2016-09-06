/*******************************************************************************
 * $Id: Aspect.java,v 1.3 2009/02/11 18:11:58 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaci�n en Construcci�n de Software
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Universidad de los Andes
 * Bogot� - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.aspects.elements.aspect;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.aspects.instance.InstanceId;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Abstract Aspect
 */
public class Aspect extends AbstractAspect implements IAspect
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // BPEL Attributes
    // -----------------------------------------------------------------

    /**
     * The aspect engine
     */
    private IAspectEngine engine;

    // -----------------------------------------------------------------
    // OpenObject Attributes
    // -----------------------------------------------------------------

    /**
     * Identifier of the instance space where this aspect is used
     */
    private InstanceId instanceSpaceId;

    /**
     * This is the number of transition points that have not yet terminated in a given moment after the signal for stop has been given
     */
    private int notYetTerminated;

    /**
     * The index of the activity that in in line for execution
     */
    private int transitionPointIndex;

    /**
     * If true, the aspect executes
     */
    private boolean enabled;

    /**
     * The list of transition points
     */
    private List<ITransitionPoint> transitionPoints;

    /**
     * The element beeing aspected
     */
    private uniandes.cumbia.bpel.elements.IBasicElement aspectedElement;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Creates the new abstract aspect
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     */
    public Aspect( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_ASPECT );
        transitionPointIndex = 0;
        transitionPoints = new ArrayList<ITransitionPoint>( );
    }

    /**
     * Creates the new abstract aspect
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type in the metamodel
     */
    public Aspect( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    // -----------------------------------------------------------------
    // Methods for the BasicElement
    // -----------------------------------------------------------------

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return elementName;
    }

    /**
     * Changes the name of the element
     */
    public void setName( String name )
    {
        this.elementName = name;
    }

    /**
     * Sets the instance space id
     * 
     * @param isID
     */
    public void setInstanceSpaceId( InstanceId isID )
    {
        this.instanceSpaceId = isID;
    }

    /**
     * Returns the instance space id
     * @return instanceSpaceId
     */
    public InstanceId getInstanceSpaceId( )
    {
        return this.instanceSpaceId;
    }

    /**
     * Initialize the element
     */
    public void initialize( )
    {

    }

    /**
     * @return the engine
     */
    public IAspectEngine getEngine( )
    {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine( IAspectEngine engine )
    {
        this.engine = engine;
        for( ITransitionPoint transitionPoint : transitionPoints )
        {
            transitionPoint.setAspectEngine( engine );
        }
    }

    /**
     * Method that begins execution of the element
     */
    public void execute( )
    {

    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IAspect.TYPE_ASPECT;
    }

    // -----------------------------------------------------------------
    // Methods for the Aspect
    // -----------------------------------------------------------------

    /**
     * Returns the parent aspect of this element. If this element is the aspect-root, null is returned
     * @return null
     */
    public IAspect getParentAspect( )
    {
        return null;
    }

    /**
     * Enables the aspect for execution
     * @param enabled
     */
    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }

    /**
     * Sets the aspected element
     */
    public void setAspectedElement( uniandes.cumbia.bpel.elements.IBasicElement aspectedElement )
    {
        this.aspectedElement = aspectedElement;
    }

    public uniandes.cumbia.bpel.elements.IBasicElement getAspectedElement( )
    {
        return aspectedElement;

    }

    /**
     * Disables the aspect
     */
    public void disable( )
    {
        this.enabled = false;
    }

    /**
     * Sets the parent aspect for this element
     * @param parentAspect Parent aspect
     */
    public void setParentAspect( IAspect parentAspect )
    {
        // Does nothing
    }

    /**
     * Initializes the aspect
     */
    public void initializeAspect( )
    {
        initiateAspect( );
    }

    /**
     * Selects the next transition point
     */
    public void selectFirstTransitionPoint( )
    {
        if( enabled )
        {
            transitionPointIndex = 0;
            super.executeTP( );
        }
        else
            super.finalizedAspect( );
    }

    /**
     * Selects the next transition point
     */
    public void selectNextTransitionPoint( )
    {
        if( transitionPointIndex < transitionPoints.size( ) )
        {
            transitionPointIndex++;
            executeTP( );
        }
        else
            finalizedAspect( );
    }

    /**
     * Returns all the transitions points of the aspect
     * @return
     */
    public List<ITransitionPoint> getTransitionPoints( )
    {
        return transitionPoints;
    }

    /**
     * Adds a new transition point
     * @param transitionPoint
     */
    public void addTransitionPoint( ITransitionPoint transitionPoint )
    {
        transitionPoints.add( transitionPoint );
    }

    /**
     * Executes the next transiti on point
     */
    public void executeNextTransitionPoint( )
    {
        transitionPoints.get( transitionPointIndex ).execute( );
        transitionPointIndex++;
    }

    /**
     * Stops the aspect
     */
    public void stop( )
    {
        notYetTerminated = 0;
    }

    /**
     * Returns notYetTerminated
     * 
     * @return notYetTerminated
     */
    public int getNotYetTerminated( )
    {
        return notYetTerminated;
    }

    /**
     * Terminates the aspect
     */
    protected void terminate( )
    {
        aspectEnded( );
        finalizedAspect( );
    }

    /**
     * Method called when all transition points are finished executing
     */
    protected void finalizedAspect( )
    {
        transitionPointIndex = 0;
        super.finalizedAspect( );
        engine.getAspectSpace( instanceSpaceId.getAspectId( ) ).getInstance( instanceSpaceId.getInstanceId( ) ).setFinishedDate( );
        getModelInstance( ).getRuntime( ).stop( );
        super.reInit( );
    }

    /**
     * Notifies that the aspect ended
     */
    private void aspectEnded( )
    {

    }

    /**
     * Notifies that an element has terminated
     * @param e The element that stopped
     */
    public void notifyElementTerminated( IBasicElement e )
    {
        notYetTerminated--;
        if( notYetTerminated == 0 )
        {
            finalizedAspect( );
        }
    }
}
