/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Mu�oz - Aug 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.interfaz;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.bolsa.mundo.IBolsa;

/**
 * Panel donde se muestra la informaci�n de la bolsa
 */
public class PanelInfoBolsa extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta donde se muestra la informaci�n del l�mite superior
     */
    private JLabel superior;

    /**
     * Etiqueta donde se muestra la informaci�n del l�mite inferior
     */
    private JLabel inferior;

    /**
     * Etiqueta donde se muestra la informaci�n de la longitud de la bolsa
     */
    private JLabel longitud;

    /**
     * M�todo constructor por defecto de la clas PanelInfoBolsa
     */
    public PanelInfoBolsa( )
    {
        // Instanciaci�n de los objetos gr�ficos
        setLayout( new BorderLayout( ) );
        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Estado de la Bolsa" );
        titulo.setTitleJustification( TitledBorder.LEFT );
        setBorder( titulo );

        // Valores iniciales de las etiquetas
        superior = new JLabel( "L�mite Superior: N/A" );
        inferior = new JLabel( "L�mite Inferior: N/A" );
        longitud = new JLabel( "Longitud: N/A" );

        add( superior, BorderLayout.CENTER );
        add( inferior, BorderLayout.NORTH );
        add( longitud, BorderLayout.SOUTH );
    }

    /**
     * Pinta de nuevo la informaci�n de la bolsa
     * @param bolsa Bolsa a la cual se le quiere mostrar la informaci�n
     */
    public void actualizar( IBolsa bolsa )
    {
        superior.setText( "L�mite Superior: " + bolsa.darSuperior( ) );
        inferior.setText( "L�mite Inferior: " + bolsa.darInferior( ) );
        longitud.setText( "Longitud: " + bolsa.darLongitud( ) );
        this.updateUI( );

    }
}
