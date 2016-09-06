package uniandes.cupi2.pinturaPuntos.mundo;

/**
 * Interfaz donde se definen los servicios de una imagen
 */
public interface IImagen
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Agrega un punto a la imagen dadas las coordenadas.
     * @param fila Fila en la que se quiere colocar el punto.
     * @param columna Columna que se quiere poner el punto.
     */
    public void agregarPunto( int fila, int columna );

    /**
     * Devuelve el número de columnas que tiene la imagen.
     * @return Número de columnas de la imagen. Entero mayor o igual a cero
     */
    public int darColumnas( );

    /**
     * Devuelve el número de filas de la imagen.
     * @return Número de filas de la imagen. Entero mayor o igual a cero
     */
    public int darFilas( );

    /**
     * Devuelve si el píxel que se encuentra en las coordenada dada es negro.
     * @param fila La fila del píxel que se quiere saber si es negro. Entero mayor o igual a cero.
     * @param columna La columna del píxel que se quiere saber si es negro. Entero mayor o igual a cero.
     * @return Booleano que representa: <br>
     *         <li>true, en caso que sea negro. <br>
     *         <li>false, en caso que sea blanco. <br>
     */
    public boolean esNegro( int fila, int columna );

    /**
     * Devuelve el numero de píxeles negros dada una fila.
     * @param fila Fila que se desea averiguar cuantos píxeles son negros. Entero mayor o igual a cero.
     * @return Número de píxeles negros en una imagen. Entero mayor a cero o cero.
     */
    public int darNumeroPixelesNegrosFila( int fila );

    /**
     * Devuelve el número de píxeles negros que tiene toda la imagen.
     * @return Píxeles negros que tiene la imagen. Entero mayor a cero o cero.
     */
    public int darNumeroNegros( );

    /**
     * Retorna un iterador sobre los elementos de la imagen
     * @param fila Entero que representa la fila donde se quiere obtener el iterador
     * @return Un iterador sobre los elementos de la imagen
     */
    public IIteradorPintura darIterador( int fila );

    // -----------------------------------------------------------------
    // Métodos de extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( );

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( );

}