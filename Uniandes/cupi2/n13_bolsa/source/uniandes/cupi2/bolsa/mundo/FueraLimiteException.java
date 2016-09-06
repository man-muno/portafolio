package uniandes.cupi2.bolsa.mundo;

/**
 * Excepción generada cuando se quiere agregar un elemento que esta fuera del rango de la bolsa
 */
public class FueraLimiteException extends Exception
{
    /**
     * Constructor por parámetros
     * @param mensaje Mensaje a mostrarse.
     */
    public FueraLimiteException(String mensaje)
    {
        super( mensaje );
    }
}
