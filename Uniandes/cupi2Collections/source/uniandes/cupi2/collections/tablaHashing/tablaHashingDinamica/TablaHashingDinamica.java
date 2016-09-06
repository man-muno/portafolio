/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * $Id: TablaHashingDinamica.java,v 1.2 2008/03/28 03:07:26 jua-gome Exp $
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Marzo 12, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.tablaHashing.tablaHashingDinamica;

import uniandes.cupi2.collections.bitArray.BitArray;
import uniandes.cupi2.collections.listaEncadenada.ListaEncadenada;
import uniandes.cupi2.collections.listaEncadenada.NoExisteException;
import uniandes.cupi2.collections.tablaHashing.EntradaTabla;
import uniandes.cupi2.collections.tablaHashing.ITablaHashing;
import uniandes.cupi2.collections.tablaHashing.LlaveInvalidaException;

/**
 * Representa una tabla de hashing que va creiendo a medida que su factor de carga crece sin necesidad de hacer un rehash que implique un traspaso de todos los elementos
 * presentes en la tabla.
 * @param <L> Tipo de las llaves asociada con los elementos.
 * @param <V> Tipo de los elementos guardados en la tabla.
 */
public class TablaHashingDinamica<L, V> implements ITablaHashing<L, V>
{

    // -----------------------------------------------------------------
    // Constantes
    // ------------------------------------------------------------------

    /**
     * Representa el porcentaje aceptable para el factor de carga de la tabla
     */
    private static double FACTOR_CARGA_ACEPTABLE = .75;

    // -----------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------

    /**
     * Número de objetos presentes en la tabla
     */
    private int nObjetos;

    /**
     * Número inicial de bits de la llave que se toman en cuenta
     */
    private int bitsActualesHash;

    /**
     * Posición dentro de la tabla en la que inicia la zona que usa <code>bitsInicialesHash</code> bits
     */
    private int inicioZona;

    /**
     * Posición dentro de la tabla en la que termina la zona que usa <code>bitsInicialesHash</code> bits
     */
    private int finZona;

    /**
     * Número maximo de bits a tomar en cuenta para la función de hashing
     */
    private int bitsHashing;

    /**
     * Elementos de la tabla
     */
    private ListaEncadenada<EntradaTabla<L, V>>[] entradasTabla;

    // -----------------------------------------------------------------
    // Métodos constructores
    // ------------------------------------------------------------------

    /**
     * Constructor por defecto
     */
    @SuppressWarnings("unchecked")
    public TablaHashingDinamica( )
    {
        // Inicializar los parametros de la tabla
        inicioZona = 0;
        bitsHashing = 16;
        bitsActualesHash = 4;
        finZona = ( int ) ( Math.pow( 2, bitsActualesHash ) - 1 );
        entradasTabla = new ListaEncadenada[( int )Math.pow( 2, bitsHashing )];

        // Inicializar las posiciones de la tabla
        inicializarListas( );
    }

    // -----------------------------------------------------------------
    // Métodos modificadores
    // ------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.tablaHashing.ITablaHashing#agregar(java.lang.Object, java.lang.Object)
     */
    public V agregar( L llave, V elemento )
    {
        // Calcular la posición correspondiente a la llave
        int indice = determinarPosición( llave );

        V elementoAnterior = null;
        EntradaTabla<L, V> entrada = buscarLugarInsercion( indice, llave );

        // Si la entrada es null la llave no se encuentra asociada con otro elemento
        if( entrada == null )
        {
            EntradaTabla<L, V> nuevaEntrada = new EntradaTabla<L, V>( llave, elemento );
            entradasTabla[ indice ].insertarCola( nuevaEntrada );
            nObjetos++;

            // Verificar si es necesario reajustar la tabla
            verificarFactorDeCarga( );
        }
        // la llave se encuentra asociada con un elemento
        else
        {
            elementoAnterior = entrada.darElemento( );
            entrada.cambiarElemento( elemento );
        }
        return elementoAnterior;
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.tablaHashing.ITablaHashing#eliminar(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public V eliminar( L llave )
    {
        // Calcular la posición correspondiente a la llave
        int indice = determinarPosición( llave );

        // Eliminar el elemento
        ListaEncadenada<EntradaTabla<L, V>> lista = entradasTabla[ indice ];
        V elemento = null;
        EntradaTabla entrada = new EntradaTabla( llave );

        try
        {
            entrada = ( EntradaTabla )lista.eliminar( entrada );
            elemento = ( V )entrada.darElemento( );
            nObjetos--;
            return elemento;
        }
        catch( NoExisteException e )
        {
            return null;
        }
    }

    // -----------------------------------------------------------------
    // Métodos consultores
    // ------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.tablaHashing.ITablaHashing#dar(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public V dar( L llave )
    {
        int indice = determinarPosición( llave );

        ListaEncadenada<EntradaTabla<L, V>> lista = entradasTabla[ indice ];
        V elemento = null;
        EntradaTabla entrada = new EntradaTabla( llave );

        try
        {
            entrada = ( EntradaTabla )lista.eliminar( entrada );
            elemento = ( V )entrada.darElemento( );
            nObjetos--;
            return elemento;
        }
        catch( NoExisteException e )
        {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.tablaHashing.ITablaHashing#darNumeroElementos()
     */
    public int darNumeroElementos( )
    {
        return nObjetos;
    }

    // -----------------------------------------------------------------
    // Métodos auxiliares
    // ------------------------------------------------------------------

    /**
     * Verifica que el factor de carga de la tabla no supere el límite permitido.
     * </p>
     * En caso de detectar sobrecarga en la tabla redistribuye tantas posiciones de la tabla como sea necesario para lograr un factor de carga aceptable.
     */
    private void verificarFactorDeCarga( )
    {
        while( bitsActualesHash < bitsHashing && darFactorDeCarga( ) > FACTOR_CARGA_ACEPTABLE )
        {
            // Determinar las entradas a reacomodar
            ListaEncadenada<EntradaTabla<L, V>> reajustar = entradasTabla[ inicioZona ];
            entradasTabla[ inicioZona ] = new ListaEncadenada<EntradaTabla<L, V>>( );
            nObjetos -= reajustar.darLongitud( );

            // Disminuir la zona que usa bitsHashing
            inicioZona++;
            if( inicioZona == finZona )
            {
                bitsActualesHash++;
                inicioZona = 0;
                finZona = ( int ) ( Math.pow( 2, bitsActualesHash ) - 1 );
            }

            // Volver a agregar los elementos que se encontraban en inicioZona
            for( int i = 0; i < reajustar.darLongitud( ); i++ )
            {
                EntradaTabla<L, V> ent = reajustar.dar( i );
                agregar( ent.darLlave( ), ent.darElemento( ) );
            }
        }

    }

    /**
     * Busca el lugar en donde se debe situar una nueva entrada con la llave especificada dentro del bucket dado.
     * @param indice Indice del bucket al que corresponde la llave.
     * @param llave La llave a la que se le va a localizar el lugar de inserción.
     * @return Null si no existe una entrada asociada con la llave especificada o la entrada en la que debe adicionarse el elemento asociado con la llave dada.
     */
    private EntradaTabla<L, V> buscarLugarInsercion( int indice, L llave )
    {
        EntradaTabla<L, V> lugarInsercion = new EntradaTabla<L, V>( llave );
        ListaEncadenada<EntradaTabla<L, V>> lista = entradasTabla[ indice ];
        lugarInsercion = lista.buscar( lugarInsercion );
        return lugarInsercion;
    }

    /**
     * Calcula el factor de carga de la tabla
     * @return El factor de carga de la tabla
     */
    private double darFactorDeCarga( )
    {
        return nObjetos / ( finZona + 1 + inicioZona );
    }

    /**
     * Calcula la posición dentro de la matriz que corresponde a una llava
     * @param llave Llave a partir de la cual se quiere calcular la posición dentro de la matriz
     * @return la posición dentro de la matriz que corresponde a la llave ingresada como parámetro
     */
    private int determinarPosición( L llave )
    {
        // Calcular la función de hashing sobre la llave
        int hashing = darHash( llave );

        // Aislar los bitsHashing menos significativos de hashBinario
        BitArray hashAislado = darNBits( hashing, bitsActualesHash );

        // Si este valor (hashAislado) está dentro de la zona demarcada por inicioZona y finZona retornamos el valor convertido a entero
        int hashAisladoDecimal = bitsAInt( hashAislado );
        if( hashAisladoDecimal >= inicioZona && hashAisladoDecimal <= finZona )
            return hashAisladoDecimal;
        // De no ser así retornamos el valor convertido a decimal pero tomando en cuenta un bit más
        else
            return bitsAInt( darNBits( hashing, bitsActualesHash + 1 ) );
    }

    /**
     * Retorna el resultado de ejecutar la función de hashing sobre una llave
     * @param llave Llave sobre la que se ejecuta la función de hashing
     * @return El resultado de ejecutar la función de hashing sobre una llave
     */
    private int darHash( L llave )
    {
        if( llave == null )
            throw new LlaveInvalidaException( "La llave para eliminar un elemento no puede ser null" );
        else
            return llave.hashCode( );
    }

    /**
     * Retorna los <code>nBits</code> menos significativos de un valor entero
     * @param valor Valor entero a convertir
     * @param nBits Número de bits a tomar en cuenta
     * @return Los <code>nBits</code> menos significativos de un valor entero
     */
    private BitArray darNBits( int valor, int nBits )
    {
        // Calcular el arreglo de bits que representa el valor entero en binario
        BitArray binario = intABits( valor );

        // Crear un nuevo arreglo de bits, tomar en cuenta solo los bits especificados y retornarlos
        BitArray retorno = new BitArray( nBits );
        for( int i = 0; i < nBits; i++ )
            retorno.asignar( nBits - 1 - i, binario.estaEncendido( nBits - 1 - i ) );
        return retorno;
    }

    /**
     * Convierte un valor entero a su equivalente binario
     * @param valor Valor entero a convertir
     * @return Equivalente binario del valor entero ingresado como parámetro
     */
    private BitArray intABits( int valor )
    {
        // Determinar la longitud del arreglo
        int longitud;
        for( longitud = 1; Math.pow( 2, longitud ) <= valor; longitud++ )
            ;
        BitArray arreglo = new BitArray( longitud );

        // Convertir el entero a bitArray
        for( int i = 0; i < longitud; i++ )
        {
            // Determinar el valor del bit (longitud - 1 -i)
            if( valor % 2 == 1 )
                arreglo.asignar( longitud - 1 - i, true );
            else
                arreglo.asignar( longitud - 1 - i, false );

            // Disminuir el valor del entero a convertir
            valor = valor / 2;
        }

        return arreglo;
    }

    /**
     * Convierte un valor binario a su equivalente valor decimal
     * @param valor Valor binario a convertir
     * @return Equivalente decimal del valor binario ingresado como parámetro
     */
    private int bitsAInt( BitArray valor )
    {
        int retorno = 0;
        for( int i = 0; i < valor.darLongitud( ); i++ )
        {
            if( valor.estaEncendido( valor.darLongitud( ) - 1 - i ) )
                retorno += Math.pow( 2, i );
        }
        return retorno;
    }

    /**
     * Inicializa las listas que representan los buckets en la tabla de hashing. <br>
     * <b>pre: </b> entradasTabla!=null. <br>
     * <b>post: </b> Se inicializaron las lista que representan los buckets.
     */
    @SuppressWarnings("unchecked")
    private void inicializarListas( )
    {
        for( int cont = 0; cont < entradasTabla.length; cont++ )
        {
            entradasTabla[ cont ] = new ListaEncadenada( );
        }
    }
}
