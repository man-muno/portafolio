package uniandes.cupi2.collections.iterador.test;

import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase IteradorSimple
 */
public class IteradorSimpleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private IteradorSimple iterador;

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
     * Construye un iterador vacío con capacidad de 100 elementos
     */
    public void setupEscenario1( )
    {
        numeroElementos = 100;
        iterador = new IteradorSimple<Integer>( numeroElementos );
        verificador = new VerificadorEstructura<Integer>( );

    }

    /**
     * Construye un iterador con 200 elementos
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        numeroElementos = 200;
        iterador = new IteradorSimple<Integer>( numeroElementos );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            try
            {
                iterador.agregar( new Integer( cont ) );
            }
            catch( IteradorException e )
            {

                e.printStackTrace( );
            }
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
        boolean estructuraBien = verificador.verificarIterador( iterador );

        assertTrue( "La estructura del iterador no es correcta", estructuraBien );
    }

    /**
     * Verifica que el método haySiguiente funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testHaySiguiente( )
    {
        setupEscenario1( );

        boolean resp = iterador.haySiguiente( );

        assertFalse( "No deberían haber elementos en el iterador", resp );

        setupEscenario2( );

        int cont = 0;
        while( iterador.haySiguiente( ) )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );

            assertEquals( "El elemento retornado por el iterador no es el correcto", new Integer( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos del iterador", numeroElementos, cont );

    }

    /**
     * Verifica que el método darSiguiente funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarSiguiente( )
    {
        setupEscenario2( );

        int cont = 0;
        while( iterador.haySiguiente( ) )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );
            verificarInvariante( );
            assertEquals( "El elemento retornado por el iterador no es el correcto", new Integer( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos del iterador", numeroElementos, cont );

        setupEscenario1( );
        Integer elemento = null;

        for( int i = 0; i < numeroElementos; i++ )
        {

            elemento = ( Integer )iterador.darSiguiente( );

            assertNull( "No se debería haber retornado elemento alguno", elemento );
        }
    }

    /**
     * Verifica que el método reiniciar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testReiniciar( )
    {
        setupEscenario2( );

        int cont = 0;
        while( iterador.haySiguiente( ) )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );
            verificarInvariante( );

            assertEquals( "El elemento retornado por el iterador no es el correcto", new Integer( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos del iterador", numeroElementos, cont );

        iterador.reiniciar( );
        verificarInvariante( );

        cont = 0;
        while( iterador.haySiguiente( ) )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );

            assertEquals( "El elemento retornado por el iterador no es el correcto", new Integer( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos del iterador", numeroElementos, cont );
    }

    /**
     * Verifica que el método agregar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar( )
    {
        setupEscenario1( );
        try
        {
            // Agregar elementos y después consultarlos
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                iterador.agregar( new Integer( cont * ( -10 ) ) );
                verificarInvariante( );
            }

            for( int cont = 0; cont < numeroElementos; cont++ )
            {

                Integer elemento = ( Integer )iterador.darSiguiente( );
                assertEquals( "El elemento no se agregó correctamente", new Integer( cont * ( -10 ) ), elemento );
                verificarInvariante( );

            }

            // agregar elementos pasando la capacidad del iterador
            int cont = 0;
            try
            {

                for( cont = 0; cont < numeroElementos; cont++ )
                {
                    iterador.agregar( new Integer( cont * ( -10 ) ) );
                    verificarInvariante( );
                }

                assertEquals( "No se debería haber agredado elemento alguno", 0, cont );

            }
            catch( IteradorException e )
            {
                assertEquals( "No se debería haber agredado elemento alguno", 0, cont );
            }

        }
        catch( IteradorException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Verifica que el método insertar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar( )
    {
        setupEscenario1( );
        try
        {
            // Agregar elementos y después consultarlos
            int cont;
            for( cont = 0; cont < numeroElementos - 10; cont++ )
            {
                iterador.insertar( new Integer( cont * ( 100 ) ) );
                verificarInvariante( );
            }

            for( cont = numeroElementos - 11; cont >= 0; cont-- )
            {

                Integer elemento = ( Integer )iterador.darSiguiente( );
                assertEquals( "El elemento no se agregó correctamente", new Integer( cont * ( 100 ) ), elemento );
                verificarInvariante( );

            }

            // Insertar sólo 10 elementos. Verificar que el iterador continue consistente después de las inserciones
            iterador.reiniciar( );
            for( cont = 0; cont < 10; cont++ )
            {

                iterador.insertar( new Integer( cont * ( -1 ) ) );
                verificarInvariante( );

            }

            for( cont = 9; cont >= 0; cont-- )
            {
                Integer elemento = ( Integer )iterador.darSiguiente( );
                assertEquals( "El elemento no se agregó correctamente", new Integer( cont * ( -1 ) ), elemento );
                verificarInvariante( );
            }

            for( cont = numeroElementos - 11; cont >= 0; cont-- )
            {
                Integer elemento = ( Integer )iterador.darSiguiente( );
                assertEquals( "El elemento no se agregó correctamente", new Integer( cont * ( 100 ) ), elemento );
                verificarInvariante( );
            }

            // agregar elementos pasando la capacidad del iterador
            setupEscenario2( );
            try
            {

                for( cont = 0; cont < numeroElementos; cont++ )
                {
                    iterador.insertar( new Integer( cont * ( -10 ) ) );
                    verificarInvariante( );
                }

                assertEquals( "No se debería haber insertado elemento alguno", 0, cont );

            }
            catch( IteradorException e )
            {
                assertEquals( "No se debería haber insertado elemento alguno", 0, cont );
            }
        }
        catch( IteradorException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Verifica que el método toString funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {
        setupEscenario2( );
        String iteradorS = iterador.toString( );

        // Construir la cadena que se espera
        String resp = "[" + numeroElementos + "]:";
        for( int i = 0; i < numeroElementos; i++ )
        {
            resp += i + "-";
        }

        assertEquals( "El método toString no funciona correctamente", resp, iteradorS );
    }
}
