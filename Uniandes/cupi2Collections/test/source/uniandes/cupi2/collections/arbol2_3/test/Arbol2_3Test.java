package uniandes.cupi2.collections.arbol2_3.test;

import uniandes.cupi2.collections.arbol2_3.Arbol2_3;
import uniandes.cupi2.collections.arbol2_3.ExisteException;
import uniandes.cupi2.collections.arbol2_3.Nodo2_3;
import uniandes.cupi2.collections.arbol2_3.NoExisteException;

import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase árbol 2_3
 */
public class Arbol2_3Test extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private Arbol2_3 arbol;

    /**
     * El número de elementos a manejar en cada escenario
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
        arbol = new Arbol2_3<Long>( );
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
        arbol = new Arbol2_3<Long>( );

        Long[] elementos = new Long[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        try
        {
            arbol.insertar( elementos[ 5 ] );
            arbol.insertar( elementos[ 4 ] );
            arbol.insertar( elementos[ 6 ] );
            arbol.insertar( elementos[ 3 ] );
            arbol.insertar( elementos[ 7 ] );
            arbol.insertar( elementos[ 2 ] );
            arbol.insertar( elementos[ 1 ] );
            arbol.insertar( elementos[ 8 ] );
            arbol.insertar( elementos[ 9 ] );
            arbol.insertar( elementos[ 0 ] );
            arbol.insertar( elementos[ 10 ] );
        }
        catch( ExisteException e )
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
        arbol = new Arbol2_3<Long>( );

        try
        {
            arbol.insertar( new Long( -8 ) );
            arbol.insertar( new Long( -11 ) );
            arbol.insertar( new Long( -12 ) );
            arbol.insertar( new Long( -19 ) );
            arbol.insertar( new Long( -20 ) );

        }
        catch( ExisteException e )
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
        arbol = new Arbol2_3<Long>( );

        try
        {
            arbol.insertar( new Long( -800 ) );
            numeroElementos = 1;
            verificador = new VerificadorEstructura<Long>( );
        }
        catch( ExisteException e )
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
            arbol.insertar( elementos[ 5 ] );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol

            Nodo2_3 raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );

            // Inserción
            arbol.insertar( elementos[ 4 ] );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz2( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 6 ] );

            raiz = arbol.darRaiz( );
            Nodo2_3 hijo1 = raiz.darHijo1( );
            Nodo2_3 hijo2 = raiz.darHijo2( );
            Nodo2_3 hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 3 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 7 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 2 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 1 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 8 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            Nodo2_3 hijo1_1 = hijo1.darHijo1( );
            Nodo2_3 hijo1_2 = hijo1.darHijo2( );
            Nodo2_3 hijo1_3 = hijo1.darHijo3( );

            Nodo2_3 hijo2_1 = hijo2.darHijo1( );
            Nodo2_3 hijo2_2 = hijo2.darHijo2( );
            Nodo2_3 hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );

            assertNull( "El elemento debería ser null", hijo3 );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 9 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );

            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );

            assertNull( "El elemento debería ser null", hijo3 );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 0 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );

            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );

            assertNull( "El elemento debería ser null", hijo3 );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 10 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );

            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );

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
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Verifica que no se permita la inserción (que se arroje excepción) de elementos que ya existan en el árbol 2_3
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
        catch( ExisteException e )
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

            Nodo2_3 raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -11 ) );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )raiz.darRaiz2( ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            Nodo2_3 hijo1 = raiz.darHijo1( );
            Nodo2_3 hijo2 = raiz.darHijo2( );
            Nodo2_3 hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -19 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );

            verificarInvariante( );

        }
        catch( ExisteException e )
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

        assertEquals( "La altura retornada no es correcta", 3, altura );

        setupEscenario1( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 0, altura );

        setupEscenario3( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 2, altura );

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
        catch( NoExisteException e )
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
        catch( NoExisteException e )
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
            catch( NoExisteException e1 )
            {
                assertTrue( "No se debió eliminar el elemento", true );
                verificarInvariante( );
            }

        }
    }

    /**
     * Verifica que todos los elementos de que se encuentran en las hojas se eliminen correctamente
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
            // Eliminación caso A
            arbol.insertar( new Long( 11 ) );
            arbol.eliminar( new Long( 11 ) );

            Nodo2_3 raiz = arbol.darRaiz( );
            Nodo2_3 hijo1 = raiz.darHijo1( );
            Nodo2_3 hijo2 = raiz.darHijo2( );
            Nodo2_3 hijo3 = raiz.darHijo3( );
            Nodo2_3 hijo1_1 = hijo1.darHijo1( );
            Nodo2_3 hijo1_2 = hijo1.darHijo2( );
            Nodo2_3 hijo1_3 = hijo1.darHijo3( );

            Nodo2_3 hijo2_1 = hijo2.darHijo1( );
            Nodo2_3 hijo2_2 = hijo2.darHijo2( );
            Nodo2_3 hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminación caso B
            arbol.insertar( new Long( -1 ) );
            arbol.eliminar( new Long( -1 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );

            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminación caso C
            setupEscenario3( );
            arbol.insertar( new Long( -13 ) );
            arbol.eliminar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -13", new Long( -13 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso D
            setupEscenario3( );
            arbol.insertar( new Long( -10 ) );
            arbol.eliminar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Long( -10 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso E
            setupEscenario3( );
            arbol.eliminar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminación caso F
            setupEscenario3( );
            arbol.eliminar( new Long( -8 ) );
            arbol.eliminar( new Long( -11 ) );
            arbol.eliminar( new Long( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminación caso G
            setupEscenario3( );
            arbol.insertar( new Long( -13 ) );
            arbol.eliminar( new Long( -13 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso H
            setupEscenario3( );
            arbol.insertar( new Long( -13 ) );
            arbol.eliminar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -13", new Long( -13 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso I
            /*
             * setupEscenario3(); arbol.insertar(new Long(-21)); arbol.eliminar(new Long(-12));
             * 
             * 
             * raiz= arbol.darRaiz(); hijo1= raiz.darHijo1(); hijo2= raiz.darHijo2(); hijo3= raiz.darHijo3();
             * 
             * assertEquals("La altura retornada no es correcta", 2, arbol.darAltura()); //verificar la altura del arbol assertEquals("El elemento debería ser -20", new
             * Long(-20), (Long)raiz.darRaiz1()); //Verificar el contenido de los nodos assertEquals("El elemento debería ser -11", new Long(-11), (Long)raiz.darRaiz2());
             * assertEquals("El elemento debería ser -21", new Long(-21), (Long)hijo1.darRaiz1()); assertNull("El elemento debería ser null", hijo1.darRaiz2());
             * assertEquals("El elemento debería ser -19", new Long(-19), (Long)hijo2.darRaiz1()); assertNull("El elemento debería ser null", hijo2.darRaiz2());
             * assertEquals("El elemento debería ser -8", new Long(-8), (Long)hijo3.darRaiz1()); assertNull("El elemento debería ser null", hijo3.darRaiz2());
             */

            // Eliminación caso H
            setupEscenario3( );
            arbol.insertar( new Long( -10 ) );
            arbol.eliminar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Long( -10 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso K
            setupEscenario3( );
            arbol.eliminar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminación caso L
            setupEscenario3( );
            arbol.eliminar( new Long( -8 ) );
            arbol.eliminar( new Long( -11 ) );
            arbol.eliminar( new Long( -12 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz2( ) ); // Verificar el contenido de los nodos
            verificarInvariante( );

            // Eliminación caso M
            setupEscenario3( );
            arbol.insertar( new Long( -10 ) );
            arbol.eliminar( new Long( -8 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            verificarInvariante( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -10", new Long( -10 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso N
            setupEscenario3( );
            arbol.insertar( new Long( -10 ) );
            arbol.eliminar( new Long( -10 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Long( -8 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso O
            setupEscenario3( );
            arbol.insertar( new Long( -13 ) );
            arbol.eliminar( new Long( -8 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -13", new Long( -13 ), ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -11", new Long( -11 ), ( Long )hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            verificarInvariante( );

            // Eliminación caso P
            setupEscenario3( );
            arbol.eliminar( new Long( -8 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -19", new Long( -19 ), ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -20", new Long( -20 ), ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Long( -12 ), ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -12", new Long( -11 ), ( Long )hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );
        }
        catch( NoExisteException e )
        {
            e.printStackTrace( );
        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }

    }

    /**
     * Verifica que los elementos que no se encuentran en las hojas se eliminen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar4( )
    {
        setupEscenario2( );

        Long[] elementos = new Long[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        try
        {
            // Caso R
            arbol.eliminar( elementos[ 3 ] );

            Nodo2_3 raiz = arbol.darRaiz( );
            Nodo2_3 hijo1 = raiz.darHijo1( );
            Nodo2_3 hijo2 = raiz.darHijo2( );
            Nodo2_3 hijo3 = raiz.darHijo3( );
            Nodo2_3 hijo1_1 = hijo1.darHijo1( );
            Nodo2_3 hijo1_2 = hijo1.darHijo2( );
            Nodo2_3 hijo1_3 = hijo1.darHijo3( );
            Nodo2_3 hijo2_1 = hijo2.darHijo1( );
            Nodo2_3 hijo2_2 = hijo2.darHijo2( );
            Nodo2_3 hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Caso Q
            setupEscenario2( );
            arbol.eliminar( elementos[ 1 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );
            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_3 );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminar un elemento de la raíz
            setupEscenario2( );
            arbol.eliminar( elementos[ 5 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );
            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser 9", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminar el 7
            setupEscenario2( );
            arbol.eliminar( elementos[ 7 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );
            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser 9", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], ( Long )hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], ( Long )hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

            // Eliminar el 9
            setupEscenario2( );
            arbol.eliminar( elementos[ 9 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo1_1 = hijo1.darHijo1( );
            hijo1_2 = hijo1.darHijo2( );
            hijo1_3 = hijo1.darHijo3( );
            hijo2_1 = hijo2.darHijo1( );
            hijo2_2 = hijo2.darHijo2( );
            hijo2_3 = hijo2.darHijo3( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], ( Long )hijo1_1.darRaiz1( ) );
            assertNull( "El elemento debería ser 2", hijo1_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 10 ], ( Long )hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_3 );
            assertNull( "El elemento debería ser null", hijo3 );
            verificarInvariante( );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }
}
