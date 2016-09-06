/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Prision.java,v 1.5 2007/01/22 16:02:23 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - 04-Sep-2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.mundo;

import java.util.ArrayList;

/**
 * Clase que representa la prisi�n
 */
public class Prision
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
  
    /**
     * El n�mero de sectores que hay en la prisi�n
     */
    private static final int NUMERO_SECTORES = 5;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Sectores de la prisi�n
     */
    private Sector[] sectores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la prisi�n. <br>
     * <b> post: </b> Se construy� la prisi�n con la cantidad de sectores NUMERO_SECTORES, los sectores no tienen prisioneros <br>
     */
    public Prision( )
    {
        // Inicializa el arreglo de sectores
        sectores = new Sector[NUMERO_SECTORES];

        // Crea e inicializa los sectores de la prisi�n
        Sector sectorA = new Sector( "Sector A", Sector.MAXIMA );
        Sector sectorB = new Sector( "Sector B", Sector.MEDIA );
        Sector sectorC = new Sector( "Sector C", Sector.MINIMA );
        Sector sectorD = new Sector( "Sector D", Sector.TEMPORAL );
        Sector sectorE = new Sector( "Sector E", Sector.PROBLEMATICO );

        // Coloca los sectores en la prisi�n
        sectores[ 0 ] = sectorA;
        sectores[ 1 ] = sectorB;
        sectores[ 2 ] = sectorC;
        sectores[ 3 ] = sectorD;
        sectores[ 4 ] = sectorE;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo prisionero a la prisi�n en el sector dado. <br>
     * <b>pre:</b> sectores!=null, cada uno de los sectores se encuentra inicializado, <br>
     * no existe un prisionero con el n�mero de identificaci�n n�mero <br>
     * y el n�mero total de prisioneros es menor a capacidad
     * @param nombre Nombre del prisionero. nombre!=null
     * @param apellido Apellido del prisionero. apellido!=null
     * @param numero Numero que identifica al prisionero. numero>=0
     * @param crimen Crimen que cometi� el prisionero. crimen!=null
     * @param sentencia Sentencia en meses que tiene el prisionero. sentencia>0
     * @param grupo Grupo criminal al que pertenece el prisionero. grupo!=null
     * @param nombreSector Sector al cual el prisionero va a ser agregado. nombreSector!=null <br>
     *        y nombreSector corresponde a alguno de los sectores de la prisi�n
     */
    public void ingresarNuevoPrisionero( String nombre, String apellido, int numero, String crimen, int sentencia, String grupo, String nombreSector )
    {
        Prisionero prisionero = new Prisionero( nombre, apellido, numero, crimen, sentencia, grupo );
        for( int i = 0; i < sectores.length; i++ )
        {
            Sector sector = sectores[ i ];
            if( sector.darNombre( ).equals( nombreSector ) )
            {

                sector.agregarPrisionero( prisionero );
            }
        }
    }

    /**
     * Busca el prisionero con el n�mero dado. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param numero El n�mero del prisionero a ser buscado.
     * @return Se retorn� el prisionero con el n�mero dado o null si �ste no es encontrado
     */
    public Prisionero buscarPrisionero( int numero )
    {
        Prisionero prisionero = null;
        for( int i = 0; i < sectores.length && prisionero == null; i++ )
        {
            Sector sector = sectores[ i ];

            prisionero = sector.buscarPrisionero( numero );

        }
        return prisionero;
    }

    /**
     * M�todo que retorna los sectores que tiene la prisi�n
     * @return Se retorn� el arreglo de sectores de la prisi�n.
     */
    public Sector[] darSectores( )
    {
        return sectores;
    }

    /**
     * Revisa que en la prisi�n no exista un prisionero con el n�mero dado. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param numero N�mero del prisionero
     * @return Se retorn� true si existe un prisionero con ese n�mero, false de lo contrario.
     */
    public boolean existePrisionero( int numero )
    {
        boolean respuesta = false;
        for( int i = 0; i < sectores.length; i++ )
        {
            if( !respuesta )
            {
                respuesta = sectores[ i ].existePrisionero( numero );
            }
        }
        return respuesta;
    }

    /**
     * Busca en todos los sectores el prisionero para darle salida. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * <b>post:</b> El prisionero con el n�mero dado no existe en el sector <br>
     * @param numero N�mero del prisionero
     * @return Se retorn� true si encontr� un prisionero con ese n�mero y por tanto se le dio salida. <br>
     *         En caso contrario se retorna false.
     */
    public boolean darSalidaPrisionero( int numero )
    {
        
        boolean salida = false;
        for( int i = 0; i < sectores.length && !salida; i++ )
        {
            Sector sector = sectores[ i ];
            if( sector.existePrisionero( numero ) )
            {
                sector.darSalidaPrisionero( numero );
                salida = true;
            }
        }
        return salida;
    }

    /**
     * Cambia de un sector a otro a un prisionero. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * <b>post:</b> El prisionero fue cambiado de sector si el nuevo sector no era el mismo en el que el prisionero ya se encontraba.
     * @param numero El n�mero del prisionero a reubicar. numero pertenece a un prisionero de la prisi�n
     * @param nuevoSector Nombre del sector al cual se quiere cambiar el prisionero. nuevoSector!=null, <br>
     *        nuevoSector es alguno de los sectores de la prisi�n.
     * @return Se retorn� true si el prisionero fue reubicado o false de lo contrario. Un prisionero no es reubicado debido a que el nuevo <br>
     *         sector correspond�a al sector en el que se encontraba
     */
    public boolean reubicarPrisionero( int numero, String nuevoSector )
    {
        // Elimina el prisionero de su sector original
        boolean reubicado = false;
        Sector sectorOriginal = buscarSectorPrisionero( numero );
        if( !sectorOriginal.darNombre( ).equals( nuevoSector ) )
        {
            Prisionero prisionero = sectorOriginal.buscarPrisionero( numero );
            sectorOriginal.darSalidaPrisionero( numero );

            for( int i = 0; i < sectores.length && !reubicado; i++ )
            {
                Sector sector = sectores[ i ];

                if( sector.darNombre( ).equals( nuevoSector ) )
                {
                    sector.agregarPrisionero( prisionero );
                    reubicado = true;
                }

            }
        }

        return reubicado;
    }

    /**
     * Retorna el sector al que pertenece el preso con el n�mero dado. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param numero El n�mero del preso
     * @return El sector del preso o null si no existe un preso con el n�mero dado
     */
    public Sector buscarSectorPrisionero( int numero )
    {
        Sector sector = null;
        for( int i = 0; i < sectores.length && sector == null; i++ )
        {
            Sector sAux = sectores[ i ];
            if( sAux.existePrisionero( numero ) )
            {
                sector = sAux;
            }
        }

        return sector;
    }

    /**
     * Indica si en la prisi�n existe un sector con el nombre dado <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param nombre El nombre del sector. nombre!=null
     * @return true si el sector con el nombre dado existe o false de lo contrario
     */
    public boolean existeSector( String nombre )
    {
        boolean existe = false;
        for( int i = 0; i < sectores.length && !existe; i++ )
        {
            Sector sAux = sectores[ i ];
            if( sAux.darNombre( ).equals( nombre ) )
            {
                existe = true;
            }
        }

        return existe;
    }

    /**
     * Retorna los prisioneros del sector especificado. <br>
     * <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param nombre Nombre del sector
     * @return Los prisioneros del sector especificado. Si el sector no se encuentra se retorna null
     */
    public ArrayList darPrisionerosSector( String nombre )
    {
        ArrayList prisioneros = null;
        for( int i = 0; i < sectores.length && prisioneros == null; i++ )
        {
            Sector sAux = sectores[ i ];
            if( sAux.darNombre( ).equals( nombre ) )
            {
                prisioneros = sAux.darPrisioneros( );
            }
        }
        return prisioneros;
    }

    /**
     * Retorna el sector con el nombre dado <b>pre:</b> sectores!=null y cada uno de los sectores se encuentra inicializado <br>
     * @param nombre Nombre del sector
     * @return El sector especificado. Si el sector no se encuentra se retorna null
     */
    public Sector buscarSector( String nombre )
    {
        Sector sector = null;
        for( int i = 0; i < sectores.length && sector == null; i++ )
        {
            Sector sAux = sectores[ i ];
            if( sAux.darNombre( ).equals( nombre ) )
            {
                sector = sAux;
            }
        }
        return sector;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}