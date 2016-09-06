/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelTablero.java,v 1.2 2007/04/03 07:40:44 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Muñoz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.crucigrama.interfaz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel donde se muestran los campos para introducir las palabras 
 */
public class PanelTablero extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Ventana principal de la aplicación
     */
    private InterfazCrucigrama principal;
    
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Campos de texto donde el usuario puede colocar las palabras
     */
    private JTextField[][] camposTexto;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor por parámetros del panel.
     * @param interfaz Instancia de la ventana principal de la aplicación
     */
    public PanelTablero( InterfazCrucigrama interfaz )
    {
        principal = interfaz;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Modifica los componentes de la interfaz de acuerdo al nuevo tablero
     * @param casillasNegras Matriz de caracteres que contienen cuales campos deben ser negros.
     */
    public void actualizar( char[][] casillasNegras )
    {
        removeAll( );
        camposTexto = new JTextField[casillasNegras.length][casillasNegras[0].length];
        setLayout( new GridLayout(casillasNegras.length+1,casillasNegras[0].length+1) );
        for(int i=0;i<casillasNegras.length+1;i++)
        {
            for(int j=0;j<casillasNegras[0].length+1;j++)
            {
                if(i== 0 && j==0)
                {
                    add(new JLabel());
                } 
                else  if(i == 0 && j!=0)
                {
                    JLabel numero = new JLabel("   " + j);
                    add(numero);
                } 
                else if(j == 0 && i!=0)
                {
                    JLabel numero = new JLabel("   " + i);
                    add(numero);
                }
                else if(i != 0 && j != 0)
                {
                    char letra = casillasNegras[i-1][j-1];
                    JTextField campo = new JTextField();
                    if(letra == '$')
                    {
                        campo.setEditable( false );
                        campo.setBackground( Color.BLACK );
                    }
                    add(campo);
                    camposTexto[i-1][j-1] = campo;
                }
            }
        }
    }

    /**
     * Retorna una matriz con las letras que el usuario ingreso en el tablero
     * @return Matriz de objetos char. En los lugares donde el usuario no coloco nada se coloca $
     */
    public String[][] obtenerJuegoUsuario( )
    {
        String[][] juegoUsuario = new String[camposTexto.length][camposTexto[0].length];
        for(int i=0;i<camposTexto.length;i++)
        {
            for(int j=0;j<camposTexto[0].length;j++)
            {
                String txt = camposTexto[i][j].getText( );
                if(txt.length( ) == 0)
                    juegoUsuario[i][j] = "$";
                else
                    juegoUsuario[i][j] = txt;
            }
        }
        return juegoUsuario;
    }

}
