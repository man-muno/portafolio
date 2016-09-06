package uniandes.cupi2.tiendaMascotas.mundo;

/**
 * Clase que representa una mascota
 */
public class Mascota
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa las mascotas de tipo macho
     */
    public static final String SEXO_MACHO = "Macho";

    // TODO
    // Declarar la constante SEXO_HEMBRA
    // Completar la documentación
    /**
     * Representa las mascotas tipo hembra.
     */
    public static final String SEXO_HEMBRA = "Hembra";


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre nombre de la mascota exótica
     */
    private String nombre;

    /**
     * Edad de la mascota
     */
    private int edad;

    /**
     * Precio de venta de la mascota
     */
    // TODO
    // Declarar el atributo que modela el precio de venta de la mascota
    private int precio;
    /**
     * Cantidad de mascotas que se tiene actualmente
     */
    private int cantidadActual;

    /**
     * Sexo de la mascota
     */
    private String sexo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor que inicializa los atributos con los parámetros.
     * @param nNombre Nombre del animal. Diferente de null. Debe ser uno de los valores de las constantes definidas en TiendaMascotas
     * @param nEdad Edad de la mascota, entero mayor a cero
     * @param nPrecio Precio que tiene la mascota, entero mayor a cero
     * @param nCantidad Cantidad de mascotas que se han comprado.
     * @param nSexo Sexo de la mascota. Debe ser uno de los valores de las constantes definidas en Mascota
     */
    public Mascota( String nNombre, int nEdad, int nPrecio, int nCantidad, String nSexo )
    {
        //TODO
        // Completar el método de construcción de una mascota con los parámetros dados
        nombre = nNombre;
        edad = nEdad;
        precio = nPrecio;
        cantidadActual = nCantidad;
        sexo = nSexo;
 
     }

    /**
     * Retorna la cantidad de mascotas que se tiene en la tienda
     * @return Entero mayor o igual a cero
     */
    public int obtenerCantidadActual( )
    {
        return cantidadActual;
    }

    /**
     * Compra una cantidad dada de mascotas. Modifica la cantidad de mascotas actuales
     * @param cantidad Entero mayor a cero
     */
    public void comprarMascota( int cantidad )
    {
        cantidadActual += cantidad;
    }
    
    /**
     * Vende una cantidad dada de mascotas. Modifica la cantidad de mascotas vendidas. <br>
     * Resta la cantidad de las mascotas actuales
     * @param cantidad Cantidad de mascotas que se vendieron. Entero mayor a cero y menor que que la cantidad actual
     */
    // TODO
    // Construir el método (signatura y cuerpo) según la documentación
    public void venderMascota ( int cantidad )
    {
    	cantidadActual -= cantidad;
    }
    
    /**
     * Retorna la edad que tiene la mascota
     * @return Entero mayor a cero
     */
    public int obtenerEdad( )
    {
        return edad;
    }

    /**
     * Retorna el nombre de la mascota
     * @return Diferente de null
     */
    public String obtenerNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el precio de la mascota
     * @return Entero mayor a cero
     */
    public int obtenerPrecio( )
    {
        // TODO
        // Retornar el precio de venta de la mascota
        return precio;
    }

    /**
     * Retorna el sexo de la mascota
     * @return String cuyos valores pueden ser las constantes SEXO_HEMBRA o SEXO_MACHO
     */
    public String obtenerSexo( )
    {
        return sexo;
    }

}
