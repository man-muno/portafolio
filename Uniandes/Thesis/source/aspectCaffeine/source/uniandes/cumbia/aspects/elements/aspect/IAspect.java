/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: IAspect.java,v 1.3 2009/02/11 18:11:58 man-muno Exp $ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto CUMBIA
 * Aplicaci�n: JCumbia
 * Autor: Daniel Francisco - 21/01/2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cumbia.aspects.elements.aspect;

import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.aspects.instance.InstanceId;
import uniandes.cumbia.openobjects.elements.IOpenObject;

/**
 * The services that must be provided by an aspect
 */
public interface IAspect extends IBasicElement, IOpenObject
{

	static final String TYPE_ASPECT = "Aspect";

    /**
     * Initializes the aspect
     */
    public void initializeAspect( );
    
    /**
     * Notifies that an element has terminated
     * @param e The element that stopped
     */
    void notifyElementTerminated( IBasicElement e );
    
    /**
     * Sets the instance space id
     * 
     * @param isID
     */
    public void setInstanceSpaceId(InstanceId isID);
    
    /**
     * Returns the instance space id
     * @return instanceSpaceId
     */
    public InstanceId getInstanceSpaceId();
    
    /**
     * @return the engine
     */
    public IAspectEngine getEngine( );

    /**
     * @param engine the engine to set
     */
    public void setEngine( IAspectEngine engine );

    /**
     * Returns all the transitions points of the aspect
     * @return
     */
    public List<ITransitionPoint> getTransitionPoints( );

    /**
     * Executes the next transition point
     */
    public void executeNextTransitionPoint( );

    /**
     * Selects the next transition point
     */
    public void selectNextTransitionPoint( );

    /**
     * Selects the next transition point
     */
    public void selectFirstTransitionPoint( );

    /**
     * Adds a new transition point
     * @param transitionPoint
     */
    public void addTransitionPoint( ITransitionPoint transitionPoint );
    
    /**
     * Enables the aspect for execution
     * @param enabled
     */
    public void setEnabled(boolean enabled);
    
    /**
     * Disables the aspect
     */
    public void disable();
    
    /**
     * Sets the aspected element
     */
    public void setAspectedElement(uniandes.cumbia.bpel.elements.IBasicElement aspectedElement);
    
    public uniandes.cumbia.bpel.elements.IBasicElement getAspectedElement();
}
