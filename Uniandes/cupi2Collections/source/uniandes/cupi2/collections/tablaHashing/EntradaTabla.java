package uniandes.cupi2.collections.tablaHashing;

/**
 * Representa una entrada en la tabla de hashing, que asocia un valor con una llave. Contiene una rerencia al siguiente elemento del bucket al que pertenece
 * @param <L> Tipo de las llaves asociada con los elementos
 * @param <V> Tipo de los elementos guardados en la tabla
 */
public class EntradaTabla<L, V>
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento de la entrada
     */
    private V elemento;

    /**
     * Llave asociada con el elemento de la entrada
     */
    private L llave;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la entrada con el elemento y su llave asociada. <br>
     * <b>post: </b> Se constuy� una entrada de la tabla con la llave y elementos asociados.
     * @param tLlave la llave asociada con el elemento
     * @param tElemento El elemento de la entrada
     */
    public EntradaTabla( L tLlave, V tElemento )
    {
        elemento = tElemento;
        llave = tLlave;
    }

    /**
     * Constructor de la entrada con la llave especificada. <br>
     * <b>post: </b> Se constuy� una entrada de la tabla con la llave especificada, elemento= null.
     * @param tLlave la llave de la entrada
     */
    public EntradaTabla( L tLlave )
    {
        elemento = null;
        llave = tLlave;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento de la entrada. <br>
     * <b>post: </b> Se retorn� el elemento de la entrada.
     * @return Elemento de la entrada
     */
    public V darElemento( )
    {
        return elemento;
    }

    /**
     * Retorna la llave de la entrada. <br>
     * <b>post: </b> Se retorn� la llave de la entrada.
     * @return Llave de la entrada
     */
    public L darLlave( )
    {
        return llave;
    }

    /**
     * Cambia el elemento de la entrada. <br>
     * <b>post: </b> Se cambi� el elemento de la entrada.
     * @param Nuevo elemento de la entrada
     */
    public void cambiarElemento( V elem )
    {
        elemento = elem;
    }

    /**
     * Convierte la entrada a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String de la entrada. El String tiene el formato "[Llave: l Elemento: E]", donde l es la llave de la entrada y E su
     * elemento.
     * @return La representaci�n en String de la entrada
     */
    @Override
    public String toString( )
    {
        return "[Llave: " + llave.toString( ) + " Elemento: " + elemento.toString( ) + "]";
    }

    /**
     * Indica si la entrada especificada es igual a la actual. <br>
     * <b>post: </b> Se retorn� true si la entrada es igual a la actual o false de lo contrario. Dos entradas son iguales si sus llaves correspondientes lo son.
     * @return True si la entrada es igual a la actual o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals( Object o )
    {
        boolean igual = false;

        if( o instanceof EntradaTabla )
        {
            EntradaTabla<L, V> entrada = ( EntradaTabla )o;
            igual = llave.equals( entrada.darLlave( ) );

        }
        return igual;
    }

}
