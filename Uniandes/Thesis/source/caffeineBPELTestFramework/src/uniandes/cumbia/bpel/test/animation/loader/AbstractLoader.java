/*******************************************************************************
 * $Id: AbstractLoader.java,v 1.1 2009/01/29 21:48:51 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigación en Construcción de Software
 * Departamento de Ingeniería de Sistemas y Computación
 * Universidad de los Andes
 * Bogotá - Colombia
 * 
 * Copyright (c) 2006
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.test.animation.loader;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Base class for loading elements from a DOM Node
 */
public abstract class AbstractLoader
{

    // -----------------------------------------------------------------
    // Atttributes
    // -----------------------------------------------------------------

    /**
     * DOM Node
     */
    protected Node node;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node of the loader
     * @param context The context for loading the element
     */
    public AbstractLoader( Node node )
    {
        this.node = node;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the value of an attribute
     * @param name The name of the attribute
     * @return The attribute value
     */
    protected String getAttribute( String name )
    {
        return getAttribute( node, name );
    }

    /**
     * Returns a List of nodes with the specified name
     * @param name
     * @return
     */
    protected List<Node> getChilds( String name )
    {
        return getChilds( node, name );
    }

    /**
     * Returns the first child with the specified name. <br>
     * Returns null if the child is not found.
     * @param name The name of the child
     * @return The child with the name provided
     */
    protected Node getChild( String name )
    {
        return getChild( node, name );
    }

    /**
     * Returns the value of an attribute from the specified node
     * @param sourceNode The source node for get the attribute
     * @param name The name of the attribute
     * @return The attribute value or null if it does not exist
     */
    protected String getAttribute( Node sourceNode, String name )
    {
        Node attribute = sourceNode.getAttributes( ).getNamedItem( name );
        if( attribute != null )
        {
            return attribute.getNodeValue( );
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns a List of nodes with the specified name
     * @param sourceNode The source node
     * @param name The name of the node
     * @return the list of nodes node with the name provided
     */
    protected List<Node> getChilds( Node sourceNode, String name )
    {
        NodeList childs = sourceNode.getChildNodes( );
        List<Node> result = new ArrayList<Node>( );
        for( int i = 0; i < childs.getLength( ); i++ )
        {
            Node node = childs.item( i );
            if( name.equals( node.getLocalName( ) ) )
            {
                result.add( node );
            }
        }
        return result;
    }

    /**
     * Returns the first child with the specified name. <br>
     * Returns null if the child is not found.
     * @param sourceNode The source node
     * @param name The name of the node
     * @return the node with the name provided or null if it does not exist
     */
    protected Node getChild( Node sourceNode, String name )
    {
        NodeList childs = sourceNode.getChildNodes( );
        for( int i = 0; i < childs.getLength( ); i++ )
        {
            Node node = childs.item( i );
            if( name.equals( node.getLocalName( ) ) )
            {
                return node;
            }
        }
        return null;
    }
}