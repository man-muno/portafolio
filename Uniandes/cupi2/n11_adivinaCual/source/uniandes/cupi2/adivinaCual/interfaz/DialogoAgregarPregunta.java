/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarPregunta.java,v 1.5 2007/04/13 12:36:33 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 9, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.adivinaCual.interfaz;

import javax.swing.JDialog;

/**
 * Diálogo para agregar una nueva pregunta a la aplicación.
 */
public class DialogoAgregarPregunta extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que esta en el dialogo
     */
    private PanelAgregarPregunta panelAgregarPregunta;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Se inicializan los atributos.
     * @param cual Instancia de la interfaz principal de la aplicación.
     * @param nombreAnimal Nombre del animal que el programa adivinó. Diferente de null
     */
    public DialogoAgregarPregunta( InterfazAdivinaCual cual, String nombreAnimal )
    {
        principal = cual;
        setTitle( "Agregar un nuevo animal" );
        panelAgregarPregunta = new PanelAgregarPregunta( this, nombreAnimal );
        setModal( true );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelAgregarPregunta );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Llama la ventana principal para agregar una nueva pregunta
     * @param pregunta Pregunta para agregar. Diferente de null
     * @param animal Animal respuesta a la pregunta. Diferente de null.
     */
    public void agregarPregunta( String pregunta, String animal )
    {
        principal.agregarPregunta( pregunta, animal );
    }

}
