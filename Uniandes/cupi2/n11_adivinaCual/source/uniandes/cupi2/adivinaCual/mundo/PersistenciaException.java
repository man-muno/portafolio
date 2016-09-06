package uniandes.cupi2.adivinaCual.mundo;

/**
 * Clase que representa un error cuando se quiere cargar o salvar el mundo
 */
public class PersistenciaException extends Exception
{
    /**
     * Constructor de la clase
     * @param mensaje El mensaje de la excepcion.
     */
    public PersistenciaException( String mensaje )
    {
        super( mensaje );
    }
}
