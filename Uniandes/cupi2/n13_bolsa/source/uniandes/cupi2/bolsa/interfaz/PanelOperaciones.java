/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Muñoz - Aug 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.interfaz;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel donde están las opciones de la bolsa
 */
public class PanelOperaciones extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Constante que identifica la opción de iniciar la bolsa
     */
    public static final String INICIAR = "INICIAR";

    /**
     * Constante que identifica la opción de agregar un elemento a la bolsa
     */
    public static final String AGREGAR = "AGREGAR";

    /**
     * Constante que identifica la opción de eliminar un elemento la bolsa
     */
    public static final String ELIMINAR = "ELIMINAR";

    /**
     * Constante que identifica la opción de buscar un elemento de la bolsa
     */
    public static final String BUSCAR = "BUSCAR";

    /**
     * Constante que identifica la opción de retornar un elemento de la bolsa
     */
    public static final String RETORNAR = "RETORNAR";

    /**
     * Método constructor de la clase PanelOperaciones.
     * @param panel Panel donde se va a localizar la lógica de los botones.
     */
    public PanelOperaciones( ActionListener panel )
    {
        // Creación de elementos gráficos
        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Operaciones" );
        titulo.setTitleJustification( TitledBorder.LEFT );
        setBorder( titulo );

        // Nombres de los botones
        JButton iniciar = new JButton( "Iniciar" );
        JButton agregar = new JButton( "Agregar" );
        JButton eliminar = new JButton( "Eliminar" );
        JButton buscar = new JButton( "Buscar" );
        JButton retornar = new JButton( "Retornar" );

        // Se le asigna a cada botón una acción
        iniciar.setActionCommand( INICIAR );
        agregar.setActionCommand( AGREGAR );
        eliminar.setActionCommand( ELIMINAR );
        buscar.setActionCommand( BUSCAR );
        retornar.setActionCommand( RETORNAR );

        // Se agrega a cada boton el actionListener
        iniciar.addActionListener( panel );
        agregar.addActionListener( panel );
        eliminar.addActionListener( panel );
        buscar.addActionListener( panel );
        retornar.addActionListener( panel );

        add( iniciar );
        add( agregar );
        add( eliminar );
        add( buscar );
        add( retornar );
    }
}
