/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - 27-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa el juego como tal <b>inv: </b> <br>
 * No debería existir animales repetidos<br>
 */
public class AdivinaCual
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol. Es la primera pregunta.
     */
    private Nodo raiz;

    /**
     * Pregunta que esta en juego actualmente
     */
    private Nodo preguntaActual;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista con los animales <br>
     * <b>post: </b> Se retornó la lista de los animales<br>
     * @return Diferente de null
     */
    public List darAnimales( )
    {
        List hojas = new ArrayList( );
        if( raiz != null )
        {
            raiz.darHojas( hojas );
        }
        return hojas;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retornó la lista con las preguntas ordenadas en inorden<br>
     * @return Lista de Strings con las preguntas en inorden. Diferente de null.
     */
    public List darInorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darInorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en preorden. <b>post: </b> Se retornó la lista con las preguntas ordenadas en preorden<br>
     * @return Lista de Strings con las preguntas en preorden. Diferente de null.
     */
    public List darPreorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darPreorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en postorden. <b>post: </b> Se retornó la lista con las preguntas ordenadas en postorden<br>
     * @return Lista de Strings con las preguntas en postorden. Diferente de null
     */
    public List darPostorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darPostorden( resultado );
        }
        return resultado;
    }

    /**
     * Retorna la altura del árbol. <br>
     * <b>post: </b> Se retornó la altura del árbol<br>
     * @return La altura del árbol. Entero mayor o igual a cero.
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso del árbol (número de elementos que contiene). <b>post: </b> Se retornó la altura del árbol<br>
     * @return El peso del árbol. Entero mayor o igual a cero.
     */
    public int darPeso( )
    {
        return raiz == null ? 0 : raiz.darPeso( );
    }

    /**
     * Reinicia el juego <b>post: </b> El valor de la pregunta actual ahora es la raiz.<br>
     */
    public void reiniciar( )
    {
        preguntaActual = raiz;
        verificarInvariante( );
    }

    /**
     * Retorna la pregunta que esta actualmente en juego <b>post: </b> Se retornó la pregunta actual<br>
     * @return Diferente de null
     */
    public Nodo darPreguntaActual( )
    {
        return preguntaActual;
    }

    /**
     * Si la respuesta a la pregunta actual fue positiva, obtiene la siguiente pregunta por la rama positiva.En caso que la la siguiente pregunta por la rama positiva sea null
     * no se cambia <br>
     * post:<br>
     * El valor de preguntaActual cambia por la siguiente pregunta por la rama positiva
     */
    public void seleccionarRespuestaPositiva( )
    {
        if( preguntaActual != null && preguntaActual.darNodoPositivo( ) != null )
            preguntaActual = preguntaActual.darNodoPositivo( );
        verificarInvariante( );
    }

    /**
     * Si la respuesta a la pregunta actual fue positiva, obtiene la siguiente pregunta por la rama negativa. En caso que la la siguiente pregunta por la rama negativa sea
     * null no se cambia<br>
     * post:<br>
     * El valor de preguntaActual cambia por la siguiente pregunta por la rama negativa
     */
    public void seleccionarRespuestaNegativa( )
    {
        if( preguntaActual != null && preguntaActual.darNodoNegativo( ) != null )
            preguntaActual = preguntaActual.darNodoNegativo( );
        verificarInvariante( );
    }

    /**
     * Agrega una nueva pregunta y un nuevo animal al árbol, también actualiza la lista de animales.
     * @param descripcion Descripcion de la nueva pregunta. Diferente de null.
     * @param animal Nombre del nuevo animal. Diferente de null
     * @param ruta Ruta del animal seleccionado. Diferente de null
     * @throws AnimalExisteException Excepción que se lanza cuando se desea agregar un animal que ya existe
     * @throws PreguntaNoAgregadaException Excepción que se lanza cuando se desea agregar una pregunta a un árbol vacío.
     */
    public void agregarPregunta( String descripcion, String animal, String ruta ) throws AnimalExisteException, PreguntaNoAgregadaException
    {
        if( buscarAnimal( animal ) )
        {
            throw new AnimalExisteException( "El animal que quiere agregar ya se encuentra en el sistema" );
        }
        if( preguntaActual != null )
        {
            preguntaActual.agregarPregunta( descripcion, animal, ruta );
        }
        else
            throw new PreguntaNoAgregadaException( "No se puede agregar una pregunta a un árbol vacío" );
    }

    /**
     * Busca si existe un animal especifico en el mundo<br>
     * @param animal Nombre del animal que se quiere buscar. Diferente de null.
     * @return True en caso que el animal exista, false de lo contrario.
     */
    private boolean buscarAnimal( String animal )
    {
        return raiz == null ? false : raiz.buscarAnimal( animal );
    }

    /**
     * Retorna la raíz del árbol<br>
     * @return la raíz del árbol
     */
    public Nodo darRaiz( )
    {
        return raiz;
    }

    /**
     * Crea un árbol con un solo animal
     * @param primerAnimal el nombre del primer animal
     * @param rutaAnimal Ruta de la imagen del primer animal. Diferente de null
     */
    public void agregarPrimerAnimal( String primerAnimal, String rutaAnimal )
    {
        raiz = new Nodo( primerAnimal, rutaAnimal );
        preguntaActual = raiz;
    }

    /**
     * Retorna la primera pregunta del juego
     * @return Primera pregunta del juego. Puede ser null.
     */
    public Nodo darPrimeraPregunta( )
    {
        return raiz;
    }

    /**
     * Guarda en un archivo identificado con el nombre, el estado del mundo. <br>
     * <b>post:</b> El estado del mundo quedará guardado en un archivo que estará identificado con rutaNombre<br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null.
     * @throws PersistenciaException Lanzada cuando no se encuentra el archivo o no se puede guardar en él.
     */
    public void guardarAdivinaCual( String rutaNombre ) throws PersistenciaException
    {
        try
        {
            FileOutputStream archivo = new FileOutputStream( rutaNombre );
            ObjectOutputStream objetoSaliente = new ObjectOutputStream( archivo );
            objetoSaliente.writeObject( raiz );
            objetoSaliente.close( );
            archivo.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }

    }

    /**
     * Cargar de un archivo identificado con el nombre el estado del mundo que se encuentra guardado. <br>
     * <b>post:</b> El archivo que está identificado con rutaNombre queda cargado en el sistema.<br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null
     * @throws PersistenciaException Lanzada cuando ocurre algún problema con el archivo o con su formato
     */
    public void cargarAdivinaCual( String rutaNombre ) throws PersistenciaException
    {

        try
        {
            File archivo = new File( rutaNombre );
            if( archivo.exists( ) )
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                raiz = ( Nodo )ois.readObject( );
                preguntaActual = raiz;
                ois.close( );
            }
            else
            {
                raiz = null;
            }

        }
        catch( FileNotFoundException e )
        {
            throw new PersistenciaException( "No se encontró archivo de persistencia. \nAl cerrar la aplicación se generará por primera vez el archivo de persistencia " );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "No fue posible leer el archivo de persistencia" + e.getMessage( ) );
        }
        catch( ClassNotFoundException e )
        {
            throw new PersistenciaException( "Problemas al cargar el archivo de persistencia" + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Método que verifica la invariante<br>
     * <b>inv: </b> <br>
     * No debería existir animales repetidos<br>
     */
    private void verificarInvariante( )
    {
        List animales = darAnimales( );
        boolean encontrado = false;
        for( int i = 0; i < animales.size( ) && !encontrado; i++ )
        {
            Nodo animal = ( Nodo )animales.get( i );
            for( int j = 0; j < animales.size( ) && !encontrado; j++ )
            {
                Nodo animal2 = ( Nodo )animales.get( j );
                if( animal.darNombreAnimal( ).equalsIgnoreCase( animal2.darNombreAnimal( ) ) && i != j )
                {
                    encontrado = true;
                }
            }
        }
        assert !encontrado : "No debería haber animales repetidos";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}