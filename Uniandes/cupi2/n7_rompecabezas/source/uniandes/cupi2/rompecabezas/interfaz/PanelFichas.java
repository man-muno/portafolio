/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Mu�oz - Oct 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.interfaz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import uniandes.cupi2.rompecabezas.mundo.Ficha;

/**
 * Panel que contiene las fichas que no se han colocado del rompecabezas
 */
public class PanelFichas extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * JScrollPane que contiene las fichas que no se han colocado
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * M�todo constructor por defecto
     */
    public PanelFichas( )
    {
        scroll = new JScrollPane( );
        add( scroll );
    }

    /**
     * M�todo que pinta las fichas que se van a colocar en la figura
     * @param fichas Lista de las fichas de la figura. Diferente de null
     */
    public void pintarFichas( ArrayList fichas )
    {
        JPanel panelFichas = new JPanel( );
        panelFichas.setLayout( new GridLayout( ) );
        FichaTransferHandler picHandler = new FichaTransferHandler( );
        int preferedHeigth = 0;
        int preferedWith = 0;
        for( int i = 0; i < fichas.size( ); i++ )
        {
            Ficha ficha = ( Ficha )fichas.get( i );
            ImageIcon icono = new ImageIcon( ficha.obtenerRuta( ) );
            Imagen imagen = new Imagen( icono.getImage( ), ficha.obtenerPosicion( ) );
            preferedHeigth = icono.getIconHeight( );
            DTImagenFicha temp = new DTImagenFicha( imagen, icono.getIconWidth( ) * 20, preferedHeigth );
            preferedWith += icono.getIconWidth( );
            temp.setTransferHandler( picHandler );

            panelFichas.add( temp );
        }
        scroll.getViewport( ).add( panelFichas );
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.setPreferredSize( new Dimension( 1000, preferedHeigth + 20 ) );
        panelFichas.setPreferredSize( new Dimension( preferedWith, preferedHeigth ) );
        add( scroll );
    }

    /**
     * M�todo que resalta una ficha dada
     * @param numeroFicha entero con la posici�n de la ficha que se quiere resaltar
     * @return True si la ficha que se quer�a resaltar est� en este panel, false de lo contrario.
     */
    public boolean resaltarFicha( int numeroFicha )
    {
        Component[] componentes = obtenerImagenesFicha( );
        boolean encontrada = false;
        for( int i = 0; i < componentes.length && !encontrada; i++ )
        {
            DTImagenFicha imagen = ( DTImagenFicha )componentes[ i ];
            if( numeroFicha == imagen.obtenerPosicion( ) )
            {
                imagen.resaltar( );
                encontrada = true;
            }
        }
        return encontrada;
    }

    /**
     * M�todo que retorna un arreglo con las fichas que est�n en este panel
     * @return Arreglo con las im�genes que est�n en este panel
     */
    private Component[] obtenerImagenesFicha( )
    {
        // Se obtienen los componentes del panel
        Component[] componentes = getComponents( );
        // El �nico componente que tiene es el JScrollPane
        JScrollPane temp = ( JScrollPane )componentes[ 0 ];
        // A ese JScrollPane se le pide el viewport
        componentes = temp.getViewport( ).getComponents( );
        // Ese viewport solo tiene el panel y este contiene las im�genes
        JPanel panel = ( JPanel )componentes[ 0 ];
        componentes = panel.getComponents( );

        return componentes;
    }

    /**
     * M�todo que quita el panel de las fichas
     */
    public void quitarFichas( )
    {
        setVisible( false );
    }

}
