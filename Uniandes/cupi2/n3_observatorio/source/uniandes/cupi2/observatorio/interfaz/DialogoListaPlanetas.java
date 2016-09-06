/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoListaPlanetas.java,v 1.2 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Dialogo para listar los planetas
 */
public class DialogoListaPlanetas extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que se encuentra en el dialogo
     */
    private PanelListaPlanetas panel;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros
     * @param planetas Lista de los planetas a mostrar
     */
    public DialogoListaPlanetas( ArrayList planetas )
    {
        setLocationRelativeTo( null );
        setTitle( "Lista de Planetas" );
        setSize( new Dimension( 161, 173 ) );
        panel = new PanelListaPlanetas( this, planetas );
        add( panel );
        setVisible( true );
    }

}
