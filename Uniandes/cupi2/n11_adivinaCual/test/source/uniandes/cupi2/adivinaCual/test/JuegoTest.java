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

package uniandes.cupi2.adivinaCual.test;

import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.adivinaCual.mundo.AnimalExisteException;
import uniandes.cupi2.adivinaCual.mundo.ArchivoNoCargadoException;
import uniandes.cupi2.adivinaCual.mundo.Juego;
import uniandes.cupi2.adivinaCual.mundo.Pregunta;
import uniandes.cupi2.adivinaCual.mundo.PreguntaNoAgregadaException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Juego estén correctamente implementados
 */
public class JuegoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Juego juego;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Juego con un árbol de prueba.
     */
    private void setupEscenario1( )
    {
        try
        {
            juego = new Juego( "./test/data/animales.properties", true );
        }
        catch( ArchivoNoCargadoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Construye un nuevo Juego con un árbol vacío de prueba.
     */
    private void setupEscenario2( )
    {
        try
        {
            juego = new Juego( "./test/data/animales2.properties", false );
        }
        catch( ArchivoNoCargadoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Construye un nuevo Juego con un árbol con un solo elemento de prueba.
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );
        juego.agregarPrimerAnimal( "Primer Animal" );
    }

    /**
     * Este método se encarga de verificar los métodos obtenerPeso<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un árbol lleno<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del árbol de prueba. Se espera que el peso sea 59<br>
     */
    public void testDarPeso( )
    {
        setupEscenario1( );
        assertEquals( "El peso del árbol de pruebas debería ser 59", 59, juego.darPeso( ) );
    }

    /**
     * Este método se encarga de verificar los métodos obtenerAltura<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un árbol lleno<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del árbol de prueba. Se espera que el peso sea 8<br>
     */
    public void testDarAltura( )
    {
        setupEscenario1( );
        assertEquals( "La altura del árbol debería ser 8", 8, juego.darAltura( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerAnimales<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerAnimales<br>
     * Pregunta: obtenerNombreAnimal<br>
     * Pregunta: obtenerRuta<br>
     * Pregunta: obtenerNegativa<br>
     * Pregunta: obtenerPositiva<br>
     * Pregunta: obtenerPregunta<br>
     * <b> Objetivo: </b> Probar que se tenga una lista de pregunta que representa las hojas de un árbol lleno<br>
     * <b> Resultados esperados: </b> <br>
     * Se obtiene una lista valida de pregunta y tiene atributos validos
     */
    public void testDarAnimales( )
    {
        setupEscenario1( );
        List animales = juego.darAnimales( );
        assertNotNull( "La lista de los animales no debería ser null", animales );
        for( int i = 0; i < animales.size( ); i++ )
        {
            Pregunta animal = ( Pregunta )animales.get( i );
            assertNotNull( "Ninguno de los elementos de la lista de animales debería ser null", animal );
            assertNotNull( "El nombre del animal no debería ser null", animal.darNombreAnimal( ) );
            assertNotNull( "La ruta de la imagen del animal no debería ser null", animal.darRuta( ) );
            assertNull( "Una hoja no debería tener más hijos", animal.darRespuestaNegativa( ) );
            assertNull( "Una hoja no debería tener más hijos", animal.darRespuestaPositiva( ) );
            assertNull( "Una hoja no debería tener una pregunta", animal.darDescripcion( ) );
            assertNull( "La pregunta debería ser null", animal.darDescripcion( ) );
            assertNull( "Una hoja no debería tener respuesta negativa", animal.darRespuestaNegativa( ) );
            assertNull( "Una hoja no debería tener respuesta positiva", animal.darRespuestaPositiva( ) );
            assertNotNull( "El nombre el animal no debería ser null", animal.darNombreAnimal( ) );
            assertNotNull( "La ruta de la imagen no debería ser null", animal.darRuta( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método reiniciar<br>
     * <b> Métodos a probar: </b> <br>
     * reiniciar<br>
     * obtenerPreguntaActual<br>
     * seleccionarRespuestaNegativa<br>
     * <b> Objetivo: </b> Probar que se reinicie el juego de manera correcta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar por primera vez el juego la pregunta actual debe ser null.<br>
     * 2. Al iniciar la pregunta actual es la misma raíz. <br>
     * 3. Al avanzar por el árbol, la pregunta actual cambia. Al reiniciar, la pregunta actual vuelve a ser la raíz.<br>
     */
    public void testReiniciar( )
    {
        setupEscenario1( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.reiniciar( );
        raiz = juego.darPreguntaActual( );
        assertNotNull( "La pregunta raíz no debería ser null.", raiz );
        juego.seleccionarRespuestaNegativa( );
        assertNotSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
        juego.reiniciar( );
        assertEquals( "La pregunta actual y la raíz deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método seleccionarRespuestaPositiva<br>
     * <b> Métodos a probar: </b> <br>
     * seleccionarRespuestaPositiva<br>
     * obtenerPreguntaActual<br>
     * <b> Objetivo: </b> Probar que se cambie la pregunta actual por la respuesta positiva<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar la pregunta actual es la misma raíz. Al avanzar por el árbol, la pregunta actual cambia.
     */
    public void testRespuestaPositiva( )
    {
        setupEscenario1( );
        juego.reiniciar( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.seleccionarRespuestaPositiva( );
        assertNotSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método seleccionarRespuestaNegativa<br>
     * <b> Métodos a probar: </b> <br>
     * seleccionarRespuestaNegativa<br>
     * obtenerPreguntaActual<br>
     * <b> Objetivo: </b> Probar que se cambie la pregunta actual por la respuesta negativa<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar la pregunta actual es la misma raíz. Al avanzar por el árbol, la pregunta actual cambia.
     */
    public void testRespuestaNegativa( )
    {
        setupEscenario1( );
        juego.reiniciar( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.seleccionarRespuestaNegativa( );
        assertNotSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método armarArbol<br>
     * <b> Métodos a probar: </b> <br>
     * armarArbol<br>
     * <b> Objetivo: </b> Probar que se obtenga una raíz valida al armar el árbol<br>
     * <b> Resultados esperados: </b> <br>
     * El armador retorna una raíz valida
     */
    public void testArmarArbol( )
    {
        setupEscenario1( );
        assertNotNull( "La raíz que retorna el método que arma el árbol no puede ser null", juego.armarArbol( ) );
    }

    /**
     * Este método se encarga de verificar el método agregarPregunta<br>
     * <b> Métodos a probar: </b> <br>
     * agregarPregunta<br>
     * darAnimales<br>
     * darAltura<br>
     * darPeso<br>
     * <b> Objetivo: </b> Probar que se agregue correctamente una nueva pregunta al árbol lleno<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene informacion antes de agregar la nueva pregunta, luego se agrega y se compara la nueva informacion<br>
     * 2. Al tratar de ingresar un animal que ya existe se informa del error<br>
     */
    public void testAgregarPregunta( )
    {
        setupEscenario1( );
        try
        {
            int tamAnimales = juego.darAnimales( ).size( );
            int altura = juego.darAltura( );
            int peso = juego.darPeso( );

            for( int i = 0; i < altura; i++ )
            {
                juego.seleccionarRespuestaNegativa( );
            }

            juego.agregarPregunta( "Pregunta de prueba", "animal de prueba" );
            assertEquals( "No agregó el animal a la lista", tamAnimales + 1, juego.darAnimales( ).size( ) );
            assertTrue( "El peso del árbol no cambió", peso != juego.darPeso( ) );
        }
        catch( AnimalExisteException e )
        {
            fail( e.getMessage( ) );
        }
        catch( PreguntaNoAgregadaException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            juego.agregarPregunta( "Pregunta de prueba", "animal de prueba" );
            fail( "No debería agregar un animal existente" );
        }
        catch( AnimalExisteException e )
        {
            // Prueba Correcta
        }
        catch( PreguntaNoAgregadaException e )
        {
            // Prueba Correcta
        }

    }

    /**
     * Este método se encarga de verificar el método agregarPrimerAnimal<br>
     * <b> Métodos a probar: </b> <br>
     * agregarPrimerAnimal<br>
     * darRaiz<br>
     * darPreguntaActual<br>
     * Pregunta: darRespuestaPositiva<br>
     * Pregunta: darRespuestaNegativa<br>
     * <b> Objetivo: </b> Probar que se agregue correctamente una raíz al árbol vacío<br>
     * <b> Resultados esperados: </b> <br>
     * 1. A partir de un árbol vacío, se crea una raíz sin hijos.<br>
     */
    public void testAgregarPrimerAnimal( )
    {
        setupEscenario2( );
        juego.agregarPrimerAnimal( "El nuevo animal" );
        assertNotNull( "La raíz debería ser el animal agregado", juego.darRaiz( ) );
        assertNotNull( "La pregunta actual no debería ser null", juego.darPreguntaActual( ) );
        assertNull( "La respuesta positiva de la raíz debería ser null", juego.darRaiz( ).darRespuestaPositiva( ) );
        assertNull( "La respuesta negativa de la raíz debería ser null", juego.darRaiz( ).darRespuestaNegativa( ) );
    }

    /**
     * Este método se encarga de verificar los métodos obtenerPeso<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un árbol vacío<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del árbol para cuando no hay elementos. Se espera que el peso sea 0<br>
     */
    public void testDarPesoArbolVacio( )
    {
        setupEscenario2( );
        assertEquals( "El peso del árbol de pruebas debería ser 0", 0, juego.darPeso( ) );
    }

    /**
     * Este método se encarga de verificar los métodos obtenerAltura<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un árbol vacío<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del árbol para cuando no hay elementos. Se espera que la altura sea 0<br>
     */
    public void testDarAlturaArbolVacio( )
    {
        setupEscenario2( );
        assertEquals( "La altura del árbol debería ser 0", 0, juego.darAltura( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerAnimales<br>
     * <b> Métodos a probar: </b> <br>
     * darAnimales<br>
     * <b> Objetivo: </b> Probar que se tenga una lista de pregunta que representa las hojas de un árbol vacío<br>
     * <b> Resultados esperados: </b> <br>
     * Se obtiene una lista vacía.<br>
     */
    public void testdarAnimalesArbolVacio( )
    {
        setupEscenario2( );
        List animales = juego.darAnimales( );
        assertNotNull( "La lista de los animales no debería ser null", animales );
        assertEquals( "La lista de animales debería ser vacía", 0, animales.size( ) );
    }

    /**
     * Este método se encarga de verificar el método agregarPregunta<br>
     * <b> Métodos a probar: </b> <br>
     * agregarPregunta<br>
     * <b> Objetivo: </b> Probar que se agregue correctamente una nueva pregunta al árbol vacío<br>
     * <b> Resultados esperados: </b> <br>
     * 1. No agregue una pregunta a un árbol vacío<br>
     */
    public void testAgregarPreguntaArbolVacio( )
    {
        setupEscenario2( );
        try
        {
            juego.agregarPregunta( "Pregunta de prueba", "animal de prueba" );
            fail( "No debería agregar una pregunta a un árbol vació" );
        }
        catch( AnimalExisteException e )
        {
            fail( e.getMessage( ) );
        }
        catch( PreguntaNoAgregadaException e )
        {
            // Prueba Correcta
        }
    }

    /**
     * Este método se encarga de verificar los métodos obtenerPeso<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un árbol con una sola hoja<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del árbol para un elemento. Se espera que el peso sea 0<br>
     */
    public void testDarPesoHoja( )
    {
        setupEscenario3( );
        assertEquals( "El peso del árbol de pruebas debería ser 1", 1, juego.darPeso( ) );
    }

    /**
     * Este método se encarga de verificar los métodos obtenerAltura<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un árbol con una sola hoja<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del árbol para un elemento. Se espera que la altura sea 0<br>
     */
    public void testDarAlturaHoja( )
    {
        setupEscenario3( );
        assertEquals( "La altura del árbol debería ser 1", 1, juego.darAltura( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerAnimales<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerAnimales<br>
     * Pregunta: obtenerNombreAnimal<br>
     * Pregunta: obtenerRuta<br>
     * Pregunta: obtenerNegativa<br>
     * Pregunta: obtenerPositiva<br>
     * Pregunta: obtenerPregunta<br>
     * <b> Objetivo: </b> Probar que se tenga una lista de pregunta que representa las hojas de un árbol con un solo elemento<br>
     * <b> Resultados esperados: </b> <br>
     * Se obtiene una lista valida de pregunta y tiene sus atributos validos<br>
     */
    public void testDarAnimalesHoja( )
    {
        setupEscenario3( );
        List animales = juego.darAnimales( );
        assertNotNull( "La lista de los animales no debería ser null", animales );
        for( int i = 0; i < animales.size( ); i++ )
        {
            Pregunta animal = ( Pregunta )animales.get( i );
            assertNotNull( "Ninguno de los elementos de la lista de animales debería ser null", animal );
            assertNotNull( "El nombre del animal no debería ser null", animal.darNombreAnimal( ) );
            assertNotNull( "La ruta de la imagen del animal no debería ser null", animal.darRuta( ) );
            assertNull( "Una hoja no debería tener más hijos", animal.darRespuestaNegativa( ) );
            assertNull( "Una hoja no debería tener más hijos", animal.darRespuestaPositiva( ) );
            assertNull( "Una hoja no debería tener una pregunta", animal.darDescripcion( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método reiniciar<br>
     * <b> Métodos a probar: </b> <br>
     * reiniciar<br>
     * darPreguntaActual<br>
     * seleccionarRespuestaNegativa<br>
     * <b> Objetivo: </b> Probar que se reinicie el juego de manera correcta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar por primera vez el juego la pregunta actual debe ser null. 2. Al iniciar la pregunta actual es la misma raíz. 3. Al avanzar por el árbol, la pregunta
     * actual no cambia. Al reiniciar, la pregunta actual vuelve a ser la raíz.
     */
    public void testReiniciarHoja( )
    {
        setupEscenario3( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.reiniciar( );
        raiz = juego.darPreguntaActual( );
        assertNotNull( "La pregunta raíz no debería ser null.", raiz );
        juego.seleccionarRespuestaNegativa( );
        assertSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
        juego.reiniciar( );
        assertEquals( "La pregunta actual y la raíz deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método seleccionarRespuestaPositiva<br>
     * <b> Métodos a probar: </b> <br>
     * seleccionarRespuestaPositiva<br>
     * obtenerPreguntaActual<br>
     * <b> Objetivo: </b> Probar que se cambie la pregunta actual de manera correcta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar la pregunta actual es la misma raíz. Al avanzar por el árbol, la pregunta no actual cambia.<br>
     */
    public void testRespuestaPositivaHoja( )
    {
        setupEscenario3( );
        juego.reiniciar( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.seleccionarRespuestaPositiva( );
        assertSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método seleccionarRespuestaNegativa<br>
     * <b> Métodos a probar: </b> <br>
     * seleccionarRespuestaNegativa<br>
     * obtenerPreguntaActual<br>
     * <b> Objetivo: </b> Probar que se cambie la pregunta actual de manera correcta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar la pregunta actual es la misma raíz. Al avanzar por el árbol, la pregunta no actual cambia.<br>
     */
    public void testRespuestaNegativaHoja( )
    {
        setupEscenario3( );
        juego.reiniciar( );
        Pregunta raiz = juego.darPreguntaActual( );
        juego.seleccionarRespuestaNegativa( );
        assertSame( "La pregunta actual y la raíz no deberían ser iguales", raiz, juego.darPreguntaActual( ) );
    }

    /**
     * Este método se encarga de verificar el método agregarPregunta<br>
     * <b> Métodos a probar: </b> <br>
     * agregarPregunta<br>
     * darAnimales<br>
     * darAltura<br>
     * darPeso<br>
     * <b> Objetivo: </b> Probar que se agregue correctamente una nueva pregunta al árbol de un solo elemento<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene informacion antes de agregar la nueva pregunta, luego se agrega y se compara la nueva informacion<br>
     * 2. Al tratar de ingresar un animal que ya existe se informa del error<br>
     */
    public void testAgregarPreguntaHoja( )
    {
        setupEscenario3( );
        try
        {
            int tamAnimales = juego.darAnimales( ).size( );
            int altura = juego.darAltura( );
            int peso = juego.darPeso( );

            for( int i = 0; i < altura; i++ )
            {
                juego.seleccionarRespuestaNegativa( );
            }

            juego.agregarPregunta( "Pregunta de prueba", "animal de prueba" );
            assertEquals( "No agrego el animal a la lista", tamAnimales + 1, juego.darAnimales( ).size( ) );
            assertTrue( "El peso del árbol no cambió", peso != juego.darPeso( ) );
        }
        catch( AnimalExisteException e )
        {
            fail( e.getMessage( ) );
        }
        catch( PreguntaNoAgregadaException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            juego.agregarPregunta( "Pregunta de prueba", "animal de prueba" );
            fail( "No debería agregar un animal existente" );
        }
        catch( AnimalExisteException e )
        {
            // Prueba Correcta
        }
        catch( PreguntaNoAgregadaException e )
        {
            // Prueba Correcta
        }
    }
}