package uniandes.cupi2.adivinaCual.mundo;

/**
 * Clase que representa un error de lectura de un archivo
 */
public class ArchivoNoCargadoException extends Exception
{
    /**
     * Constructor de la clase
     * @param mensaje Mensaje. Puede ser null
     */
    public ArchivoNoCargadoException( String mensaje )
    {
        super( mensaje );
    }

}
