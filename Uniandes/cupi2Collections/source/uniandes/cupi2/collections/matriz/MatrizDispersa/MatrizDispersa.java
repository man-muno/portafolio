/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MatrizDispersa.java,v 1.1 2008/04/07 01:37:57 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz.MatrizDispersa;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.listaEncadenadaOrdenada.ListaEncadenadaOrdenada;
import uniandes.cupi2.collections.listaEncadenadaOrdenada.NoExisteException;
import uniandes.cupi2.collections.matriz.CoordenadaFueraDeRangoException;
import uniandes.cupi2.collections.matriz.DimensionesInvalidasException;
import uniandes.cupi2.collections.matriz.IMatriz;
import uniandes.cupi2.collections.matriz.IMatrizVariable;

/**
 * Representa una matriz en la que solo se reserva memoria para los elementos que sean diferentes a <code>null</code>.
 * @see IMatriz
 * @see IMatrizVariable
 */
public class MatrizDispersa<T> implements IMatriz<T>, IMatrizVariable<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con las cabezas de las filas.
     */
    private ListaEncadenadaOrdenada<CabezaCoordenada<T>> cabezasFila;

    /**
     * Lista con las cabezas de las columnas.
     */
    private ListaEncadenadaOrdenada<CabezaCoordenada<T>> cabezasColumna;

    /**
     * Número de columnas de la matriz.
     */
    private int nColumnas;

    /**
     * Número de filas de la matriz.
     */
    private int nFilas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase.
     * @param nColumnas Número de columnas de la matriz.
     * @param nFilas Número de filas de la matriz.
     * @throws DimensionesInvalidasException Si las dimensiones de la matriz son invalidas (<=0)
     */
    public MatrizDispersa( int nColumnas, int nFilas ) throws DimensionesInvalidasException
    {
        if( nColumnas <= 0 || nFilas <= 0 )
            throw new DimensionesInvalidasException( nColumnas, nFilas );
        cabezasColumna = new ListaEncadenadaOrdenada<CabezaCoordenada<T>>( );
        cabezasFila = new ListaEncadenadaOrdenada<CabezaCoordenada<T>>( );
        this.nColumnas = nColumnas;
        this.nFilas = nFilas;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatriz#cambiarElemento(int, int, java.lang.Object)
     */
    public void cambiarElemento( int fila, int columna, T elemento )
    {
        verificarDimensiones( fila, columna );

        if( elemento != null )
        {
            // Crear el nodo
            NodoMatrizDispersa<T> nodo = new NodoMatrizDispersa<T>( elemento, fila, columna );

            // Insertar por fila
            NodoMatrizDispersa<T> cFila = darCabezaFila( fila );
            cambiarCabezaFila( fila, insertarOrdenadoEnFila( cFila, nodo ) );

            // Insertar por columna
            NodoMatrizDispersa<T> cColumna = darCabezaColumna( columna );
            cambiarCabezaColumna( columna, insertarOrdenadoEnColumna( cColumna, nodo ) );
        }
        else
        {
            eliminarElemento( fila, columna );
        }
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatrizVariable#aumentarFilas(int)
     */
    public void aumentarFilas( int aumento ) throws DimensionesInvalidasException
    {
        if( nFilas + aumento <= 0 )
            throw new DimensionesInvalidasException( nFilas - aumento, nColumnas );
        nFilas += aumento;

        // Eliminar los elementos que pertenescan a las filas eliminadas
        for( Iterador<CabezaCoordenada<T>> iter = cabezasColumna.darIterador( ); iter.haySiguiente( ); )
        {
            CabezaCoordenada<T> c = iter.darSiguiente( );
            int columna = c.darCabeza( ).darColumna( );
            cambiarCabezaColumna( columna, eliminarEnColumnaDespuesDe( c.darCabeza( ), nFilas ) );
        }

        // Eliminar las cabezas de las filas eliminadas.
        for( Iterador<CabezaCoordenada<T>> iterador = cabezasFila.darIterador( ); iterador.haySiguiente( ); )
        {
            CabezaCoordenada<T> cabeza = iterador.darSiguiente( );

            if( cabeza.darIndice( ) >= nFilas )
                try
                {
                    cabezasFila.eliminar( cabeza );
                }
                catch( NoExisteException e )
                {
                    // Esto no debería suceder
                }
        }

    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatrizVariable#aumentarColumnas(int)
     */
    public void aumentarColumnas( int aumento ) throws DimensionesInvalidasException
    {
        if( nColumnas + aumento <= 0 )
            throw new DimensionesInvalidasException( nFilas, nColumnas - aumento );
        nColumnas += aumento;

        // Eliminar los elementos que pertenescan a las columnas eliminadas
        for( Iterador<CabezaCoordenada<T>> iter = cabezasFila.darIterador( ); iter.haySiguiente( ); )
        {
            CabezaCoordenada<T> c = iter.darSiguiente( );
            int fila = c.darCabeza( ).darFila( );
            cambiarCabezaFila( fila, eliminarEnFilaDespuesDe( c.darCabeza( ), nColumnas ) );
        }

        // Eliminar las cabezas de las columnas eliminadas.
        for( Iterador<CabezaCoordenada<T>> iterador = cabezasColumna.darIterador( ); iterador.haySiguiente( ); )
        {
            CabezaCoordenada<T> cabeza = iterador.darSiguiente( );

            if( cabeza.darIndice( ) >= nColumnas )
                try
                {
                    cabezasColumna.eliminar( cabeza );
                }
                catch( NoExisteException e )
                {
                    // Esto no debería suceder
                }
        }
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatriz#darNFilas()
     */
    public int darNFilas( )
    {
        return nFilas;
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatriz#darNColumnas()
     */
    public int darNColumnas( )
    {
        return nColumnas;
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.IMatriz#darElemento(int, int)
     */
    public T darElemento( int fila, int columna )
    {
        verificarDimensiones( fila, columna );

        NodoMatrizDispersa<T> cabezaF = darCabezaFila( fila );
        NodoMatrizDispersa<T> buscado = buscarEnFila( cabezaF, columna );
        return buscado == null ? null : buscado.darElemento( );
    }

    /**
     * Elimina un elemento de los encademientos de fila y columna.
     * @param fila Fila del elemento a eliminar.
     * @param columna Columna del elemento a eliminar.
     */
    public void eliminarElemento( int fila, int columna )
    {
        verificarDimensiones( fila, columna );

        // Eliminar de la lista de fila
        NodoMatrizDispersa<T> cabezaF = darCabezaFila( fila );
        cambiarCabezaFila( fila, eliminarEnFila( cabezaF, columna ) );

        // Eliminar de la lista de columna
        NodoMatrizDispersa<T> cabezaC = darCabezaColumna( columna );
        cambiarCabezaColumna( fila, eliminarEnColumna( cabezaC, fila ) );

    }

    // -----------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------

    /**
     * Elimina todos los elementos que esten en o más aya de la columna ingresada por parámetro.
     * </p>
     * Esta eliminación se hace recursivamente a partir del nodo ingresada por parametro.
     * @param cabeza Nodo a partir del cual se hace la eliminación.
     * @param columna Columna a partir de la cual se quiere eliminar.
     * @return La nueva cabeza de la lista.
     */
    private NodoMatrizDispersa<T> eliminarEnFilaDespuesDe( NodoMatrizDispersa<T> cabeza, int columna )
    {
        if( cabeza == null )
            return null;
        else if( cabeza.darColumna( ) >= columna )
            return null;
        else
        {
            cabeza.cambiarSigDerecha( eliminarEnFilaDespuesDe( cabeza.darSigDerecha( ), columna ) );
            return cabeza;
        }
    }

    /**
     * Elimina todos los elementos que esten en o más aya de la fila ingresada por parámetro.
     * </p>
     * Esta eliminación se hace recursivamente a partir del nodo ingresada por parametro.
     * @param cabeza Nodo a partir del cual se hace la eliminación.
     * @param fila Fila a partir de la cual se quiere eliminar.
     * @return La nueva cabeza de la lista.
     */
    private NodoMatrizDispersa<T> eliminarEnColumnaDespuesDe( NodoMatrizDispersa<T> cabeza, int fila )
    {
        if( cabeza == null )
            return null;
        else if( cabeza.darFila( ) >= fila )
            return null;
        else
        {
            cabeza.cambiarSigAbajo( eliminarEnColumnaDespuesDe( cabeza.darSigAbajo( ), fila ) );
            return cabeza;
        }
    }

    /**
     * Cambia la cabeza de una fila en la lista de cabezas.
     * @param fila Fila que se quiere modificar.
     * @param cabeza Nueva cabeza de la fila que se quiere modificar.
     */
    private void cambiarCabezaFila( int fila, NodoMatrizDispersa<T> cabeza )
    {
        CabezaCoordenada<T> cf = cabezasFila.buscar( new CabezaCoordenada<T>( null, fila ) );
        if( cf == null && cabeza != null )
            cabezasFila.insertar( new CabezaCoordenada<T>( cabeza, fila ) );
        else if( cabeza == null && cf != null )
            try
            {
                cabezasFila.eliminar( cf );
            }
            catch( NoExisteException e )
            {
                // Esto no debería suceder
            }
        else if( cf != null && cabeza != null )
            cf.cambiarCabeza( cabeza );
    }

    /**
     * Cambia la cabeza de una columna en la lista de cabezas.
     * @param columna Columna que se quiere modificar.
     * @param cabeza Nueva cabeza de la columna que se quiere modificar.
     */
    private void cambiarCabezaColumna( int columna, NodoMatrizDispersa<T> cabeza )
    {
        CabezaCoordenada<T> cc = cabezasColumna.buscar( new CabezaCoordenada<T>( null, columna ) );
        if( cc == null && cabeza != null )
            cabezasColumna.insertar( new CabezaCoordenada<T>( cabeza, columna ) );
        else if( cc != null && cabeza == null )
            try
            {
                cabezasColumna.eliminar( cc );
            }
            catch( NoExisteException e )
            {
                // Esto no debería suceder
            }
        else if( cabeza != null && cc != null )
            cc.cambiarCabeza( cabeza );
    }

    /**
     * Retorna el nodo cabeza de una fila.
     * @param fila Fila seleccionada.
     * @return El nodo cabeza de la fila seleccionada.
     */
    private NodoMatrizDispersa<T> darCabezaFila( int fila )
    {
        CabezaCoordenada<T> cf = cabezasFila.buscar( new CabezaCoordenada<T>( null, fila ) );
        return cf == null ? null : cf.darCabeza( );
    }

    /**
     * Retorna el nodo cabeza de una columna.
     * @param columna Columna seleccionada.
     * @return El nodo cabeza de la columna seleccionada.
     */
    private NodoMatrizDispersa<T> darCabezaColumna( int columna )
    {
        CabezaCoordenada<T> cc = cabezasColumna.buscar( new CabezaCoordenada<T>( null, columna ) );
        return cc == null ? null : cc.darCabeza( );
    }

    /**
     * Busca un nodo en una fila de forma recursiva.
     * @param cabeza Nodo a partir del cual se quiere hacer la busqueda.
     * @param columna Columna del nodo que se quiere buscar.
     * @return El nodo seleccionada o <code>null</code> si este no existe.
     */
    private NodoMatrizDispersa<T> buscarEnFila( NodoMatrizDispersa<T> cabeza, int columna )
    {
        if( cabeza == null || cabeza.darColumna( ) > columna )
            return null;
        else if( cabeza.darColumna( ) == columna )
            return cabeza;
        else
            return buscarEnFila( cabeza.darSigDerecha( ), columna );
    }

    /**
     * Busca un nodo en una columna de forma recursiva.
     * @param cabeza Nodo a partir del cual se quiere hacer la busqueda.
     * @param fila Fila del nodo que se quiere buscar.
     * @return El nodo seleccionada o <code>null</code> si este no existe.
     */
    private NodoMatrizDispersa<T> buscarEnColumna( NodoMatrizDispersa<T> cabeza, int fila )
    {
        if( cabeza == null || cabeza.darFila( ) > fila )
            return null;
        else if( cabeza.darFila( ) == fila )
            return cabeza;
        else
            return buscarEnColumna( cabeza.darSigAbajo( ), fila );
    }

    /**
     * Inserta un nodo en el encadenamiento de una fila de forma cursiva manteniendo un orden ascendente por columnas.
     * @param cabeza Nodo a partir del cual quiere hacer la insersión.
     * @param elem Elemento que se quiere insertar.
     * @return La nueva caebeza de la lista.
     */
    private NodoMatrizDispersa<T> insertarOrdenadoEnFila( NodoMatrizDispersa<T> cabeza, NodoMatrizDispersa<T> elem )
    {
        if( cabeza == null )
            return elem;
        else if( elem.darColumna( ) < cabeza.darColumna( ) )
        {
            elem.cambiarSigDerecha( cabeza );
            return elem;
        }
        else if( elem.darColumna( ) == cabeza.darColumna( ) )
        {
            elem.cambiarSigDerecha( cabeza.darSigDerecha( ) );
            return elem;
        }
        else
        {
            cabeza.cambiarSigDerecha( insertarOrdenadoEnFila( cabeza.darSigDerecha( ), elem ) );
            return cabeza;
        }
    }

    /**
     * Inserta un nodo en el encadenamiento de una columna de forma cursiva manteniendo un orden ascendente por filas.
     * @param cabeza Nodo a partir del cual quiere hacer la insersión.
     * @param elem Elemento que se quiere insertar.
     * @return La nueva caebeza de la lista.
     */
    private NodoMatrizDispersa<T> insertarOrdenadoEnColumna( NodoMatrizDispersa<T> cabeza, NodoMatrizDispersa<T> elem )
    {
        if( cabeza == null )
            return elem;
        else if( elem.darFila( ) < cabeza.darFila( ) )
        {
            elem.cambiarSigAbajo( cabeza );
            return elem;
        }
        else if( elem.darFila( ) == cabeza.darFila( ) )
        {
            elem.cambiarSigAbajo( cabeza.darSigAbajo( ) );
            return elem;
        }
        else
        {
            cabeza.cambiarSigAbajo( insertarOrdenadoEnColumna( cabeza.darSigAbajo( ), elem ) );
            return cabeza;
        }
    }

    /**
     * Elimina un nodo del encadenamiento de una fila de forma recursiva.
     * @param cabeza Nodo a partir del cual se quere hacer la eliminación.
     * @param columna Columna del nodo que se quiere eliminar.
     * @return La nueva cabeza de la lista.
     */
    private NodoMatrizDispersa<T> eliminarEnFila( NodoMatrizDispersa<T> cabeza, int columna )
    {
        if( cabeza == null || cabeza.darColumna( ) > columna )
            return cabeza;
        else if( cabeza.darColumna( ) == columna )
            return cabeza.darSigDerecha( );
        else
        {
            cabeza.cambiarSigDerecha( eliminarEnFila( cabeza.darSigDerecha( ), columna ) );
            return cabeza;
        }

    }

    /**
     * Elimina un nodo del encadenamiento de una columna de forma recursiva.
     * @param cabeza Nodo a partir del cual se quere hacer la eliminación.
     * @param columna Columna del nodo que se quiere eliminar.
     * @return La nueva cabeza de la lista.
     */
    private NodoMatrizDispersa<T> eliminarEnColumna( NodoMatrizDispersa<T> cabeza, int fila )
    {
        if( cabeza == null || cabeza.darFila( ) > fila )
            return cabeza;
        else if( cabeza.darFila( ) == fila )
            return cabeza.darSigAbajo( );
        else
        {
            cabeza.cambiarSigAbajo( eliminarEnColumna( cabeza.darSigAbajo( ), fila ) );
            return cabeza;
        }

    }

    /**
     * Verifica que la coordenada ingresa por parametro esté dentro de los límites de la matriz.
     * @param fila Fila de la coordenada.
     * @param columna Columna de la coordenada.
     * @throws CoordenadaFueraDeRangoException Si la coordenada ingresada por parametro excede los límites de la matriz.
     */
    private void verificarDimensiones( int fila, int columna )
    {
        if( fila < 0 || columna < 0 || fila >= nFilas || columna >= nColumnas )
            throw new CoordenadaFueraDeRangoException( fila, columna );
    }
}
