package uniandes.cupi2.collections.secuencia.test;

import junit.framework.TestCase;
import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.IndiceFueraDeRango;
import uniandes.cupi2.collections.secuencia.Secuencia;

/**
 * Esta es la clase usada para verificar los métodos de la clase Secuencia
 */
public class SecuenciaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Es la clase donde se harán las pruebas
     */
    private Secuencia secuencia;
    
    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una secuencia con 1000 elementos ordenados
     * en orden descendente
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {   
        numeroElementos= 1000;
        secuencia= new Secuencia<Integer>(numeroElementos); 
                        
        for(int cont=numeroElementos-1; cont>=0; cont--)
        {
            secuencia.agregar(new Integer(cont));
        }        
    }
    
    /**
     * Construye una secuencia con 100 elementos ordenados
     * ascendentemente
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {   
        numeroElementos= 100;
        secuencia= new Secuencia<Integer>(numeroElementos);               
        for(int cont=0; cont<numeroElementos; cont++)
        {
            secuencia.agregar(new Integer(cont));
        }        
    }
    
    
    /**
     * Construye una secuencia vacia
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {        
        secuencia= new Secuencia<Integer>(); 
        
        numeroElementos= 0;                         
    }
    
    /**
     * Prueba que los elementos se obtengan correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarElemento1( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {

            int pos = secuencia.busquedaSecuencial( new Integer( cont ) );

            Integer elemento = ( Integer )secuencia.darElemento( pos );
            assertEquals( "la búsqueda no se realizó correctamente", cont, elemento.intValue( ) );
        }
    }

    /**
     * Prueba que se arroje excepción cuando se use un índice inválido
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarElemento2( )
    {
        setupEscenario3( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {

                int pos = secuencia.busquedaSecuencial( new Integer( cont ) );

                Integer elemento = ( Integer )secuencia.darElemento( pos );
                assertNull( "La búsqueda no se realizó correctamente", elemento );
            }
        }
        catch( IndiceFueraDeRango e )
        {
            try
            {
                Integer elemento = ( Integer )secuencia.darElemento( -1000 );
                assertNull( "La búsqueda no se realizó correctamente", elemento );
            }
            catch( IndiceFueraDeRango e2 )
            {

            }
        }
    }
    
    /**
     * Prueba que los elementos se estén ingresando en la posición especificada correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar( )
    {
        setupEscenario3( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            secuencia.agregar( new Integer( 10 * cont ) );

        }

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )secuencia.darElemento( cont );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 10 * cont, elemento.intValue( ) );

        }
    }
    
    /**
     * Prueba que el iterator funcione correctamente para el recorrido de los elementos de la secuencia
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarIterador1( )
    {
        setupEscenario1( );
        secuencia.bubbleSort(); 

        Iterador it = secuencia.darIterador( );
        int cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que el número de elementos recorridos con el iterador sea el correcto
        assertEquals( "No se recorrieron todos los elementos con el iterador", numeroElementos, cont );

        // Verificar que el iterador se reinicia correctamente
        it.reiniciar( );

        cont = 0;
        while( cont < 10 )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que swe hayan recorrido 10 elementos
        assertEquals( "No se recorrieron todos los elementos con el iterador", 10, cont );

        // Verificar que el iterador se reinicia correctamente y recorrer todo la lista de nuevo
        it.reiniciar( );

        cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que el número de elementos recorridos con el iterador sea el correcto
        assertEquals( "No se recorrieron todos los elementos con el iterador", numeroElementos, cont );

    }

    /**
     * Prueba que el iterator sobre una secuencia vacia elementos de la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarIterador2( )
    {
        setupEscenario3( );

        Iterador it = secuencia.darIterador( );

        Integer elemento = ( Integer )it.darSiguiente( );

        assertNull( "El iterador no debería retornar elemento", elemento );

        elemento = ( Integer )it.darSiguiente( );

        assertNull( "El iterador no debería retornar elemento", elemento );
    }
    
    // -----------------------------------------------------------------
    // Pruebas ordenamientos
    // -----------------------------------------------------------------
    
    /**
     * Prueba que el ordenamiento con el algoritmo bubble sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testBubbleSort1()
    {
        setupEscenario1(); 
               
        secuencia.bubbleSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo bubble sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testBubbleSort2()
    {
        setupEscenario2(); 
                
        secuencia.bubbleSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo bubble sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testBubbleSort3()
    {
        setupEscenario3(); 
                
        secuencia.bubbleSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.bubbleSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.bubbleSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo insertion sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testInsertionSort1()
    {
        setupEscenario1(); 
               
        secuencia.insertionSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo insertion sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testInsertionSort2()
    {
        setupEscenario2(); 
                
        secuencia.insertionSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo insertion sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testInsertionSort3()
    {
        setupEscenario3(); 
                
        secuencia.insertionSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.insertionSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.insertionSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo merge sort de efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testMergeSort1()
    {
        setupEscenario1(); 
               
        secuencia.mergeSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo merge sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testMergeSort2()
    {
        setupEscenario2(); 
                
        secuencia.mergeSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo merge sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testMergeSort3()
    {
        setupEscenario3(); 
                
        secuencia.mergeSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.mergeSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.mergeSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo quick sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testQuickSort1()
    {
        setupEscenario1(); 
               
        secuencia.mergeSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo quick sort de efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testQuickSort2()
    {
        setupEscenario2(); 
                
        secuencia.quickSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo quick sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testQuickSort3()
    {
        setupEscenario3(); 
                
        secuencia.quickSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.quickSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.quickSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo selection sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testSelectionSort1()
    {
        setupEscenario1(); 
               
        secuencia.selectionSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo selection sort de efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testSelectionSort2()
    {
        setupEscenario2(); 
                
        secuencia.selectionSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo selection sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testSelectionSort3()
    {
        setupEscenario3(); 
                
        secuencia.selectionSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.selectionSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.selectionSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shake sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShakeSort1()
    {
        setupEscenario1(); 
               
        secuencia.shakeSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shake sort de efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShakeSort2()
    {
        setupEscenario2(); 
                
        secuencia.shakeSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shell sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShakeSort3()
    {
        setupEscenario3(); 
                
        secuencia.shakeSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.shakeSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.shakeSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shell sort se efectue correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShellSort1()
    {
        setupEscenario1(); 
               
        secuencia.shellSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
                 
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El orden no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shell sort de efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShellSort2()
    {
        setupEscenario2(); 
                
        secuencia.shellSort();
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud()); 
        
        //Verificar que el ordenamiento se haya realizado correctamente         
        for(int cont=0; cont< numeroElementos; cont++)
        {
            Integer elemento= ( Integer )secuencia.darElemento(cont);             
            assertEquals("El ordenamiento no se efectúo de forma correcta", new Integer(cont), elemento);
        }                
    }
    
    /**
     * Prueba que el ordenamiento con el algoritmo shell sort se efectúe correctamente
     *
     */
    @SuppressWarnings("unchecked")
    public void testShellSort3()
    {
        setupEscenario3(); 
                
        secuencia.shellSort(); 
        
        //verificar el tamaño de la secuencia
        assertEquals("El tamaño de la secuencia no es correcto", numeroElementos, secuencia.darLongitud());
       
       //Probar el ordenamiento con dos elementos
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(-10));
       
       
       secuencia.shellSort();
       
       Integer elemento= ( Integer )secuencia.darElemento(0);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       
       elemento= ( Integer )secuencia.darElemento(1);             
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       
       //Probar el ordenamiento con diversos elementos
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-100));
       secuencia.agregar(new Integer(-10));
       secuencia.agregar(new Integer(-1));
       secuencia.agregar(new Integer(100));
       secuencia.agregar(new Integer(58));
       secuencia.agregar(new Integer(6));
       secuencia.agregar(new Integer(77));
       secuencia.agregar(new Integer(99));
       secuencia.agregar(new Integer(55698));
       secuencia.agregar(new Integer(10));
       
       secuencia.shellSort();
       
       elemento= ( Integer )secuencia.darElemento(0);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(1);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-100), elemento);
       elemento= ( Integer )secuencia.darElemento(2);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(3);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-10), elemento);
       elemento= ( Integer )secuencia.darElemento(4);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(-1), elemento);
       elemento= ( Integer )secuencia.darElemento(5);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(6), elemento);
       elemento= ( Integer )secuencia.darElemento(6);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(10), elemento);
       elemento= ( Integer )secuencia.darElemento(7);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(58), elemento);
       elemento= ( Integer )secuencia.darElemento(8);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(77), elemento);
       elemento= ( Integer )secuencia.darElemento(9);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(99), elemento);
       elemento= ( Integer )secuencia.darElemento(10);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(11);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(100), elemento);
       elemento= ( Integer )secuencia.darElemento(12);
       assertEquals("El orden no se efectúo de forma correcta", new Integer(55698), elemento);
       
    }
    
    
    // -----------------------------------------------------------------
    // Pruebas búsquedas
    // -----------------------------------------------------------------
    
    /**
     * Verifica que la búsqueda binaria funcione correctamente
     */
    @SuppressWarnings("unchecked")
    public void testBusquedaBinaria()
    {
        setupEscenario1(); 
        secuencia.bubbleSort(); 
        
        //Verificar que todos los elementos existan
        for(int cont=0; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaBinaria(new Integer(cont));
            
            assertEquals("La búsqueda no se realizó correctamente", cont, pos); 
        }
        
        //Realizar la búsqueda de elementos que no existan
        for(int cont=1; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaBinaria(new Integer((-1)*cont));
            
            assertEquals("La búsqueda no se realizó correctamente", -1, pos); 
        }
        
        setupEscenario3();
        
        for(int cont=0; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaBinaria(new Integer(-cont));
            
            assertEquals("La búsqueda no se realizó correctamente", -1, pos); 
        }
    }
    
    /**
     * Verifica que la búsqueda secuencial funcione correctamente
     */
    @SuppressWarnings("unchecked")
    public void testBusquedaSecuencial()
    {
        setupEscenario1(); 
        secuencia.bubbleSort(); 
        
        //Verificar que todos los elementos existan
        for(int cont=0; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaSecuencial(new Integer(cont));
            
            assertEquals("La búsqueda no se realizó correctamente", cont, pos); 
        }
        
        //Realizar la búsqueda de elementos que no existan
        for(int cont=1; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaSecuencial(new Integer((-1)*cont));
            
            assertEquals("La búsqueda no se realizó correctamente", -1, pos); 
        }
        
        setupEscenario3();
        
        for(int cont=0; cont<numeroElementos; cont++)
        {
            int pos= secuencia.busquedaSecuencial(new Integer(-cont));
            
            assertEquals("La búsqueda no se realizó correctamente", -1, pos); 
        }
    }
}
