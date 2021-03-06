/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Munoz - Sep 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.test;

import uniandes.cupi2.bolsa.mundo.*;
import uniandes.cupi2.bolsa.mundo.bolsa1.*;

/**
 * Prueba de las operaciones de la bolsa de tipo 1
 * 
 */
public class Bolsa1Test extends AbstractBolsaTest
{
    /**
     * Creaci�n de una instancia con las estructuras concretas de datos
     */
     public IBolsa crearInstancia( int inferior, int superior )
    {
        return new Bolsa1( inferior, superior );
    }
}
