package uniandes.cupi2.collections.arbolB.test;


import junit.framework.TestCase;
import uniandes.cupi2.collections.arbolB.ArbolB;
import uniandes.cupi2.collections.arbolB.ExisteException;
import uniandes.cupi2.collections.arbolB.NodoArbolB;
import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Esta es la clase usada para verificar los métodos de la clase árbol B
 */
public class ArbolBTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ArbolB arbol;

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
        arbol = new ArbolB<Long>( 3 );
        numeroElementos = 0;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye un árbol con 11 nodos
     * 
     */
    /*@SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        arbol = new ArbolB<Integer>( 4 );

        Integer[] elementos = new Integer[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Integer( cont );
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
        verificador = new VerificadorEstructura<Integer>( );
    }

    *//**
     * Construye un árbol con 7 nodos
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        arbol = new ArbolB<Integer>( 5 );

        try
        {
            arbol.insertar( new Integer( -100 ) );
            arbol.insertar( new Integer( -50 ) );
            arbol.insertar( new Integer( -10 ) );
            arbol.insertar( new Integer( -2000 ) );
            arbol.insertar( new Integer( 1000 ) );
            arbol.insertar( new Integer( 2000 ) );
            arbol.insertar( new Integer( 3000 ) );

        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
        numeroElementos = 7;
        verificador = new VerificadorEstructura<Integer>( );
    }

    *//**
     * Construye un árbol con 1 nodo
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        arbol = new ArbolB<Integer>( 5 );

        try
        {
            arbol.insertar( new Integer( -800 ) );
            numeroElementos = 1;
            verificador = new VerificadorEstructura<Integer>( );
        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
    }
    
    *//**
     * Construye un árbol con 12 nodos
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void setupEscenario5( )
    {
        arbol = new ArbolB<Integer>( 4 );

        try
        {
            arbol.insertar( new Integer( -100 ) );
            arbol.insertar( new Integer( -50 ) );
            arbol.insertar( new Integer( -10 ) );
            arbol.insertar( new Integer( -2000 ) );
            arbol.insertar( new Integer( 1000 ) );
            arbol.insertar( new Integer( 2000 ) );
            arbol.insertar( new Integer( 3000 ) );
            arbol.insertar( new Integer( -40 ) );
            arbol.insertar( new Integer( 0 ) );
            arbol.insertar( new Integer( -20 ) );
            arbol.insertar( new Integer( -15 ) );
            arbol.insertar( new Integer( -13 ) );

        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
        numeroElementos = 12;
        verificador = new VerificadorEstructura<Integer>( );
    }
    
    *//**
     * Construye un árbol con 11 nodos
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void setupEscenario6( )
    {
        arbol = new ArbolB<Integer>( 5 );

        try
        {
            arbol.insertar( new Integer( -100 ) );
            arbol.insertar( new Integer( -50 ) );
            arbol.insertar( new Integer( -10 ) );
            arbol.insertar( new Integer( -2000 ) );
            arbol.insertar( new Integer( 1000 ) );
            arbol.insertar( new Integer( 2000 ) );
            arbol.insertar( new Integer( 3000 ) );
            arbol.insertar( new Integer( -40 ) );
            arbol.insertar( new Integer( 0 ) );
            arbol.insertar( new Integer( -20 ) );
            arbol.insertar( new Integer( -15 ) );            
            arbol.insertar( new Integer( 10 ) );
            arbol.insertar( new Integer( 20 ) );

        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
        numeroElementos = 11;
        verificador = new VerificadorEstructura<Integer>( );
    }

    *//**
     * Verifica que la estructura y el orden se mantengan en el árbol 2_3
     * 
     *//*
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = verificador.verificarArbol( arbol );

        assertTrue( "La estructura y/o el orden en el árbol no es correcto", estructuraBien );
    }

    *//**
     * Verifica que las inserciones se estén realizando correctamente en el árbol
     * 
     *//*
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

            NodoArbolB<Long> raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );

            // Inserción
            arbol.insertar( elementos[ 4 ] );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 1 ) );
            assertEquals( "El número de raíces no es correcto", 2, raiz.darRaices( ).darLongitud() );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 6 ] );

            raiz = arbol.darRaiz( );            
            NodoArbolB<Long> hijo1 = raiz.darHijo( 0 );
            NodoArbolB<Long> hijo2 = raiz.darHijo( 1 );
            

            assertEquals( "La altura retornada no es correcto", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo1.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo2.darRaices( ).darLongitud() );
            assertEquals( "El número de raíces no es correcto", 2, raiz.darHijos( ).darLongitud() );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 3 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 );            

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 2, hijo1.darRaices( ).darLongitud() );
            assertEquals( "El número de raíces no es correcto", 2, raiz.darHijos( ).darLongitud() );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 7 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 );            

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz( 1 ) );
            assertEquals( "El número de raíces no es correcto", 2, raiz.darHijos( ).darLongitud() );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 2 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            NodoArbolB<Long> hijo3 = raiz.darHijo( 2 );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El número de hijos no es correcto", 1, hijo1.darHijos( ).darLongitud() );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El número de hijos no es correcto", 1, hijo2.darHijos( ).darLongitud() );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo3.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo3.darRaiz( 1 ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 1 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            hijo3 = raiz.darHijo( 2 );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo2.darHijos( ).darLongitud() );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo3.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo3.darRaiz( 1 ) );
            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 8 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            hijo3 = raiz.darHijo( 2 );
            NodoArbolB hijo1_1 = hijo1.darHijo( 0 );
            NodoArbolB hijo1_2 = hijo1.darHijo( 1 );
            NodoArbolB hijo1_3 = hijo1.darHijo( 2 );

            NodoArbolB hijo2_1 = hijo2.darHijo( 0 );
            NodoArbolB hijo2_2 = hijo2.darHijo( 1 );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], ( Long )hijo1.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo1.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], ( Long )hijo1_1.darRaiz( 0 ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], ( Long )hijo1_1.darRaiz( 1 ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], ( Long )hijo1_2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo1_2.darRaices( ).darLongitud() );
            assertEquals( "El número de hijos no es correcto", 2, hijo1.darHijos().darLongitud() );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], ( Long )hijo2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo2.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], ( Long )hijo2_1.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo2_1.darRaices( ).darLongitud() );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], ( Long )hijo2_2.darRaiz( 0 ) );
            assertEquals( "El número de raíces no es correcto", 1, hijo2_2.darRaices( ).darLongitud() );                        
            assertEquals( "El número de hijos no es correcto", 2, raiz.darHijos( ).darLongitud() );

            verificarInvariante( );

            // Inserción
            arbol.insertar( elementos[ 9 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            hijo3 = raiz.darHijo( 2 );
            hijo1_1 = hijo1.darHijo( 0 );
            hijo1_2 = hijo1.darHijo( 1 );
            hijo1_3 = hijo1.darHijo( 2 );

            hijo2_1 = hijo2.darHijo( 0 );
            hijo2_2 = hijo2.darHijo( 1 );           */ 

            //TODO Arreglar lo que faltan
            /*assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], ( Long )raiz.darRaiz( 0 ) ); // Verificar el contenido de los nodos
            assertEquals( "El número de raíces no es correcto", 1, raiz.darRaices( ).darLongitud() );            
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
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            hijo3 = raiz.darHijo( 2 );
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
            hijo1 = raiz.darHijo( 0 );
            hijo2 = raiz.darHijo( 1 ); 
            hijo3 = raiz.darHijo( 2 );
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
            }*/

        /*}
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }
    }*/
    
    /**
     * Verifica la inserción de elementos se realice de forma correcta
     * 
     */
    /*@SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        setupEscenario3( );

        try
        {            
            arbol.insertar( new Integer( -40 ) );
            arbol.insertar( new Integer( 0 ) );
            
            Nodo2_4<Integer> raiz = arbol.darRaiz( );
            Nodo2_4<Integer> hijo1 = raiz.darHijo1( );
            Nodo2_4<Integer> hijo2 = raiz.darHijo2( );
            Nodo2_4<Integer> hijo3 = raiz.darHijo3( );
            Nodo2_4<Integer> hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) );             
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz2( ) );            
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo2.darRaiz3( ) );             
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo3.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertNull( "El hijo 4 debería ser null", hijo4 );
            verificarInvariante( );
            
            arbol.insertar( new Integer( -20 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -10", new Integer(-20), hijo2.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );            
            verificarInvariante( );   
                        
            arbol.insertar( new Integer( -15 ) );
            arbol.insertar( new Integer( -13 ) );
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );
            
            Nodo2_4<Integer> hijo1_1 = hijo1.darHijo1( );
            Nodo2_4<Integer> hijo1_2 = hijo1.darHijo2( );
            Nodo2_4<Integer> hijo1_3 = hijo1.darHijo3( );
            Nodo2_4<Integer> hijo1_4 = hijo1.darHijo4( );
            Nodo2_4<Integer> hijo2_1 = hijo2.darHijo1( );
            Nodo2_4<Integer> hijo2_2 = hijo2.darHijo2( );
            Nodo2_4<Integer> hijo2_3 = hijo2.darHijo3( );
            Nodo2_4<Integer> hijo2_4 = hijo2.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo4( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1_1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1_1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_1.darRaiz3( ) );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz3( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo1_3.darRaiz1( ) );
            assertEquals( "El elemento debería ser -13", new Integer(-13), hijo1_3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz3( ) );
            assertNull( "El hijo debería ser null", hijo1_4);
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz3( ) );
            assertEquals( "El elemento debería ser -9", new Integer(2000), hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz3( ) );
            assertNull( "El hijo debería ser null", hijo2_3);
            assertNull( "El hijo debería ser null", hijo2_4);
                        
            verificarInvariante( ); 


        }
        catch( ExisteException e )
        {
            e.printStackTrace();             
        }
    }

    *//**
     * Verifica que no se permita la inserción (que se arroje excepción) de elementos que ya existan en el árbol 2_3
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testInsertar3( )
    {
        setupEscenario2( );

        try
        {
            arbol.insertar( new Integer( 5 ) );

            assertTrue( "No se debio realizar la inserción del elemento", false );

        }
        catch( ExisteException e )
        {

            assertTrue( "No se debio realizar la inserción del elemento", true );
            verificarInvariante( );
        }
    }

    *//**
     * Verifica la inserción en el árbol adicionando sólo elementos menores a la raíz
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testInsertar4( )
    {
        setupEscenario1( );

        try
        {
            // Inserción
            arbol.insertar( new Integer( -8 ) );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol

            Nodo2_4<Integer> raiz = arbol.darRaiz( );
            assertEquals( "El elemento debería ser -8", new Integer( -8 ), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo1( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo2( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo4( ) );
            
            verificarInvariante( );

            // Inserción
            arbol.insertar( new Integer( -11 ) );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Integer( -11 ), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -8", new Integer( -8 ), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo1( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo2( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo4( ) );
            verificarInvariante( );
            
            //Inserción
            arbol.insertar( new Integer( -12 ) );

            raiz = arbol.darRaiz( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -12", new Integer( -12 ), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -11", new Integer( -11 ), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -8", new Integer( -8 ), raiz.darRaiz3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo1( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo2( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo3( ) );
            assertNull( "El hijo debería ser null", raiz.darHijo4( ) );
            verificarInvariante( );

            // Inserción

            // Inserción
            arbol.insertar( new Integer( -19 ) );

            raiz = arbol.darRaiz( );
            Nodo2_4<Integer> hijo1 = raiz.darHijo1( );
            Nodo2_4<Integer> hijo2 = raiz.darHijo2( );
            Nodo2_4<Integer> hijo3 = raiz.darHijo3( );
            Nodo2_4<Integer> hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Integer( -11 ), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );
            assertEquals( "El elemento debería ser -19", new Integer( -19 ), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -12", new Integer( -12 ), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -8", new Integer( -8 ), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );
            assertNull( "El hijo debería ser null", hijo3 );
            assertNull( "El hijo debería ser null", hijo4 );
            verificarInvariante( );            

            // Inserción
            arbol.insertar( new Integer( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -11", new Integer( -11 ), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );
            assertEquals( "El elemento debería ser -19", new Integer( -20 ), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -19", new Integer( -19 ), hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser -12", new Integer( -12 ), hijo1.darRaiz3( ) );           
            assertEquals( "El elemento debería ser -8", new Integer( -8 ), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );
            assertNull( "El hijo debería ser null", hijo3 );
            assertNull( "El hijo debería ser null", hijo4 );
            verificarInvariante( ); 

        }
        catch( ExisteException e )
        {

            assertTrue( "No se debio realizar la inserción del elemento", true );
        }
    }

    *//**
     * Verifica que la altura del árbol se genere correctamente
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testDarAltura( )
    {
        setupEscenario2( );

        int altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 2, altura );

        setupEscenario1( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 0, altura );

        setupEscenario3( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 2, altura );

        setupEscenario4( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 1, altura );
        
        setupEscenario5( );
        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 3, altura );

    }

    *//**
     * Verifica que la búsqueda de un elemento se realice correctamente en el árbol 2_3
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testBuscar( )
    {
        setupEscenario2( );
        Integer elemento;
        Integer respuesta;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            elemento = new Integer( cont );
            respuesta = ( Integer )arbol.buscar( elemento );
            assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        }

        // Verificar que al buscar un elemento que no exista se retorne null
        elemento = new Integer( 1000 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertNull( "No se retorno el elemento correcto en la realización de la búsqueda", respuesta );

        setupEscenario2( );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertNull( "No se retorno el elemento correcto en la realización de la búsqueda", respuesta );

        // Realizar la búsqueda de elemetos solos
        setupEscenario3( );
        elemento = new Integer( -2000 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        //Realizar la búsqueda de elemetos solos
        setupEscenario5( );
        elemento = new Integer( 3000 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        elemento = new Integer( 2000 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        elemento = new Integer( -15 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        elemento = new Integer( -13 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        elemento = new Integer( -10 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
        
        elemento = new Integer( 1000 );
        respuesta = ( Integer )arbol.buscar( elemento );
        assertEquals( "No se retorno el elemento correcto en la realización de la búsqueda", elemento, respuesta );
    }

    *//**
     * Verifica que el peso se retorne correctamente
     * 
     *//*
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
        
        setupEscenario5( );

        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

    }

    *//**
     * Verifica que se recorra el árbol en inorden de forma correcta
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testInorden( )
    {
        setupEscenario2( );

        Iterador iterador = arbol.inorden( );

        // Verificar que el recorrido en inorden sea correcto
        int cont = 0;
        Integer elemento; 
        while( iterador.haySiguiente( ) )
        {

            elemento = ( Integer )iterador.darSiguiente( );

            assertEquals( "El elemento retornado no es el correcto", new Integer( cont ), elemento );
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
        assertEquals( "El peso no es correcto", new Integer( -800 ), iterador.darSiguiente( ) );
        
        setupEscenario5(); 
        iterador= arbol.inorden(); 
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -2000 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -100 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -50 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -40 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -20 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -15 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -13 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( -10 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( 0 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( 1000 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( 2000 ), elemento );
        
        elemento= ( Integer )iterador.darSiguiente(); 
        assertEquals( "El elemento retornado no es el correcto", new Integer( 3000 ), elemento );
    }

    *//**
     * Verifica que todos los elementos del árbol sean eliminados de forma correcta
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                System.out.println("Eliminando "+ cont); 
                arbol.eliminar( new Integer( cont ) );
                verificarInvariante( );
            }

            // Verificar que se hayan eliminado todos los elementos
            for( int cont = 6; cont < numeroElementos + 8; cont++ )
            {

                if( cont != 13 && cont != 14 )
                {
                    Integer elemento = ( Integer )arbol.buscar( new Integer( cont ) );
                    assertNull( "No se debió haber retornado el elemento", elemento );

                }
            }

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );

            setupEscenario4( );
            arbol.eliminar( new Integer( -800 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );
            verificarInvariante( );

            setupEscenario3( );
            arbol.eliminar( new Integer( 3000 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", numeroElementos - 1, arbol.darPeso( ) );
            verificarInvariante( );

            // buscar el elemento que se acabo de eliminar
            Integer elemento = ( Integer )arbol.buscar( new Integer( -1 ) );
            assertNull( "No se debió haber retornado el elemento", elemento );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    *//**
     * Verifica que se arroje excepción al tratar de eliminar un elemento que no existe
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario2( );

        try
        {
            arbol.eliminar( new Integer( 10000 ) );
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
                arbol.eliminar( new Integer( 15 ) );
                assertTrue( "No se debió eliminar el elemento", false );
            }
            catch( NoExisteException e1 )
            {
                assertTrue( "No se debió eliminar el elemento", true );
                verificarInvariante( );
            }

        }
    }

    *//**
     * Verifica que todos los elementos de que se encuentran en las hojas se eliminen correctamente
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario2( );

        Integer[] elementos = new Integer[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Integer( cont );
        }

        try
        {
            // Eliminación caso 1a             
            arbol.eliminar( new Integer( 2 ) );

            Nodo2_4 raiz = arbol.darRaiz( );
            Nodo2_4 hijo1 = raiz.darHijo1( );
            Nodo2_4 hijo2 = raiz.darHijo2( );
            Nodo2_4 hijo3 = raiz.darHijo3( );
            Nodo2_4 hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz3( ) );
            
            //Eliminación caso 1b             
            setupEscenario2(); 
            arbol.eliminar( new Integer( 1 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz3( ) );                        
            
            verificarInvariante( );
           
           
            //Eliminación caso 1c             
            setupEscenario2(); 
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3( ) );
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz3( ) );
            
            
            //Eliminación caso 2a (simetrico 1a)             
            setupEscenario2(); 
            arbol.eliminar( new Integer( 10 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );                         
            
            verificarInvariante( );
            
            //Eliminación caso 2b (simetrico 1b)             
            setupEscenario2(); 
            arbol.eliminar( new Integer( 9 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            
            //Eliminación caso 2c (simetrico 1c)             
            setupEscenario2(); 
            arbol.eliminar( new Integer( 8 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            
            //Eliminación caso 3a             
            setupEscenario6(); 
            arbol.eliminar( new Integer( -15 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", new Integer(20), hijo3.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante(); 
            
            //Eliminación caso 3b             
            setupEscenario6(); 
            arbol.eliminar( new Integer( -20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", new Integer(20), hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante(); 
            
            //Eliminación caso 3c           
            setupEscenario6(); 
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", new Integer(20), hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante(); 
            
            
            //Eliminación caso 4a (caso simetrico 3a)            
            setupEscenario6(); 
            arbol.eliminar( new Integer( 20 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante();
            
            //Eliminación caso 4b (caso simetrico 3b)            
            setupEscenario6(); 
            arbol.eliminar( new Integer( 10 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante();
            
            //Eliminación caso 4c (caso simetrico 3c)            
            setupEscenario6(); 
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
            verificarInvariante();
            
            //Eliminación caso 5            
            setupEscenario2(); 
            arbol.eliminar( new Integer( 8 ) );
            arbol.eliminar( new Integer( 9 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 7 ) );
            arbol.eliminar( new Integer( 1 ) );
            arbol.eliminar( new Integer( 2 ) );
            
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", new Integer(4), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) ); 
            assertEquals( "El elemento debería ser 3", new Integer(3), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser 5", new Integer(5), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 6", new Integer(6), hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertNull( "El hijo debería ser null", hijo3 );
            assertNull( "El hijo debería ser null", hijo4 ); 
            verificarInvariante();
            
            //Eliminación caso 6  (simetrico caso 5)          
            setupEscenario2();             
            arbol.eliminar( new Integer( 9 ) );
            arbol.eliminar( new Integer( 10 ) );            
            arbol.eliminar( new Integer( 1 ) );
            arbol.eliminar( new Integer( 2 ) );
            
            arbol.eliminar( new Integer( 8 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", new Integer(3), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", new Integer(5), raiz.darRaiz2( ) ); // Verificar el contenido de los nodos            
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) ); 
            assertEquals( "El elemento debería ser 3", new Integer(0), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser 4", new Integer(4), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", new Integer(6), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 7", new Integer(7), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertNull( "El hijo debería ser null", hijo4 ); 
            verificarInvariante();
            
            //Eliminación caso 7          
            setupEscenario6();                         
            arbol.eliminar( new Integer( -100 ) );                       
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz2( ) );             
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 8 (simetrico caso 7)         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 3000 ) );
            
            arbol.eliminar( new Integer( 2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo4.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 9         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -15 ) );            
            arbol.eliminar( new Integer( -100 ) );
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                        
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz2( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 1000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(3000), hijo4.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 10 (simetrico caso 9)         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 20 ) );            
            arbol.eliminar( new Integer( 3000 ) );
            
            arbol.eliminar( new Integer( 2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 10", new Integer(10), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                                   
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo4.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz2() );                        
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 11         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( -100 ) );
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );                                   
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 12         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 3000 ) );
            
            arbol.eliminar( new Integer( 2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                       
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo2.darRaiz3( ) );                                      
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                               
            assertNull( "El hijo debería ser null", hijo4 );
            verificarInvariante();
            
            
            //Eliminación caso 13         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( -100 ) );
            arbol.eliminar( new Integer( 20 ) );
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz2( ) );                                   
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 14         
            setupEscenario3();                         
            arbol.eliminar( new Integer( 3000 ) );                        
            arbol.eliminar( new Integer( -100 ) );            
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );            
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo2.darRaiz2( ) );                       
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertNull( "El hijo debería ser null", hijo3);
            assertNull( "El hijo debería ser null", hijo4);
            
            verificarInvariante();
            
            
            //Eliminación caso 15         
            setupEscenario3();                         
            arbol.eliminar( new Integer( 3000 ) );                        
            arbol.eliminar( new Integer( -100 ) );            
            arbol.eliminar( new Integer( -10 ) );
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), raiz.darRaiz3( ) );            
            assertNull( "El hijo debería ser null", hijo1);
            assertNull( "El hijo debería ser null", hijo2);
            assertNull( "El hijo debería ser null", hijo3);
            assertNull( "El hijo debería ser null", hijo4);
            
            verificarInvariante();
            
            //Eliminación caso 16         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( -100 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.insertar(new Integer(4000)); 
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4000", new Integer(4000), hijo4.darRaiz2( ) );                                   
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 17         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( -100 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );            
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz2() );                                              
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );
            verificarInvariante();
            
            //Eliminación caso 18         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( -100 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer(3000 ) );
            
            arbol.eliminar( new Integer( -2000 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -40", new Integer(-40), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(2000), hijo3.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );            
            assertNull( "La raíz debería ser null", hijo4);                                                          
            verificarInvariante();
            
            //Eliminación caso 19         
            setupEscenario3();                         
            arbol.eliminar( new Integer( 3000 ) );                        
            arbol.eliminar( new Integer( 2000 ) );
            arbol.eliminar( new Integer( 1000 ) );
            arbol.insertar( new Integer( -60 ) );
            
            arbol.eliminar( new Integer( -10 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -60", new Integer(-60), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );            
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -50", new Integer(-50), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );                                   
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertNull( "El hijo debería ser null", hijo3);
            assertNull( "El hijo debería ser null", hijo4);
            
            verificarInvariante();
            
            //Eliminación caso 20         
            setupEscenario3();                         
            arbol.eliminar( new Integer( 3000 ) );                        
            arbol.eliminar( new Integer( 2000 ) );
            arbol.eliminar( new Integer( 1000 ) );            
            
            arbol.eliminar( new Integer( -10 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 1, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -100", new Integer(-100), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz3( ) );            

            assertNull( "El hijo debería ser null", hijo1);
            assertNull( "El hijo debería ser null", hijo2);
            assertNull( "El hijo debería ser null", hijo3);
            assertNull( "El hijo debería ser null", hijo4);
            
            verificarInvariante();
            
            //Eliminación caso 21         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );                        
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 20", new Integer(20), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                        
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );                                                
            verificarInvariante();
            
            //Eliminación caso 22         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( 20 ) ); 
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 10", new Integer(10), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );  
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                        
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(3000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );                         
            verificarInvariante();
            
            //Eliminación caso 23         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( 2000 ) );
            arbol.eliminar( new Integer( 3000 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 1000 ) );
            arbol.eliminar( new Integer( 20 ) );
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -0", new Integer(0), hijo2.darRaiz2( ) );         
            assertNull( "El hijo debería ser null", hijo3 );                                               
            assertNull( "El hijo debería ser null", hijo4 );                         
            verificarInvariante();
            
            //Eliminación caso 24         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.insertar(new Integer(4000)); 
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );  
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                        
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 4000", new Integer(4000), hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );                         
            verificarInvariante();
            
            //Eliminación caso 25         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );            
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );  
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                        
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3() );                         
            verificarInvariante();
            
            //Eliminación caso 26         
            setupEscenario6();                         
            arbol.eliminar( new Integer( -20 ) );            
            arbol.eliminar( new Integer( -15 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( 10 ) );            
            arbol.eliminar( new Integer( 3000 ) );
                        
            arbol.eliminar( new Integer( -40 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo3.darRaiz2( ) );             
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                               
            assertNull( "El hijo debería ser null", hijo4);                         
            verificarInvariante();
            
            //Eliminación caso 27
            setupEscenario2(); 
            arbol.eliminar( new Integer( 6 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 8 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 7 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) ); 
                        
            //Eliminación caso 28
            setupEscenario2();
            arbol.eliminar( new Integer( 10 ) );            
            arbol.eliminar( new Integer( 6 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 8 ], raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 6", elementos[ 7 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );
            
            //Eliminación caso 29
            setupEscenario2();
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 9 ) );
            arbol.eliminar( new Integer( 6 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3( ) );
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3( ) );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], hijo3.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo3.darRaiz3( ) );           
            assertNull( "El hijo debería ser null", hijo4 ); 
            
            //Eliminación caso 30         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 2000 ) );            
            arbol.eliminar( new Integer( 3000 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 1000 ) );
            arbol.eliminar( new Integer( 20 ) );                        
                        
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -15", new Integer(-15), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo2.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );                         
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                               
            assertNull( "El hijo debería ser null", hijo4);                         
            verificarInvariante();
            
            //Eliminación caso 31         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 2000 ) );            
            arbol.eliminar( new Integer( 3000 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 1000 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( -20 ) );
                        
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -15", new Integer(-15), raiz.darRaiz2( ) );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );                        
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );                         
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                               
            assertNull( "El hijo debería ser null", hijo4);                         
            verificarInvariante();
            
            //Eliminación caso 32         
            setupEscenario6();                         
            arbol.eliminar( new Integer( 2000 ) );            
            arbol.eliminar( new Integer( 3000 ) );
            arbol.eliminar( new Integer( 10 ) );
            arbol.eliminar( new Integer( 1000 ) );
            arbol.eliminar( new Integer( 20 ) );
            arbol.eliminar( new Integer( -20 ) );
            arbol.eliminar( new Integer( -15 ) );
                        
            arbol.eliminar( new Integer( 0 ) );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertEquals( "El elemento debería ser -10", new Integer(-10), hijo2.darRaiz2( ) );                                    
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertNull( "El hijo debería ser null", hijo3);                                       
            assertNull( "El hijo debería ser null", hijo4);                         
            verificarInvariante();
            
            
            //Prueba en árbol de tres niveles
            setupEscenario5(); 
            arbol.eliminar( new Integer( 0 ) );
            
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -20", new Integer(-20), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser -10", new Integer(-10), raiz.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );                                              
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser -13", new Integer(-13), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                                            
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz3( ) );
            verificarInvariante();            
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

    *//**
     * Verifica que los elementos que no se encuentran en las hojas se eliminen correctamente
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testEliminar4( )
    {
        setupEscenario2( );

        Integer[] elementos = new Integer[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Integer( cont );
        }

        try
        {
        	//Eliminación caso 33
            arbol.eliminar( elementos[ 3 ] );

            Nodo2_4 raiz = arbol.darRaiz( );
            Nodo2_4 hijo1 = raiz.darHijo1( );
            Nodo2_4 hijo2 = raiz.darHijo2( );
            Nodo2_4 hijo3 = raiz.darHijo3( );
            Nodo2_4 hijo4 = raiz.darHijo4( );
            

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos            
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 5", elementos[ 5 ], hijo2.darRaiz1( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );            
            verificarInvariante( );
            
            //Eliminación caso 34
            setupEscenario2( );
            arbol.eliminar( elementos[ 5 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );
            

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos            
            assertEquals( "El elemento debería ser 6", elementos[ 6 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );
            assertEquals( "El elemento debería ser 7", elementos[ 7 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );            
            verificarInvariante( );
            
            //Eliminación caso 35
            setupEscenario2( );
            arbol.eliminar( elementos[ 7 ] );

            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );
            

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser 3", elementos[ 3 ], raiz.darRaiz1( ) ); // Verificar el contenido de los nodos            
            assertEquals( "El elemento debería ser 6", elementos[ 5 ], raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 8", elementos[ 8 ], raiz.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 0", elementos[ 0 ], hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser 1", elementos[ 1 ], hijo1.darRaiz2( ) );
            assertEquals( "El elemento debería ser 2", elementos[ 2 ], hijo1.darRaiz3( ) );            
            assertEquals( "El elemento debería ser 4", elementos[ 4 ], hijo2.darRaiz1( ) );            
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );
            assertEquals( "El elemento debería ser 7", elementos[ 6 ], hijo3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );
            assertEquals( "El elemento debería ser 9", elementos[ 9 ], hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 10", elementos[ 10 ], hijo4.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo4.darRaiz3( ) );            
            verificarInvariante( );
            
            //Prueba en árbol de tres niveles
            setupEscenario5(); 
            arbol.eliminar( new Integer(-10) );
            
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );

            assertEquals( "La altura retornada no es correcta", 2, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-50), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertEquals( "El elemento debería ser -20", new Integer(-20), raiz.darRaiz2( ) );
            assertEquals( "El elemento debería ser 0", new Integer(0), raiz.darRaiz3( ) );                        
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );                                              
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );                                   
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo3.darRaiz1( ) );
            assertEquals( "El elemento debería ser -13", new Integer(-13), hijo3.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo3.darRaiz3() );                                                            
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo4.darRaiz1( ) );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo4.darRaiz2( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo4.darRaiz3( ) );
            verificarInvariante();  
            
            //Prueba en árbol de tres niveles
            setupEscenario5(); 
            arbol.eliminar( new Integer(-50) );
            
            raiz = arbol.darRaiz( );
            hijo1 = raiz.darHijo1( );
            hijo2 = raiz.darHijo2( );
            hijo3 = raiz.darHijo3( );
            hijo4 = raiz.darHijo4( );
            Nodo2_4<Integer> hijo1_1 = hijo1.darHijo1();
            Nodo2_4<Integer> hijo1_2 = hijo1.darHijo2();
            Nodo2_4<Integer> hijo1_3 = hijo1.darHijo3();
            Nodo2_4<Integer> hijo1_4 = hijo1.darHijo4();
            Nodo2_4<Integer> hijo2_1 = hijo2.darHijo1();
            Nodo2_4<Integer> hijo2_2 = hijo2.darHijo2();
            Nodo2_4<Integer> hijo2_3 = hijo2.darHijo3();
            Nodo2_4<Integer> hijo2_4 = hijo2.darHijo4();
            

            assertEquals( "La altura retornada no es correcta", 3, arbol.darAltura( ) ); // verificar la altura del arbol
            assertEquals( "El elemento debería ser -50", new Integer(-10), raiz.darRaiz1( ) ); // Verificar el contenido de los nodos
            assertNull( "El elemento debería ser null", raiz.darRaiz2() );
            assertNull( "El elemento debería ser null", raiz.darRaiz3() );
            assertEquals( "El elemento debería ser -40", new Integer(-40), hijo1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -15", new Integer(-15), hijo1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1.darRaiz3() );
            assertEquals( "El elemento debería ser -2000", new Integer(-2000), hijo1_1.darRaiz1( ) );
            assertEquals( "El elemento debería ser -100", new Integer(-100), hijo1_1.darRaiz2( ) );            
            assertNull( "El elemento debería ser null", hijo1_1.darRaiz3() );
            assertEquals( "El elemento debería ser -20", new Integer(-20), hijo1_2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1_2.darRaiz3() );            
            assertEquals( "El elemento debería ser -13", new Integer(-13), hijo1_3.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo1_3.darRaiz3() );
            assertNull( "El hijo debería ser null", hijo1_4 );
            assertEquals( "El elemento debería ser 1000", new Integer(1000), hijo2.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2.darRaiz2() );                                              
            assertNull( "El elemento debería ser null", hijo2.darRaiz3() );            
            assertEquals( "El elemento debería ser 0", new Integer(0), hijo2_1.darRaiz1( ) );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz2() );
            assertNull( "El elemento debería ser null", hijo2_1.darRaiz3() );
            assertEquals( "El elemento debería ser 2000", new Integer(2000), hijo2_2.darRaiz1( ) );
            assertEquals( "El elemento debería ser 3000", new Integer(3000), hijo2_2.darRaiz2( ) );
            assertNull( "El elemento debería ser null", hijo2_2.darRaiz3() );
            assertNull( "El hijo debería ser null", hijo2_3);
            assertNull( "El hijo debería ser null", hijo2_4);
            assertNull( "El hijo debería ser null", hijo3 );
            assertNull( "El hijo debería ser null", hijo4 );
            verificarInvariante();
        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }
    
    *//**
     * Verifica que el árbol rojo negro se retorne correctamente
     * 
     *//*
    @SuppressWarnings("unchecked")
    public void testDarRojoNegro()
    {
    	setupEscenario2();
    	
    	Integer[] elementos = new Integer[11];

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Integer( cont );
        }
        
        ArbolRojoNegro<Integer> rn= arbol.darArbolRojoNegro();
        
        NodoRojoNegro<Integer> raiz= rn.darRaiz();
        
        assertEquals("El elemento no es correcto",elementos[5], raiz.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.NEGRO, raiz.darColor() ); 
        NodoRojoNegro<Integer> auxiliar= raiz.darHijoIzquierdo();
        assertEquals("El elemento no es correcto",elementos[3], auxiliar.darRaiz() );
        //assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
        auxiliar= raiz.darHijoDerecho();
        assertEquals("El elemento no es correcto",elementos[7], auxiliar.darRaiz() );
        //assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
        auxiliar= raiz.darHijoIzquierdo().darHijoIzquierdo();
        assertEquals("El elemento no es correcto",elementos[1], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.NEGRO, auxiliar.darColor() );
        auxiliar= raiz.darHijoIzquierdo().darHijoDerecho();
        assertEquals("El elemento no es correcto",elementos[4], auxiliar.darRaiz() );
        //assertEquals("El color no es correcto",NodoRojoNegro.NEGRO, auxiliar.darColor() );
        auxiliar= raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo();
        assertEquals("El elemento no es correcto",elementos[0], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
        auxiliar= raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho();
        assertEquals("El elemento no es correcto",elementos[2], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
        auxiliar= raiz.darHijoDerecho().darHijoIzquierdo();
        assertEquals("El elemento no es correcto",elementos[6], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.NEGRO, auxiliar.darColor() );
        auxiliar= raiz.darHijoDerecho().darHijoDerecho();
        assertEquals("El elemento no es correcto",elementos[9], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.NEGRO, auxiliar.darColor() );
        auxiliar= raiz.darHijoDerecho().darHijoDerecho().darHijoIzquierdo();
        assertEquals("El elemento no es correcto",elementos[8], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
        auxiliar= raiz.darHijoDerecho().darHijoDerecho().darHijoDerecho();
        assertEquals("El elemento no es correcto",elementos[10], auxiliar.darRaiz() );
        assertEquals("El color no es correcto",NodoRojoNegro.ROJO, auxiliar.darColor() );
    }*/
}
