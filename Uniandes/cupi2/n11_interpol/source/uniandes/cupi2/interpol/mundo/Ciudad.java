/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Ciudad.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.mundo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un elemento del árbol <b>inv:</b>nombre != null, lugares debe tener por lo menos uno, tiempoViaje mayor a cero, descripcion != null <br>
 */
public class Ciudad
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre de la ciudad
     */
    private String nombre;

    /**
     * Indica si el sospechoso se encuentra en la ciudad o no.
     */
    private boolean estaSospechoso;

    /**
     * Lugares donde se puede encontrar pistas en la ciudad
     */
    private ArrayList lugares;

    /**
     * Ciudad hija de la actual
     */
    private Ciudad ciudadDerecha;

    /**
     * Ciudad hija de la actual
     */
    private Ciudad ciudadCentral;

    /**
     * Ciudad hija de la actual
     */
    private Ciudad ciudadIzquierda;

    /**
     * Tiempo que dura el viaje de la ciudad padre a esta.
     */
    private int tiempoViaje;

    /**
     * Descripcion de la ciudad
     */
    private String descripcion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros. Inicializa a los atributos con los parámetros. El arreglo de lugares es inicializado con 3 posiciones vacías.
     * @param nombreCiudad Nombre de la ciudad. Diferente de null.
     * @param descripcion2 Descripcion de la ciudad. Diferente de null.
     * @param tiempoViaje2 Tiempo que toma llegar a la ciudad. Entero mayor a cero.
     * @param nEstaSospechoso True en caso que se encuentre el sospechoso en esta ciudad. False de lo contrario.
     */
    public Ciudad( String nombreCiudad, String descripcion2, int tiempoViaje2, boolean nEstaSospechoso )
    {
        nombre = nombreCiudad;
        descripcion = descripcion2;
        tiempoViaje = tiempoViaje2;
        estaSospechoso = nEstaSospechoso;
        lugares = new ArrayList( );
    }

    /**
     * Agrega un nuevo lugar para las pistas, en la posición que este desocupada<br>
     * <br>
     * <b>pre:</b> Arreglo de lugares inicializado<br>
     * <br>
     * <b>post:</b> En caso que haya una posición vacía en el arreglo de lugares se agrega en nuevo lugar, de lo contrario se lanza una excepción<br>
     * @param lugar Nuevo lugar para agregar. Diferente de null.
     */
    public void agregarLugar( Lugar lugar )
    {
        lugares.add( lugar );
    }

    /**
     * Retorna la ciudad que se encuentra a la derecha.<br>
     * @return Puede ser null.
     */
    public Ciudad darDerecho( )
    {
        return ciudadDerecha;
    }

    /**
     * Cambia el hijo derecho <br>
     * <b>post:</b> La ciudad derecha cambia por la pasada por parámetro<br>
     * @param ciudad Nueva ciudad, puede ser null
     */
    public void cambiarDerecho( Ciudad ciudad )
    {
        ciudadDerecha = ciudad;
    }

    /**
     * Retorna la ciudad que se encuentra en el centro.<br>
     * @return Puede ser null.
     */
    public Ciudad darCentral( )
    {
        return ciudadCentral;
    }

    /**
     * Cambia el hijo central <br>
     * <b>post:</b> La ciudad central cambia por la pasada por parámetro<br>
     * @param ciudad Nueva ciudad, puede ser null.
     */
    public void cambiarCentral( Ciudad ciudad )
    {
        ciudadCentral = ciudad;
    }

    /**
     * Retorna la ciudad que se encuentra en el lado izquierdo.<br>
     * @return Puede ser null.
     */
    public Ciudad darIzquierdo( )
    {
        return ciudadIzquierda;
    }

    /**
     * Cambia el hijo izquierdo <br>
     * <b>post:</b> La ciudad izquierda cambia por la pasada por parámetro<br>
     * @param ciudad Nueva ciudad, puede ser null
     */
    public void cambiarIzquierdo( Ciudad ciudad )
    {
        ciudadIzquierda = ciudad;
    }

    /**
     * Retorna la ciudad dado el nombre.<br>
     * @param nombreCiudad Nombre de la ciudad a buscar. Diferente de null.
     * @return La ciudad identificada con el nombre. Puede ser null.
     */
    public Ciudad darCiudad( String nombreCiudad )
    {
        if( nombre.equalsIgnoreCase( nombreCiudad ) )
        {
            return this;
        }
        else if( ciudadDerecha != null && ciudadDerecha.darCiudad( nombreCiudad ) != null )
            return ciudadDerecha.darCiudad( nombreCiudad );
        else if( ciudadCentral != null && ciudadCentral.darCiudad( nombreCiudad ) != null )
            return ciudadCentral.darCiudad( nombreCiudad );
        else if( ciudadIzquierda != null && ciudadIzquierda.darCiudad( nombreCiudad ) != null )
            return ciudadIzquierda.darCiudad( nombreCiudad );
        return null;
    }

    /**
     * Retorna el nombre de la ciudad.<br>
     * @return Nombre de la ciudad. Diferente de null.
     */
    public String darNombreCiudad( )
    {
        return nombre;
    }

    /**
     * Retorna una lista con los nombres de los lugares donde hay pistas.<br>
     * @return Lista de Strings. Diferente de null.
     */
    public List darNombresLugares( )
    {
        ArrayList nombres = new ArrayList( );
        for( int i = 0; i < lugares.size( ); i++ )
        {
            if( lugares.get( i ) != null )
                nombres.add( ( ( Lugar )lugares.get( i ) ).darNombre( ) );
        }
        return nombres;
    }

    /**
     * Retorna un lugar donde hay una pista dado su nombre.<br>
     * @param nombreLugar Nombre del lugar a retornar. Diferente de null.
     * @return Lugar identificada con el nombre. Puede ser null.
     */
    public Lugar darLugar( String nombreLugar )
    {
        Lugar lugar = null;
        boolean encontro = false;
        for( int i = 0; i < lugares.size( ) && !encontro; i++ )
        {
            lugar = ( Lugar )lugares.get( i );
            if( lugar != null && lugar.darNombre( ).equals( nombreLugar ) )
            {
                encontro = true;
            }
        }
        return lugar;
    }

    /**
     * Retorna los nombres de los hijos de la ciudad.<br>
     * @return Lista de Strings. Puede ser vacía pero no null.
     */
    public List darNombresPosiblesDestinos( )
    {
        List destinos = new ArrayList( );
        if( ciudadDerecha != null )
            destinos.add( ciudadDerecha.darNombreCiudad( ) );
        if( ciudadCentral != null )
            destinos.add( ciudadCentral.darNombreCiudad( ) );
        if( ciudadIzquierda != null )
            destinos.add( ciudadIzquierda.darNombreCiudad( ) );
        return destinos;
    }

    /**
     * Retorna el tiempo de viaje hacia esta ciudad.<br>
     * @return Entero mayor o igual a cero.
     */
    public int darTiempoViaje( )
    {
        return tiempoViaje;
    }

    /**
     * Informa si el sospechoso se encuentra en la ciudad.<br>
     * @return True en caso que el sospechoso se encuentre en la ciudad, false de lo contrario.
     */
    public boolean estaSospechoso( )
    {
        return estaSospechoso;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <b>post: </b> Se retorno el vector para recorrer los elementos del árbol en preorden.<br>
     * @param resultado Lista donde se guardaran los elementos en inorden. Diferente de null
     */
    public void darInorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( ciudadIzquierda != null )
        {
            ciudadIzquierda.darInorden( resultado );
        }

        // Agrega el elemento que se encuentra en el nodo
        if( nombre != null )
        {
            resultado.add( nombre );
        }

        // Agrega los elementos del subárbol central
        if( ciudadCentral != null )
        {
            ciudadCentral.darInorden( resultado );
        }

        // Agrega los elementos del subárbol derecho
        if( ciudadDerecha != null )
        {
            ciudadDerecha.darInorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en postorden. <br>
     * <b>post: </b> Se retorno el vector para recorrer los elementos del árbol en postorden.<br>
     * @param resultado Resultado del recorrido. Diferente de null<br>
     */
    public void darPostorden( List resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( ciudadIzquierda != null )
        {
            ciudadIzquierda.darPostorden( resultado );
        }
        // Agrega los elementos del subárbol central
        if( ciudadCentral != null )
        {
            ciudadCentral.darPostorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( ciudadDerecha != null )
        {
            ciudadDerecha.darPostorden( resultado );
        }

        // Agrega el elemento que se encuentra en el nodo
        if( nombre != null )
        {
            resultado.add( nombre );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en preorden.<br>
     * <b>post: </b> Se retorno el vector para recorrer los elementos del árbol en preorden.<br>
     * @param resultado Resultado del recorrido. Diferente de null
     */
    public void darPreorden( List resultado )
    {
        // Agrega el elemento que se encuentra en el nodo
        if( nombre != null )
        {
            resultado.add( nombre );
        }

        // Agrega los elementos del subárbol izquierdo
        if( ciudadDerecha != null )
        {
            ciudadDerecha.darPreorden( resultado );
        }
        // Agrega los elementos del subárbol central
        if( ciudadCentral != null )
        {
            ciudadCentral.darPreorden( resultado );
        }
        // Agrega los elementos del subárbol izquierdo
        if( ciudadIzquierda != null )
        {
            ciudadIzquierda.darPreorden( resultado );
        }
    }

    /**
     * Devuelve la altura del árbol cuya raíz es el nodo actual. <br>
     * <b>post: </b> Se retornó la altura del árbol.<br>
     * @return Altura del árbol cuya raíz es el nodo actual
     */
    public int darAltura( )
    {
        int altura1 = 0;
        int altura2 = 0;
        int altura3 = 0;
        int respuesta = 0;
        if( ciudadDerecha != null )
            altura1 = ciudadDerecha.darAltura( );
        if( ciudadCentral != null )
            altura2 = ciudadCentral.darAltura( );
        if( ciudadIzquierda != null )
            altura3 = ciudadIzquierda.darAltura( );
        if( altura1 >= altura2 && altura1 >= altura3 )
            respuesta = altura1 + 1;
        else if( altura2 >= altura1 && altura2 >= altura3 )
            respuesta = altura2 + 1;
        else if( altura3 >= altura1 && altura3 >= altura2 )
            respuesta = altura3 + 1;
        return respuesta;
    }

    /**
     * Devuelve el peso del árbol cuya raíz es el nodo actual.<br>
     * <b>post: </b> Se retornó el peso del árbol.<br>
     * @return Peso del árbol cuya raíz es el nodo actual
     */
    public int darPeso( )
    {
        int peso1 = 0;
        int peso2 = 0;
        int peso3 = 0;
        if( ciudadDerecha != null )
            peso1 = ciudadDerecha.darPeso( );
        if( ciudadCentral != null )
            peso2 = ciudadCentral.darPeso( );
        if( ciudadIzquierda != null )
            peso3 = ciudadIzquierda.darPeso( );
        return peso1 + peso2 + peso3 + 1;
    }

    /**
     * Retorna la descripcion de la ciudad.<br>
     * @return Diferente de null.
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Informa si la ciudad es una hoja.<br>
     * @return True en caso que sea una hoja, false de lo contrario.
     */
    public boolean esHoja( )
    {
        return ciudadCentral == null && ciudadDerecha == null && ciudadIzquierda == null;
    }

    /**
     * Retorna una lista con los hijos de esta ciudad.<br>
     * @return Lista con objetos de tipo Ciudad. La lista puede ser vacía, pero no null.
     */
    public List darCiudadesHijas( )
    {
        List hijos = new ArrayList( );
        if( ciudadDerecha != null )
            hijos.add( ciudadDerecha );
        if( ciudadCentral != null )
            hijos.add( ciudadCentral );
        if( ciudadIzquierda != null )
            hijos.add( ciudadIzquierda );
        return hijos;
    }

    /**
     * Retorna la ciudad identificada con el nombre.<br>
     * @param nombreCiudad Nombre de la ciudad a retornar. Esta ciudad deberia ser una de las ciudades hijas<br>
     * @return La ciudad identificada con el nombre. Si no existe ser retorna null.
     */
    public Ciudad viajar( String nombreCiudad )
    {
        Ciudad retorno = null;
        if( ciudadIzquierda != null && ciudadIzquierda.darNombreCiudad( ).equalsIgnoreCase( nombreCiudad ) )
            retorno = ciudadIzquierda;
        if( ciudadCentral != null && ciudadCentral.darNombreCiudad( ).equalsIgnoreCase( nombreCiudad ) )
            retorno = ciudadCentral;
        if( ciudadDerecha != null && ciudadDerecha.darNombreCiudad( ).equalsIgnoreCase( nombreCiudad ) )
            retorno = ciudadDerecha;
        return retorno;
    }

    /**
     * Agrega una nueva ciudad a la otra ciudad identificada con el nombre.
     * @param ciudad Nueva ciudad a agregar. Diferente de null.
     * @param nombrePadre Nombre de la ciudad padre de la ciudad a agregar.
     * @throws CiudadNoAgredagaException Excepción lanzada cuando la ciudad donde se quiere agregar la nueva ciudad no existe
     * @throws CiudadNoAgredagaException Excepción lanzada cuando la ciudad donde se quiere agregar la nueva ciudad ya tiene a sus hijos llenos
     */
    public void agregarCiudad( Ciudad ciudad, String nombrePadre ) throws CiudadNoAgredagaException
    {
        if( nombre.equalsIgnoreCase( nombrePadre ) )
        {
            boolean agregado = false;
            if( ciudadDerecha == null )
            {
                cambiarDerecho( ciudad );
                agregado = true;
            }
            else if( ciudadCentral == null )
            {
                cambiarCentral( ciudad );
                agregado = true;
            }
            else if( ciudadIzquierda == null )
            {
                cambiarIzquierdo( ciudad );
                agregado = true;
            }
            if( !agregado )
                throw new CiudadNoAgredagaException( "La ciudad no fue agregada ya que la ciudad padre tiene sus hijos completos" );
        }
        else
        {
            if( ciudadDerecha != null )
                ciudadDerecha.agregarCiudad( ciudad, nombrePadre );
            if( ciudadCentral != null )
                ciudadCentral.agregarCiudad( ciudad, nombrePadre );
            if( ciudadIzquierda != null )
                ciudadIzquierda.agregarCiudad( ciudad, nombrePadre );
        }
    }

    /**
     * Busca si existe entre sus hijos una ciudad con el nombre dado.
     * @param nombrePadre Nombre de la ciudad que se quiere buscar. Diferente de null.
     * @return Retorna true en caso que alguna de las ciudades hijas tenga el nombre, false de lo contrario.
     */
    public boolean existeCiudad( String nombrePadre )
    {
        boolean respuesta = false;
        if( nombre.equalsIgnoreCase( nombrePadre ) )
            respuesta = true;
        if( ciudadCentral != null )
            respuesta = respuesta || ciudadCentral.existeCiudad( nombrePadre );
        if( ciudadDerecha != null )
            respuesta = respuesta || ciudadDerecha.existeCiudad( nombrePadre );
        if( ciudadIzquierda != null )
            respuesta = respuesta || ciudadIzquierda.existeCiudad( nombrePadre );
        return respuesta;
    }

    /**
     * Retorna una lista de Strings con los nombres de las ciudades que forman el camino del nodo actual a la hoja con el sospechoso.<br>
     * @return Lista de Strings con los nombres que puede ser vacía pero no null.
     */
    public ArrayList darCaminoRespuesta( )
    {
        ArrayList camino = new ArrayList( );

        if( ciudadDerecha != null && ciudadDerecha.darCaminoRespuesta( ).size( ) != 0 )
        {
            camino.add( nombre );
            camino.addAll( ciudadDerecha.darCaminoRespuesta( ) );
        }
        if( ciudadCentral != null && ciudadCentral.darCaminoRespuesta( ).size( ) != 0 )
        {
            camino.add( nombre );
            camino.addAll( ciudadCentral.darCaminoRespuesta( ) );
        }
        if( ciudadIzquierda != null && ciudadIzquierda.darCaminoRespuesta( ).size( ) != 0 )
        {
            camino.add( nombre );
            camino.addAll( ciudadIzquierda.darCaminoRespuesta( ) );
        }

        if( esHoja( ) && estaSospechoso )
            camino.add( nombre );

        return camino;
    }

    /**
     * Cuenta el número de veces que aparece una ciudad con un nombre que se recibe como parámetro<br>
     * @param nombreP nombre de la ciudad que se está buscando. Diferente de null<br>
     * @return número de ciudades con un nombre dado
     */
    public int contarOcurrencias( String nombreP )
    {
        int cuantas = 0;
        if( nombre.equalsIgnoreCase( nombreP ) )
            cuantas++;
        cuantas += ( ciudadDerecha == null ) ? 0 : ciudadDerecha.contarOcurrencias( nombreP );
        cuantas += ( ciudadCentral == null ) ? 0 : ciudadCentral.contarOcurrencias( nombreP );
        cuantas += ( ciudadIzquierda == null ) ? 0 : ciudadIzquierda.contarOcurrencias( nombreP );
        return cuantas;
    }
}
