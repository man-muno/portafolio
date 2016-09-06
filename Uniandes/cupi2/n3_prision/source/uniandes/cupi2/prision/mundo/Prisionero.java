/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Prisionero.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 10, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.mundo;

/**
 * Clase que representa a un prisionero
 */
public class Prisionero
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el grupo criminal FARC
     */
    public static final String FARC = "FARC";

    /**
     * Constante que representa el grupo criminal paramilitares
     */
    public static final String PARAMILITARES = "Paramilitares";

    /**
     * Constante que representa el grupo criminal del cartel de Medellín
     */
    public static final String CARTEL_MEDELLIN = "Cartel de Medellín";

    /**
     * Constante que representa a los criminales independientes
     */
    public static final String INDEPENDIENTE = "Independiente";
    
    /**
     * Constante que representa ningún grupo criminal
     */
    public static final String NINGUNO = "Ninguno";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del prisionero
     */
    private String nombre;

    /**
     * Apellido del prisionero
     */
    private String apellido;

    /**
     * Número del prisionero
     */
    private int numero;

    /**
     * Crimen que cometió el prisionero
     */
    private String crimen;

    /**
     * Sentencia en meses del prisionero
     */
    private int tiempoSentencia;

    /**
     * Grupo criminal al que pertenece el prisionero
     */
    private String grupoCriminal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param nombreP Nombre del prisionero nombreP!=null
     * @param apellidoP Apellido del prisionero. apellidoP!=null
     * @param numeroP Número del prisionero. numeroP>0
     * @param crimenP Crimen que cometió el prisionero. crimenP!=null
     * @param sentenciaP Sentencia en meses del prisionero. sentenciaP>0
     * @param grupoP Grupo criminal al que pertenece el prisionero. grupoP!=null
     */
    public Prisionero( String nombreP, String apellidoP, int numeroP, String crimenP, int sentenciaP, String grupoP )
    {
        nombre = nombreP;
        apellido = apellidoP;
        numero = numeroP;
        crimen = crimenP;
        tiempoSentencia = sentenciaP;
        grupoCriminal = grupoP;
    }

    /**
     * Devuelve el apellido del prisionero
     * @return Se retornó el apellido del prisionero
     */
    public String darApellido( )
    {
        return apellido;
    }

    /**
     * Cambia el apellido del prisionero
     * @param apellidoPrisionero El nuevo apellido del prisionero. nuevoApellido!=null
     */
    public void cambiarApellido( String apellidoPrisionero )
    {
        apellido = apellidoPrisionero;
    }

    /**
     * Devuelve el crimen cometido por el prisionero.
     * @return Se retornó el crimen cometido por el prisionero
     */
    public String darCrimenCometido( )
    {
        return crimen;
    }

    /**
     * Cambia el crimen que cometió el prisionero
     * @param crimenCometido El nuevo crimen. crimenCometido!=null
     */
    public void cambiarCrimenCometido( String crimenCometido )
    {
        crimen = crimenCometido;
    }

    /**
     * Retorna el grupo criminal del prisionero
     * @return Se retornó el grupo criminal del prisionero
     */
    public String darGrupoCriminal( )
    {
        return grupoCriminal;
    }

    /**
     * Cambia el grupo criminal al que pertence el prisionero
     * @param grupo El nuevo grupo criminal del prisionero. grupo!=null
     */
    public void cambiarGrupoCriminal( String grupo )
    {
        grupoCriminal = grupo;
    }

    /**
     * Retorna la sentencia (en meses) que tiene que cumplir el prisionero
     * @return Se retornó la sentencia del prisionero
     */
    public int darTiempoSentencia( )
    {
        return tiempoSentencia;
    }

    /**
     * Cambia el largo de la sentencia del prisionero
     * @param tSentencia La nueva sentencia del prisionero. tSentencia>0
     */
    public void cambiarTiempoSentencia( int tSentencia )
    {
        tiempoSentencia = tSentencia;
    }

    /**
     * Retorna el nombre del prisionero
     * @return Se retornó el nombre del prisionero
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Cambia el nombre del prisionero
     * @param nombrePrisionero Nuevo nombre del prisionero. nombrePrisionero!=null
     */
    public void cambiarNombre( String nombrePrisionero )
    {
        nombre = nombrePrisionero;
    }

    /**
     * Devuelve el número que identifica al prisionero
     * @return Se retornó el numero que identifica al prisionero
     */
    public int darNumero( )
    {
        return numero;
    }

    /**
     * Cambia el número que identifica al prisionero
     * @param numeroPrisionero El nuevo número que identifica prisionero. numeroPrisionero>0
     */
    public void cambiarNumero( int numeroPrisionero )
    {
        numero = numeroPrisionero;
    }
}
