package uniandes.cupi2.collections.trie.test;

import junit.framework.TestCase;
import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.collections.trie.PalabraInvalidaException;
import uniandes.cupi2.collections.trie.Trie;

/**
 * Esta es la clase usada para verificar los métodos de la clase Trie
 */
public class TrieTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Es la clase donde se harán las pruebas
     */
    private Trie<String> trie; 
    
    /**
     * El número de elementos a manejar en cada prueba
     */
    private int numeroElementos;
    
    /**
     * El peso del trie que se va a manejar en cada prueba
     */
    private int peso; 
    
    /**
     * Objeto para verificar el invariante de la estructura
     */
    private VerificardorEstructura verificador= new VerificardorEstructura();
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Construye un trie vacio
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        trie= new Trie<String>(); 
        numeroElementos=0; 
        peso=0; 
    }
    
    
    /**
     * Construye un trie con 10 elementos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        trie= new Trie<String>(); 
        numeroElementos=10; 
        peso= 45; 
        
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";
        
        try
        {            
            for(int cont=0; cont<numeroElementos; cont++)
            {
                trie.insertar(palabras[cont]);    
            }
                 
        }
        catch( PalabraInvalidaException e )
        {                
            e.printStackTrace();
        }           
        
    }
    
    /**
     * Construye un trie con 27 palabras
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        trie= new Trie<String>(); 
    	numeroElementos= 27; 
        peso= 133; 
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "Abaco"; //5 
        palabras[1]= "barco"; //5
        palabras[2]= "Casual"; //6
        palabras[3]= "dado"; //4
        palabras[4]= "Efimero"; //7
        palabras[5]= "foca"; //4
        palabras[6]= "Gato"; //4
        palabras[7]= "hoz"; //3
        palabras[8]= "Indio"; //5
        palabras[9]= "jamón"; //5
        palabras[10]= "Kimono"; //6
        palabras[11]= "lima"; //4
        palabras[12]= "Mora"; //4
        palabras[13]= "niño"; //4
        palabras[14]= "Ñoño"; //4
        palabras[15]= "oso"; //3
        palabras[16]= "Pequeño"; //7
        palabras[17]= "queso"; //5
        palabras[18]= "Ratón"; //5
        palabras[19]= "sapo"; //4
        palabras[20]= "Tomate"; //6
        palabras[21]= "unión"; //5
        palabras[22]= "Vaca"; //4
        palabras[23]= "wilson"; //6
        palabras[24]= "Xilofono"; //8
        palabras[25]= "yate"; //4
        palabras[26]= "Zapato"; //6
       
        try{
            for(int cont=numeroElementos-1; cont>=0; cont--)
            {               
                trie.insertar(palabras[cont]);               
            }
        }
        catch( PalabraInvalidaException e )
        {                
            e.printStackTrace();
        } 
            
    }
    
    /**
     * Construye un trie con 20 palabras
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        trie= new Trie<String>(); 
        numeroElementos= 20; 
        peso= 79; 
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "abaco"; //5
        palabras[1]= "avance"; //5
        palabras[2]= "avanzar"; //3
        palabras[3]= "abanico"; //6
        palabras[4]= "sopa"; //4
        palabras[5]= "sopranos"; //5
        palabras[6]= "soprano"; //0
        palabras[7]= "sorpresa"; //6
        palabras[8]= "merienda"; //8
        palabras[9]= "mesa"; //2
        palabras[10]= "me"; //0
        palabras[11]= "mercado"; //5
        palabras[12]= "marca"; //4
        palabras[13]= "Marca"; //5
        palabras[14]= "bella"; //5
        palabras[15]= "belleza"; //3
        palabras[16]= "bellícimo"; //5
        palabras[17]= "buque"; //4
        palabras[18]= "zapato"; //6
        palabras[19]= "zapatos"; //1     
        
        try
        {            
            for(int cont=0; cont<numeroElementos; cont++)
            {
                trie.insertar(palabras[cont]);    
            }
                 
        }
        catch( PalabraInvalidaException e )
        {                
            e.printStackTrace();
        }   
    }

    /**
     * Verifica que la estructura en el trie sea correcta
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = ( verificador.verificarTrie( trie ) );

        assertTrue( "La estructura del trie no es correcta", estructuraBien );
    }
    
    /**
     * Prueba que la inserción de palabras en el trie se efectue correctamente
     *
     */
    public void testInsertar1()
    {
        setupEscenario1(); 
        numeroElementos= 13; 
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";        
        palabras[10]= "!*#$%&//&//()=*";
        palabras[11]= "una palabra con espacios de prueba";
        palabras[12]= "una palabra";
        
        try
        {
            //Insertar una palabra
            trie.insertar(palabras[0]);
                        
            assertNotNull("El elemento debería existir",trie.buscar(palabras[0]));
            assertEquals(palabras[0], trie.buscar(palabras[0])); 
            assertEquals("El número de letras no es el esperado", 4, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra cuyo prefijo ya existe
            trie.insertar(palabras[1]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertEquals(palabras[1], trie.buscar(palabras[1])); 
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 11, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra
            trie.insertar(palabras[2]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertEquals(palabras[2], trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 16, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra cuyo prefijo ya existe
            trie.insertar(palabras[3]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertEquals(palabras[3], trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 18, trie.darPeso());
            verificarInvariante(); 
            
            //Buscar una palabra que no debería existir
            assertNull("El elemento no debería existir", trie.buscar(palabras[4]));
            
            //Insertar una palabra que es prefijo de otra
            trie.insertar(palabras[4]);
                       
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertEquals(palabras[4], trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 18, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra que es prefijo de otra
            trie.insertar(palabras[5]);
                       
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertEquals(palabras[5], trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 18, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra intermedia
            trie.insertar(palabras[6]);
                       
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertEquals(palabras[6], trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 25, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra cuyo prefijo ya existe
            trie.insertar(palabras[7]);
                       
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertEquals(palabras[7], trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El elemento debería existir", 30, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra que vaya en la izquierda externa
            trie.insertar(palabras[8]);
                       
            assertNotNull("El elemento debería existir", trie.buscar(palabras[8]));
            assertEquals(palabras[8], trie.buscar(palabras[8]));            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 40, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra que vaya en la izquierda externa
            trie.insertar(palabras[9]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[9]));
            assertEquals(palabras[9], trie.buscar(palabras[9]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[8]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 45, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra con caracteres que no sean letras
            trie.insertar(palabras[10]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[10]));
            assertEquals(palabras[10], trie.buscar(palabras[10]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[9]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[8]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertEquals("El número de letras no es el esperado", 60, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra con espacios
            trie.insertar(palabras[11]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[11]));
            assertEquals(palabras[11], trie.buscar(palabras[11]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[9]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[8]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[10]));
            assertEquals("El número de letras no es el esperado", 94, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra con espacios
            trie.insertar(palabras[12]);
            
            assertNotNull("El elemento debería existir", trie.buscar(palabras[12]));
            assertEquals(palabras[12], trie.buscar(palabras[12]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[9]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[8]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[11]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[7]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[6]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[5]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[4]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[3]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[2]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[1]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[0]));
            assertNotNull("El elemento debería existir", trie.buscar(palabras[10]));
            assertEquals("El número de letras no es el esperado", 94, trie.darPeso());
            verificarInvariante(); 
            
        }
        catch( PalabraInvalidaException e )
        {
            
            e.printStackTrace();
        } 
    }
    
    /**
     * Prueba que al tratar de insertar palabras existentes o inválidas 
     *
     */
    public void testInsertar2()
    {
        setupEscenario2(); 
        
        //Tratar de insertar una palabra que ya exista y que este compuesta por un prefijo
        try
        {
            trie.insertar("mundialísimo");
            assertTrue("No se debío insertar la palabra", false); 
        }
        catch( PalabraInvalidaException e )
        {
            //Verificar que el peso no se haya afectado
            assertEquals("El número de letras no es el esperado", peso, trie.darPeso());
            verificarInvariante(); 
            
            //Tratar de insertar una palabra que ya exista y que sea prefijo
            try
            {
                trie.insertar("pe");
                assertTrue("No se debío insertar la palabra", false);
            }
            catch( PalabraInvalidaException e1 )
            {
                //Verificar que el peso no se haya afectado
                assertEquals("El número de letras no es el esperado", peso, trie.darPeso());
                verificarInvariante(); 
                
                //Tratar de insertar una palabra que no sea prefijo
                try
                {
                    trie.insertar("abecedario");
                    assertTrue("No se debío insertar la palabra", false);
                }
                catch( PalabraInvalidaException e2 )
                {
                    //Verificar que el peso no se haya afectado
                    assertEquals("El número de letras no es el esperado", peso, trie.darPeso());
                    verificarInvariante(); 
                }
                
            } 
            
        } 
    }
    
    
    /**
     * Prueba que la inserción de palabras en el trie se efectúe correctamente creando palabras iniciando con cada
     * letra del abecedario. Se verifica que el trie sea sensible a mayúsculas y minúsculas
     *
     */
    public void testInsertar3()
    {
        setupEscenario1(); 
        numeroElementos= 27; 
        peso= 133; 
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "Abaco"; //5 
        palabras[1]= "barco"; //5
        palabras[2]= "Casual"; //6
        palabras[3]= "dado"; //4
        palabras[4]= "Efimero"; //7
        palabras[5]= "foca"; //4
        palabras[6]= "Gato"; //4
        palabras[7]= "hoz"; //3
        palabras[8]= "Indio"; //5
        palabras[9]= "jamón"; //5
        palabras[10]= "Kimono"; //6
        palabras[11]= "lima"; //4
        palabras[12]= "Mora"; //4
        palabras[13]= "niño"; //4
        palabras[14]= "Ñoño"; //4
        palabras[15]= "oso"; //3
        palabras[16]= "Pequeño"; //7
        palabras[17]= "queso"; //5
        palabras[18]= "Ratón"; //5
        palabras[19]= "sapo"; //4
        palabras[20]= "Tomate"; //6
        palabras[21]= "unión"; //5
        palabras[22]= "Vaca"; //4
        palabras[23]= "wilson"; //6
        palabras[24]= "Xilofono"; //8
        palabras[25]= "yate"; //4
        palabras[26]= "Zapato"; //6
       
        try{
            for(int cont=numeroElementos-1; cont>=0; cont--)
            {
                //Insertar una palabra
                trie.insertar(palabras[cont]);
                assertNotNull("El elemento debería existir", trie.buscar(palabras[cont]));        
                verificarInvariante(); 
            }
            
            //Verificar el peso del trie
            assertEquals("El peso no es correcto", peso, trie.darPeso()); 
            
            //Insertar una palabra cuyo prefijo no exista
            trie.insertar("ximena");
            peso+=6; 
            assertNotNull("El elemento debería existir", trie.buscar("ximena"));            
            assertEquals("El peso no es correcto", peso, trie.darPeso());
            verificarInvariante(); 
            
            //Insertar una palabra cuyo prefijo exista
            trie.insertar("Ximena");
            assertNotNull("El elemento debería existir", trie.buscar("Ximena"));
            peso+=4; 
            assertEquals("El peso no es correcto", peso, trie.darPeso());
            verificarInvariante(); 
            
        }
        catch (PalabraInvalidaException e) {
            e.printStackTrace(); 
        }                
    }
    
    /**
     * Prueba que la inserción de palabras en el trie se efectúe correctamente.
     *
     */
    public void testInsertar4()
    {
        setupEscenario1(); 
        
        numeroElementos= 20; 
        peso= 79; 
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "abaco"; //5
        palabras[1]= "avance"; //5
        palabras[2]= "avanzar"; //3
        palabras[3]= "abanico"; //4
        palabras[4]= "sopa"; //4
        palabras[5]= "sopranos"; //5
        palabras[6]= "soprano"; //0
        palabras[7]= "sorpresa"; //6
        palabras[8]= "merienda"; //8
        palabras[9]= "mesa"; //2
        palabras[10]= "me"; //0
        palabras[11]= "mercado"; //4
        palabras[12]= "marca"; //4
        palabras[13]= "Marca"; //5
        palabras[14]= "bella"; //5
        palabras[15]= "belleza"; //3
        palabras[16]= "bellícimo"; //5
        palabras[17]= "buque"; //4
        palabras[18]= "zapato"; //6
        palabras[19]= "zapatos"; //1     
        
        try
        {            
            for(int cont=0; cont<numeroElementos; cont++)
            {
                trie.insertar(palabras[cont]);   
                assertNotNull("El elemento debería existir", trie.buscar(palabras[cont]));        
                verificarInvariante(); 
            }
            
            //Verificar el peso del trie
            assertEquals("El peso no es correcto", peso, trie.darPeso());
                 
        }
        catch( PalabraInvalidaException e )
        {                
            e.printStackTrace();
        }   
    }
    
    /**
     * Prueba que la eliminación de palabras existentes se efectúe correctamente
     *
     */
    public void testEliminar1()
    {
        setupEscenario2(); 
        
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";
        
        //Eliminar una palabra que es prefijo y verificar que no se encuentre después de la eliminación 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[4]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[4]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[3])); //Buscar palabras que empiecen con la palabra eliminada
        assertNotNull("El elemento debe existir",trie.buscar(palabras[2]));        
        assertEquals("El peso no es correcto", peso, trie.darPeso());
        verificarInvariante(); 
        
        //Eliminar una palabra cuyo prefijo es otra palabra y verificar que no exista después de la eliminación
        peso-=4; 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[1]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[1]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[0])); //Buscar palabras que empiecen con la palabra eliminada
        assertNotNull("El elemento debe existir",trie.buscar(palabras[5]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[9]));
        assertEquals("El peso no es correcto", peso, trie.darPeso());
        verificarInvariante();
        
        
        //Eliminaria una palabra que no es prefijo ni está asociada con un prefijo
        peso-=10; 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[8]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[8]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[0])); //Buscar otras palabras del trie
        assertNotNull("El elemento debe existir",trie.buscar(palabras[9]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[5]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[7]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[6]));
        assertEquals("El peso no es correcto", peso, trie.darPeso());
        verificarInvariante();

        //Eliminar una palabra interna
        peso-=5; 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[7]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[7]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[6])); //Buscar otras palabras del trie
        assertNotNull("El elemento debe existir",trie.buscar(palabras[9]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[5]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[3]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[6]));
        assertEquals("El peso no es correcto", peso, trie.darPeso());
        verificarInvariante();
        
        //Eliminar la palabra que se encuentra más a la derecha
        peso-=2; 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[3]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[3]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[2])); //Buscar otras palabras del trie
        assertNotNull("El elemento debe existir",trie.buscar(palabras[9]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[5]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[0]));
        assertNotNull("El elemento debe existir",trie.buscar(palabras[6]));
        assertEquals("El peso no es correcto", peso, trie.darPeso());
        verificarInvariante();
        
        //Eliminar el resto de palabras
        peso=0; 
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[0]));
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[2]));
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[5]));
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[6]));
        assertNotNull("El elemento se debio eliminar",trie.eliminar(palabras[9]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[9]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[6]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[5]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[4]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[2]));
        assertNull("El elemento no debe existir",trie.buscar(palabras[0]));
        assertEquals("El peso no es correcto", peso, trie.darPeso()); 
        verificarInvariante();
    }
    
    /**
     * Prueba que se retorne null al tratar de eliminar palabras que no existan
     *
     */
    public void testEliminar2()
    {
        setupEscenario3(); 
        
        String palabra= trie.eliminar("raton");
                
        assertNull("No se debió eliminar nada", palabra);
        assertEquals("No se debió eliminar nada", peso, trie.darPeso()); 
        
        palabra= trie.eliminar("gato");
        
        assertNull("No se debió eliminar nada", palabra);
        assertEquals("No se debió eliminar nada", peso, trie.darPeso());
        
        palabra= trie.eliminar("casona");
        
        assertNull("No se debió eliminar nada", palabra);
        assertEquals("No se debió eliminar nada", peso, trie.darPeso());
        
        palabra= trie.eliminar("Niño");
        
        assertNull("No se debió eliminar nada", palabra);
        assertEquals("No se debió eliminar nada", peso, trie.darPeso());
        
        verificarInvariante();
    }
    
    /**
     * Prueba que el método buscar funcione correctamente
     *
     */
    public void testBuscar1()
    {
        setupEscenario2(); 
        
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";
        
        //Verificar que todas las palabras que tiene el trie sean encontradas
        String palabra; 
        
        for(int cont=0; cont<numeroElementos; cont++)
        {
            palabra= trie.buscar(palabras[cont]);
            assertNotNull("El elemento debería existir",palabra); 
            assertEquals("El elemento no se busco correctamente",palabras[cont], palabra);
        }
       
        //Verificar que todas las palabras que tiene el trie sean encontradas
        setupEscenario3(); 
        
        palabras= new String[numeroElementos];
        
        palabras[0]= "Abaco"; 
        palabras[1]= "barco";
        palabras[2]= "Casual";
        palabras[3]= "dado";
        palabras[4]= "Efimero";
        palabras[5]= "foca";
        palabras[6]= "Gato";
        palabras[7]= "hoz";
        palabras[8]= "Indio"; 
        palabras[9]= "jamón";
        palabras[10]= "Kimono";
        palabras[11]= "lima";
        palabras[12]= "Mora";
        palabras[13]= "niño"; 
        palabras[14]= "Ñoño"; 
        palabras[15]= "oso"; 
        palabras[16]= "Pequeño";
        palabras[17]= "queso";
        palabras[18]= "Ratón";
        palabras[19]= "sapo";
        palabras[20]= "Tomate";
        palabras[21]= "unión";
        palabras[22]= "Vaca";
        palabras[23]= "wilson";
        palabras[24]= "Xilofono";
        palabras[25]= "yate";
        palabras[26]= "Zapato";
        
        for(int cont=0; cont<numeroElementos; cont++)
        {
            palabra= trie.buscar(palabras[cont]);
            assertNotNull("El elemento debería existir",palabra); 
            assertEquals("El elemento no se busco correctamente",palabras[cont], palabra);
        }
       
    }
    
    /**
     * Prueba que el método buscar retorne null cuando se busque una palabra que no exista
     *
     */
    public void testBuscar2()
    {
        setupEscenario2(); 
        
        //Buscar una palabra que no exista
        String palabra= trie.buscar("la palabra");
        assertNull("El elemento no debería existir",palabra);
        
        //Buscar una palabra correspondiente a un prefijo que existe pero que no es palabra
        palabra= trie.buscar("pep");
        assertNull("El elemento no debería existir",palabra);
        
        //Buscar una palabra que existe pero escrita con mayúsculas
        palabra= trie.buscar("Mundialísimo");
        assertNull("El elemento no debería existir",palabra);
        
        //Buscar una palabra que existe pero escrita con mayúsculas
        palabra= trie.buscar("caSaMen");
        assertNull("El elemento no debería existir",palabra);
        
        //Buscar una palabra que existe pero con espacios al inicio
        palabra= trie.buscar(" perro");
        assertNull("El elemento no debería existir",palabra);
        
        //Buscar una palabra que existe pero con espacios al final
        palabra= trie.buscar("casamentera ");
        assertNull("El elemento no debería existir",palabra);
        
    }
    
    /**
     * Prueba que el método buscarPorPrefijo funcione correctamente
     *
     */
    public void testBuscarPorPrefijo1()
    {
        setupEscenario2();
        
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";
        
        Lista<String> lista= trie.buscarPorPrefijo("pe");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 3, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[4], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[3], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[2], lista.darElemento(2));
        
        lista= trie.buscarPorPrefijo("ca");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[0], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[5], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[1], lista.darElemento(2));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[9], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("a");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[8], lista.darElemento(0));
        
        lista= trie.buscarPorPrefijo("m");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[7], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("mun");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[7], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("perro");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[2], lista.darElemento(0));  
        
        
        //Prueba con otro escenario
        setupEscenario4(); 
        palabras= new String[numeroElementos];
        
        palabras[0]= "abaco"; 
        palabras[1]= "avance";
        palabras[2]= "avanzar";
        palabras[3]= "abanico";
        palabras[4]= "sopa";
        palabras[5]= "sopranos";
        palabras[6]= "soprano";
        palabras[7]= "sorpresa";
        palabras[8]= "merienda";
        palabras[9]= "mesa";
        palabras[10]= "me";
        palabras[11]= "mercado";
        palabras[12]= "marca";
        palabras[13]= "Marca";
        palabras[14]= "bella";
        palabras[15]= "belleza";
        palabras[16]= "bellícimo";
        palabras[17]= "buque";
        palabras[18]= "zapato";
        palabras[19]= "zapatos";
        
        lista= trie.buscarPorPrefijo("a");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[0], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[3], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[1], lista.darElemento(2));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[2], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("ab");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[0], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[3], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("aba");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[0], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[3], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("abac");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[0], lista.darElemento(0));
        
        lista= trie.buscarPorPrefijo("aban");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[3], lista.darElemento(0));
        
        lista= trie.buscarPorPrefijo("avan");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[1], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[2], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("s");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[4], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[5], lista.darElemento(2)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[7], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("so");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[4], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[5], lista.darElemento(2)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[7], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("sop");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 3, lista.darLongitud());
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[4], lista.darElemento(0)); 
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[5], lista.darElemento(2));  
        
        lista= trie.buscarPorPrefijo("sopr");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[6], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[5], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("sor");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[7], lista.darElemento(0));
        
        lista= trie.buscarPorPrefijo("m");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 5, lista.darLongitud());        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[12], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[10], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[11], lista.darElemento(2));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[8], lista.darElemento(3));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[9], lista.darElemento(4));
        
        lista= trie.buscarPorPrefijo("me");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());                
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[10], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[11], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[8], lista.darElemento(2));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[9], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("mer");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[11], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[8], lista.darElemento(1));
        
        lista= trie.buscarPorPrefijo("mar");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[12], lista.darElemento(0));
        
        lista= trie.buscarPorPrefijo("b");
        
        //Verificar que la lista contenga todos los elementos que debe        
        assertEquals("El número de elementos de la lista no es el esperado", 4, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[14], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[15], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[16], lista.darElemento(2));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[17], lista.darElemento(3));
        
        lista= trie.buscarPorPrefijo("be");
        //Verificar que la lista contenga todos los elementos que debe        
        assertEquals("El número de elementos de la lista no es el esperado", 3, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[14], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[15], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[16], lista.darElemento(2));
        
        lista= trie.buscarPorPrefijo("bel");
        //Verificar que la lista contenga todos los elementos que debe      
        assertEquals("El número de elementos de la lista no es el esperado", 3, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[14], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[15], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[16], lista.darElemento(2));
        
        lista= trie.buscarPorPrefijo("bell");
        //Verificar que la lista contenga todos los elementos que debe      
        assertEquals("El número de elementos de la lista no es el esperado", 3, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[14], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[15], lista.darElemento(1));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[16], lista.darElemento(2));
        
        lista= trie.buscarPorPrefijo("bu");
        //Verificar que la lista contenga todos los elementos que debe      
        assertEquals("El número de elementos de la lista no es el esperado", 1, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[17], lista.darElemento(0));   
        
        lista= trie.buscarPorPrefijo("zapato");
        //Verificar que la lista contenga todos los elementos que debe      
        assertEquals("El número de elementos de la lista no es el esperado", 2, lista.darLongitud());                        
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[18], lista.darElemento(0));
        assertEquals("La lista con los prefijos no se contruyó correctamente", palabras[19], lista.darElemento(1));
        
    }
    

    /**
     * Prueba que el método buscar por prefijo retorne una lista vacia cuando se den prefijos inexistentes
     *
     */
    public void testBuscarPorPrefijo2()
    {
        setupEscenario4(); 
        
        Lista<String> lista= trie.buscarPorPrefijo("abas");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 0, lista.darLongitud());
        
        lista= trie.buscarPorPrefijo("Abaco");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 0, lista.darLongitud());
    
        lista= trie.buscarPorPrefijo("mE");
    
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 0, lista.darLongitud());
        
        lista= trie.buscarPorPrefijo("belL");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 0, lista.darLongitud());
        
        //Prueba con otro escenario
        setupEscenario1(); 
        lista= trie.buscarPorPrefijo("abaco");
        
        //Verificar que la lista contenga todos los elementos que debe
        assertEquals("El número de elementos de la lista no es el esperado", 0, lista.darLongitud());       
    }
    
    /**
     * Prueba que el método de recorrido por inorden funcione correctamente
     *
     */
    public void testInorden()
    {
        setupEscenario2(); 
        Iterador<String> it= trie.inorden();
        
        String[] palabras= new String[numeroElementos];
        
        palabras[0]= "casa"; 
        palabras[1]= "casamentera";
        palabras[2]= "perro";
        palabras[3]= "pepe";
        palabras[4]= "pe";
        palabras[5]= "casamen";
        palabras[6]= "mundial";
        palabras[7]= "mundialísimo";
        palabras[8]= "abecedario";
        palabras[9]= "casamiento";
        
        //Verificar que el recorrido en inorden se retorne correctamente
        assertEquals("El recorrido no se realizó de forma correcta", palabras[8],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[1],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[5],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[9],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[0],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[7],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[6],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[3],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[4],it.darSiguiente());
        assertEquals("El recorrido no se realizó de forma correcta", palabras[2],it.darSiguiente());                
    }
    
    
    /**
     * Prueba que el método de dar peso funcione correctamente
     *
     */
    public void testDarPeso()
    {
        setupEscenario1(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("El peso del trie no es correcto", peso, trie.darPeso());
        
        setupEscenario2(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("El peso del trie no es correcto", peso, trie.darPeso());
        
        setupEscenario3(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("El peso del trie no es correcto", peso, trie.darPeso());
        
        setupEscenario4(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("El peso del trie no es correcto", peso, trie.darPeso());
    }
    
    /**
     * Prueba que el método dar altura funcione correctamente
     *
     */
    public void testDarAltura()
    {
        setupEscenario1(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("La altura del trie no es correcta", 1, trie.darAltura());
        
        setupEscenario2(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("La altura del trie no es correcta", 13, trie.darAltura());
        
        setupEscenario3(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("La altura del trie no es correcta", 9, trie.darAltura());
        
        setupEscenario4(); 
        
        //Verificar que el peso del trie sea correcto
        assertEquals("La altura del trie no es correcta", 10, trie.darAltura());
        
        
    }
}
