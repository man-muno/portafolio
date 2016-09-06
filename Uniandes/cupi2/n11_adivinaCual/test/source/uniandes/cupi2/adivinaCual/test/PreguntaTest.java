/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Mu�oz - 27-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.adivinaCual.mundo.Pregunta;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Pregunta est�n correctamente implementados
 */
public class PreguntaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas. Representa una pregunta.
     */
    private Pregunta pregunta;

    /**
     * Es la clase donde se har�n las pruebas. Representa un animal
     */
    private Pregunta animal;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye dos preguntas, una con la pregunta y otra con un animal
     */
    private void setupEscenario1( )
    {
        Pregunta animal1 = new Pregunta( "Animal1", "./data/prueba" );
        Pregunta animal2 = new Pregunta( "Animal2", "./data/prueba" );
        pregunta = new Pregunta( "La pregunta", animal1, animal2 );
        animal = new Pregunta( "Animal", "./data/prueba" );
    }

    /**
     * Construye un peque�o �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos
     */
    private void setupEscenario2( )
    {
        Pregunta animal2 = new Pregunta( "Animal2", "./data/prueba" );
        Pregunta animal3 = new Pregunta( "Animal3", "./data/prueba" );
        Pregunta pregunta1 = new Pregunta( "Pregunta 1", animal2, animal3 );
        Pregunta animal1 = new Pregunta( "Animal1", "./data/prueba" );

        pregunta = new Pregunta( "La pregunta", animal1, pregunta1 );

    }

    /**
     * Este m�todo se encarga de verificar los m�todos constructores<br>
     * <b> M�todos a probar: </b> <br>
     * Pregunta<br>
     * obtenerNegativa<br>
     * obtenerPositiva<br>
     * obtenerPregunta<br>
     * obtenerNombreAnimal<br>
     * obtenerRuta<br>
     * <b> Objetivo: </b> Probar la inicializaci�n correcta de una pregunta<br>
     * <b> Resultados esperados: </b> <br>
     * Se inicializa una pregunta como hoja y una pregunta como una pregunta con sus dos hijos<br>
     */
    public void testPregunta( )
    {
        setupEscenario1( );
        assertNotNull( "La respuesta negativa no deber�a ser null", pregunta.darRespuestaNegativa( ) );
        assertNotNull( "La respuesta positiva no deber�a ser null", pregunta.darRespuestaPositiva( ) );
        assertNotNull( "La descripci�n de la pregunta no deber�a ser null", pregunta.darDescripcion( ) );
        assertNull( "El nombre el animal deber�a ser null", pregunta.darNombreAnimal( ) );
        assertNull( "La ruta de la imagen deber�a ser null", pregunta.darRuta( ) );
        assertNull( "La pregunta deber�a ser null", animal.darDescripcion( ) );
        assertNull( "Una hoja no deber�a tener respuesta negativa", animal.darRespuestaNegativa( ) );
        assertNull( "Una hoja no deber�a tener respuesta positiva", animal.darRespuestaPositiva( ) );
        assertNotNull( "El nombre el animal no deber�a ser null", animal.darNombreAnimal( ) );
        assertNotNull( "La ruta de la imagen no deber�a ser null", animal.darRuta( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerPeso<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un �rbol de un solo elemento<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del �rbol para cuando solo hay un elemento.<br>
     */
    public void testDarPesoUnElemento( )
    {
        setupEscenario1( );
        assertEquals( "El peso del �rbol con un solo elemento es 1", 1, animal.darPeso( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerPeso<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un �rbol de un elemento con dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del �rbol para cuando hay varios elementos<br>
     */
    public void testDarPesoArbolSencillo( )
    {
        setupEscenario1( );
        assertEquals( "El peso del �rbol debe ser 3", 3, pregunta.darPeso( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerPeso<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerPeso<br>
     * <b> Objetivo: </b> Probar el calculo del peso de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula el peso del �rbol para cuando hay varios elementos<br>
     */
    public void testDarPesoArbol( )
    {
        setupEscenario2( );
        assertEquals( "El peso del �rbol debe ser 5", 5, pregunta.darPeso( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerAltura<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un �rbol de un elemento<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del �rbol para cuando solo hay un elemento, y para cuando hay varios<br>
     */
    public void testDarAlturaUnElemento( )
    {
        setupEscenario1( );
        assertEquals( "La altura del �rbol con un solo elemento es 1", 1, animal.darAltura( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerAltura<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un �rbol de un elemento con dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del �rbol para cuando solo hay varios elementos<br>
     */
    public void testDarAlturaSencillo( )
    {
        setupEscenario1( );
        assertEquals( "La altura del �rbol debe ser 2", 2, pregunta.darAltura( ) );
    }

    /**
     * Este m�todo se encarga de verificar los m�todos obtenerAltura<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerAltura<br>
     * <b> Objetivo: </b> Probar el calculo de la altura de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se calcula la altura del �rbol para cuando solo hay varios elementos<br>
     */
    public void testDarAltura( )
    {
        setupEscenario2( );
        assertEquals( "La altura del �rbol debe ser 3", 3, pregunta.darAltura( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo inorden<br>
     * <b> M�todos a probar: </b> <br>
     * inorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en inorden de un �rbol elemento con dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en inorden
     */
    public void testInordenSencillo( )
    {
        setupEscenario1( );
        ArrayList resultado = new ArrayList( );
        pregunta.darInorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 3", resultado.size( ) == 3 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "Animal1", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "La pregunta", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercer elemento es incorrecto", "Animal2", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo inorden<br>
     * <b> M�todos a probar: </b> <br>
     * inorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en inorden de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en inorden
     */
    public void testInorden( )
    {
        setupEscenario2( );
        ArrayList resultado = new ArrayList( );
        pregunta.darInorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 5", resultado.size( ) == 5 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "Animal1", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "La pregunta", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercer elemento es incorrecto", "Animal2", elemento );
                    break;
                case 3:
                    assertEquals( "El nombre del cuarto elemento es incorrecto", "Pregunta 1", elemento );
                    break;
                case 4:
                    assertEquals( "El nombre del quinto elemento es incorrecto", "Animal3", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo preorden<br>
     * <b> M�todos a probar: </b> <br>
     * preorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en preorden de un �rbol elemento con dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en preorden
     */
    public void testPreordenSencillo( )
    {
        setupEscenario1( );
        ArrayList resultado = new ArrayList( );
        pregunta.darPreorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 3", resultado.size( ) == 3 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "La pregunta", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "Animal1", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercer elemento es incorrecto", "Animal2", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo preorden<br>
     * <b> M�todos a probar: </b> <br>
     * preorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en preorden de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en preorden
     */
    public void testPreorden( )
    {
        setupEscenario2( );
        ArrayList resultado = new ArrayList( );
        pregunta.darPreorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 5", resultado.size( ) == 5 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "La pregunta", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "Animal1", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercer elemento es incorrecto", "Pregunta 1", elemento );
                    break;
                case 3:
                    assertEquals( "El nombre del cuarto elemento es incorrecto", "Animal2", elemento );
                    break;
                case 4:
                    assertEquals( "El nombre del quinto elemento es incorrecto", "Animal3", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo postorden<br>
     * <b> M�todos a probar: </b> <br>
     * postorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en postordende un �rbol elemento con dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en postorden
     */
    public void testPostordenSencillo( )
    {
        setupEscenario1( );
        ArrayList resultado = new ArrayList( );
        pregunta.darPostorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 3", resultado.size( ) == 3 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "Animal1", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "Animal2", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercero elemento es incorrecto", "La pregunta", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo postorden<br>
     * <b> M�todos a probar: </b> <br>
     * postorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en postorden de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en postorden
     */
    public void testPostorden( )
    {
        setupEscenario2( );
        ArrayList resultado = new ArrayList( );
        pregunta.darPostorden( resultado );
        assertNotNull( "El resultado del recorrido no puede ser null.", resultado );
        assertTrue( "El tama�o de la lista debe ser 5", resultado.size( ) == 5 );
        for( int i = 0; i < resultado.size( ); i++ )
        {
            String elemento = ( String )resultado.get( i );
            assertNotNull( "Ning�n elemento de los resultados puede ser null", elemento );
            switch( i )
            {
                case 0:
                    assertEquals( "El nombre del primer elemento es incorrecto", "Animal1", elemento );
                    break;
                case 1:
                    assertEquals( "El nombre del segundo elemento es incorrecto", "Animal2", elemento );
                    break;
                case 2:
                    assertEquals( "El nombre del tercer elemento es incorrecto", "Animal3", elemento );
                    break;
                case 4:
                    assertEquals( "El nombre del cuarto elemento es incorrecto", "La pregunta", elemento );
                    break;
                case 3:
                    assertEquals( "El nombre del quinto elemento es incorrecto", "Pregunta 1", elemento );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarPregunta<br>
     * <b> M�todos a probar: </b> <br>
     * agregarPregunta<br>
     * darAltura<br>
     * darPeso<br>
     * darPregunta<br>
     * darRuta<br>
     * <b> Objetivo: </b> Probar que se agregue correctamente una nueva pregunta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agrega una nueva pregunta con dos respuestas(animales)<br>
     */
    public void testAgregarPregunta( )
    {
        setupEscenario1( );
        assertNotNull( "El nombre del animal no deber�a ser null", animal.darNombreAnimal( ) );
        assertNotNull( "La ruta del animal no deber�a ser null", animal.darRuta( ) );
        assertNull( "El animal no deber�a tener hijo negativo", animal.darRespuestaNegativa( ) );
        assertNull( "El animal no deber�a tener hijo positivo", animal.darRespuestaPositiva( ) );
        assertNull( "La descripcion deber�a ser null", animal.darDescripcion( ) );
        animal.agregarPregunta( "Descripcion de la nueva pregunta", "Nuevo animal" );
        assertNull( "El nombre del animal no deber�a ser null", animal.darNombreAnimal( ) );
        assertNull( "La ruta del animal no deber�a ser null", animal.darRuta( ) );
        assertNotNull( "El animal no deber�a tener hijo negativo", animal.darRespuestaNegativa( ) );
        assertNotNull( "El animal no deber�a tener hijo positivo", animal.darRespuestaPositiva( ) );
        assertNotNull( "La descripcion deber�a ser null", animal.darDescripcion( ) );
        assertEquals( "La descripcion de la pregunta es invalida", "Descripcion de la nueva pregunta", animal.darDescripcion( ) );

        Pregunta animalViejo = animal.darRespuestaNegativa( );
        assertNotNull( "El nombre del animal no deber�a ser null", animalViejo.darNombreAnimal( ) );
        assertNotNull( "La ruta del animal no deber�a ser null", animalViejo.darRuta( ) );
        assertNull( "El animal no deber�a tener hijo negativo", animalViejo.darRespuestaPositiva( ) );
        assertNull( "El animal no deber�a tener hijo positivo", animalViejo.darRespuestaNegativa( ) );
        assertNull( "La descripcion deber�a ser null", animalViejo.darDescripcion( ) );
        assertEquals( "El nombre del animal por la rama positiva es inv�lido", "Animal", animalViejo.darNombreAnimal( ) );
        assertEquals( "La ruta del animal por la rama positiva es invalida", "./data/prueba", animalViejo.darRuta( ) );

        Pregunta animaNuevo = animal.darRespuestaPositiva( );
        assertNotNull( "El nombre del animal no deber�a ser null", animaNuevo.darNombreAnimal( ) );
        assertNotNull( "La ruta del animal no deber�a ser null", animaNuevo.darRuta( ) );
        assertNull( "El animal no deber�a tener hijo negativo", animaNuevo.darRespuestaPositiva( ) );
        assertNull( "El animal no deber�a tener hijo positivo", animaNuevo.darRespuestaNegativa( ) );
        assertNull( "La descripcion deber�a ser null", animaNuevo.darDescripcion( ) );
        assertEquals( "El nombre del animal por la rama positiva es inv�lido", "Nuevo animal", animaNuevo.darNombreAnimal( ) );
        assertEquals( "La ruta del animal por la rama positiva es invalida", "", animaNuevo.darRuta( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darHojas<br>
     * <b> M�todos a probar: </b> <br>
     * darHojas<br>
     * <b> Objetivo: </b> Probar que se obtengan las hojas de un �rbol con tres elementos<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la lista de hojas. Se espera que tenga 2 elementos<br>
     * 2. Se espera que todos los elementos de la lista sean hojas<br>
     */
    public void testDarHojasSencillo( )
    {
        setupEscenario1( );
        List hojas = new ArrayList( );
        pregunta.darHojas( hojas );
        assertEquals( "La lista de hojas deber�a tener 2 elementos", 2, hojas.size( ) );
        for( int i = 0; i < hojas.size( ); i++ )
        {
            if( ! ( ( Pregunta )hojas.get( i ) ).esHoja( ) )
                fail( "La lista deber�a contener solamente hojas" );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darHojas<br>
     * <b> M�todos a probar: </b> <br>
     * darHojas<br>
     * <b> Objetivo: </b> Probar que se obtengan las hojas de un �rbol con ra�z e hijo derecho e izquierdo. El hijo izquierdo a su vez tiene dos hijos<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la lista de hojas. Se espera que tenga 3 elementos<br>
     * 2. Se espera que todos los elementos de la lista sean hojas<br>
     */
    public void testDarHojas( )
    {
        setupEscenario2( );
        List hojas = new ArrayList( );
        pregunta.darHojas( hojas );
        assertEquals( "La lista de hojas deber�a tener 3 elementos", 3, hojas.size( ) );
        for( int i = 0; i < hojas.size( ); i++ )
        {
            if( ! ( ( Pregunta )hojas.get( i ) ).esHoja( ) )
                fail( "La lista deber�a contener solamente hojas" );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darHojas<br>
     * <b> M�todos a probar: </b> <br>
     * darHojas<br>
     * <b> Objetivo: </b> Probar que se evalu� correctamente los elementos que son hojas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se eval�a un elemento que representa un animal e informa que es una hoja<br>
     * 2. Se eval�a un elemento que representa una pregunta e informa que es una hoja<br>
     */
    public void testEsHoja( )
    {
        setupEscenario1( );
        assertTrue( "Una pregunta definida como animal deber�a ser hoja", animal.esHoja( ) );
        assertFalse( "Una pregunta que no es animal no deber�a ser hoja", pregunta.esHoja( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo buscarAnimal<br>
     * <b> M�todos a probar: </b> <br>
     * buscarAnimal<br>
     * <b> Objetivo: </b> Probar que se busque correctamente un animal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentra un animal que existe en el �rbol<br>
     * 2. No se encuentra una pregunta que exista en el �rbol<br>
     * 3. No se encuentra una animal que no exista en el �rbol<br>
     */
    public void testBuscarAnimal( )
    {
        setupEscenario2( );
        assertTrue( "Deber�a encontrar al animal, ya que esta en el �rbol", pregunta.buscarAnimal( "Animal1" ) );
        assertFalse( "No deber�a encontrar al animal, ya que lo que se esta buscando no es una hoja", pregunta.buscarAnimal( "Pregunta 1" ) );
        assertFalse( "No deber�a encontrar al animal, ya que no existe", pregunta.buscarAnimal( "laskdf" ) );
    }
}