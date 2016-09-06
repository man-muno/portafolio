package uniandes.cupi2.interpol.mundo;

/**
 * Clase que representa una excepci�n lanzada cuando ocurre un error al agregar una ciudad.
 */
public class CiudadNoAgredagaException extends Exception
{
    /**
     * Constructor por par�metros.
     * @param mensaje Mensaje a mostrar
     */
    public CiudadNoAgredagaException( String mensaje )
    {
        super( mensaje );
    }

}
