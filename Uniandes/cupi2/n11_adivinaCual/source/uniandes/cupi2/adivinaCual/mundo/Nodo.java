/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 5, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un elemento del árbol. Puede representar una pregunta o una respuesta<br>
 * Un nodo representa una pregunta si su descripcion es diferente de null<br>
 * Un nodo representa una respuesta si nombreAnimal es diferente de null<br>
 * <b>inv: </b> <br>
 * Si descripcion es diferente de null entonces nombreAnimal es igual a null y rutaAnimal es igual a null<br>
 * Si descripcion es igual a null entonces nombreAnimal es diferente de null<br>
 */
public class Nodo implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Versión de la clase
     */
    private static final long serialVersionUID = 2354436757330119127L;

    /**
     * Descripcion de la pregunta.
     */
    private String descripcion;

    /**
     * Rama del árbol que corresponde a un Si
     */
    private Nodo nodoSI;

    /**
     * Rama del árbol que corresponde a un NO
     */
    private Nodo nodoNO;

    /**
     * Nombre del animal
     */
    private String nombreAnimal;

    /**
     * Ruta de la imagen donde está el animal que pertenece a la respuesta
     */
    private String rutaImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor que recibe un animal y donde se encuentra su imagen
     * @param nNombreAnimal Cadena de caracteres que contiene el animal. Diferente de null.
     * @param nRutaImagen Cadena de caracteres que contiene la ruta de la imangen. Diferente de null.
     */
    public Nodo( String nNombreAnimal, String nRutaImagen )
    {
        descripcion = null;
        nodoSI = null;
        nodoNO = null;
        nombreAnimal = nNombreAnimal;
        rutaImagen = nRutaImagen;
        verificarInvariante( );
    }

    /**
     * Constructor de una pregunta. Recibe la descripcion de la pregunta y sus dos hijos
     * @param nDescripcion Descripcion de la pregunta. Diferente de null.
     * @param respuestaSi Respuesta que va a ser la hija por el camino afirmativo.
     * @param respuestaNo Respuesta que va a ser la hija por el camino negativo.
     */
    public Nodo( String nDescripcion, Nodo respuestaSi, Nodo respuestaNo )
    {
        descripcion = nDescripcion;
        nodoSI = respuestaSi;
        nodoNO = respuestaNo;
        nombreAnimal = null;
        rutaImagen = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retorno el vector de Strings para recorrer los elementos del árbol en inorden.<br>
     * @param resultado Lista donde se guardaran los elementos en inorden. Diferente de null
     */
    public void darInorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( nodoSI != null )
        {
            nodoSI.darInorden( resultado );
        }

        // Agrega el elemento que se encuentra en el nodo
        if( descripcion != null )
        {
            resultado.add( descripcion );
        }
        else
        {
            resultado.add( nombreAnimal );
        }

        // Agrega los elementos del subárbol derecho
        if( nodoNO != null )
        {
            nodoNO.darInorden( resultado );
        }
    }

    /**
     * Devuelve los elementos del árbol en preorden. <br>
     * <b>post: </b> Se retorno el vector de Strings para recorrer los elementos del árbol en preorden.<br>
     * @param resultado Lista donde se guardaran los elementos en preorden. Diferente de null
     */
    public void darPreorden( List resultado )
    {
        // Agrega el elemento que se encuentra en el nodo
        if( descripcion != null )
        {
            resultado.add( descripcion );
        }
        else
        {
            resultado.add( nombreAnimal );
        }

        // Agrega los elementos del subárbol izquierdo
        if( nodoSI != null )
        {
            nodoSI.darPreorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( nodoNO != null )
        {
            nodoNO.darPreorden( resultado );
        }
    }

    /**
     * Devuelve los elementos del árbol en postorden. <br>
     * <b>post: </b> Se retorno el vector de Strings para recorrer los elementos del árbol en postorden.<br>
     * @param resultado Lista donde se guardaran los elementos en postorden. Diferente de null
     */
    public void darPostorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( nodoSI != null )
        {
            nodoSI.darPostorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( nodoNO != null )
        {
            nodoNO.darPostorden( resultado );
        }

        // Agrega el elemento que se encuentra en el nodo
        if( descripcion != null )
        {
            resultado.add( descripcion );
        }
        else
        {
            resultado.add( nombreAnimal );
        }
    }

    /**
     * Devuelve la altura del árbol cuya raíz es el nodo actual. <br>
     * <b>post: </b> Se retornó la altura del árbol.<br>
     * @return Altura del árbol cuya raíz es el nodo actual
     */
    public int darAltura( )
    {
        int altura1 = nodoSI != null ? nodoSI.darAltura( ) : 0;
        int altura2 = nodoNO != null ? nodoNO.darAltura( ) : 0;
        return ( altura1 >= altura2 ? altura1 : altura2 ) + 1;
    }

    /**
     * Devuelve el peso del árbol cuya raíz es el nodo actual.<br>
     * <b>post: </b> Se retornó el peso del árbol.<br>
     * @return Peso del árbol cuya raíz es el nodo actual
     */
    public int darPeso( )
    {
        int peso1 = nodoSI != null ? nodoSI.darPeso( ) : 0;
        int peso2 = nodoNO != null ? nodoNO.darPeso( ) : 0;
        return peso1 + peso2 + 1;
    }

    /**
     * Retorna la ruta de la imagen que representa la respuesta<br>
     * <b>post: </b> Se retornó la ruta de la imagen<br>
     * @return Puede ser null
     */
    public String darRuta( )
    {
        return rutaImagen;
    }

    /**
     * Retorna la pregunta<br>
     * <b>post: </b> Se retornó la pregunta<br>
     * @return Cadena de caracteres que contiene la pregunta. Puede ser null.
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna el siguiente nodo, por el subárbol positivo<br>
     * <b>post: </b> Se retornó el subárbol positivo<br>
     * @return Puede ser null
     */
    public Nodo darNodoPositivo( )
    {
        return nodoSI;
    }

    /**
     * Retorna el siguiente nodo, por el subárbol negativo<br>
     * <b>post: </b> Se retornó el subárbol negativo<br>
     * @return Puede ser null
     */
    public Nodo darNodoNegativo( )
    {
        return nodoNO;
    }

    /**
     * Retorna el nombre del animal en caso que sea una hoja del árbol <br>
     * <b>post: </b> Se retornó en nombre del animal<br>
     * @return Puede ser null
     */
    public String darNombreAnimal( )
    {
        return nombreAnimal;
    }

    /**
     * Vuelve una hoja un nodo con la descripcion que se pasa por parámetro. <br>
     * La ruta del nuevo animal deberá ser un String vacío.<br>
     * La respuesta negativa se convierte en el valor que tenía la hoja anteriormente y la respuesta positiva se convierte en el nuevo animal.<br>
     * <b>pre: </b> La pregunta es una hoja<br>
     * @param descripcion2 Descripcion de la mueva pregunta. Diferente de null.
     * @param animal Nombre del animal a agregar. Diferente de null.
     * @param ruta Ruta de la imagen del animal seleccionado. Diferente de null.
     */
    public void agregarPregunta( String descripcion2, String animal, String ruta )
    {
        descripcion = descripcion2;
        nodoNO = new Nodo( nombreAnimal, rutaImagen );
        nodoSI = new Nodo( animal, ruta );
        nombreAnimal = null;
        rutaImagen = null;
        verificarInvariante( );
    }

    /**
     * Retorna una lista con las hojas que tiene el árbol.<br>
     * <b>post: </b> hojas contiene todas las hojas del árbol, a partir de this<br>
     * @param hojas Lista donde se coloca el resultado de la búsqueda, las hojas que existen por debajo de esta pregunta.
     */
    public void darHojas( List hojas )
    {
        if( esHoja( ) )
            hojas.add( this );
        if( nodoSI != null )
            nodoSI.darHojas( hojas );
        if( nodoNO != null )
            nodoNO.darHojas( hojas );
    }

    /**
     * Evalúa si esta pregunta es una hoja.
     * @return True en caso que sea una hoja, false de lo contrario.
     */
    public boolean esHoja( )
    {
        return nodoSI == null && nodoNO == null && descripcion == null && nombreAnimal != null;
    }

    /**
     * Informa si un animal existe o no en el árbol.
     * @param animal Nombre del animal que se quiere buscado. Diferente de null.
     * @return True en caso que exista un animal en el arbol con ese nombre, false de lo contrario.
     */
    public boolean buscarAnimal( String animal )
    {
        boolean respuesta = false;
        if( esHoja( ) && nombreAnimal.equalsIgnoreCase( animal ) )
            respuesta = true;
        else
        {
            boolean encontradoSI = nodoSI != null ? nodoSI.buscarAnimal( animal ) : false;
            boolean encontradoNO = nodoNO != null ? nodoNO.buscarAnimal( animal ) : false;
            respuesta = encontradoNO || encontradoSI;
        }
        return respuesta;
    }

    /**
     * Verifica que una pregunta es la misma que se pasa por parámetro.
     */
    public boolean equals( Object objeto )
    {
        if( ! ( objeto instanceof Nodo ) )
            return false;
        Nodo pregunta = ( Nodo )objeto;

        if( pregunta.darDescripcion( ) != null && pregunta.darDescripcion( ).equals( descripcion ) )
            return true;
        else if( pregunta.darNombreAnimal( ) != null && pregunta.darNombreAnimal( ).equals( nombreAnimal ) )
            return true;
        return false;
    }

    /**
     * Verifica que el árbol sea completo
     * @return True en caso que el árbol sea completo, false de lo contrario.
     */
    private boolean esCompleto( )
    {
        boolean respuesta = false;
        if( nodoNO == null && nodoSI == null )
        {
            respuesta = true;
        }
        else if( nodoSI == null || nodoNO == null )
        {
            respuesta = false;
        }
        else
        {
            respuesta = nodoNO.esCompleto( ) && nodoSI.esCompleto( );
        }
        return respuesta;
    }

    /**
     * Retorna la lista de los nodos hijos que tiene el nodo actual
     * @return Lista inicializada pero puede ser vacía
     */
    public List darNodosHijos( )
    {
        ArrayList hijas = new ArrayList( );
        if( nodoNO != null )
            hijas.add( nodoNO );
        if( nodoSI != null )
            hijas.add( nodoSI );
        return hijas;
    }

    /**
     * Método que verifica la invariante de la clase.<br>
     * <b>inv: </b> <br>
     * Si descripcion es diferente de null entonces nombreAnimal es igual a null y rutaAnimal es igual a null<br>
     * Si descripcion es igual a null entonces nombreAnimal es diferente de null<br>
     * El árbol es completo, todos los nodos que no son hojas tienen dos hojas.<br>
     */
    private void verificarInvariante( )
    {
        assert ( descripcion != null && nombreAnimal == null && rutaImagen == null ) || ( descripcion == null && nombreAnimal != null ) : "La pregunta está mal construida";
        assert esCompleto( ) : "El árbol debe ser completo";
    }

}
