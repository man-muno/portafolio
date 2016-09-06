package uniandes.cupi2.interpol.mundo;

/**
 * Clase que representa una excepción lanzada cuando ocurre un error al agregar una ciudad.
 */
public class CiudadNoAgredagaException extends Exception
{
    /**
     * Constructor por parámetros.
     * @param mensaje Mensaje a mostrar
     */
    public CiudadNoAgredagaException( String mensaje )
    {
        super( mensaje );
    }

}
