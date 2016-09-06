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

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IIteradorBolsa;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

/**
 * Panel donde se muestra la lista de los elementos de la bolsa
 */
public class PanelRepresentacion extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista donde se mostraran los elementos de la bolsa
     */
    private JList lista;

    /**
     * Método constructor de la clase PanelRepresentacion
     */
    public PanelRepresentacion( )
    {
        // Creación de los elementos gráficos del panel
        setLayout( new BorderLayout( ) );
        lista = new JList( );
        lista.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        lista.setLayoutOrientation( JList.VERTICAL );
        lista.setVisibleRowCount( -1 );
        JScrollPane listScroller = new JScrollPane( lista );
        listScroller.setPreferredSize( new Dimension( 100, 100 ) );
        add( listScroller );
    }

    /**
     * Pinta de nuevo la información de la bolsa
     * @param bolsa Bolsa a la cual se le quiere mostrar la información
     * @throws NoExisteException No debería ocurrir, porque se quiere mostrar los elementos presentes en la bolsa
     */
    public void actualizar( IBolsa bolsa ) throws NoExisteException
    {
        IIteradorBolsa iterador = bolsa.darIterador( );
        String[] elementos = new String[bolsa.darLongitud( )];
        int i = 0;
        while( iterador.haySiguiente( ) )
        {
            elementos[ i ] = String.valueOf( iterador.darSiguiente( ) );
            i++;
        }
        lista.setListData( elementos );
        updateUI( );
    }
}
