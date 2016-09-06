package uniandes.cupi2.adivinaCual.mundo;

/**
 * Clase que representa un error cuando se quiere agregar un animal que ya existe
 */
public class AnimalExisteException extends Exception
{
    /**
     * Constructor de la clase
     * @param mensaje El mensaje de la excepcion.
     */
    public AnimalExisteException( String mensaje )
    {
        super( mensaje );
    }

}
