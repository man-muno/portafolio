package uniandes.cupi2.collections.arbolNArio.test;

import uniandes.cupi2.collections.arbol.arbolNArio.ArbolNArio;
import uniandes.cupi2.collections.arbol.arbolNArio.NodoArbolNArio;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ArbolNArio
 */
public class ArbolNArioTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ArbolNArio arbol;

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
        arbol = new ArbolNArio<Long>( );

        NodoArbolNArio<Long> nodo0 = new NodoArbolNArio<Long>( new Long( 0 ) );
        NodoArbolNArio<Long> nodo1 = new NodoArbolNArio<Long>( new Long( 1 ) );
        NodoArbolNArio<Long> nodo2 = new NodoArbolNArio<Long>( new Long( 2 ) );
        NodoArbolNArio<Long> nodo3 = new NodoArbolNArio<Long>( new Long( 3 ) );
        NodoArbolNArio<Long> nodo4 = new NodoArbolNArio<Long>( new Long( 4 ) );
        NodoArbolNArio<Long> nodo5 = new NodoArbolNArio<Long>( new Long( 5 ) );
        NodoArbolNArio<Long> nodo6 = new NodoArbolNArio<Long>( new Long( 6 ) );
        NodoArbolNArio<Long> nodo7 = new NodoArbolNArio<Long>( new Long( 7 ) );
        NodoArbolNArio<Long> nodo8 = new NodoArbolNArio<Long>( new Long( 8 ) );
        NodoArbolNArio<Long> nodo9 = new NodoArbolNArio<Long>( new Long( 9 ) );
        NodoArbolNArio<Long> nodo10 = new NodoArbolNArio<Long>( new Long( 10 ) );
        NodoArbolNArio<Long> nodo11 = new NodoArbolNArio<Long>( new Long( 11 ) );
        NodoArbolNArio<Long> nodo12 = new NodoArbolNArio<Long>( new Long( 12 ) );
        NodoArbolNArio<Long> nodo13 = new NodoArbolNArio<Long>( new Long( 13 ) );
        NodoArbolNArio<Long> nodo14 = new NodoArbolNArio<Long>( new Long( 14 ) );
        NodoArbolNArio<Long> nodo15 = new NodoArbolNArio<Long>( new Long( 15 ) );
        NodoArbolNArio<Long> nodo16 = new NodoArbolNArio<Long>( new Long( 16 ) );
        NodoArbolNArio<Long> nodo17 = new NodoArbolNArio<Long>( new Long( 17 ) );
        NodoArbolNArio<Long> nodo18 = new NodoArbolNArio<Long>( new Long( 18 ) );
        NodoArbolNArio<Long> nodo19 = new NodoArbolNArio<Long>( new Long( 19 ) );
        NodoArbolNArio<Long> nodo20 = new NodoArbolNArio<Long>( new Long( 20 ) );

        // La raiz del arbol es el nodo0
        arbol.definirRaiz( nodo0 );
        // Se agregan los hijos del nodo0
        nodo0.agregarHijo( nodo1 );
        nodo0.agregarHijo( nodo2 );
        nodo0.agregarHijo( nodo3 );
        // Se agregan los hijos del nodo1
        nodo1.agregarHijo( nodo4 );
        nodo1.agregarHijo( nodo5 );
        // Se agregan los hijos del nodo4
        nodo4.agregarHijo( nodo12 );
        nodo4.agregarHijo( nodo13 );
        nodo4.agregarHijo( nodo14 );
        // Se agregan los hijos del nodo5
        nodo5.agregarHijo( nodo15 );
        // Se agregan los hijos del nodo2
        nodo2.agregarHijo( nodo6 );
        nodo2.agregarHijo( nodo7 );
        nodo2.agregarHijo( nodo8 );
        // Se agregan los hijos del nodo8
        nodo8.agregarHijo( nodo16 );
        nodo8.agregarHijo( nodo17 );
        nodo8.agregarHijo( nodo18 );
        nodo8.agregarHijo( nodo19 );
        nodo8.agregarHijo( nodo20 );
        // Se agregan los hijos del nodo 3
        nodo3.agregarHijo( nodo9 );
        nodo3.agregarHijo( nodo10 );
        nodo3.agregarHijo( nodo11 );

        numeroElementos = 21;

    }

    /**
     * Construye un árbol vacio de enteros
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        arbol = new ArbolNArio<Integer>( );
        numeroElementos = 0;
    }

    /**
     * Construye un árbol de enteros con tan solo la raíz
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        arbol = new ArbolNArio<Integer>( );
        NodoArbolNArio<Integer> nodo = new NodoArbolNArio<Integer>( new Integer( 10 ) );
        arbol.definirRaiz( nodo );
        numeroElementos = 1;
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

        cont = 4;
        while( it.haySiguiente( ) )
        {
            Long elemento = ( Long )it.darSiguiente( );

            assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
            cont++;
        }

        it = arbol.darNivel( 3 );
        cont = 12;
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

        assertEquals( "El número de hojas no es correcto", 14, hojas );

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

        assertEquals( "la altura retornada no es correcta", 4, altura );

        setupEscenario2( );

        altura = arbol.darAltura( );
        assertEquals( "la altura retornada no es correcta", 0, altura );

        setupEscenario3( );
        altura = arbol.darAltura( );
        assertEquals( "la altura retornada no es correcta", 1, altura );

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
        Long elemento = new Long( 12 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 15 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 0 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en inorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el inorden por el método toString
        String resp = "[" + numeroElementos + "]:12-4-13-14-1-15-5-0-6-2-7-16-8-17-18-19-20-9-3-10-11-";
        assertEquals( "El recorrido no se realizó correctamente", resp, it.toString( ) );

        setupEscenario2( );
        it = arbol.darInorden( );
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
        Long elemento = new Long( 12 );
        Long resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 15 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 1 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 0 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en postorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el postorden por el método toString
        String resp = "[" + numeroElementos + "]:12-13-14-4-15-5-1-6-7-16-17-18-19-20-8-2-9-10-11-3-0-";
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

        elemento = new Long( 4 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 12 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 13 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 14 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 5 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 15 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 2 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 6 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 7 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 8 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 16 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 17 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 18 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 19 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 20 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 3 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 9 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 10 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        elemento = new Long( 11 );
        resul = ( Long )it.darSiguiente( );

        assertEquals( "El recorrido en preorden no se realizó correctamente", elemento, resul );

        assertFalse( "No deberían haber más elementos", it.haySiguiente( ) );

        // Verificar el preorden por el método toString
        String resp = "[" + numeroElementos + "]:0-1-4-12-13-14-5-15-2-6-7-8-16-17-18-19-20-3-9-10-11-";
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
     * Verifica que los tamaños de los niveles se retornen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarTamanoNivel( )
    {
        setupEscenario1( );
        int tamEsperado;
        int tamNivel;

        tamNivel = arbol.darTamanoNivel( 0 );
        tamEsperado = 1;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

        tamNivel = arbol.darTamanoNivel( 1 );
        tamEsperado = 3;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

        tamNivel = arbol.darTamanoNivel( 2 );
        tamEsperado = 8;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

        tamNivel = arbol.darTamanoNivel( 4 );
        tamEsperado = 0;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

        // Verificar que se retorne 0 para el tamaño de niveles que no existan
        tamNivel = arbol.darTamanoNivel( -1 );
        tamEsperado = 0;
        assertEquals( "El tamaño del nivel no es correcto", tamEsperado, tamNivel );

    }
}
