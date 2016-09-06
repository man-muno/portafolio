/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FabricaImagenPunto.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Manuel Muñoz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.mundo;

/**
 * Fabrica de imágenes dispersas por puntos
 */
public class FabricaImagenPunto implements IFabrica
{

    /**
     * Dadas las coordenadas crea una imagen dispersas por puntos.
     * @param filas Cantidad de filas que tiene la imagen. Entero mayor o igual a cero.
     * @param columnas Cantidad de columnas que tiene la imagen. Entero mayor o igual a cero.
     */
    public IImagen crearImagen( int filas, int columnas )
    {
        return new ImagenPunto( filas, columnas );
    }

}
