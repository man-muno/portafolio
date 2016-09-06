/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.generador.mundo;

import java.util.Date;

/**
 * Valida que los datos de un ejercicio sean correctos
 * 
 * @author Pablo Barvo
 */
public class Validador
{

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public Validador( )
    {
    }

    //-----------------------------------------------------------------
    // Métodos públicos
    //-----------------------------------------------------------------

    /**
     * Valida un ejercicio para confirmar que todos los datos son validos y correctos
     * @param ejercicio Ejercicio a validar
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    public void validar( Ejercicio ejercicio ) throws DatoInvalidoException
    {
        //
        //Verifica que ningun valor sea null ni vacío
        validarNoVacio( "El autor", ejercicio.darAutor( ) );
        validarNoVacio( "La clase principal", ejercicio.darClasePrincipal( ) );
        validarNoVacio( "El nombre", ejercicio.darNombre( ) );
        validarNoVacio( "La fécha", ejercicio.darFecha( ) );
        validarNoVacio( "La ubicación de los archivos", ejercicio.darUbicacion( ) );
        //
        //Valida los nombres y los niveles
        validarCamelCase( "El nombre", ejercicio.darNombre( ) );
        validarPascalCase( "La clase principal", ejercicio.darClasePrincipal( ) );
        validarNumero( "El nivel", ejercicio.darNivel( ), 1, 18 );
    }

    //-----------------------------------------------------------------
    // Métodos privados
    //-----------------------------------------------------------------

    /**
     * Valida que un valor tenga formato camel case.
     * @param nombre Nombre del dato
     * @param valor Valor a validar
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    private void validarCamelCase( String nombre, String valor ) throws DatoInvalidoException
    {
        if( !valor.matches( "([A-Z,0-9]*[a-z,0-9]++)++" ) )
        {
            throw new DatoInvalidoException( nombre + " debe tener un formato camel case (ej: brazoAzul)." );
        }
        if( Character.toLowerCase( valor.charAt( 0 ) ) != valor.charAt( 0 ) )
        {
            throw new DatoInvalidoException( nombre + " debe tener un formato camel case (ej: brazoAzul)." );
        }
    }

    /**
     * Valida que un valor tenga formato pascal case.
     * @param nombre Nombre del dato
     * @param valor Valor a validar
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    public void validarPascalCase( String nombre, String valor ) throws DatoInvalidoException
    {
        if( !valor.matches( "([A-Z,0-9]{1}[a-z,0-9]++)++" ) )
        {
            throw new DatoInvalidoException( nombre + " debe tener un formato pascal case (ej: BrazoAzul)." );
        }
    }

    /**
     * Valida que un valor sea numerico y se encuentre entre un rango determinado
     * @param nombre Nombre del dato
     * @param valor Valor a validar
     * @param valorMinimo Valor minimo que debe tener
     * @param valorMaximo Valor máximo que debe tener
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    public void validarNumero( String nombre, int valor, int valorMinimo, int valorMaximo ) throws DatoInvalidoException
    {
        if( valor < valorMinimo )
        {
            throw new DatoInvalidoException( nombre + " no puede ser menor a " + valorMinimo );
        }
        if( valor > valorMaximo )
        {
            throw new DatoInvalidoException( nombre + " no puede ser mayor a " + valorMaximo );
        }
    }

    /**
     * Valida que un valor no sea null ni vacío
     * @param nombre Nombre del dato
     * @param valor Valor a validar
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    public void validarNoVacio( String nombre, String valor ) throws DatoInvalidoException
    {
        if( valor == null )
        {
            throw new DatoInvalidoException( nombre + " no puede ser null" );
        }
        else if( valor.length( ) == 0 )
        {
            throw new DatoInvalidoException( nombre + "  no puede ser vacío" );
        }
    }

    /**
     * Valida que un valor fecha no sea null
     * @param nombre Nombre del dato
     * @param valor Valor a validar
     * @throws DatoInvalidoException Exsisten datos inválidos en el ejercicio.
     */
    public void validarNoVacio( String nombre, Date valor ) throws DatoInvalidoException
    {
        if( valor == null )
        {
            throw new DatoInvalidoException( nombre + " no puede ser null" );
        }
    }

}
