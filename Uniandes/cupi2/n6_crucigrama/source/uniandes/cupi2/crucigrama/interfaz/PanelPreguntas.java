/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelPreguntas.java,v 1.1 2007/03/22 14:05:58 man-muno Exp $
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.crucigrama.mundo.Pregunta;

/**
 * Panel donde se muestran las preguntas del crucigrama
 */
public class PanelPreguntas extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante que identifica que el usuario quiere la siguiente pregunta vertical
     */
    private static final String SIG_VERTICAL = "SIG_VERTICAL";

    /**
     * Constante que identifica que el usuario quiere la anterior pregunta vertical
     */
    private static final String ANT_VERTICAL = "ANT_VERTICAL";

    /**
     * Constante que identifica que el usuario quiere la siguiente pregunta horizontal
     */
    private static final String SIG_HORIZONTAL = "SIG_HORIZONTAL";

    /**
     * Constante que identifica que el usuario quiere la anterior pregunta horizontal
     */
    private static final String ANT_HORIZONTAL = "ANT_HORIZONTAL";
    
    /**
     * Constante donde se define el tamaño para que los Strings se vean en dos líneas
     */
    private static final int TAM_CORTE = 50;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    
    private JButton btnSigVertical;

    private JButton btnAntVertical;

    private JButton btnSigHorizontal;

    private JButton btnAntHorizontal;

    private JTextArea txtVertical;

    private JTextArea txtHorizontal;
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Lista de preguntas verticales
     */
    private ArrayList verticales;
    
    /**
     * Lista de preguntas horizontales
     */
    private ArrayList horizontales;
    
    /**
     * Índice para las preguntas de la lista vertical
     */
    private int indiceVertical;
    
    /**
     * Índice para las preguntas de la lista horizontal
     */
    private int indiceHorizontal;
    
    /**
     * Ventana principal de la aplicación
     */
    private InterfazCrucigrama principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor por defecto. Inicializa los contadores en 0;
     */
    public PanelPreguntas(InterfazCrucigrama crucigrama)
    {
        indiceHorizontal = 0;
        indiceVertical = 0;
        principal = crucigrama;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Actualiza la informacion de acuerdo a lo que se encuentre en el archivo de propiedades
     * @param nVerticales Lista de las preguntas verticales. Diferente de null.
     * @param nHorizontales Lista de las preguntas horizontales. Diferente de null.
     */
    public void actualizar(ArrayList nVerticales, ArrayList nHorizontales)
    {
        verticales = nVerticales;
        horizontales = nHorizontales;


        removeAll();
        
        setLayout( new GridLayout(2,3) );
        
        JPanel panelVertical = new JPanel();
            
        btnSigVertical = new JButton(">>");
        btnSigVertical.addActionListener( this );
        btnSigVertical.setActionCommand( SIG_VERTICAL );
      
        btnAntVertical = new JButton("<<");
        btnAntVertical.addActionListener( this );
        btnAntVertical.setActionCommand( ANT_VERTICAL );
        
        String coordenadaHoriz = ((Pregunta)horizontales.get(indiceHorizontal)).obtenerCoordenada() ;
        String descripcionHoriz = ((Pregunta)horizontales.get(indiceHorizontal)).obtenerDescripcion();
        if(descripcionHoriz.length( ) > TAM_CORTE)
            descripcionHoriz = descripcionHoriz.substring( 0, TAM_CORTE ) + "\n" + descripcionHoriz.substring( TAM_CORTE, descripcionHoriz.length( ) );
        txtHorizontal = new JTextArea(coordenadaHoriz+ ">" + descripcionHoriz);
       
        
        JPanel panelHorizontal = new JPanel();
        btnSigHorizontal = new JButton(">>");
        btnSigHorizontal.addActionListener( this );
        btnSigHorizontal.setActionCommand( SIG_HORIZONTAL );
        
        btnAntHorizontal = new JButton("<<");
        btnAntHorizontal.addActionListener( this );
        btnAntHorizontal.setActionCommand( ANT_HORIZONTAL );
        
        String coordenadaVert = ((Pregunta)verticales.get(indiceVertical)).obtenerCoordenada() ;
        String descripcionVert = ((Pregunta)verticales.get(indiceVertical)).obtenerDescripcion();
        if(descripcionVert.length( ) > TAM_CORTE)
            descripcionVert = descripcionVert.substring( 0, TAM_CORTE ) + "\n" + descripcionVert.substring( TAM_CORTE, descripcionVert.length( ) );
        txtVertical = new JTextArea(coordenadaVert+ ">" + descripcionVert);
                
        panelVertical.add(btnAntVertical);
        panelVertical.add(txtVertical);
        panelVertical.add(btnSigVertical);
        TitledBorder borde = new TitledBorder( "Preguntas Verticales" );
        borde.setTitleColor( Color.BLACK );
        panelVertical.setBorder( borde );
        panelHorizontal.add(btnAntHorizontal);
        panelHorizontal.add(txtHorizontal);
        panelHorizontal.add(btnSigHorizontal);
        borde = new TitledBorder( "Preguntas Horizontales" );
        borde.setTitleColor( Color.BLACK );
        panelHorizontal.setBorder( borde );
        
        add(panelVertical);
        add(panelHorizontal);
        principal.validate( );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if(comando.equals( SIG_HORIZONTAL ))
        {
            if(indiceHorizontal<horizontales.size( )-1)
                indiceHorizontal++;
            else
                JOptionPane.showMessageDialog( this, "No hay una palabra después de esta", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(comando.equals( ANT_HORIZONTAL ))
        {
            if(indiceHorizontal!=0)
                indiceHorizontal--;
            else
                JOptionPane.showMessageDialog( this, "No hay una palabra antes de esta", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        if(comando.equals( SIG_VERTICAL ))
        {
            if(indiceVertical<verticales.size( )-1)
                indiceVertical++;
            else
                JOptionPane.showMessageDialog( this, "No hay una palabra después de esta", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(comando.equals( ANT_VERTICAL ))
        {
            if(indiceVertical!=0)
                indiceVertical--;
            else
                JOptionPane.showMessageDialog( this, "No hay una palabra antes de esta", "Atención", JOptionPane.INFORMATION_MESSAGE);                
        }


        actualizar(); 
    }

    private void actualizar( )
    {
        String coordenadaHoriz = ((Pregunta)horizontales.get(indiceHorizontal)).obtenerCoordenada() ;
        String descripcionHoriz = ((Pregunta)horizontales.get(indiceHorizontal)).obtenerDescripcion();
        if(descripcionHoriz.length( ) > TAM_CORTE)
            descripcionHoriz = descripcionHoriz.substring( 0, TAM_CORTE ) + "\n" + descripcionHoriz.substring( TAM_CORTE, descripcionHoriz.length( ) );
        txtHorizontal.setText( coordenadaHoriz+ ">" + descripcionHoriz);
       
        String coordenadaVert = ((Pregunta)verticales.get(indiceVertical)).obtenerCoordenada() ;
        String descripcionVert = ((Pregunta)verticales.get(indiceVertical)).obtenerDescripcion();
        if(descripcionVert.length( ) > TAM_CORTE)
            descripcionVert = descripcionVert.substring( 0, TAM_CORTE ) + "\n" + descripcionVert.substring( TAM_CORTE, descripcionVert.length( ) );
        txtVertical.setText( coordenadaVert+ ">" + descripcionVert);
    }

}
