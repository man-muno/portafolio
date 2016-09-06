/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorSimple.java,v 1.3 2007/02/01 15:18:00 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

/**
 * Implementación de un iterador simple para una bolsa
 */
public class IteradorSimple implements IIteradorBolsa
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private final static int NADA = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Valores sobre los que se está iterando
     */
    private int[] elems;

    /**
     * Posición del próximo valor a ser visitado
     */
    private int posActual;

    /**
     * La siguiente posición libre en la estructura. Corresponde también al número de valores sobre los que se está iterando
     */
    private int sigPosLibre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un iterador con el tamaño (capacidad) especificado. <br>
     * <b> post: </b> Se creó un iterador con la capacidad especificada.
     * @param tamanio El tamaño que va a tener el iterador
     */
    public IteradorSimple( int tamanio )
    {
        elems = new int[tamanio];
        sigPosLibre = 0;
        posActual = NADA;
    }

    // -----------------------------------------------------------------
    // Métodos: interface IteradorBolsa
    // -----------------------------------------------------------------
    /**
     * Indica si aún hay valores sobre los cuales iterar. <br>
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( )
    {
        return elems.length > 0 && ( posActual + 1 ) < sigPosLibre;
    }

    /**
     * Retorna el siguiente valor del iterador. <br>
     * @return El siguiente valor del iterador
     */
    public int darSiguiente( )
    {
        return ( ( posActual + 1 ) < sigPosLibre && elems.length > 0 ) ? elems[ ++posActual ] : INVALIDO;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo elemento al final del iterador. <br>
     * <b>pre: </b> Hay espacio en el iterador para el nuevo valor <br>
     * <b>post: </b> Se agregó el elemento especificado en la última posición del iterador.
     */
    public void agregar( int elem )
    {
        elems[ sigPosLibre++ ] = elem;
    }
}
