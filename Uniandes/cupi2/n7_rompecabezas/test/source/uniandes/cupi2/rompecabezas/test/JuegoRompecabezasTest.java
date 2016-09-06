/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JuegoRompecabezasTest.java,v 1.3 2007/01/16 14:36:31 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Mu�oz - 02-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.rompecabezas.mundo.Ficha;
import uniandes.cupi2.rompecabezas.mundo.JuegoRompecabezas;
import uniandes.cupi2.rompecabezas.mundo.Figura;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase JuegoRompecabezas est�n correctamente implementados
 */
public class JuegoRompecabezasTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private JuegoRompecabezas juego;

    /**
     * La cantidad de figuras que hay en el juego.
     */
    private int cantidadFiguras;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Juego de Rompecabezas sin figuras
     */
    private void setupEscenario1( )
    {
        juego = new JuegoRompecabezas( );
        cantidadFiguras = 0;
    }

    /**
     * Construye un nuevo Juego de Rompecabezas con figuras sin fichas
     */
    private void setupEscenario2( )
    {
        juego = new JuegoRompecabezas( );

        Figura f1 = new Figura( "nombre1", Figura.DIFICULTAD_BAJA, 2, 2, "a", "ruta1", new Ficha[2][2] );
        juego.agregarFigura( f1 );

        Figura f2 = new Figura( "nombre2", Figura.DIFICULTAD_MEDIA, 3, 4, "b", "ruta2", new Ficha[3][4] );
        juego.agregarFigura( f2 );

        Figura f3 = new Figura( "nombre3", Figura.DIFICULTAD_SUPERIOR, 4, 3, "c", "ruta3", new Ficha[4][3] );
        juego.agregarFigura( f3 );

        cantidadFiguras = 3;
    }

    /**
     * Construye un nuevo Juego de Rompecabezas con figuras con fichas
     */
    private void setupEscenario3( )
    {
        juego = new JuegoRompecabezas( );

        Ficha fichas1[][] = new Ficha[3][4];
        fichas1[ 0 ][ 0 ] = new Ficha( "f1imagen1", 1, 0, 0, Ficha.ESQUINA );
        fichas1[ 0 ][ 1 ] = new Ficha( "f1imagen2", 2, 0, 1, Ficha.BORDE );
        fichas1[ 0 ][ 2 ] = new Ficha( "f1imagen3", 3, 0, 2, Ficha.BORDE );
        fichas1[ 0 ][ 3 ] = new Ficha( "f1imagen4", 4, 0, 3, Ficha.ESQUINA );
        fichas1[ 1 ][ 0 ] = new Ficha( "f1imagen5", 5, 1, 0, Ficha.BORDE );
        fichas1[ 1 ][ 1 ] = new Ficha( "f1imagen6", 6, 1, 1, Ficha.INTERNA );
        fichas1[ 1 ][ 2 ] = new Ficha( "f1imagen7", 7, 1, 2, Ficha.INTERNA );
        fichas1[ 1 ][ 3 ] = new Ficha( "f1imagen8", 8, 1, 3, Ficha.BORDE );
        fichas1[ 2 ][ 0 ] = new Ficha( "f1imagen9", 9, 2, 0, Ficha.ESQUINA );
        fichas1[ 2 ][ 1 ] = new Ficha( "f1imagen10", 10, 2, 1, Ficha.BORDE );
        fichas1[ 2 ][ 2 ] = new Ficha( "f1imagen11", 11, 2, 2, Ficha.BORDE );
        fichas1[ 2 ][ 3 ] = new Ficha( "f1imagen12", 12, 2, 3, Ficha.ESQUINA );

        Figura f1 = new Figura( "Figura1 de prueba", Figura.DIFICULTAD_MEDIA, 3, 4, "Categor�a de Prueba1", "Ruta de Prueba1", fichas1 );
        juego.agregarFigura( f1 );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * JuegoRompecabezas<br>
     * obtenerArmados<br>
     * obtenerIntentos<br>
     * obtenerNombresFiguras<br>
     * obtenerFiguraActual<br>
     * obtenerUltimoTiempo<br>
     * <b> Objetivo: </b> Probar la inicializaci�n correcta de un Juego de Rompecabezas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Los atributos del juego son inicializados correctamente <br>
     */
    public void testJuego( )
    {
        setupEscenario1( );
        assertEquals( "El valor inicial de los juegos armados debe ser 0", 0, juego.obtenerArmados( ) );
        assertEquals( "El valor inicial de los juegos intentados debe ser 0", 0, juego.obtenerIntentos( ) );
        assertNotNull( "La lista de figuras deber�a estar inicializada", juego.obtenerFiguras( ) );
        assertTrue( "La cantidad de figuras debe ser cero", juego.obtenerFiguras( ).size( ) == 0 );
        assertNull( "La figura actual deber�a ser null, porque no se ha comenzado el juego", juego.obtenerFiguraActual( ) );
        assertEquals( "El tiempo de juego deber�a ser 0", 0, juego.darTiempoTotal( ) );

    }

    /**
     * Verifica el m�todo agregarFigura agregando correctamente una figura. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarFigura, buscarFigura, obtenerFiguras. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarFigura() sea capaz de agregar una figura al juego. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una figura a un vector de figuras vac�o, el tama�o del vector debe ser 1. <br>
     * 2. Al buscar (por nombre) una figura previamente agregada se debe obtener una posici�n diferente de -1 (se debe encontrar) y los datos de la figura en esa posici�n
     * deben corresponder a los de la figura con el nombre correspondiente. <br>
     * 
     */
    public void testAgregarFigura1( )
    {
        // Configura los datos de prueba
        setupEscenario2( );
        ArrayList figurasInicial = juego.obtenerFiguras( );
        assertEquals( "El n�mero de figuras del juego no es correcto", cantidadFiguras, figurasInicial.size( ) );

        // Se agrega una figura
        Figura f1 = new Figura( "nombre4", Figura.DIFICULTAD_BAJA, 4, 4, "a", "ruta4", new Ficha[4][4] );
        juego.agregarFigura( f1 );
        cantidadFiguras++;
        assertEquals( "El n�mero de figuras del juego no es correcto", cantidadFiguras, figurasInicial.size( ) );

        ArrayList figurasFinal = juego.obtenerFiguras( );
        assertEquals( "El n�mero de figuras del juego no es correcto ", cantidadFiguras, figurasFinal.size( ) );

        assertTrue( "La figura debe existir en el vector", juego.buscarFigura( "nombre4" ) != -1 );

    }

    /**
     * Verifica el m�todo agregarFigura agregando una figura con nombre repetido. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarFigura, buscarFigura, obtenerFiguras. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarFigura() no agregue una figura en el juego cuando su nombre ya pertenece a otra figura. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una figura con nombre repetido el n�mero de figuras se debe mantener igual y la informaci�n de las figuras existentes no debe haberse alterado.
     */
    public void testAgregarFigura2( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        ArrayList figuras = juego.obtenerFiguras( );
        Figura f = ( Figura )figuras.get( 0 );

        boolean agregado = juego.agregarFigura( f );
        assertFalse( "La figura no deber�a haberse agregado", agregado );
        assertEquals( "El n�mero de figuras del juego no es correcto ", cantidadFiguras, figuras.size( ) );
    }

    /**
     * Verifica el m�todo buscarFigura buscando una figura que se sabe que deber�a encontrarse. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarFigura. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarFigura() sea capaz de encontrar figuras registrados en la bolsa de empleo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar una figura previamente agregada se debe obtener una posici�n diferente de -1. <br>
     * 2. Al buscar una figura que no exista la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarFigura( )
    {
        // Configura los datos de prueba
        setupEscenario2( );
        ArrayList figuras = juego.obtenerFiguras( );
        Figura f0 = ( Figura )figuras.get( 0 );
        String nombreFigura = f0.obtenerNombre( );
        juego.establecerFiguraActual( f0 );
        juego.ordenarFigurasPorDificultad( );

        int posicion = juego.buscarFigura( nombreFigura );
        assertTrue( "No se encontr� la figura", posicion != -1 );

        figuras = juego.obtenerFiguras( );
        Figura fn = ( Figura )figuras.get( posicion );
        assertEquals( "No se encontr� la figura buscada", fn.obtenerNombre( ), nombreFigura );

        posicion = juego.buscarFigura( "La figura no existe" );
        assertEquals( "No se encontr� la figura buscada", -1, posicion );
    }

    /**
     * Verifica el m�todo buscarBinarioPorNombre buscando una figura que se sabe que deber�a encontrarse. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarBinarioPorNombre. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarBinarioPorNombre() sea capaz de encontrar figuras del rompecabezas. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar una figura previamente agregada se debe obtener una posici�n diferente de -1. <br>
     * 2. Al buscar una figura que no exista la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarBinarioPorNombre( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        juego.ordenarFigurasPorNombre( );
        ArrayList figuras = juego.obtenerFiguras( );

        // Busca la primera figura
        Figura figura = ( Figura )figuras.get( 0 );
        String nombreAspirante = figura.obtenerNombre( );

        int posicion = juego.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� la figura", posicion != -1 );

        Figura figuraNueva = ( Figura )figuras.get( posicion );
        assertEquals( "No se encontr� la figura buscada", figuraNueva.obtenerNombre( ), nombreAspirante );

        // Busca la figura del medio
        figura = ( Figura )figuras.get( cantidadFiguras / 2 );
        nombreAspirante = figura.obtenerNombre( );

        posicion = juego.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� la figura", posicion != -1 );

        figuraNueva = ( Figura )figuras.get( posicion );
        assertEquals( "No se encontr� la figura buscada", figuraNueva.obtenerNombre( ), nombreAspirante );

        // Busca la figura del final
        figura = ( Figura )figuras.get( cantidadFiguras - 1 );
        nombreAspirante = figura.obtenerNombre( );

        posicion = juego.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� la figura", posicion != -1 );

        figuraNueva = ( Figura )figuras.get( posicion );
        assertEquals( "No se encontr� la figura buscada", figuraNueva.obtenerNombre( ), nombreAspirante );

        // Busca una figura que no existe
        posicion = juego.buscarFigura( "La figura no existe" );
        assertEquals( "No se encontr� la figura buscada", -1, posicion );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo ordenarFigurasPorCategoria<br>
     * <b> M�todos a probar: </b> <br>
     * ordenarFigurasPorCategoria<br>
     * obtenerFiguras<br>
     * <b> Objetivo: </b> Probar que se ordenen las figuras de acuerdo a su categor�a<br>
     * <b> Resultados esperados: </b> <br>
     * La cantidad de figuras es la misma despu�s de ordenado<br>
     * Las figuras fueron ordenadas lexicogr�ficamente por su categor�a<br>
     */
    public void testOrdenarPorCategoria( )
    {
        setupEscenario2( );
        ArrayList figuras = juego.obtenerFiguras( );
        juego.ordenarFigurasPorCategoria( );
        ArrayList figuras2 = juego.obtenerFiguras( );
        assertTrue( "La cantidad de figuras deber�a ser la misma", figuras.size( ) == figuras2.size( ) );
        for( int i = 0; i < figuras2.size( ); i++ )
        {
            String cat1 = ( ( Figura )figuras2.get( i ) ).obtenerCategoria( );
            for( int j = i; j < figuras2.size( ); j++ )
            {
                String cat2 = ( ( Figura )figuras2.get( j ) ).obtenerCategoria( );
                assertTrue( "Las figuras no est�n organizadas por categor�a", cat1.compareToIgnoreCase( cat2 ) <= 0 );
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo ordenarFigurasPorDificultad<br>
     * <b> M�todos a probar: </b> <br>
     * ordenarFigurasPorDificultad<br>
     * obtenerFiguras<br>
     * <b> Objetivo: </b> Probar que se ordenen las figuras de acuerdo a su dificultad<br>
     * <b> Resultados esperados: </b> <br>
     * La cantidad de figuras es la misma despu�s de ordenado<br>
     * Las figuras fueron ordenados lexicogr�ficamente por su dificultad<br>
     */
    public void testOrdenarPorDificultad( )
    {
        setupEscenario2( );
        ArrayList figuras = juego.obtenerFiguras( );
        juego.ordenarFigurasPorDificultad( );
        ArrayList figuras2 = juego.obtenerFiguras( );
        assertTrue( "La cantidad de figuras deber�a ser la misma", figuras.size( ) == figuras2.size( ) );
        for( int i = 0; i < figuras2.size( ); i++ )
        {
            String dificultad1 = ( ( Figura )figuras2.get( i ) ).obtenerDificultad( );
            for( int j = i; j < figuras2.size( ); j++ )
            {
                String dificultad2 = ( ( Figura )figuras2.get( j ) ).obtenerDificultad( );
                assertTrue( "Las figuras no est�n ordenados por dificultad", dificultad1.compareTo( dificultad2 ) <= 0 );
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo ordenarFigurasPorNombre<br>
     * <b> M�todos a probar: </b> <br>
     * ordenarFigurasPornombre<br>
     * obtenerFiguras<br>
     * <b> Objetivo: </b> Probar que se ordenen las figuras de acuerdo a su nombre<br>
     * <b> Resultados esperados: </b> <br>
     * La cantidad de figuras es la misma despu�s de ordenado<br>
     * Las figuras fueron ordenados lexicogr�ficamente por su nombre<br>
     */
    public void testOrdenarPornombre( )
    {
        setupEscenario2( );
        ArrayList figuras = juego.obtenerFiguras( );
        juego.ordenarFigurasPorNombre( );
        ArrayList figuras2 = juego.obtenerFiguras( );
        assertTrue( "La cantidad de figuras deber�a ser la misma", figuras.size( ) == figuras2.size( ) );
        for( int i = 0; i < figuras2.size( ); i++ )
        {
            String dificultad1 = ( ( Figura )figuras2.get( i ) ).obtenerNombre( );
            for( int j = i; j < figuras2.size( ); j++ )
            {
                String dificultad2 = ( ( Figura )figuras2.get( j ) ).obtenerNombre( );
                assertTrue( "Las figuras no est�n ordenados por nombre", dificultad1.compareTo( dificultad2 ) <= 0 );
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo iniciarJuego<br>
     * <b> M�todos a probar: </b> <br>
     * iniciarJuego<br>
     * <b> Objetivo: </b> Probar la iniciaci�n del juego<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar el juego la figura seleccionada queda como figura actual<br>
     */
    public void testIniciarJuego( )
    {
        setupEscenario2( );

        ArrayList figuras = juego.obtenerFiguras( );
        Figura f = ( Figura )figuras.get( 0 );
        juego.iniciarJuego( f );
        assertEquals( "", f, juego.obtenerFiguraActual( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo terminarJuego<br>
     * <b> M�todos a probar: </b> <br>
     * terminarJuego<br>
     * <b> Objetivo: </b> Probar la terminaci�n del juego<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al terminarse un juego se deben actualizar correctamente la cantidad de juegos armados e intentados.
     */
    public void testTerminarJuego( )
    {
        setupEscenario3( );
        ArrayList figuras = juego.obtenerFiguras( );
        Figura f = ( Figura )figuras.get( 0 );
        juego.iniciarJuego( f );

        // Para terminar con una figura correctamente armada
        ArrayList fichas = f.obtenerFichas( );
        int[] identificadores = new int[fichas.size( )];
        for( int i = 0; i < fichas.size( ); i++ )
        {
            identificadores[ i ] = ( ( Ficha )fichas.get( i ) ).obtenerPosicion( );
        }
        juego.terminarJuego( identificadores );
        assertTrue( "La cantidad de juegos terminados debe ser diferente de 0", juego.obtenerArmados( ) != 0 );

        // Para terminar con una figura mal armada
        identificadores = new int[0];
        juego.terminarJuego( identificadores );
        assertTrue( "La cantidad de juegos terminados debe ser diferente de 0", juego.obtenerIntentos( ) != 0 );
    }

}