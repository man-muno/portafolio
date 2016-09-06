/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Sector.java,v 1.5 2007/01/22 16:02:23 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - Aug 11, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.mundo;

import java.util.ArrayList;

/**
 * Clase que representa un sector de la prisi�n
 */
public class Sector
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el tipo del patio de m�xima seguridad
     */
    public static final String MAXIMA = "Seguridad M�xima";

    /**
     * Constante que representa el tipo de patio de seguridad media
     */
    public static final String MEDIA = "Seguridad Media";

    /**
     * Constante que representa el tipo de patio de m�nima seguridad
     */
    public static final String MINIMA = "Seguridad M�nima";

    /**
     * Constante que representa el patio para prisioneros transitorios
     */
    public static final String TEMPORAL = "Prisioneros Temporales";

    /**
     * Constante que representa el patio para prisioneros problem�ticos
     */
    public static final String PROBLEMATICO = "Prisioneros Problem�ticos";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del sector
     */
    private String nombre;

    /**
     * Nivel de seguridad del sector
     */
    private String tipo;

    /**
     * Prisioneros que se est�n en el sector
     */
    private ArrayList prisioneros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor de Sector sin prisioneros. <br>
     * <b> post: </b> Se construy� el sector con la informaci�n proporcionada. <br>
     * @param nNombre Nombre que va a tener el sector. nNombre!=null
     * @param nTipo Tipo de nivel de seguridad que tiene el sector. nTipo!=null
     */
    public Sector( String nNombre, String nTipo )
    {
        nombre = nNombre;
        tipo = nTipo;
        prisioneros = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del sector
     * @return Se retorn� el nombre actual del sector
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Cambia el nombre del sector
     * @param nNombre El nuevo nombre del sector. nNombre!=null
     */
    public void cambiarNombre( String nNombre )
    {
        nombre = nNombre;
    }

    /**
     * Agrega un nuevo prisionero al sector. <br>
     * <b> pre: </b> prisioneros!=null <br>
     * @param prisionero El prisionero a ser agregado. prisionero!=null
     */
    public void agregarPrisionero( Prisionero prisionero )
    {
        prisioneros.add( prisionero );
    }

    /**
     * Retorna el tipo de sector
     * @return El tipo de sector actual
     */
    public String darTipo( )
    {
        return tipo;
    }

    /**
     * Indica si existe el prisionero en el sector. <br>
     * <b> pre: </b> prisioneros!=null <br>
     * @param numeroPrisionero N�mero de identificaci�n del prisionero
     * @return Se retorn� true si el prisionero se encuentra en el sector, false de lo contrario
     */
    public boolean existePrisionero( int numeroPrisionero )
    {
        boolean respuesta = false;

        for( int i = 0; i < prisioneros.size( ) && !respuesta; i++ )
        {
            Prisionero prisionero = ( Prisionero )prisioneros.get( i );
            if( prisionero.darNumero( ) == numeroPrisionero )
            {
                respuesta = true;
            }
        }

        return respuesta;
    }

    /**
     * Saca el prisionero del sector. <br>
     * <b> pre: </b> prisioneros!=null <br>
     * <b> post: </b> Si el prisionero se encontraba en el sector fue eliminado de la lista de prisioneros <br>
     * @param numeroPrisionero N�mero de identificaci�n del prisionero
     */
    public void darSalidaPrisionero( int numeroPrisionero )
    {
        for( int i = 0; i < prisioneros.size( ); i++ )
        {
            Prisionero prisionero = ( Prisionero )prisioneros.get( i );
            if( prisionero.darNumero( ) == numeroPrisionero )
            {
                prisioneros.remove( i );
            }
        }
    }

    /**
     * Busca el prisionero con el n�mero dado. <br>
     * <b> pre: </b> prisioneros!=null <br>
     * @param numeroPrisionero El n�mero del prisionero a ser buscado.
     * @return Se retorn� el prisionero con el n�mero especificado o null si �ste no es encontrado
     */
    public Prisionero buscarPrisionero( int numeroPrisionero )
    {
        Prisionero prisionero = null;
        for( int i = 0; i < prisioneros.size( ) && prisionero == null; i++ )
        {
            Prisionero pAux = ( Prisionero )prisioneros.get( i );
            if( pAux.darNumero( ) == numeroPrisionero )
            {
                prisionero = pAux;
            }
        }
        return prisionero;
    }

    /**
     * Retorna la cantidad de prisioneros del sector. <br>
     * <b> pre: </b> prisioneros!=null <br>
     * @return Se retorn� la cantidad de prisioneros del sector
     */
    public int darNumeroPrisioneros( )
    {
        return prisioneros.size( );
    }

    /**
     * Retorna la lista de los prisioneros que se encuentran en el sector
     * @return Se retorn� la lista de prisioneros que hay en el sector
     */
    public ArrayList darPrisioneros( )
    {
        return prisioneros;
    }
}