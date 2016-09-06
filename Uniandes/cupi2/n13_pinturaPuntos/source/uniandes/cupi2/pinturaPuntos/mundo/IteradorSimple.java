/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorSimple.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Manuel Muñoz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.pinturaPuntos.mundo;

/**
 * Implementación de un iterador simple para una bolsa
 */
public class IteradorSimple implements IIteradorPintura
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
    private IImagen imagen;

    /**
     * Posición del próximo valor a ser visitado
     */
    private int posActual;

    /**
     * La siguiente posición libre en la estructura. Corresponde también al número de valores sobre los que se está iterando
     */
    private int sigPosLibre;

    /**
     * Fila sobre la cual se quieren obtener los elementos
     */
    private int fila;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un iterador con el tamaño (capacidad) especificado. <br>
     * <b> post: </b> Se creó un iterador con la capacidad especificada.
     * @param nFila La fila sobre la cual se quiere iterar. Entero superior o igual a cero.
     * @param nImagen Imagen sobre la cual se quiere obtener los valores
     */
    public IteradorSimple( int fila, IImagen nImagen )
    {
        imagen = nImagen;
        sigPosLibre = imagen.darColumnas( );
        posActual = NADA;
    }

    // -----------------------------------------------------------------
    // Métodos: interface IIteradorPintura
    // -----------------------------------------------------------------
    /**
     * Indica si aún hay valores sobre los cuales iterar. <br>
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( )
    {
        return imagen.darFilas( ) > 0 && ( posActual + 1 ) < sigPosLibre;
    }

    /**
     * Retorna el siguiente valor del iterador. <br>
     * @return El siguiente valor del iterador
     */
    public boolean darSiguiente( )
    {
        return ( ( posActual + 1 ) < sigPosLibre && imagen.darFilas( ) > 0 ) ? imagen.esNegro( fila, ++posActual ) : INVALIDO;
    }

    /**
     * Agrega un nuevo elemento al final del iterador. <br>
     * <b>pre: </b> Hay espacio en el iterador para el nuevo valor <br>
     * <b>post: </b> Se agregó el elemento especificado en la última posición del iterador.
     */
    public void agregar( boolean elem )
    {
    }
}
