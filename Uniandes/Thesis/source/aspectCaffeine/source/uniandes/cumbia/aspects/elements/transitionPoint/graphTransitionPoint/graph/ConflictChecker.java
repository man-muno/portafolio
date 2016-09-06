/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: ConflictChecker.java,v 1.2 2009/02/11 18:11:58 man-muno Exp $ 
 * Universidad de los Andes (Bogota - Colombia) 
 * Departamento de Ingenieria de Sistemas y Computacion 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto CUMBIA
 * Aplicacion: AspectCaffeine
 * Autor: Manuel - Apr 18, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;


/**
 * Class that checks conflicts between advices given the instructions of each advice
 */
public class ConflictChecker
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Instance of the ConflictCheker
     */
    private static ConflictChecker instance;

    /**
     * Table where the key is an instruction name and the object is a list whit the names of all the instructions that conflict with it
     */
    private Hashtable<String, List<String>> instructionConflicts;
    
    /**
     * Table where the key is an advice name and the object is a list whit the names of all the advice that conflict with it
     */
    private Hashtable<String, List<String>> adviceConflicts;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a new ConflictChecker
     */
    private ConflictChecker( )
    {
        instructionConflicts = new Hashtable<String, List<String>>( );
        adviceConflicts = new Hashtable<String, List<String>>( );
        ArrayList<String> list = new ArrayList<String>( );
    }

    /**
     * Returns an instance of ConflictChecker
     * @return The instance of the class
     */
    public static ConflictChecker getInstance( )
    {
        if( instance == null )
        {
            synchronized( ConflictChecker.class )
            {
                if( instance == null )
                    instance = new ConflictChecker( );
            }
        }
        return instance;
    }

    /**
     * Returns a new list of the advices that don't conflict with the fist advice given by parameter.
     * @param targetAdvice The reference advice.
     * @param advices The list of the advices to check
     * @return A list of all the advices that the first advice has no conflict with
     */
    public List<IAdvice> getNonConflictingAdvices( IAdvice targetAdvice, List<IAdvice> advices )
    {
        List<IAdvice> nonConflictingAdvices = new ArrayList<IAdvice>( );
        for( int i = 0; i < advices.size( ); i++ )
            // Check if that the target advice is not in the list
            if( !advices.get( i ).getName( ).equals( targetAdvice.getName( ) ) )
                if( !existsConflictingInstructions( targetAdvice.getInstructions( ), advices.get( i ).getInstructions( ) ) && !existsConflictingAdvice(targetAdvice,advices.get( i )))
                    nonConflictingAdvices.add( advices.get( i ) );
        return nonConflictingAdvices;
    }

    private boolean existsConflictingAdvice( IAdvice targetAdvice, IAdvice advice )
    {
        boolean conflictFound = false;
        List<String> conflictingAdvices = adviceConflicts.get( targetAdvice.getName( ) );
        if( conflictingAdvices != null )
            for( int j = 0; j < conflictingAdvices.size( ) && !conflictFound; j++ )
            {
                String adviceName = conflictingAdvices.get( j );
                conflictFound = advice.getName( ).equals( adviceName );
            }
        return conflictFound;
    }

    /**
     * Checks if a conflict exists between the target instructions and the advice instructions
     * @param targetInstructions The target instructios
     * @param adviceInstructions The advice instructions
     * @return true in case where one of the targetInstructions conflicts with one of the adviceInstructions, false otherwise
     */
    private boolean existsConflictingInstructions( List<IInstruction> targetInstructions, List<IInstruction> adviceInstructions )
    {
        boolean conflictFound = false;
        for( int i = 0; i < targetInstructions.size( ) && !conflictFound; i++ )
        {
            List<String> conflictingInstructions = instructionConflicts.get( targetInstructions.get( i ).getName( ) );
            if( conflictingInstructions != null )
                for( int j = 0; j < conflictingInstructions.size( ) && !conflictFound; j++ )
                {
                    String instructionName = conflictingInstructions.get( j );
                    for( int k = 0; k < adviceInstructions.size( ) && !conflictFound; k++ )
                        conflictFound = adviceInstructions.get( k ).getName( ).equals( instructionName );
                }
        }
        return conflictFound;
    }

    /**
     * Adds a new conflict to a given instructions
     * @param instructionName The instruction
     * @param conflictName The conflict
     */
    public void addConflictingInstruction( String instructionName, String conflictName )
    {
        List<String> instructions = instructionConflicts.get( instructionName );
        if( instructions == null )
        {
            instructions = new ArrayList<String>( );
            instructions.add( conflictName );
        }
        else
        {
            boolean found = false;
            for( int i = 0; i < instructions.size( ) && !found; i++ )
                found = instructions.get( i ).equals( conflictName );
            if( !found )
                instructions.add( conflictName );
        }

        instructionConflicts.put( instructionName, instructions );
    }
    
    /**
     * Adds a new conflict to a given advice
     * @param adviceName The advice
     * @param conflictName The conflict
     */
    public void addConflictingAdvice( String adviceName, String conflictName )
    {
        List<String> advices = adviceConflicts.get( adviceName );
        if( advices == null )
        {
            advices = new ArrayList<String>( );
            advices.add( conflictName );
        }
        else
        {
            boolean found = false;
            for( int i = 0; i < advices.size( ) && !found; i++ )
                found = advices.get( i ).equals( conflictName );
            if( !found )
                advices.add( conflictName );
        }

        adviceConflicts.put( adviceName, advices );
    }
}
