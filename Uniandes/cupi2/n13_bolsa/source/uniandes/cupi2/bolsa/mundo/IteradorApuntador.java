/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorApuntador.java,v 1.3 2007/02/01 15:18:00 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Munoz - Sep 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

/**
 * Clase que representa un iterador que va a utilizar un apuntador a un primer elemento
 */
public class IteradorApuntador implements IIteradorBolsa
{
    /**
     * Elemento que contiene el iterador
     */
    private Elemento elemento;

    /**
     * Constructor del iterador
     * @param primerElemento Primer elemento del iterador
     */
    public IteradorApuntador( Elemento primerElemento )
    {
        elemento = primerElemento;
    }

    /**
     * Retorna el siguiente valor del iterador. <br>
     * @return El siguiente valor del iterador
     */
    public int darSiguiente( )
    {
        int valor = elemento.obtenerValor( );
        elemento = elemento.obtenerSiguiente( );
        return valor;
    }

    /**
     * Indica si aún hay valores sobre los cuales iterar. <br>
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( )
    {
        if( elemento == null )
            return false;
        return true;
    }
}
