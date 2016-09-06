package uniandes.cupi2.collections.heap.test;

import uniandes.cupi2.collections.heap.Heap;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase Heap
 */
public class HeapTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private Heap heap;

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
     * Construye un heap con 8 elementos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        heap = new Heap<Integer>( );
        heap.insertar( new Integer( 10 ) );
        heap.insertar( new Integer( 9 ) );
        heap.insertar( new Integer( 12 ) );
        heap.insertar( new Integer( 8 ) );
        heap.insertar( new Integer( 11 ) );
        heap.insertar( new Integer( 15 ) );
        heap.insertar( new Integer( 7 ) );
        heap.insertar( new Integer( 6 ) );

        numeroElementos = 8;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye un heap vacio de enteros
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        heap = new Heap<Integer>( );
        numeroElementos = 0;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye un heap de enteros con tan solo la raíz
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {        
        heap = new Heap<Integer>( );
        heap.insertar( new Integer( 10 ) );
        numeroElementos = 1;
        verificador = new VerificadorEstructura<Integer>( );


    }

    /**
     * Construye un heap con 6 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {        
        heap = new Heap<Integer>( );
        heap.insertar( new Integer( -2 ) );
        heap.insertar( new Integer( -6 ) );
        heap.insertar( new Integer( -1 ) );
        heap.insertar( new Integer( -7 ) );
        heap.insertar( new Integer( -3 ) );
        heap.insertar( new Integer( 0 ) );

        numeroElementos = 6;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Verifica que la estructura y el orden se mantengan en el heap ordenado
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = verificador.verificarHeap( heap );

        assertTrue( "La estructura y/o el orden en el heap no es correcto", estructuraBien );
    }

    /**
     * Verifica que las inserciones en el heap se realicen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar( )
    {
        setupEscenario2( );

        numeroElementos = 11;

        for( int cont = numeroElementos-1; cont>=0; cont-- )
        {

            heap.insertar( new Integer( cont ) );
            verificarInvariante( );
        }
        
        //Verificar que el número de elementos sea correcto
        assertEquals("El número de elementos no es correcto", numeroElementos, heap.darPeso()); 

        // Verificar que la inserción se haya realizado en el orden correcto

        Iterador iterador = heap.darRecorridoNiveles( );
        
        Integer elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(0), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(1), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(5), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(4), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(2), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(3), elemento);                        
    }

    /**
     * Verifica que los elementos se estén insertando en el orden correcto
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        // Insertarle más elementos al heap y verificar que se esté creando correctamente
        setupEscenario3( );
        Integer elemento7 = new Integer( 7 );
        Integer elemento8 = new Integer( 8 );
        Integer elemento9 = new Integer( 9 );
        Integer elemento13 = new Integer( 13 );
        Integer elemento10 = new Integer( 10 );
        Integer elemento11 = new Integer( 11 );
        Integer elemento12 = new Integer( 12 );
        Integer elemento14 = new Integer( 14 );

        heap.insertar( elemento9 );
        verificarInvariante( );
        heap.insertar( elemento12 );
        verificarInvariante( );
        heap.insertar( elemento8 );
        verificarInvariante( );
        heap.insertar( elemento13 );
        verificarInvariante( );
        heap.insertar( elemento11 );
        verificarInvariante( );
        heap.insertar( elemento14 );
        verificarInvariante( );
        heap.insertar( elemento7 );
        
        //Verificar el número de elementos
        assertEquals("El número de elementos no es correcto", numeroElementos+7, heap.darPeso()); 
                    
        // Verificar que los elementos se hayan insertado en el orden correcto
        Iterador iterador = heap.darRecorridoNiveles( );

        Integer elemento;
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento7, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento8, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento11, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento9, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento13, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento12, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento14, elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", elemento10, elemento);

    }

    /**
     * Verifica que la búsqueda de un elemento se realice correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar( )
    {
        setupEscenario1( );

        Integer elemento = new Integer( 7 );
        Integer busqueda = ( Integer )heap.buscar( elemento );

        assertEquals( "El resultado de la búsqueda no es correcto", elemento, busqueda );

        elemento = new Integer( 15 );

        busqueda = ( Integer )heap.buscar( elemento );

        assertEquals( "El resultado de la búsqueda no es correcto", elemento, busqueda );

        // buscar un elemento que no exista
        elemento = new Integer( 1000 );

        busqueda = ( Integer )heap.buscar( elemento );

        assertNull( "El resultado de la búsqueda no es correcto", busqueda );
    }

    /**
     * Verifica que la altura del heap se genere correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarAltura( )
    {
        setupEscenario1( );

        int altura = heap.darAltura( );

        assertEquals( "La altura retornada no es correcta", 4, altura );

        setupEscenario2( );

        altura = heap.darAltura( );

        assertEquals( "La altura retornada no es correcta", 0, altura );

        setupEscenario3( );

        altura = heap.darAltura( );

        assertEquals( "La altura retornada no es correcta", 1, altura );

        setupEscenario4( );

        altura = heap.darAltura( );

        assertEquals( "La altura retornada no es correcta", 3, altura );
    }

    /**
     * Verifica que se retorne el elemento mayor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMayor( )
    {
        setupEscenario1( );

        Integer mayor = ( Integer )heap.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Integer( 15 ), mayor );

        // Verificar que se retorne null cuando el heap no tiene elementos
        setupEscenario2( );
        mayor = ( Integer )heap.darMayor( );

        assertNull( "El elemento mayor no es el correcto", mayor );

        // Verificar el método mayor con un solo elemento en el heap
        setupEscenario3( );

        mayor = ( Integer )heap.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Integer( 10 ), mayor );

        setupEscenario4( );

        mayor = ( Integer )heap.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Integer( 0 ), mayor );
    }

    /**
     * Verifica que se retorne el elemento menor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMenor( )
    {
        setupEscenario1( );

        Integer menor = ( Integer )heap.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Integer( 6 ), menor );

        // Verificar que se retorne null cuando el heap no tiene elementos
        setupEscenario2( );
        menor = ( Integer )heap.darMenor( );

        assertNull( "El elemento menor no es el correcto", menor );

        // Verificar el método menor con un solo elemento en el heap
        setupEscenario3( );

        menor = ( Integer )heap.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Integer( 10 ), menor );

        setupEscenario4( );

        menor = ( Integer )heap.darMenor( );

        assertEquals( "El elemento mayor no es el correcto", new Integer( -7 ), menor );
    }

    /**
     * Verifica que el peso se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPeso( )
    {
        setupEscenario1( );

        int peso = heap.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario2( );
        peso = heap.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario3( );

        peso = heap.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario4( );

        peso = heap.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );
    }

    /**
     * Verifica que se recorra el heap en inorden de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testRecorridoNiveles( )
    {
        setupEscenario1( );

        Iterador iterador = heap.darRecorridoNiveles( );

        // Verificar que el recorrido en inorden sea correcto
        Integer elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(12), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La inserción no se realizó de forma correcta", new Integer(10), elemento);
        
        // Verificar que se hayan recorrido todos los elementos
        assertFalse( "Hay más elementos de los posibles",iterador.haySiguiente());

        setupEscenario2( );
        iterador = heap.darRecorridoNiveles( );
        assertFalse( "No se debió haber recorrido elemento alguno", iterador.haySiguiente( ) ); // Verificar que no se haya recorrido nada

        // Verificar que se retorne el único elemento del heap
        setupEscenario3( );
        iterador = heap.darRecorridoNiveles( );
        assertEquals( "El peso no es correcto", new Integer( 10 ), iterador.darSiguiente( ) );
    }

    /**
     * Verifica que todos los elementos del heap sean eliminados de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario1( );
        
        for( int cont = 6; cont < numeroElementos + 8; cont++ )
        {

            if( cont != 13 && cont != 14 )
            {
                heap.eliminar( new Integer( cont ) );
                verificarInvariante( );
            }

        }

        // Verificar que se hayan eliminado todos los elementos
        for( int cont = 6; cont < numeroElementos + 8; cont++ )
        {

            if( cont != 13 && cont != 14 )
            {
                Integer elemento = ( Integer )heap.buscar( new Integer( cont ) );
                assertNull( "No se debió haber retornado el elemento", elemento );
            }
        }

        // Verificar que se hayan eliminado todos los elementos
        assertEquals( "El peso no es correcto", 0, heap.darPeso( ) );

        setupEscenario3( );
        heap.eliminar( new Integer( 10 ) );

        // Verificar que se hayan eliminado todos los elementos
        assertEquals( "El peso no es correcto", 0, heap.darPeso( ) );
        verificarInvariante( );

        setupEscenario4( );
        heap.eliminar( new Integer( -1 ) );

        // Verificar que se hayan eliminado todos los elementos
        assertEquals( "El peso no es correcto", numeroElementos - 1, heap.darPeso( ) );
        verificarInvariante( );

        // buscar el elemento que se acabo de eliminar
        Integer elemento = ( Integer )heap.buscar( new Integer( -1 ) );

        assertNull( "No se debió haber retornado el elemento", elemento );
        verificarInvariante( );

        // Eliminar la raiz y verificar que el heap haya quedado bien construido
        setupEscenario1( );
        heap.eliminar( new Integer( 15 ) );
        verificarInvariante( );
        Iterador iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 6 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 7 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals("La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 8 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento); 
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 9 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);                        
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 10 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);                                           
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 11 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);   
        
        //Eliminar la raiz y verificar que el heap haya quedado bien construido
        heap.eliminar( new Integer( 12 ) );
        
        verificarInvariante(); 
        
        iterador = heap.darRecorridoNiveles( );
        
        assertFalse("No deberían haber elementos en el heap", iterador.haySiguiente()); 
                                  
        // Eliminar otro elemento que no sea la raíz y verificar que el heap quede bien construido
        setupEscenario4( );
        heap.eliminar(new Integer(-1)); 
        iterador = heap.darRecorridoNiveles( );
        elemento = ( Integer )iterador.darSiguiente( );
        assertEquals( "Los elementos no se eliminaron de forma correcta", new Integer( -7 ), elemento );
        
        elemento = ( Integer )iterador.darSiguiente( );
        assertEquals( "Los elementos no se eliminaron de forma correcta", new Integer( -6 ), elemento );
        
        elemento = ( Integer )iterador.darSiguiente( );
        assertEquals( "Los elementos no se eliminaron de forma correcta", new Integer( 0 ), elemento );
        
        elemento = ( Integer )iterador.darSiguiente( );
        assertEquals( "Los elementos no se eliminaron de forma correcta", new Integer( -2 ), elemento );
        
        elemento = ( Integer )iterador.darSiguiente( );
        assertEquals( "Los elementos no se eliminaron de forma correcta", new Integer( -3 ), elemento );
        
        verificarInvariante( );             
        
    }

    /**
     * Verifica que se retorne null al tratar de eliminar un elemento que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario2( );

        Integer elemento= ( Integer )heap.eliminar( new Integer( 10 ) );
        assertNull( "No se debió eliminar el elemento", elemento );

        setupEscenario4( );
            
        elemento= ( Integer )heap.eliminar( new Integer( 15 ) );
        assertNull( "No se debió eliminar el elemento", elemento );        
    }
    
    /**
     * Verifica que todos los elementos intermedios y de las hojas heap sean eliminados de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario1( );
                
        //Eliminar un elemento intermedio
        heap.eliminar(new Integer(11));
        verificarInvariante( );
        Iterador iterador = heap.darRecorridoNiveles( );

        Integer elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar un elemento intermedio
        setupEscenario1(); 
        heap.eliminar(new Integer(12));
        verificarInvariante( );
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        //Eliminar un elemento intermedio
        setupEscenario1(); 
        heap.eliminar(new Integer(8));
        verificarInvariante( );
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar un elemento intermedio
        setupEscenario1(); 
        heap.eliminar(new Integer(7));
        verificarInvariante( );
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar un elemento intermedio
        setupEscenario1(); 
        heap.eliminar(new Integer(9));
        verificarInvariante( );
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(11), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
        
        //Eliminar un elemento intermedio
        setupEscenario1(); 
        heap.eliminar(new Integer(11));
        verificarInvariante( );
        iterador = heap.darRecorridoNiveles( );

        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(6), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(7), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(8), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(9), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(10), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(15), elemento);
        
        elemento = ( Integer )iterador.darSiguiente( );
        
        assertEquals(" La eliminación no se realizó de forma correcta", new Integer(12), elemento);
    }
    
    /**
     * Verifica el elemento mayor se elimine correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminarMenor( )
    {
        setupEscenario4(); 
        
        Integer[] elementos= new Integer[numeroElementos]; 
        
        
        for(int cont=0; cont<numeroElementos; cont++)
        {         
            elementos[cont]= ( Integer )heap.eliminarMenor();                        
        } 
        
        //Verificar que los elementos se hayan eliminado de forma correcta
        
        for(int cont=1; cont<numeroElementos; cont++)
        {
            assertTrue("La eliminación no se realizó de forma correcta", elementos[cont-1].compareTo(elementos[cont])<0); 
        }
    }
    
}
