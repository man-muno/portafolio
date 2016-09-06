/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Manuel Muñoz - Abr 7, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.generador.mundo;

/**
 * Clase que representa un archivo que va a ser generado para el ejercicio.
 */
public class Archivo
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Nombre del archivo a ser generado
     */
    private String nombreArchivo;
    
    /**
     * True en caso que el archivo contenga etiquetas que deban cambiarse por alguno de los datos del nuevo proyecto generado. False de lo contrario.
     */
    private boolean reemplazable;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Constructor por parámetros de la clase. Inicializa los parámetros con los métodos.
     * @param nombreArchivo Nombre del archivo. Diferente de null.
     * @param reemplazable True en caso que el archivo contenga etiquetas que deban cambiarse por alguno de los datos del nuevo proyecto generado. False de lo contrario.
     */
    public Archivo(String nombreArchivo, boolean reemplazable)
    {
        this.nombreArchivo = nombreArchivo;
        this.reemplazable = reemplazable;
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Retorna el nombre del archivo.
     * @return Diferente de null
     */
    public String darNombreArchivo()
    {
        return nombreArchivo;
    }
    
    /**
     * Retorna si se debe reemplazar alguna información del archivo.
     * @return True en caso que el archivo contenga etiquetas que deban cambiarse por alguno de los datos del nuevo proyecto generado. False de lo contrario.
     */
    public boolean esReemplazable()
    {
        return reemplazable;
    }
}
