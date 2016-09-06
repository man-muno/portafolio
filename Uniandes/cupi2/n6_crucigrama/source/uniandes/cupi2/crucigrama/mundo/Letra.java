package uniandes.cupi2.crucigrama.mundo;

/**
 * Clase que representa una casilla donde se coloca una letra en el crucigrama
 */
public class Letra
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Letra correcta
     */
    private String letraReal;
    
    /**
     * Letra que ingresa el usuario
     */
    private String letraJuego;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la letra. Se inicializa la letraReal con el parámetro pasado. La letraJuego se inicia vacia
     */
    public Letra(String nLetraReal)
    {
        letraReal = nLetraReal;
        letraJuego = new String();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Compara la letra real y la letra ingresada por el usuario para ver si son iguales.
     * @return True en caso que la letra ingresada y la letra real sean iguales, false de lo contrario 
     */
    public boolean esLetraCorrecta()
    {
        return letraReal.equalsIgnoreCase( letraJuego );
    }
    
    /**
     * Cambia el valor de la letra ingresada por el usuario
     * @param nLetraJuego Letra en juego. Diferente de null
     */
    public void ingresarLetraJuego(String nLetraJuego)
    {
        letraJuego = nLetraJuego;
    }
    
    /**
     * Verifica si la letra corresponde a una casilla negra
     * @return True en caso que corresponda a una casill negra, false de lo contrario.
     */
    public boolean esNegra( )
    {
        return letraReal.equalsIgnoreCase( "$" );
    }
}
