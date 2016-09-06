package uniandes.cupi2.collections.arbo1_2_3.test;

import junit.framework.TestCase;
import uniandes.cupi2.collections.arbol1_2_3.Arbol1_2_3;
import uniandes.cupi2.collections.arbol1_2_3.ElementoExisteException;
import uniandes.cupi2.collections.arbol1_2_3.ElementoNoExisteException;
import uniandes.cupi2.collections.arbol1_2_3.Nodo1_2_3;
import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Esta es la clase usada para verificar los métodos de la clase árbol 2_3
 */
public class Arbol1_2_3Test extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private Arbol1_2_3 arbol;

    /**
     * El número de elementos a manejar en c3ada escenario
     */
    private int numeroElementos;

    /**
     * Objeto para verificar el invariante de la estructura
     */
    private VerificadorEstructura verificador;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un árbol vacío
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        arbol = new Arbol1_2_3<Long>( );
        numeroElementos = 0;
        verificador = new VerificadorEstructura<Long>( );
    }

    /**
     * Construye un árbol con 11 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        arbol = new Arbol1_2_3<Long>( );

        Long[] elementos = new Long[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        try
        {
            arbol.insertar( elementos[ 5 ] );
            arbol.insertar( elementos[ 7 ] );
            arbol.insertar( elementos[ 4 ] );
            arbol.insertar( elementos[ 6 ] );
            arbol.insertar( elementos[ 3 ] );

            arbol.insertar( elementos[ 2 ] );
            arbol.insertar( elementos[ 1 ] );
            arbol.insertar( elementos[ 8 ] );
            arbol.insertar( elementos[ 9 ] );
            arbol.insertar( elementos[ 0 ] );
            arbol.insertar( elementos[ 10 ] );
        }
        catch( ElementoExisteException e )
        {

            e.printStackTrace( );
        }
        numeroElementos = 11;
        verificador = new VerificadorEstructura<Long>( );
    }

    /**
     * Construye un árbol con 5 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        arbol = new Arbol1_2_3<Long>( );

        try
        {
            arbol.insertar( new Long( -8 ) );
            arbol.insertar( new Long( -11 ) );
            arbol.insertar( new Long( -12 ) );
            arbol.insertar( new Long( -19 ) );
            arbol.insertar( new Long( -20 ) );

        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
        numeroElementos = 5;
        verificador = new VerificadorEstructura<Long>( );
    }

    /**
     * Construye un árbol con 1 nodo
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        arbol = new Arbol1_2_3<Long>( );

        try
        {
            arbol.insertar( new Long( -800 ) );
            numeroElementos = 1;
            verificador = new VerificadorEstructura<Long>( );
        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Verifica que la estructura y el orden se mantengan en el árbol 2_3
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = verificador.verificarArbol( arbol );

        assertTrue( "La estructura y/o el orden en el árbol no es correcto", estructuraBien );
    }

    /**
     * Verifica que las inserciones se estén realizando correctamente en el árbol
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar1( )
    {
        setupEscenario1( );

        Long[] elementos = new Long[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        try
        {
            // Inserción
            arbol.insertar( elementos[ 7 ] );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol

            Nodo1_2_3 raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaizDer( ) );

            // Inserción
            arbol.insertar( elementos[ 3 ] );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 2 ] );

            raiz = arbol.darRaiz( );
            Nodo1_2_3 hijo1 = raiz.darHijoIzq( );
            Nodo1_2_3 hijo2 = raiz.darHijoCent( );
            Nodo1_2_3 hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 8 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 1 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo3.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 4 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo3.darRaizDer( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 5 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )hijo2.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo3.darRaizDer( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 9 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            Nodo1_2_3 hijo1_1 = hijo1.darHijoIzq( );
            Nodo1_2_3 hijo1_2 = hijo1.darHijoCent( );
            Nodo1_2_3 hijo1_3 = hijo1.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )hijo2.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 6 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            hijo1_1 = hijo1.darHijoIzq( );
            hijo1_2 = hijo1.darHijoCent( );
            hijo1_3 = hijo1.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )hijo2.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darHijoDer( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo2.darHijoDer( ).darRaizDer( ) );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 0 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            hijo1_1 = hijo1.darHijoIzq( );
            hijo1_2 = hijo1.darHijoCent( );
            hijo1_3 = hijo1.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )hijo2.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darHijoDer( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo2.darHijoDer( ).darRaizDer( ) );

            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1.darHijoIzq( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo1.darHijoIzq( ).darRaizDer( ) );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 10 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            hijo1_1 = hijo1.darHijoIzq( );
            hijo1_2 = hijo1.darHijoCent( );
            hijo1_3 = hijo1.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )hijo2.darRaizDer( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darHijoDer( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo2.darHijoDer( ).darRaizDer( ) );

            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1.darHijoIzq( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo1.darHijoIzq( ).darRaizDer( ) );

            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo3.darHijoDer( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo3.darHijoDer( ).darRaizDer( ) );

            verificarInvariante( );

            // Verificar que el árbol éste bien construido con el recorrido en inorden
            Iterador iterador = arbol.inorden( );

            int cont = 0;
            while( iterador.haySiguiente( ) )
            {
                Long elemento = ( Long )iterador.darSiguiente( );

                assertEquals( "No se insertaron en orden correcto los elementos", elementos[ cont ], elemento );
                cont++;
            }

        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Verifica que no se permita la inserción (que se arroje excepción) de elementos que ya existan en el árbol 1_2_3
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion2( )
    {
        setupEscenario2( );

        try
        {
            arbol.insertar( new Long( 5 ) );

            assertTrue( "No se debio realizar la inserción del elemento", false );

        }
        catch( ElementoExisteException e )
        {

            assertTrue( "No se debio realizar la inserción del elemento", true );
            verificarInvariante( );
        }
    }

    /**
     * Verifica la inserción en el árbol adicionando sólo elementos menores a la raíz
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion3( )
    {
        setupEscenario1( );

        try
        {
            // Inserción
            arbol.insertar( new Long( -8 ) );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol

            Nodo1_2_3 raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaizDer( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -11 ) );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaizDer( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            Nodo1_2_3 hijo1 = raiz.darHijoIzq( );
            Nodo1_2_3 hijo2 = raiz.darHijoCent( );
            Nodo1_2_3 hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo1.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -19 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaizDer( ) );
            ;
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            assertNull( "El elemento debería ser null", hijo3 );

            assertEquals( "El elemento debería ser -19", new Long( -20 ), ( Long )hijo1.darHijoIzq( ).darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo1.darHijoIzq( ).darRaizDer( ) );

            verificarInvariante( );

        }
        catch( ElementoExisteException e )
        {

            assertTrue( "No se debio realizar la inserción del elemento", true );
        }
    }

    /**
     * Verifica que la altura del árbol se genere correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarAltura( )
    {
        setupEscenario2( );

        int altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 4, altura );

        setupEscenario1( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 0, altura );

        setupEscenario3( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 3, altura );

        setupEscenario4( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 1, altura );

    }

    /**
     * Verifica que la búsqueda de un elemento se realice correctamente en el árbol 2_3
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar( )
    {
        setupEscenario2( );
        Long elemento;
        Long respuesta;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            elemento = new Long( cont );
            respuesta = ( Long )arbol.buscar( elemento );
            assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        }

        // Verificar que al buscar un elemento que no exista se retorne null
        elemento = new Long( 1000 );
        respuesta = ( Long )arbol.buscar( elemento );
        assertNull( "No se retorno el elemento correcto en la realización de la búsqueda", respuesta );

        setupEscenario2( );
        respuesta = ( Long )arbol.buscar( elemento );
        assertNull( "No se retorno el elemento correcto en la realización de la búsqueda", respuesta );

        // Realizar la búsqueda de elemetos solos
        setupEscenario3( );
        elemento = new Long( -8 );
        respuesta = ( Long )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
    }

    /**
     * Verifica que el peso se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPeso( )
    {
        setupEscenario1( );

        int peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario2( );
        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario3( );

        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario4( );

        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );
    }

    /**
     * Verifica que se recorra el árbol en inorden de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInorden( )
    {
        setupEscenario2( );

        Iterador iterador = arbol.inorden( );

        // Verificar que el recorrido en inorden sea correcto
        int cont = 0;
        while( iterador.haySiguiente( ) )
        {

            Long elemento = ( Long )iterador.darSiguiente( );

            assertEquals( "El elemento retornado no es el correcto", new Long( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos en el inorden", numeroElementos, cont );

        setupEscenario1( );
        iterador = arbol.inorden( );
        assertFalse( "No se debió haber recorrido elemento alguno", iterador.haySiguiente( ) ); // Verificar que no se haya recorrido nada

        // Verificar que se retorne el único elemento del árbol
        setupEscenario4( );
        iterador = arbol.inorden( );
        assertEquals( "El peso no es correcto", new Long( -800 ), iterador.darSiguiente( ) );
    }

    /**
     * Verifica que todos los elementos del árbol sean eliminados de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                arbol.eliminar( new Long( cont ) );
                verificarInvariante( );
            }

            // Verificar que se hayan eliminado todos los elementos
            for( int cont = 6; cont < numeroElementos + 8; cont++ )
            {

                if( cont != 13 && cont != 14 )
                {
                    Long elemento = ( Long )arbol.buscar( new Long( cont ) );
                    assertNull( "No se debió haber retornado el elemento", elemento );

                }
            }

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );

            setupEscenario4( );
            arbol.eliminar( new Long( -800 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );
            verificarInvariante( );

            setupEscenario3( );
            arbol.eliminar( new Long( -8 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", numeroElementos - 1, arbol.darPeso( ) );
            verificarInvariante( );

            // buscar el elemento que se acabo de eliminar
            Long elemento = ( Long )arbol.buscar( new Long( -1 ) );
            assertNull( "No se debió haber retornado el elemento", elemento );

        }
        catch( ElementoNoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que se arroje excepción al tratar de eliminar un elemento que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario2( );

        try
        {
            arbol.eliminar( new Long( 10000 ) );
            assertTrue( "No se debió eliminar el elemento", false );

        }
        catch( ElementoNoExisteException e )
        {

            // Verificar que se hayan eliminado todos los elementos
            assertTrue( "No se debió eliminar el elemento", true );
            verificarInvariante( );
            setupEscenario4( );
            try
            {
                arbol.eliminar( new Long( 15 ) );
                assertTrue( "No se debió eliminar el elemento", false );
            }
            catch( ElementoNoExisteException e1 )
            {
                assertTrue( "No se debió eliminar el elemento", true );
                verificarInvariante( );
            }

        }
    }

    /**
     * Verifica que los elementos que no se encuentran en las hojas se eliminen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario2( );

        Long[] elementos = new Long[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        try
        {
            arbol.eliminar( elementos[ 6 ] );

            Nodo1_2_3 raiz = arbol.darRaiz( );
            Nodo1_2_3 hijo1 = raiz.darHijoIzq( );
            Nodo1_2_3 hijo2 = raiz.darHijoCent( );
            Nodo1_2_3 hijo3 = raiz.darHijoDer( );
            Nodo1_2_3 hijo1_1 = hijo1.darHijoIzq( );
            Nodo1_2_3 hijo1_2 = hijo1.darHijoCent( );
            Nodo1_2_3 hijo1_3 = hijo1.darHijoDer( );
            Nodo1_2_3 hijo2_1 = null;
            Nodo1_2_3 hijo2_2 = null;
            Nodo1_2_3 hijo2_3 = null;
            Nodo1_2_3 hijo3_1 = hijo3.darHijoIzq( );
            Nodo1_2_3 hijo3_2 = hijo3.darHijoCent( );
            Nodo1_2_3 hijo3_3 = hijo3.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 4, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo1_2 );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertNull( "El elemento debería ser null", hijo2 );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo3_1 );
            assertNull( "El elemento debería ser null", hijo3_2 );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo3_3.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", hijo3_3.darRaizDer( ) );
            verificarInvariante( );

            setupEscenario2( );
            arbol.eliminar( elementos[ 10 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            hijo1_1 = hijo1.darHijoIzq( );
            hijo1_2 = hijo1.darHijoCent( );
            hijo1_3 = hijo1.darHijoDer( );
            hijo2_1 = hijo2.darHijoIzq( );
            hijo2_2 = hijo2.darHijoCent( );
            hijo2_3 = hijo2.darHijoDer( );
            hijo3_1 = hijo3.darHijoIzq( );
            hijo3_2 = hijo3.darHijoCent( );
            hijo3_3 = hijo3.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 4, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )raiz.darRaizDer( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo1_2 );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", ( Long )hijo2.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2_1 );
            assertNull( "El elemento debería ser null", hijo2_2 );
            assertNull( "El elemento debería ser null", hijo2_3 );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo3_1 );
            assertNull( "El elemento debería ser null", hijo3_2 );
            assertNull( "El elemento debería ser null", hijo3_3 );
            verificarInvariante( );

            setupEscenario2( );
            arbol.eliminar( elementos[ 0 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijoIzq( );
            hijo2 = raiz.darHijoCent( );
            hijo3 = raiz.darHijoDer( );
            hijo1_1 = hijo1.darHijoIzq( );
            hijo1_2 = hijo1.darHijoCent( );
            hijo1_3 = hijo1.darHijoDer( );
            hijo2_1 = hijo2.darHijoIzq( );
            hijo2_2 = hijo2.darHijoCent( );
            hijo2_3 = hijo2.darHijoDer( );
            hijo3_1 = hijo3.darHijoIzq( );
            hijo3_2 = hijo3.darHijoCent( );
            hijo3_3 = hijo3.darHijoDer( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaizIzq( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaizDer( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaizDer( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo1_2 );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaizIzq( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo2_1 );
            assertNull( "El elemento debería ser null", hijo2_2 );
            assertNull( "El elemento debería ser null", hijo2_3 );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo3.darRaizIzq( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo3.darRaizDer( ) );
            assertNull( "El elemento debería ser null", hijo3_1 );
            assertNull( "El elemento debería ser null", hijo3_2 );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo3_3.darRaizIzq( ) );
            verificarInvariante( );

        }
        catch( ElementoNoExisteException e )
        {

            e.printStackTrace( );
        }
    }
}
