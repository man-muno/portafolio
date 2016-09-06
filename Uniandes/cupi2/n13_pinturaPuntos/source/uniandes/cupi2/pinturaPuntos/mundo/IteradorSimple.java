/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorSimple.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.pinturaPuntos.mundo;

/**
 * Implementaci�n de un iterador simple para una bolsa
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
     * Valores sobre los que se est� iterando
     */
    private IImagen imagen;

    /**
     * Posici�n del pr�ximo valor a ser visitado
     */
    private int posActual;

    /**
     * La siguiente posici�n libre en la estructura. Corresponde tambi�n al n�mero de valores sobre los que se est� iterando
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
     * Constructor de un iterador con el tama�o (capacidad) especificado. <br>
     * <b> post: </b> Se cre� un iterador con la capacidad especificada.
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
    // M�todos: interface IIteradorPintura
    // -----------------------------------------------------------------
    /**
     * Indica si a�n hay valores sobre los cuales iterar. <br>
     * @return True si a�n no se han recorrido todos los elementos o false en caso contrario
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
     * <b>post: </b> Se agreg� el elemento especificado en la �ltima posici�n del iterador.
     */
    public void agregar( boolean elem )
    {
    }
}
