package uniandes.cupi2.collections.avl.test;

import uniandes.cupi2.collections.arbolBinario.ArbolBinario;
import uniandes.cupi2.collections.arbolBinario.NodoArbolBinario;
import uniandes.cupi2.collections.avl.ArbolAVL;
import uniandes.cupi2.collections.avl.ExisteException;
import uniandes.cupi2.collections.avl.NoExisteException;
import uniandes.cupi2.collections.avl.NodoAVL;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase árbol AVL
 */
public class ArbolAVLTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ArbolAVL arbol;

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
        arbol = new ArbolAVL<Long>( );
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
        arbol = new ArbolAVL<Long>( );

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
            verificador = new VerificadorEstructura<Long>( );
        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }

        numeroElementos = 11;
    }

    /**
     * Construye un árbol con 5 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        arbol = new ArbolAVL<Long>( );

        try
        {
            arbol.insertar( new Long( -8 ) );
            arbol.insertar( new Long( -11 ) );
            arbol.insertar( new Long( -12 ) );
            arbol.insertar( new Long( -10 ) );
            arbol.insertar( new Long( -9 ) );
            verificador = new VerificadorEstructura<Long>( );

        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }

        numeroElementos = 5;
    }

    /**
     * Construye un árbol con 1 nodo
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        arbol = new ArbolAVL<Long>( );

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
     * Construye un árbol con 21 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario5( )
    {
        arbol = new ArbolAVL<Long>( );

        try
        {
            arbol.insertar( new Long( 0 ) );
            arbol.insertar( new Long( 1 ) );
            arbol.insertar( new Long( 2 ) );
            arbol.insertar( new Long( 3 ) );
            arbol.insertar( new Long( 4 ) );
            arbol.insertar( new Long( 5 ) );
            arbol.insertar( new Long( 6 ) );
            arbol.insertar( new Long( 7 ) );
            arbol.insertar( new Long( 8 ) );
            arbol.insertar( new Long( 9 ) );
                        
            numeroElementos = 10;
        }
        catch( ExisteException e )
        {            
            e.printStackTrace();
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
     * Verifica que la inserción de elementos se realice correctamente en el árbol AVL
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion1( )
    {
        setupEscenario1( );

        Long[] elementos = new Long[11];
        Long elemento;

        for( int cont = 0; cont < 11; cont++ )
        {
            elementos[ cont ] = new Long( cont );
        }

        // Realizar inserciones

        try
        {
            arbol.insertar( elementos[ 5 ] );
            arbol.insertar( elementos[ 4 ] );
            arbol.insertar( elementos[ 6 ] );
            arbol.insertar( elementos[ 3 ] );
            arbol.insertar( elementos[ 7 ] );

            // Verificar que las alturas de los árboles sean correctas
            NodoAVL raiz = arbol.darRaiz( );
            NodoAVL derecho = raiz.darHijoDerecho( );
            NodoAVL izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( elementos[ 2 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( elementos[ 8 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );
            verificarInvariante( );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            arbol.insertar( elementos[ 1 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( elementos[ 9 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( elementos[ 0 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( elementos[ 10 ] );

            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

            // Verificar que el orden sea correcto
            Iterador iterador = arbol.inorden( );

            int cont = 0;
            while( iterador.haySiguiente( ) )
            {
                elemento = ( Long )iterador.darSiguiente( );

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
     * Verifica que la inserción de elementos se realice correctamente en el árbol AVL obligando a que haya una rotación de la raíz y de los arboles internos
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion2( )
    {
        setupEscenario1( );

        // Realizar inserciones

        try
        {
            arbol.insertar( new Long( -8 ) );
            arbol.insertar( new Long( -11 ) );

            NodoAVL raiz = arbol.darRaiz( );
            NodoAVL derecho = raiz.darHijoDerecho( );
            NodoAVL izquierdo = raiz.darHijoIzquierdo( );

            assertNull( "El hijo derecho no debería existir", derecho );
            assertEquals( "La altura del subarbol debería ser 1", 1, izquierdo.darAltura( ) );
            verificarInvariante( );

            // Verificar que las alturas de los árboles sean correctas
            arbol.insertar( new Long( -12 ) );
            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );
            assertEquals( "La altura del subarbol debería ser 1", 1, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 1", 1, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( new Long( -10 ) );
            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 1", 1, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( new Long( -9 ) );
            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 1, izquierdo.darAltura( ) );
            verificarInvariante( );

            // Verificar que el orden sea correcto
            Iterador iterador = arbol.inorden( );
            Long elemento;

            int cont = -12;
            while( iterador.haySiguiente( ) )
            {
                elemento = ( Long )iterador.darSiguiente( );

                assertEquals( "No se insertaron en orden correcto los elementos", new Long( cont ), elemento );
                cont++;
            }

        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que no se permita la inserción (que se arroje excepción) de elementos que ya existan en el árbol AVL
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion3( )
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
     * Verifica que la inserción de elementos se realice correctamente en el árbol AVL probando una rotación interna
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsercion4( )
    {
        setupEscenario1( );

        // Realizar inserciones

        try
        {
            arbol.insertar( new Long( 30 ) );
            arbol.insertar( new Long( 25 ) );
            arbol.insertar( new Long( 38 ) );
            arbol.insertar( new Long( 24 ) );
            arbol.insertar( new Long( 26 ) );
            arbol.insertar( new Long( 34 ) );
            arbol.insertar( new Long( 40 ) );
            arbol.insertar( new Long( 39 ) );
            arbol.insertar( new Long( 41 ) );
            arbol.insertar( new Long( 33 ) );
            arbol.insertar( new Long( 36 ) );

            // Verificar que las alturas de los árboles sean correctas
            NodoAVL raiz = arbol.darRaiz( );
            NodoAVL derecho = raiz.darHijoDerecho( );
            NodoAVL izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );
            verificarInvariante( );

            // Verificar que el árbol se haya construido correctamente
            assertEquals( "El elemento debería ser 30", new Long( 30 ), raiz.darRaiz( ) ); // Raiz

            assertEquals( "El elemento debería ser 38", new Long( 38 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser 34", new Long( 34 ), derecho.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 33", new Long( 33 ), derecho.darHijoIzquierdo( ).darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 36", new Long( 36 ), derecho.darHijoIzquierdo( ).darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 40", new Long( 40 ), derecho.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 39", new Long( 39 ), derecho.darHijoDerecho( ).darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 41", new Long( 41 ), derecho.darHijoDerecho( ).darHijoDerecho( ).darRaiz( ) );

            assertEquals( "El elemento debería ser 25", new Long( 25 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            assertEquals( "El elemento debería ser 24", new Long( 24 ), izquierdo.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 26", new Long( 26 ), izquierdo.darHijoDerecho( ).darRaiz( ) );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) ); // Verificar la altura
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );
            verificarInvariante( );

            arbol.insertar( new Long( 37 ) ); // Implica rotación interna

            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "El elemento debería ser 34", new Long( 34 ), raiz.darRaiz( ) ); // Raiz

            assertEquals( "El elemento debería ser 38", new Long( 38 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser 36", new Long( 36 ), derecho.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 37", new Long( 37 ), derecho.darHijoIzquierdo( ).darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 40", new Long( 40 ), derecho.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 41", new Long( 41 ), derecho.darHijoDerecho( ).darHijoDerecho( ).darRaiz( ) );

            assertEquals( "El elemento debería ser 30", new Long( 30 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            assertEquals( "El elemento debería ser 25", new Long( 25 ), izquierdo.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 24", new Long( 24 ), izquierdo.darHijoIzquierdo( ).darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 26", new Long( 26 ), izquierdo.darHijoIzquierdo( ).darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 33", new Long( 33 ), izquierdo.darHijoDerecho( ).darRaiz( ) );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que la búsqueda de un elemento se realice correctamente en el árbol AVL
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
     * Verifica que se retorne el elemento mayor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMayor( )
    {
        setupEscenario2( );

        Long mayor = ( Long )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( 10 ), mayor );

        // Verificar que se retorne null cuando el árbol no tiene elementos
        setupEscenario1( );
        mayor = ( Long )arbol.darMayor( );

        assertNull( "El elemento mayor no es el correcto", mayor );

        // Verificar el método mayor con un solo elemento en el árbol
        setupEscenario3( );

        mayor = ( Long )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( -8 ), mayor );

        setupEscenario4( );

        mayor = ( Long )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( -800 ), mayor );
    }

    /**
     * Verifica que se retorne el elemento menor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMenor( )
    {
        setupEscenario2( );

        Long menor = ( Long )arbol.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Long( 0 ), menor );

        // Verificar que se retorne null cuando el árbol no tiene elementos
        setupEscenario1( );
        menor = ( Long )arbol.darMenor( );

        assertNull( "El elemento menor no es el correcto", menor );

        setupEscenario3( );

        menor = ( Long )arbol.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Long( -12 ), menor );

        setupEscenario4( );

        menor = ( Long )arbol.darMenor( );

        // Verificar el método menor con un solo elemento en el árbol

        assertEquals( "El elemento mayor no es el correcto", new Long( -800 ), menor );
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
        setupEscenario1( );

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
            verificarInvariante( );

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
     * Verifica que se elimine correctamente la raíz del árbol
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        // Eliminar la raiz y verificar que el árbol haya quedado bien construido
        setupEscenario2( );
        try
        {
            arbol.eliminar( new Long( 5 ) ); // Rotación izquierda
            NodoAVL raiz = arbol.darRaiz( );
            NodoAVL derecho = raiz.darHijoDerecho( );
            NodoAVL izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) ); // Verificar la altura
            assertEquals( "La altura del subarbol debería ser 3", 3, izquierdo.darAltura( ) );
            verificarInvariante( );

            // Verificar la estructura del arbol
            assertEquals( "El elemento debería ser 4", new Long( 4 ), raiz.darRaiz( ) ); // Raiz
            assertEquals( "El elemento debería ser 7", new Long( 7 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser 6", new Long( 6 ), derecho.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 9", new Long( 9 ), derecho.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 8", new Long( 8 ), derecho.darHijoDerecho( ).darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 8", new Long( 10 ), derecho.darHijoDerecho( ).darHijoDerecho( ).darRaiz( ) );

            assertEquals( "El elemento debería ser 1", new Long( 1 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            assertEquals( "El elemento debería ser 7", new Long( 0 ), izquierdo.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 3", new Long( 3 ), izquierdo.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 2", new Long( 2 ), izquierdo.darHijoDerecho( ).darHijoIzquierdo( ).darRaiz( ) );

            Iterador iterador = arbol.inorden( );
            Long elemento;

            int cont = 0;
            while( iterador.haySiguiente( ) )
            {
                elemento = ( Long )iterador.darSiguiente( );

                if( cont == 5 )
                {
                    cont++;
                }

                assertEquals( "Los elementos no se eliminaron de forma correcta", new Long( cont ), elemento );
                cont++;
            }

            arbol.eliminar( new Long( 4 ) );

            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );

            // Verificar la estructura del arbol
            assertEquals( "El elemento debería ser 3", new Long( 3 ), raiz.darRaiz( ) ); // Raiz
            assertEquals( "El elemento debería ser 7", new Long( 7 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser 6", new Long( 6 ), derecho.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 9", new Long( 9 ), derecho.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 8", new Long( 8 ), derecho.darHijoDerecho( ).darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 10", new Long( 10 ), derecho.darHijoDerecho( ).darHijoDerecho( ).darRaiz( ) );

            assertEquals( "El elemento debería ser 1", new Long( 1 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            assertEquals( "El elemento debería ser 0", new Long( 0 ), izquierdo.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 2", new Long( 2 ), izquierdo.darHijoDerecho( ).darRaiz( ) );
            verificarInvariante( );

            arbol.eliminar( new Long( 6 ) );

            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 3", 3, derecho.darAltura( ) );
            assertEquals( "La altura del subarbol debería ser 2", 2, izquierdo.darAltura( ) );

            assertEquals( "El elemento debería ser 3", new Long( 3 ), raiz.darRaiz( ) ); // Raiz
            assertEquals( "El elemento debería ser 7", new Long( 9 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser 6", new Long( 7 ), derecho.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 6", new Long( 8 ), derecho.darHijoIzquierdo( ).darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 9", new Long( 10 ), derecho.darHijoDerecho( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 1", new Long( 1 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            assertEquals( "El elemento debería ser 7", new Long( 0 ), izquierdo.darHijoIzquierdo( ).darRaiz( ) );
            assertEquals( "El elemento debería ser 2", new Long( 2 ), izquierdo.darHijoDerecho( ).darRaiz( ) );

            // Verificar que el peso del árbol sea correcto después de las eliminaciones
            assertEquals( "El peso debería ser", numeroElementos - 3, arbol.darPeso( ) );
            verificarInvariante( );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que se elimine correctamente los nodos internos del árbol
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar4( )
    {
        setupEscenario3( );
        try
        {
            arbol.eliminar( new Long( -9 ) );
            NodoAVL raiz = arbol.darRaiz( );
            NodoAVL derecho = raiz.darHijoDerecho( );
            NodoAVL izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 2", 2, derecho.darAltura( ) ); // Verificar la altura
            assertEquals( "La altura del subarbol debería ser 1", 1, izquierdo.darAltura( ) );

            // Verificar la estructura del arbol
            assertEquals( "El elemento debería ser -11", new Long( -11 ), raiz.darRaiz( ) ); // Raiz
            assertEquals( "El elemento debería ser -10", new Long( -10 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser -8", new Long( -8 ), derecho.darHijoDerecho( ).darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser -12", new Long( -12 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo
            verificarInvariante( );

            arbol.eliminar( new Long( -12 ) );
            raiz = arbol.darRaiz( );
            derecho = raiz.darHijoDerecho( );
            izquierdo = raiz.darHijoIzquierdo( );

            assertEquals( "La altura del subarbol debería ser 1", 1, derecho.darAltura( ) ); // Verificar la altura
            assertEquals( "La altura del subarbol debería ser 1", 1, izquierdo.darAltura( ) );

            // Verificar la estructura del arbol
            assertEquals( "El elemento debería ser -10", new Long( -10 ), raiz.darRaiz( ) ); // Raiz
            assertEquals( "El elemento debería ser -8", new Long( -8 ), derecho.darRaiz( ) ); // Subarbol derecho
            assertEquals( "El elemento debería ser -11", new Long( -11 ), izquierdo.darRaiz( ) ); // Subarbol izquierdo

            // Verificar que el peso del árbol sea correcto después de las eliminaciones
            assertEquals( "El peso debería ser", numeroElementos - 2, arbol.darPeso( ) );
            verificarInvariante( );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }
    
    /**
     * Verifica que el recorrido por niveles se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarRecorridoPorNiveles( )
    {
        setupEscenario5( );

        Iterador it = arbol.darRecorridoNiveles( );

        // Verificar que el recorrido por niveles sea correcto        
        Long elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 3 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 1 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 7 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 0 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 2 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 5 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 8 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 4 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 6 ), elemento );
        
        elemento = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( 9 ), elemento );
               
        setupEscenario1( );
        it = arbol.darRecorridoNiveles( );
        assertFalse( "El recorrido por niveles no se realizó de forma correcta", it.haySiguiente( ) );
    } 
}
