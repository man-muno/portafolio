package uniandes.cupi2.collections.iterador.test;

import uniandes.cupi2.collections.iterador.IteradorFlexible;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los m�todos de la clase IteradorCompuesto
 */
public class IteradorFlexibleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private IteradorFlexible iterador;

    /**
     * El n�mero de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    /**
     * Objeto para verificar el invariante de la estructura
     */
    private VerificadorEstructuraFlexible verificador;
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un iterador vac�o
     */
    public void setupEscenario1( )
    {
        numeroElementos = 0;
        iterador = new IteradorFlexible<Integer>( );
        verificador = new VerificadorEstructuraFlexible<Integer>( );

    }

    /**
     * Construye un iterador con 200 elementos
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        numeroElementos = 200;
        iterador = new IteradorFlexible<Integer>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {            
            iterador.insertarCola( new Integer( cont ) );
        }

        verificador = new VerificadorEstructuraFlexible<Integer>( );

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
     * Verifica que el m�todo haySiguiente funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testHaySiguiente( )
    {
        setupEscenario1( );

        boolean resp = iterador.haySiguiente( );

        assertFalse( "No deber�an haber elementos en el iterador", resp );

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
     * Verifica que el m�todo darSiguiente funcione correctamente
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

            assertNull( "No se deber�a haber retornado elemento alguno", elemento );
        }
    }

    /**
     * Verifica que el m�todo reiniciar funcione correctamente
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
     * Verifica que el m�todo agregar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar( )
    {
        setupEscenario1( );
        
        // Agregar elementos y despu�s consultarlos
        numeroElementos= 100; 
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            iterador.agregar( new Integer( cont * ( -10 ) ) );
            verificarInvariante( );
        }

        for( int cont = 0; cont < numeroElementos; cont++ )
        {

            Integer elemento = ( Integer )iterador.darSiguiente( );
            assertEquals( "El elemento no se agreg� correctamente", new Integer( cont * ( -10 ) ), elemento );
            verificarInvariante( );

        }
        
        assertEquals( "No se agregaron todos los elementos", numeroElementos, iterador.darLongitud() );               
    }

    /**
     * Verifica que el m�todo insertar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar( )
    {
        setupEscenario1( );

        // Agregar elementos y despu�s consultarlos
        numeroElementos= 200;
        int cont;
        for( cont = 0; cont < numeroElementos - 10; cont++ )
        {
            iterador.insertar( new Integer( cont * ( 100 ) ) );
            verificarInvariante( );
        }

        for( cont = numeroElementos - 11; cont >= 0; cont-- )
        {

            Integer elemento = ( Integer )iterador.darSiguiente( );
            assertEquals( "El elemento no se agreg� correctamente", new Integer( cont * ( 100 ) ), elemento );
            verificarInvariante( );

        }

        // Insertar s�lo 10 elementos. Verificar que el iterador continue consistente despu�s de las inserciones
        iterador.reiniciar( );
        for( cont = 0; cont < 10; cont++ )
        {

            iterador.insertar( new Integer( cont * ( -1 ) ) );
            verificarInvariante( );

        }

        for( cont = 9; cont >= 0; cont-- )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );
            assertEquals( "El elemento no se agreg� correctamente", new Integer( cont * ( -1 ) ), elemento );
            verificarInvariante( );
        }

        for( cont = numeroElementos - 11; cont >= 0; cont-- )
        {
            Integer elemento = ( Integer )iterador.darSiguiente( );
            assertEquals( "El elemento no se agreg� correctamente", new Integer( cont * ( 100 ) ), elemento );
            verificarInvariante( );
        }                         
    }

    /**
     * Verifica que el m�todo toString funcione correctamente
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

        assertEquals( "El m�todo toString no funciona correctamente", resp, iteradorS );
    }
}
