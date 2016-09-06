/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ImagenPuntoTest.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogotï¿½ - Colombia)
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagen
 * Autor: Pablo Andrï¿½s Mï¿½rquez - 03-sep-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.pinturaPuntos.test;

import uniandes.cupi2.pinturaPuntos.mundo.IImagen;
import uniandes.cupi2.pinturaPuntos.mundo.ImagenPunto;

/**
 * Esta es la clase usada para verificar que los métodos de la clase InterfazImagen están correctamente implementados
 */
public class ImagenPuntoTest extends AbstractImagenTest
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea la instancia de una imagen de tipo ImagenPunto
     */
    public IImagen crearInstancia( int filas, int columnas )
    {
        return new ImagenPunto( filas, columnas );
    }
}