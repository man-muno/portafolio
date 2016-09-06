package uniandes.cupi2.collections.colaEncadenada.test;

import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.ColaVaciaException;
import uniandes.cupi2.collections.colaEncadenada.NodoCola;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ColaEncadenada
 */
public class ColaEncadenadaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ColaEncadenada cola;

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
     * Construye una nueva cola encadenada vacía de Integers
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        cola = new ColaEncadenada<Integer>( );
        numeroElementos = 500;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye una nueva cola con 400 elementos inicialados con el valor de la posicion por 8
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        numeroElementos = 400;
        cola = new ColaEncadenada<Integer>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            cola.insertar( new Integer( cont * 8 ) );
        }
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye una nueva cola con 50 elementos inicialados con el valor de la posicion por -4
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        numeroElementos = 50;
        cola = new ColaEncadenada<Long>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            cola.insertar( new Long( cont * ( -4 ) ) );
        }
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Verifica que la estructura de la cola sea correcta
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = ( verificador.verificarCola( cola ) );
        assertTrue( "La estructura de la cola no es correcta", estructuraBien );
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar1( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            cola.insertar( new Integer( 5 * cont ) );
            verificarInvariante( );
        }

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, cola.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoCola nodo = ( NodoCola )cola.darPrimero( );

        int cont = 0;
        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 5 * cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont++;
        }
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            cola.insertar( new Integer( numeroElementos - cont ) );
            verificarInvariante( );
        }

        // Verificar que la cola tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, cola.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoCola nodo = ( NodoCola )cola.darPrimero( );
        int cont = numeroElementos;

        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + ( numeroElementos - cont ) + " no se agrego de forma correcta", cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont--;

        }
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar3( )
    {
        setupEscenario1( );

        // Insertar 5 elementos en ordenes distintos
        cola.insertar( new Integer( 5 ) );
        verificarInvariante( );

        cola.insertar( new Integer( 1 ) );
        verificarInvariante( );

        cola.insertar( new Integer( 1000 ) );
        verificarInvariante( );

        cola.insertar( new Integer( 500 ) );
        verificarInvariante( );

        cola.insertar( new Integer( -1 ) );
        verificarInvariante( );

        // Verificar que la cola tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", 5, cola.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoCola nodo = ( NodoCola )cola.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 5 no se agrego de forma correcta", 5, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 1 no se agrego de forma correcta", 1, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 1000 no se agrego de forma correcta", 1000, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 500 no se agrego de forma correcta", 500, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor -1 no se agrego de forma correcta", -1, elemento.intValue( ) );

    }

    /**
     * Prueba que todos los elementos se estén eliminando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testTomarElemento1( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                Integer elemento = ( Integer )cola.tomarElemento( );

                assertEquals( "No se tomó el elemento correcto", cont * 8, elemento.intValue( ) );
                verificarInvariante( );
            }
        }
        catch( ColaVaciaException e )
        {

            e.printStackTrace( );
        }

        // Verificar que la cola tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", 0, cola.darLongitud( ) );
    }

    /**
     * Prueba que se retorne null al tomar un elemento de una cola vacía
     * 
     */
    @SuppressWarnings("unchecked")
    public void testTomarElemento2( )
    {
        setupEscenario1( );
        Integer elemento = null;

        try
        {
            elemento = ( Integer )cola.tomarElemento( );
            assertNull( "No se debio haber tomado nada", elemento );
        }
        catch( ColaVaciaException e )
        {
            assertNull( "No se debio tomado nada ", elemento );
            verificarInvariante( );
        }
    }

    /**
     * Prueba que la cola siga consistente al tomar 5 elementos de esta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testTomarElemento3( )
    {
        setupEscenario3( );

        try
        {

            Long elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", 0, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -4, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -8, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -12, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -16, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -20, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -24, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -28, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -32, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )cola.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -36, elemento.intValue( ) );
            verificarInvariante( );

        }
        catch( ColaVaciaException e )
        {

            e.printStackTrace( );
        }

        // Verificar que la cola tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", numeroElementos - 10, cola.darLongitud( ) );
    }

    /**
     * Verifica que se indique correctamente si una cola está vacia o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaVacia( )
    {
        setupEscenario1( );

        boolean vacia = cola.estaVacia( );

        assertEquals( "La cola debería estar vacía", true, vacia );

        setupEscenario2( );

        vacia = cola.estaVacia( );

        assertEquals( "La cola no debería estar vacía", false, vacia );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                Integer elemento = ( Integer )cola.tomarElemento( );

                assertEquals( "No se tomó el elemento correcto", cont * 8, elemento.intValue( ) );

            }
        }
        catch( ColaVaciaException e )
        {

            e.printStackTrace( );
        }

        vacia = cola.estaVacia( );

        assertEquals( "La cola debería estar vacía", true, vacia );

    }

    /**
     * Prueba que se retorne la longitud de la cola correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {
        setupEscenario2( );

        // Verificar que la longitud de la cola sea correcta
        assertEquals( "La longitud no se retorno correctamente", numeroElementos, cola.darLongitud( ) );

        try
        {
            cola.tomarElemento( );
            cola.tomarElemento( );
            cola.tomarElemento( );
            cola.tomarElemento( );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 4, cola.darLongitud( ) );

            cola.tomarElemento( );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 5, cola.darLongitud( ) );
        }
        catch( ColaVaciaException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Prueba que el método toString de la cola funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {

        // Verificar que la lista sea vaciada
        setupEscenario3( );

        String colaS = cola.toString( );

        // Construir el string espererado para la prueba
        String resp = "[" + numeroElementos + "]:";
        for( int i = 0; i < numeroElementos; i++ )
        {
            resp += ( new Long( i * ( -4 ) ) ).toString( ) + "->";
        }

        assertEquals( "El método toString no funciona correctamente", resp, colaS );
    }

    /**
     * Prueba que se retorne correctamente el primer elemento de la cola
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPrimero( )
    {
        setupEscenario2( );

        NodoCola nodo = cola.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        // Verificar que el primero elemento sea el correcto
        assertEquals( "El primer elemento no es correcto", 0, elemento.intValue( ) );

        // Verificar que el encadenamiento entre los nodos sea correcto
        NodoCola aux = nodo;
        nodo = nodo.darSiguiente( );

        int cont = 1;
        while( nodo != null )
        {
            aux = nodo;
            nodo = nodo.darSiguiente( );
            cont++;
        }

        // Verificar que el nodo siguiente al ultimo sea null
        assertNull( "El siguiente elemento debería ser null", aux.darSiguiente( ) );

        // Verificar que el número de nodos sea igual al número de elementos
        assertEquals( "El número de nodos no correcponde al número de elementos", numeroElementos, cont );
    }
}
