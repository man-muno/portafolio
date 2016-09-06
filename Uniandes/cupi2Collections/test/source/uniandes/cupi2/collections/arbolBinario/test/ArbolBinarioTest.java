package uniandes.cupi2.collections.arbolBinario.test;

import uniandes.cupi2.collections.arbolBinario.ArbolBinario;
import uniandes.cupi2.collections.arbolBinario.NoExisteException;
import uniandes.cupi2.collections.arbolBinario.NodoArbolBinario;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ArbolBinario
 */
public class ArbolBinarioTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ArbolBinario arbol;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un árbol con 21 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        arbol = new ArbolBinario<Long>( );

        NodoArbolBinario<Long> nodo0 = new NodoArbolBinario<Long>( new Long( 0 ) );
        NodoArbolBinario<Long> nodo1 = new NodoArbolBinario<Long>( new Long( 1 ) );
        NodoArbolBinario<Long> nodo2 = new NodoArbolBinario<Long>( new Long( 2 ) );
        NodoArbolBinario<Long> nodo3 = new NodoArbolBinario<Long>( new Long( 3 ) );
        NodoArbolBinario<Long> nodo4 = new NodoArbolBinario<Long>( new Long( 4 ) );
        NodoArbolBinario<Long> nodo5 = new NodoArbolBinario<Long>( new Long( 5 ) );
        NodoArbolBinario<Long> nodo6 = new NodoArbolBinario<Long>( new Long( 6 ) );
        NodoArbolBinario<Long> nodo7 = new NodoArbolBinario<Long>( new Long( 7 ) );
        NodoArbolBinario<Long> nodo8 = new NodoArbolBinario<Long>( new Long( 8 ) );
        NodoArbolBinario<Long> nodo9 = new NodoArbolBinario<Long>( new Long( 9 ) );
        NodoArbolBinario<Long> nodo10 = new NodoArbolBinario<Long>( new Long( 10 ) );
        NodoArbolBinario<Long> nodo11 = new NodoArbolBinario<Long>( new Long( 11 ) );
        NodoArbolBinario<Long> nodo12 = new NodoArbolBinario<Long>( new Long( 12 ) );
        NodoArbolBinario<Long> nodo13 = new NodoArbolBinario<Long>( new Long( 13 ) );
        NodoArbolBinario<Long> nodo14 = new NodoArbolBinario<Long>( new Long( 14 ) );
        NodoArbolBinario<Long> nodo15 = new NodoArbolBinario<Long>( new Long( 15 ) );
        NodoArbolBinario<Long> nodo16 = new NodoArbolBinario<Long>( new Long( 16 ) );
        NodoArbolBinario<Long> nodo17 = new NodoArbolBinario<Long>( new Long( 17 ) );
        NodoArbolBinario<Long> nodo18 = new NodoArbolBinario<Long>( new Long( 18 ) );
        NodoArbolBinario<Long> nodo19 = new NodoArbolBinario<Long>( new Long( 19 ) );
        NodoArbolBinario<Long> nodo20 = new NodoArbolBinario<Long>( new Long( 20 ) );

        arbol.definirRaiz( nodo0 );

        // Definir los hijos de nodo0
        nodo0.encadenarIzquierdo( nodo1 );
        nodo0.encadenarDerecho( nodo2 );

        // Definir los hijos de nodo1
        nodo1.encadenarIzquierdo( nodo3 );
        nodo1.encadenarDerecho( nodo4 );

        // Definir los hijos de nodo2
        nodo2.encadenarIzquierdo( nodo5 );
        nodo2.encadenarDerecho( nodo6 );

        // Definir los hijos de nodo3
        nodo3.encadenarIzquierdo( nodo7 );
        nodo3.encadenarDerecho( nodo8 );

        // Definir los hijos de nodo4
        nodo4.encadenarIzquierdo( nodo9 );
        nodo4.encadenarDerecho( nodo10 );

        // Definir los hijos de nodo5
        nodo5.encadenarIzquierdo( nodo11 );
        nodo5.encadenarDerecho( nodo12 );

        // Definir los hijos de nodo6
        nodo6.encadenarIzquierdo( nodo13 );
        nodo6.encadenarDerecho( nodo14 );

        // Definir los hijos de nodo7
        nodo7.encadenarIzquierdo( nodo15 );
        nodo7.encadenarDerecho( nodo16 );

        // Definir los hijos de nodo8
        nodo8.encadenarIzquierdo( nodo17 );
        nodo8.encadenarDerecho( nodo18 );

        // Definir los hijos de nodo9
        nodo9.encadenarIzquierdo( nodo19 );
        nodo9.encadenarDerecho( nodo20 );
        numeroElementos = 21;

    }

    /**
     * Construye un árbol vacio de enteros
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        arbol = new ArbolBinario<Integer>( );
        numeroElementos = 0;
    }

    /**
     * Construye un árbol de enteros con tan solo la raíz
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        arbol = new ArbolBinario<Integer>( );
        NodoArbolBinario nodo = new NodoArbolBinario<Integer>( new Integer( 10 ) );
        arbol.definirRaiz( nodo );
        numeroElementos = 1;
    }

    /**
     * Construye un árbol con 8 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        arbol = new ArbolBinario<Long>( );

        NodoArbolBinario<Long> nodo0 = new NodoArbolBinario<Long>( new Long( 0 ) );
        NodoArbolBinario<Long> nodo1 = new NodoArbolBinario<Long>( new Long( 1 ) );
        NodoArbolBinario<Long> nodo2 = new NodoArbolBinario<Long>( new Long( 2 ) );
        NodoArbolBinario<Long> nodo3 = new NodoArbolBinario<Long>( new Long( 3 ) );
        NodoArbolBinario<Long> nodo4 = new NodoArbolBinario<Long>( new Long( 4 ) );
        NodoArbolBinario<Long> nodo5 = new NodoArbolBinario<Long>( new Long( 5 ) );
        NodoArbolBinario<Long> nodo6 = new NodoArbolBinario<Long>( new Long( 6 ) );
        NodoArbolBinario<Long> nodo7 = new NodoArbolBinario<Long>( new Long( 7 ) );

        arbol.definirRaiz( nodo0 );

        // Definir los hijos de nodo0
        nodo0.encadenarIzquierdo( nodo1 );
        nodo0.encadenarDerecho( nodo2 );

        // Definir los hijos de nodo1
        nodo1.encadenarIzquierdo( nodo3 );
        nodo1.encadenarDerecho( nodo4 );

        // Definir los hijos de nodo2
        nodo2.encadenarIzquierdo( nodo5 );
        nodo2.encadenarDerecho( nodo6 );

        // Definir los hijos de nodo3
        nodo3.encadenarIzquierdo( nodo7 );

        numeroElementos = 8;
    }

    /**
     * Construye un árbol con 8 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario5( )
    {
        arbol = new ArbolBinario<Long>( );

        NodoArbolBinario<Long> nodo0 = new NodoArbolBinario<Long>( new Long( 0 ) );
        NodoArbolBinario<Long> nodo1 = new NodoArbolBinario<Long>( new Long( 1 ) );
        NodoArbolBinario<Long> nodo2 = new NodoArbolBinario<Long>( new Long( 2 ) );
        NodoArbolBinario<Long> nodo3 = new NodoArbolBinario<Long>( new Long( 3 ) );
        NodoArbolBinario<Long> nodo4 = new NodoArbolBinario<Long>( new Long( 4 ) );
        NodoArbolBinario<Long> nodo5 = new NodoArbolBinario<Long>( new Long( 5 ) );
        NodoArbolBinario<Long> nodo6 = new NodoArbolBinario<Long>( new Long( 6 ) );
        NodoArbolBinario<Long> nodo7 = new NodoArbolBinario<Long>( new Long( 7 ) );
        NodoArbolBinario<Long> nodo8 = new NodoArbolBinario<Long>( new Long( 8 ) );

        arbol.definirRaiz( nodo0 );

        // Definir los hijos de nodo0
        nodo0.encadenarIzquierdo( nodo1 );
        nodo0.encadenarDerecho( nodo2 );

        // Definir los hijos de nodo1
        nodo1.encadenarIzquierdo( nodo3 );
        nodo1.encadenarDerecho( nodo4 );

        // Definir los hijos de nodo2
        nodo2.encadenarIzquierdo( nodo5 );
        nodo2.encadenarDerecho( nodo6 );

        // Definir los hijos de nodo3
        nodo3.encadenarIzquierdo( nodo7 );

        // Definir hijos nodo7
        nodo7.encadenarIzquierdo( nodo8 );

        numeroElementos = 9;
    }

    /**
     * Construye un árbol con 8 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario6( )
    {
        arbol = new ArbolBinario<Long>( );

        NodoArbolBinario<Long> nodo0 = new NodoArbolBinario<Long>( new Long( 0 ) );
        NodoArbolBinario<Long> nodo1 = new NodoArbolBinario<Long>( new Long( 1 ) );
        NodoArbolBinario<Long> nodo2 = new NodoArbolBinario<Long>( new Long( 2 ) );
        NodoArbolBinario<Long> nodo3 = new NodoArbolBinario<Long>( new Long( 3 ) );
        NodoArbolBinario<Long> nodo4 = new NodoArbolBinario<Long>( new Long( 4 ) );
        NodoArbolBinario<Long> nodo5 = new NodoArbolBinario<Long>( new Long( 5 ) );
        NodoArbolBinario<Long> nodo6 = new NodoArbolBinario<Long>( new Long( 6 ) );
        NodoArbolBinario<Long> nodo7 = new NodoArbolBinario<Long>( new Long( 7 ) );
        NodoArbolBinario<Long> nodo8 = new NodoArbolBinario<Long>( new Long( 8 ) );
        NodoArbolBinario<Long> nodo9 = new NodoArbolBinario<Long>( new Long( 9 ) );

        arbol.definirRaiz( nodo0 );

        // Definir los hijos de nodo0
        nodo0.encadenarIzquierdo( nodo1 );
        nodo0.encadenarDerecho( nodo2 );

        // Definir los hijos de nodo1
        nodo1.encadenarIzquierdo( nodo3 );
        nodo1.encadenarDerecho( nodo4 );

        // Definir los hijos de nodo2
        nodo2.encadenarIzquierdo( nodo5 );
        nodo2.encadenarDerecho( nodo6 );

        // Definir los hijos de nodo3
        nodo3.encadenarIzquierdo( nodo7 );

        // Definir los hihos de nodo6
        nodo6.encadenarDerecho( nodo8 );

        // Definir los hihos de nodo8
        nodo8.encadenarDerecho( nodo9 );

        numeroElementos = 10;
    }

    /**
     * Verifica que los niveles del árbol se retornen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarNivel1( )
    {
        setupEscenario1( );

        Iterador it = arbol.darNivel( 0 );

        int cont = 0;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        it = arbol.darNivel( 1 );

        cont = 1;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        it = arbol.darNivel( 2 );

        cont = 3;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        it = arbol.darNivel( 3 );
        cont = 7;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        it = arbol.darNivel( 4 );
        cont = 15;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos del arbol
        assertEquals( "No se recuperaron todos los elementos al recorrer el árbol por niveles", numeroElementos, cont );

    }

    /**
     * Verifica que el iterador retornado sea vacio para el caso de pedir un nivel que no exista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarNivel2( )
    {
        setupEscenario1( );

        Iterador it = arbol.darNivel( -1 );

        assertFalse( "No se debería haber retornado nada", it.haySiguiente( ) );

        it = arbol.darNivel( 5 );

        assertFalse( "No se debería haber retornado nada", it.haySiguiente( ) );

        setupEscenario2( );

        it = arbol.darNivel( -1 );

        assertFalse( "No se debería haber retornado nada", it.haySiguiente( ) );

        it = arbol.darNivel( 0 );

        assertFalse( "No se debería haber retornado nada", it.haySiguiente( ) );
    }

    /**
     * Verifica que las hojas sean contadas de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testContarHojas( )
    {
        setupEscenario1( );

        int hojas = arbol.contarHojas( );

        assertEquals( "El número de hojas no es correcto", 11, hojas );

        setupEscenario2( );

        hojas = arbol.contarHojas( );

        assertEquals( "El número de hojas no es correcto", 0, hojas );

        setupEscenario3( );

        hojas = arbol.contarHojas( );

        assertEquals( "El número de hojas no es correcto", 1, hojas );
    }

    /**
     * Verifica que la búsqueda de un elemento se esté realizando de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscarElemento1( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Long busqueda = new Long( cont );
            Long elemento = ( Long )arbol.buscar( busqueda );

            // Verificar que los elementos sean iguales
            assertEquals( "Los elementos no son iguales", busqueda, elemento );
        }
    }

    /**
     * Verifica que se retorne null en la búsqueda de un elemento inexistente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscarElemento2( )
    {
        setupEscenario1( );

        Long elemento;

        for( int cont = numeroElementos + 1; cont < numeroElementos * 2; cont++ )
        {
            Long busqueda = new Long( cont );
            elemento = ( Long )arbol.buscar( busqueda );

            // Verificar que los elementos sean iguales
            assertNull( "No se debio retornar elemento alguno", elemento );
        }

        setupEscenario3( );
        elemento = ( Long )arbol.buscar( new Long( 25 ) );
        assertNull( "No se debio retornar elemento alguno", elemento );
    }

    /**
     * Verifica que la altura del árbol se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarAltura( )
    {
        setupEscenario1( );

        int altura = arbol.darAltura( );

        assertEquals( "la altura retornada no es correcta", 5, altura );

        setupEscenario2( );

        altura = arbol.darAltura( );
        assertEquals( "la altura retornada no es correcta", 0, altura );

        setupEscenario3( );
        altura = arbol.darAltura( );
        assertEquals( "la altura retornada no es correcta", 1, altura );

    }

    /**
     * Verifica que se retorne correctamente el camino hacia un elemento
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarCamino1( )
    {
        setupEscenario1( );

        try
        {
            // Verificar el camino a 0
            Long elemento0 = new Long( 0 );
            Iterador it;
            it = arbol.darCamino( elemento0 );
            assertTrue( "No se retorno el camino correctamente", it.haySiguiente( ) );

            Long resul = ( Long )it.darSiguiente( );

            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 1
            Long elemento1 = new Long( 1 );
            it = arbol.darCamino( elemento1 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 2
            Long elemento2 = new Long( 2 );
            it = arbol.darCamino( elemento2 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 3
            Long elemento3 = new Long( 3 );
            it = arbol.darCamino( elemento3 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 4
            Long elemento4 = new Long( 4 );
            it = arbol.darCamino( elemento4 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento4, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 5
            Long elemento5 = new Long( 5 );
            it = arbol.darCamino( elemento5 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento5, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 6
            Long elemento6 = new Long( 6 );
            it = arbol.darCamino( elemento6 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento6, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 7
            Long elemento7 = new Long( 7 );
            it = arbol.darCamino( elemento7 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento7, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 7
            Long elemento8 = new Long( 8 );
            it = arbol.darCamino( elemento8 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento8, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 9
            Long elemento9 = new Long( 9 );
            it = arbol.darCamino( elemento9 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento4, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento9, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 10
            Long elemento10 = new Long( 10 );
            it = arbol.darCamino( elemento10 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento4, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento10, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 11
            Long elemento11 = new Long( 11 );
            it = arbol.darCamino( elemento11 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento5, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento11, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 12
            Long elemento12 = new Long( 12 );
            it = arbol.darCamino( elemento12 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento5, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento12, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 13
            Long elemento13 = new Long( 13 );
            it = arbol.darCamino( elemento13 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento6, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento13, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 14
            Long elemento14 = new Long( 14 );
            it = arbol.darCamino( elemento14 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento2, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento6, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento14, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 15
            Long elemento15 = new Long( 15 );
            it = arbol.darCamino( elemento15 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento7, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento15, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 16
            Long elemento16 = new Long( 16 );
            it = arbol.darCamino( elemento16 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento7, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento16, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 17
            Long elemento17 = new Long( 17 );
            it = arbol.darCamino( elemento17 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento8, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento17, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 18
            Long elemento18 = new Long( 18 );
            it = arbol.darCamino( elemento18 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento3, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento8, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento18, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 19
            Long elemento19 = new Long( 19 );
            it = arbol.darCamino( elemento19 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento4, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento9, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento19, resul );
            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar el camino a 20
            Long elemento20 = new Long( 20 );
            it = arbol.darCamino( elemento20 );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento0, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento1, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento4, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento9, resul );
            resul = ( Long )it.darSiguiente( );
            assertEquals( "No se retorno el camino correctamente", elemento20, resul );

            assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

            // Verificar que no queden más elementos en el iterador

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }

    }

    /**
     * Verifica que se arroje excepción en el caso de pedir camino de elementos inexistenes
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarCamino2( )
    {
        setupEscenario1( );
        Iterador it = null;

        try
        {
            it = arbol.darCamino( new Long( -1 ) );

            assertNull( "No se debio haber retornado camino sobre el elemento especificado", it );
        }
        catch( NoExisteException e )
        {
            assertNull( "No se debio haber retornado camino sobre el elemento especificado", it );

        }
    }

    /**
     * Verifica que el recorrido en inorden se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarInorden( )
    {
        setupEscenario1( );
        Iterador it = arbol.darInorden( );

        // Verificar que el recorrido se haya realizado correctamente
        Long elemento = new Long( 15 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 0 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 12 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el inorden por el método toString
        String resp = "[" + numeroElementos + "]:15-7-16-3-17-8-18-1-19-9-20-4-10-0-11-5-12-2-13-6-14-";
        assertEquals( "El recorrido no se realizó correctamente", resp, it.toString( ) );

        setupEscenario2( );
        it = arbol.darInorden( );
        assertFalse( "El recorrido no se realizó correctamente", it.haySiguiente( ) );
    }

    /**
     * Verifica que el recorrido en inorden iterativo se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarInordenIterativo( )
    {
        setupEscenario1( );
        Iterador it = arbol.darInordenIterativo( );

        // Verificar que el recorrido se haya realizado correctamente
        Long elemento = new Long( 15 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 0 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 12 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el inorden por el método toString
        String resp = "[" + numeroElementos + "]:15-7-16-3-17-8-18-1-19-9-20-4-10-0-11-5-12-2-13-6-14-";
        assertEquals( "El recorrido no se realizó correctamente", resp, it.toString( ) );

        setupEscenario2( );
        it = arbol.darInordenIterativo( );
        assertFalse( "El recorrido no se realizó correctamente", it.haySiguiente( ) );
    }

    /**
     * Verifica que el peso del árbol se este retornando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPeso( )
    {
        setupEscenario1( );
        int peso = arbol.darPeso( );
        assertEquals( "El peso del árbol no es correcto", numeroElementos, peso );

        setupEscenario2( );

        peso = arbol.darPeso( );
        assertEquals( "El peso del árbol no es correcto", numeroElementos, peso );

        setupEscenario3( );
        peso = arbol.darPeso( );
        assertEquals( "El peso del árbol no es correcto", numeroElementos, peso );
    }

    /**
     * Verifica que el recorrido en inorden iterativo se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPostorden( )
    {
        setupEscenario1( );
        Iterador it = arbol.darPostorden( );

        // Verificar que el recorrido se haya realizado correctamente
        Long elemento = new Long( 15 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 12 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 0 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el postorden por el método toString
        String resp = "[" + numeroElementos + "]:15-16-7-17-18-8-3-19-20-9-10-4-1-11-12-5-13-14-6-2-0-";
        assertEquals( "El recorrido no se realizó correctamente", resp, it.toString( ) );

        setupEscenario2( );
        it = arbol.darPostorden( );
        assertFalse( "El recorrido no se realizó correctamente", it.haySiguiente( ) );
    }

    /**
     * Verifica que el recorrido en inorden iterativo se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPreorden( )
    {
        setupEscenario1( );
        Iterador it = arbol.darPreorden( );

        // Verificar que el recorrido se haya realizado correctamente
        Long elemento = new Long( 0 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 15 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 12 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el preorden por el método toString
        String resp = "[" + numeroElementos + "]:0-1-3-7-15-16-8-17-18-4-9-19-20-10-2-5-11-12-6-13-14-";
        assertEquals( "El recorrido no se realizó correctamente", resp, it.toString( ) );

        setupEscenario2( );
        it = arbol.darPreorden( );
        assertFalse( "El recorrido no se realizó correctamente", it.haySiguiente( ) );

    }

    /**
     * Verifica que el recorrido por niveles se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarRecorridoPorNiveles( )
    {
        setupEscenario1( );

        Iterador it = arbol.darRecorridoNiveles( );

        // Verificar que el recorrido por niveles sea correcto
        int cont = 0;

        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "El recorrido por niveles no se realizó de forma correcta", new Long( cont ), elemento );
            cont++;
        }

        // Verificar que se haya recorrido todos los nodos
        assertEquals( "No se recorrieron todos los nodos", numeroElementos, cont );

        setupEscenario2( );
        it = arbol.darRecorridoNiveles( );
        assertFalse( "El recorrido por niveles no se realizó de forma correcta", it.haySiguiente( ) );
    }

    /**
     * Verifica que se retorne correctamente el siguiente en inorden
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarSiguienteInorden1( )
    {
        setupEscenario1( );

        try
        {
            Long elemento = new Long( 15 );

            Long respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 7 ), respuesta );

            elemento = new Long( 7 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 16 ), respuesta );

            elemento = new Long( 16 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 3 ), respuesta );

            elemento = new Long( 3 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 17 ), respuesta );

            elemento = new Long( 17 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 8 ), respuesta );

            elemento = new Long( 8 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 18 ), respuesta );

            elemento = new Long( 18 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 1 ), respuesta );

            elemento = new Long( 1 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 19 ), respuesta );

            elemento = new Long( 19 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 9 ), respuesta );

            elemento = new Long( 9 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 20 ), respuesta );

            elemento = new Long( 20 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 4 ), respuesta );

            elemento = new Long( 4 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 10 ), respuesta );

            elemento = new Long( 10 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 0 ), respuesta );

            elemento = new Long( 0 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 11 ), respuesta );

            elemento = new Long( 11 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 5 ), respuesta );

            elemento = new Long( 5 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 12 ), respuesta );

            elemento = new Long( 12 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 2 ), respuesta );

            elemento = new Long( 2 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 13 ), respuesta );

            elemento = new Long( 13 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 6 ), respuesta );

            elemento = new Long( 6 );

            respuesta = ( Long )arbol.darSiguienteInorden( elemento );

            assertEquals( "No se retorno el elemento correcto", new Long( 14 ), respuesta );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que se arroje excepción el siguiente en inorden cuando se da un elemento que no existe o que no tiene siguiente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarSiguienteInorden2( )
    {
        setupEscenario1( );
        Long elemento = new Long( 14 );
        Long respuesta = null;
        try
        {
            respuesta = ( Long )arbol.darSiguienteInorden( elemento );
        }
        catch( NoExisteException e )
        {

            assertNull( "No se debio haber retornado nada", respuesta );
            elemento = new Long( -1 );
            try
            {
                respuesta = ( Long )arbol.darSiguienteInorden( elemento );
            }
            catch( NoExisteException e1 )
            {
                assertNull( "No se debio haber retornado nada", respuesta );
            }
        }
    }

    /**
     * Verifica que los tamaños de los niveles se retornen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarTamanoNivel( )
    {
        setupEscenario1( );
        int tamEsperado;
        int tamNivel;

        for( int cont = 0; cont < 4; cont++ )
        {
            tamNivel = arbol.darTamanoNivel( cont );
            tamEsperado = ( int )Math.pow( 2, cont );
            assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );
        }

        tamNivel = arbol.darTamanoNivel( 4 );
        tamEsperado = 6;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

        // Verificar que se retorne 0 para el tamaño de niveles que no existan
        tamNivel = arbol.darTamanoNivel( -1 );
        tamEsperado = 0;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

    }

    /**
     * Verifica que se indica bien cuando un árbol es completo o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEsCompleto( )
    {
        setupEscenario1( );

        assertTrue( "El árbol debería ser completo", arbol.esCompleto( ) );

        setupEscenario2( );

        assertFalse( "El árbol no debería ser completo", arbol.esCompleto( ) );

        setupEscenario3( );

        assertTrue( "El árbol debería ser completo", arbol.esCompleto( ) );

        setupEscenario4( );

        assertFalse( "El árbol no debería ser completo", arbol.esCompleto( ) );

    }

    /**
     * Verifica que se indique correctamente cuando los árboles están balanceados por altura
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEsBalanceadoPorAltura( )
    {
        setupEscenario1( );

        assertTrue( "El árbol debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );

        setupEscenario2( );

        assertFalse( "El árbol no debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );

        setupEscenario3( );

        assertTrue( "El árbol debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );

        setupEscenario4( );

        assertTrue( "El árbol debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );

        setupEscenario5( );

        assertFalse( "El árbol no debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );

        setupEscenario6( );

        assertFalse( "El árbol no debería ser balanceado por altura", arbol.estaBalanceadoPorAltura( ) );
    }

    /**
     * Verifica que se indique correctamente cuando los árboles están balanceados por peso
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEsBalanceadoPorPeso( )
    {
        setupEscenario1( );

        assertFalse( "El árbol debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );

        setupEscenario2( );

        assertFalse( "El árbol no debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );

        setupEscenario3( );

        assertTrue( "El árbol debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );

        setupEscenario4( );

        assertTrue( "El árbol debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );

        setupEscenario5( );

        assertFalse( "El árbol no debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );

        setupEscenario6( );

        assertFalse( "El árbol no debería ser balanceado por peso", arbol.estaBalanceadoPorPeso( ) );
    }

    /**
     * Verifica que se indica bien cuando un árbol está casi lleno o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaCasiLleno( )
    {
        setupEscenario1( );

        assertTrue( "El árbol debería estar casi lleno", arbol.estaCasiLleno( ) );

        setupEscenario2( );

        assertTrue( "El árbol debería estar casi lleno", arbol.estaCasiLleno( ) );

        setupEscenario3( );

        assertTrue( "El árbol debería estar casi lleno", arbol.estaCasiLleno( ) );

        setupEscenario4( );

        assertTrue( "El árbol debería estar casi lleno", arbol.estaCasiLleno( ) );

        setupEscenario5( );

        assertFalse( "El árbol no debería estar casi lleno", arbol.estaCasiLleno( ) );

        setupEscenario6( );

        assertFalse( "El árbol no debería estar casi lleno", arbol.estaCasiLleno( ) );
    }

    /**
     * Verifica que se indica bien cuando un árbol está lleno o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaLleno( )
    {
        setupEscenario1( );

        assertFalse( "El árbol no debería estar lleno", arbol.estaLleno( ) );

        setupEscenario2( );

        assertTrue( "El árbol debería estar lleno", arbol.estaLleno( ) );

        setupEscenario3( );

        assertTrue( "El árbol debería estar lleno", arbol.estaLleno( ) );

        setupEscenario4( );

        assertFalse( "El árbol no debería estar lleno", arbol.estaLleno( ) );

        setupEscenario5( );

        assertFalse( "El árbol no debería estar lleno", arbol.estaLleno( ) );

        setupEscenario6( );

        assertFalse( "El árbol no debería estar lleno", arbol.estaLleno( ) );
    }
}
