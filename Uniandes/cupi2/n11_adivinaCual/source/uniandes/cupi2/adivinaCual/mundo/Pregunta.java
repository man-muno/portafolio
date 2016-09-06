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

import java.util.List;

/**
 * Clase que representa una pregunta del juego o una respuesta<b>inv: </b> <br>
 * Si descripcion es diferente de null entonces nombreAnimal es igual a null y rutaAnimal es igual a null<br>
 * Si descripcion es igual a null entonces nombreAnimal es diferente de null<br>
 */
public class Pregunta
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Descripcion de la pregunta.
     */
    private String descripcion;

    /**
     * Rama del árbol que corresponde a un Si
     */
    private Pregunta respuestaSI;

    /**
     * Rama del árbol que corresponde a un NO
     */
    private Pregunta respuestaNO;

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
    public Pregunta( String nNombreAnimal, String nRutaImagen )
    {
        descripcion = null;
        respuestaSI = null;
        respuestaNO = null;
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
    public Pregunta( String nDescripcion, Pregunta respuestaSi, Pregunta respuestaNo )
    {
        descripcion = nDescripcion;
        respuestaSI = respuestaSi;
        respuestaNO = respuestaNo;
        nombreAnimal = null;
        rutaImagen = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve los elementos del árbol en inorden. <b>post: </b> Se retorno el vector de Strings para recorrer los elementos del árbol en preorden.<br>
     * @param resultado Lista donde se guardaran los elementos en inorden. Diferente de null
     */
    public void darInorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( respuestaSI != null )
        {
            respuestaSI.darInorden( resultado );
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
        if( respuestaNO != null )
        {
            respuestaNO.darInorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en preorden. <b>post: </b> Se retorno el vector de Strings para recorrer
     * los elementos del árbol en preorden.<br>
     * @param resultado Resultado del recorrido. Diferente de null
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
        if( respuestaSI != null )
        {
            respuestaSI.darPreorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( respuestaNO != null )
        {
            respuestaNO.darPreorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en postorden.<br>
     * <b>post: </b> Se retorno el vector de Strings para recorrer los elementos del árbol en postorden.<br>
     * @param resultado Resultado del recorrido. Diferente de null<br>
     */
    public void darPostorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( respuestaSI != null )
        {
            respuestaSI.darPostorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( respuestaNO != null )
        {
            respuestaNO.darPostorden( resultado );
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
        int altura1 = respuestaSI != null ? respuestaSI.darAltura( ) : 0;
        int altura2 = respuestaNO != null ? respuestaNO.darAltura( ) : 0;
        return ( altura1 >= altura2 ? altura1 : altura2 ) + 1;
    }

    /**
     * Devuelve el peso del árbol cuya raíz es el nodo actual.<br>
     * <b>post: </b> Se retornó el peso del árbol.<br>
     * @return Peso del árbol cuya raíz es el nodo actual
     */
    public int darPeso( )
    {
        int peso1 = respuestaSI != null ? respuestaSI.darPeso( ) : 0;
        int peso2 = respuestaNO != null ? respuestaNO.darPeso( ) : 0;
        return peso1 + peso2 + 1;
    }

    /**
     * Retorna la ruta de la imagen que representa la respuesta <b>post: </b> Se retornó la ruta de la imagen<br>
     * @return Puede ser null
     */
    public String darRuta( )
    {
        return rutaImagen;
    }

    /**
     * Retorna la pregunta <b>post: </b> Se retornó la pregunta<br>
     * @return Cadena de caracteres que contiene la pregunta. Puede ser null.
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna la siguiente pregunta, si la respuesta a la actual fue positiva <br>
     * <b>post: </b> Se retornó la respuesta en caso que sea positiva<br>
     * @return Puede ser null
     */
    public Pregunta darRespuestaPositiva( )
    {
        return respuestaSI;
    }

    /**
     * Retorna la siguiente pregunta, si la respuesta a la actual fue negativa <br>
     * <b>post: </b> Se retornó la respuesta en caso que sea positiva<br>
     * @return Puede ser null
     */
    public Pregunta darRespuestaNegativa( )
    {
        return respuestaNO;
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
     */
    public void agregarPregunta( String descripcion2, String animal )
    {
        descripcion = descripcion2;
        respuestaNO = new Pregunta( nombreAnimal, rutaImagen );
        respuestaSI = new Pregunta( animal, "" );
        nombreAnimal = null;
        rutaImagen = null;
        verificarInvariante( );
    }

    /**
     * Retorna una lista con las hojas que tiene el árbol. <b>post: </b> hojas contiene todas las hojas del árbol, a partir de this<br>
     * @param hojas Lista donde se coloca el resultado de la búsqueda, las hojas que existen por debajo de esta pregunta.
     */
    public void darHojas( List hojas )
    {
        if( esHoja( ) )
            hojas.add( this );
        if( respuestaSI != null )
            respuestaSI.darHojas( hojas );
        if( respuestaNO != null )
            respuestaNO.darHojas( hojas );
    }

    /**
     * Evalúa si esta pregunta es una hoja.
     * @return True en caso que sea una hoja, false de lo contrario.
     */
    public boolean esHoja( )
    {
        return respuestaSI == null && respuestaNO == null && descripcion == null && nombreAnimal != null;
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
            boolean encontradoSI = respuestaSI != null ? respuestaSI.buscarAnimal( animal ) : false;
            boolean encontradoNO = respuestaNO != null ? respuestaNO.buscarAnimal( animal ) : false;
            respuesta = encontradoNO || encontradoSI;
        }
        return respuesta;
    }

    /**
     * Verifica que una pregunta es la misma que se pasa por parámetro.
     */
    public boolean equals( Object objeto )
    {
        if( ! ( objeto instanceof Pregunta ) )
            return false;
        Pregunta pregunta = ( Pregunta )objeto;

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
        if( respuestaNO == null && respuestaSI == null )
        {
            respuesta = true;
        }
        else if( respuestaSI == null || respuestaNO == null )
        {
            respuesta = false;
        }
        else
        {
            respuesta = respuestaNO.esCompleto( ) && respuestaSI.esCompleto( );
        }
        return respuesta;
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
