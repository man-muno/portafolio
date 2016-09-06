/*******************************************************************************
 * $Id: ProcessOrganizer.java,v 1.3 2009/02/10 04:57:45 man-muno Exp $
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
package uniandes.cumbia.bpel.instantiation.elements;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.caffeine.deployer.elements.NameSpace;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class used to load a process
 */
public class ProcessOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_PARTNER_LINKS = "partnerLinks";

    private static final String ELEMENT_PARTNER_LINK = "partnerLink";

    private static final String ELEMENT_VARIABLES = "variables";

    private static final String ELEMENT_VARIABLE = "variable";

    private static final String ATTRIBUTE_QUERY_LANGUAGE = "queryLanguage";

    private static final String ATTRIBUTE_EXPRESSION_LANGUAGE = "expressionLanguage";

    private static final String ATTRIBUTE_EXIT_ON_STANDARD_FAULT = "exitOnStandardFault";

    private final static String TARGET_NAME_SPACE_ATTRIBUTE = "targetNameSpace";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Creates a loader for a process
     * 
     * @param node Node for loading the process
     * @param modelInstance The instance where the elements are being organized
     */
    public ProcessOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess )
    {
        super( node, modelInstance, parentProcess );
        String processName = getAttribute( "name" );
        ////System.out.println("Process name  " + processName);
        element = ( IBasicElement )modelInstance.getElement( processName );
        
        
        
        ////System.out.println("Element " + element);
        element.setParentProcess( parentProcess );
        ( ( IProcess )element ).setName( processName );

        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        if( suppresed != null && suppresed.equalsIgnoreCase( "yes" ) )
            ( ( IProcess )element ).setSuppressJoinFailure( true );
        else
            ( ( IProcess )element ).setSuppressJoinFailure( false );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the element with its internal structure organized
     * 
     * @return element
     */
    public IActivity getActivity( )
    {
        return null;
    }

    /**
     * Organizes the internal structure of the element
     * @throws OrganizerException
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        ////System.out.println("Process organizeInternalStructure");
        IProcess process = null;
        try
        {
            process = ( IProcess )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPartnerLink.", e );
        }
        
        if( process.getName( ) == null )
            throw new LoaderException( "The process element must have a name defined" );

        NamedNodeMap attributes = node.getAttributes( );

        for( int i = 0; i < attributes.getLength( ); i++ )
        {
            Node actual = attributes.item( i );

            String value = actual.getNamespaceURI( );
            String nameSpace = actual.getLocalName( );

            if( !nameSpace.equals( ATTRIBUTE_NAME ) && !nameSpace.equals( TARGET_NAME_SPACE_ATTRIBUTE ) && !nameSpace.equals( ATTRIBUTE_QUERY_LANGUAGE ) && !nameSpace.equals( ATTRIBUTE_EXPRESSION_LANGUAGE )
                    && !nameSpace.equals( ATTRIBUTE_SUPPRESED_JOIN_FAILURE ) && !nameSpace.equals( ATTRIBUTE_EXIT_ON_STANDARD_FAULT ) )
            {
                NameSpace nSpace = new NameSpace( nameSpace, value );
                process.addNameSpace( nSpace );
            }
        }

        String queryLanguage = getAttribute( ATTRIBUTE_QUERY_LANGUAGE );
        process.setQueryLanguage( queryLanguage );
        String expressionLanguage = getAttribute( ATTRIBUTE_EXPRESSION_LANGUAGE );
        process.setExpressionLanguage( expressionLanguage );
        String exitOnStardardFault = getAttribute( ATTRIBUTE_EXIT_ON_STANDARD_FAULT );
        if( exitOnStardardFault != null && exitOnStardardFault.equalsIgnoreCase( "yes" ) )
            process.setExitOnStandardFault( true );

        ////System.out.println( "Process attributes loaded" );

        //
        // Load all the partner links
        Node partnerLinksNode = getChild( node, ELEMENT_PARTNER_LINKS );
        ArrayList<IChannel> channels = new ArrayList<IChannel>( );
        if( partnerLinksNode != null )
        {
            List<Node> partnerLinksList = getChilds( partnerLinksNode, ELEMENT_PARTNER_LINK );
            for( int i = 0; i < partnerLinksList.size( ); i++ )
            {
                Node partnerLinks = ( Node )partnerLinksList.get( i );
                loadChannels( partnerLinks, channels );
            }
        }
        ////System.out.println( "Channels loaded" );

        Node variablesNode = getChild( node, ELEMENT_VARIABLES );
        if( variablesNode != null )
        {
            List<Node> variablesList = getChilds( variablesNode, ELEMENT_VARIABLE );
            for( int i = 0; i < variablesList.size( ); i++ )
            {
                Node variables = ( Node )variablesList.get( i );
                loadVariables( variables, process.getData( ) );
            }
        }
        ////System.out.println( "Data loaded" );

        NodeList children = node.getChildNodes( );
        for( int i = 0; i < children.getLength( ); i++ )
        {
            Node child = children.item( i );
            ////System.out.println( "Element " + child );
            ActivityOrganizer elementOrganizer = new ActivityOrganizer( child, modelInstance, process );
            elementOrganizer.organizeInternalStructure( );
            ////System.out.println( "Element " + elementOrganizer.getActivity( ) + " loaded" );
            if( elementOrganizer.getActivity( ) != null )
            {
                if( elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_PICK ) || elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_RECEIVE ) )
                {
                    process.addStartingElement( elementOrganizer.getActivity( ) );
                }
                else
                    process.addNextElement( elementOrganizer.getActivity( ) );
                ////System.out.println( "Element " + elementOrganizer.getActivity( ).getName( ) + " added" );
            }
        }
    }
    /**
     * Loads all the partner links into the specified list
     * @param ports The node for loading the partner links
     * @param list The list for load the partner links
     * @throws OrganizerException Error loading the element
     */
    private void loadChannels( Node partnerLinkNode, List<IChannel> list ) throws LoaderException
    {
        //
        // Loads all partner links
        ChannelOrganizer partnerLinkOrganizer = new ChannelOrganizer( partnerLinkNode, modelInstance, ( IProcess )element );
        partnerLinkOrganizer.organizeInternalStructure( );
        list.add( partnerLinkOrganizer.getChannel( ) );
    }

    /**
     * Loads all the variables into the specified list
     * @param ports The node for loading the variables
     * @param list The list for load the variables
     * @throws OrganizerException Error loading the element
     */
    private void loadVariables( Node variableNode, List<IData> list ) throws LoaderException
    {
        //
        // Loads all variables
        DataOrganizer variableOrganizer = new DataOrganizer( variableNode, modelInstance, ( IProcess )element );
        variableOrganizer.organizeInternalStructure( );
        list.add( variableOrganizer.getElement( ) );
    }

    public IProcess getProcess()
    {
        return (IProcess) element;
    }
    
}
