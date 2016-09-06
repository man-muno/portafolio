/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ImagenPuntoTest.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagen
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.pinturaPuntos.test;

import uniandes.cupi2.pinturaPuntos.mundo.IImagen;
import uniandes.cupi2.pinturaPuntos.mundo.ImagenPunto;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase InterfazImagen est�n correctamente implementados
 */
public class ImagenPuntoTest extends AbstractImagenTest
{

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea la instancia de una imagen de tipo ImagenPunto
     */
    public IImagen crearInstancia( int filas, int columnas )
    {
        return new ImagenPunto( filas, columnas );
    }
}