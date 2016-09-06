/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * $Id: TablaHashingEstaticaTest.java,v 1.1 2008/03/28 03:38:05 jua-gome Exp $
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Mayo 15, 2008 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.tablaHashing.test;

import uniandes.cupi2.collections.tablaHashing.ITablaHashing;
import uniandes.cupi2.collections.tablaHashing.TablaHashingEstatica.TablaHashingEstatica;

/**
 * Clase completa con la que se hacen las pruebas de la tabla de hashing estatica.
 */
public class TablaHashingEstaticaTest extends AbstractTablaHashingTest
{

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.tablaHashing.test.AbstractTablaHashingTest#crearTabla()
     */
    @Override
    public ITablaHashing<String, Integer> crearTabla( )
    {
        return new TablaHashingEstatica<String, Integer>();
    }

}
