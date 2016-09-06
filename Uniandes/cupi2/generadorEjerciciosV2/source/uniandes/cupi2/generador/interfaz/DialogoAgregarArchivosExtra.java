/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: generadorEjerciciosV2
 * Autor: Manuel Muñoz - May 4, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.generador.interfaz;

import java.util.List;

import javax.swing.JDialog;

public class DialogoAgregarArchivosExtra extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que esta en el dialogo
     */
    private PanelAgregarArchivosExtra panelAgregarArchivos;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazGenerador principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Se inicializan los atributos.
     * @param padre Instancia de la interfaz principal de la aplicación.
     * @param archivosDefecto Lista de los archivos a mostrar
     * @param archivosSeleccionados Lista de archivos que el usuario ha seleccionado anteriormente
     */
    public DialogoAgregarArchivosExtra( InterfazGenerador padre, List archivosDefecto, List archivosSeleccionados)
    {
        principal = padre;
        setTitle( "Archivos Extra a Agregar" );
        panelAgregarArchivos = new PanelAgregarArchivosExtra( this,archivosDefecto,archivosSeleccionados );
        setModal( true );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelAgregarArchivos );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

    /**
     * Le indica a la interfaz principal que se cambio la lista de archivos seleccionados
     * @param archivosSeleccionados Lista con los archivos seleccionados por el usuarios. Diferente de null pero puede ser vacia
     */
    public void cambiarListaArchivosSeleccionados( List archivosSeleccionados )
    {
        principal.cambiarListaArchivosSeleccionados(archivosSeleccionados);
    }
}

