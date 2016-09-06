package uniandes.cupi2.adivinaCual.mundo;

/**
 * Clase que representa un error al tratar de agregar una pregunta nueva.
 */
public class PreguntaNoAgregadaException extends Exception
{

    /**
     * Constructor por parámetros de la excepción.
     * @param mensaje Mensaje que quiere ser mostrado al usuario.
     */
    public PreguntaNoAgregadaException( String mensaje )
    {
        super( mensaje );
    }

}
