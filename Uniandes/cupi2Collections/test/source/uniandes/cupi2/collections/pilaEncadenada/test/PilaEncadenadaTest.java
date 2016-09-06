package uniandes.cupi2.collections.pilaEncadenada.test;

import uniandes.cupi2.collections.pilaEncadenada.NodoPila;
import uniandes.cupi2.collections.pilaEncadenada.PilaEncadenada;
import uniandes.cupi2.collections.pilaEncadenada.PilaVaciaException;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase PilaEncadenada
 */
public class PilaEncadenadaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private PilaEncadenada pila;

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
     * Construye una nueva pila encadenada vacía de Integers
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        pila = new PilaEncadenada<Integer>( );
        numeroElementos = 200;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye una nueva pila con 1000 elementos inicialados con el valor de la posicion por 4
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        numeroElementos = 1000;
        pila = new PilaEncadenada<Integer>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            pila.insertar( new Integer( cont * 4 ) );
        }
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Verifica que la estructura en la cola sea correcta
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = ( verificador.verificarPila( pila ) );

        assertTrue( "La estructura de la pila no es correcta", estructuraBien );
    }
    /**
     * Construye una nueva pila con 50 elementos inicialados con el valor de la posicion por -2
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        numeroElementos = 50;
        pila = new PilaEncadenada<Long>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            pila.insertar( new Long( cont * ( -2 ) ) );
        }
        verificador = new VerificadorEstructura<Long>( );
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
            pila.insertar( new Integer( 10 * cont ) );
            verificarInvariante( );
        }

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, pila.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoPila nodo = ( NodoPila )pila.darPrimero( );

        int cont = numeroElementos - 1;
        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 10 * cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont--;
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
            pila.insertar( new Integer( numeroElementos - cont ) );
            verificarInvariante( );
        }

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, pila.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoPila nodo = ( NodoPila )pila.darPrimero( );
        int cont = 1;

        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont++;

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
        pila.insertar( new Integer( 5 ) );
        verificarInvariante( );

        pila.insertar( new Integer( 1 ) );
        verificarInvariante( );

        pila.insertar( new Integer( 1000 ) );
        verificarInvariante( );

        pila.insertar( new Integer( 500 ) );
        verificarInvariante( );

        pila.insertar( new Integer( -1 ) );
        verificarInvariante( );

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", 5, pila.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoPila nodo = ( NodoPila )pila.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor -1 no se agrego de forma correcta", -1, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 500 no se agrego de forma correcta", 500, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 1000 no se agrego de forma correcta", 1000, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 1 no se agrego de forma correcta", 1, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor 5 no se agrego de forma correcta", 5, elemento.intValue( ) );

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
            for( int cont = numeroElementos - 1; cont >= 0; cont-- )
            {
                Integer elemento = ( Integer )pila.tomarElemento( );

                assertEquals( "No se tomó el elemento correcto", cont * 4, elemento.intValue( ) );
                verificarInvariante( );

            }
        }
        catch( PilaVaciaException e )
        {

            e.printStackTrace( );
        }

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", 0, pila.darLongitud( ) );
    }

    /**
     * Prueba que se retorne null al tomar un elemento de una pila vacía
     * 
     */
    @SuppressWarnings("unchecked")
    public void testTomarElemento2( )
    {
        setupEscenario1( );
        Integer elemento = null;

        try
        {
            elemento = ( Integer )pila.tomarElemento( );

            assertNull( "No se debio haber tomado nada", elemento );
        }
        catch( PilaVaciaException e )
        {
            assertNull( "No se debio tomado nada ", elemento );
            verificarInvariante( );
        }
    }

    /**
     * Prueba que la pila siga consisten al tomar 5 elementos de esta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testTomarElemento3( )
    {
        setupEscenario3( );

        try
        {

            Long elemento = ( Long )pila.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -98, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )pila.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -96, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )pila.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -94, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )pila.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -92, elemento.intValue( ) );
            verificarInvariante( );

            elemento = ( Long )pila.tomarElemento( );

            assertEquals( "No se tomó el elemento correcto", -90, elemento.intValue( ) );
            verificarInvariante( );

        }
        catch( PilaVaciaException e )
        {

            e.printStackTrace( );
        }

        // Verificar que la pila tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", numeroElementos - 5, pila.darLongitud( ) );
    }

    /**
     * Verifica que se indique correctamente si una pila está vacia o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaVacia( )
    {
        setupEscenario1( );

        boolean vacia = pila.estaVacia( );

        assertEquals( "La pila debería estar vacía", true, vacia );

        setupEscenario2( );

        vacia = pila.estaVacia( );

        assertEquals( "La pila no debería estar vacía", false, vacia );

        try
        {
            for( int cont = numeroElementos - 1; cont >= 0; cont-- )
            {
                Integer elemento = ( Integer )pila.tomarElemento( );

                assertEquals( "No se tomó el elemento correcto", cont * 4, elemento.intValue( ) );

            }
        }
        catch( PilaVaciaException e )
        {

            e.printStackTrace( );
        }

        vacia = pila.estaVacia( );

        assertEquals( "La pila debería estar vacía", true, vacia );

    }

    /**
     * Prueba que se retorne la longitud de la pila correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {
        setupEscenario2( );

        // Verificar que la longitud de la pila sea correcta
        assertEquals( "La longitud no se retorno correctamente", numeroElementos, pila.darLongitud( ) );

        try
        {
            pila.tomarElemento( );
            pila.tomarElemento( );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 2, pila.darLongitud( ) );

            pila.tomarElemento( );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 3, pila.darLongitud( ) );
        }
        catch( PilaVaciaException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Prueba que el método toString de la pila funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {

        // Verificar que la pila sea convertida en cadena correctamente
        setupEscenario3( );

        String pilaS = pila.toString( );

        // Construir el string espererado para la prueba
        String resp = "[" + numeroElementos + "]:";
        for( int i = numeroElementos - 1; i >= 0; i-- )
        {
            resp += ( new Long( i * ( -2 ) ) ).toString( ) + "->";
        }
        assertEquals( "El método toString no funciona correctamente", resp, pilaS );
    }

    /**
     * Prueba que se retorne correctamente el primer elemento de la pila
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPrimero( )
    {
        setupEscenario2( );

        NodoPila nodo = pila.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        // Verificar que el primero elemento sea el correcto
        assertEquals( "El primer elemento no es correcto", ( numeroElementos - 1 ) * 4, elemento.intValue( ) );

        // Verificar que el encadenamiento entre los nodos sea correcto
        NodoPila aux = nodo;
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
