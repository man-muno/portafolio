/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MatrizDispersaTest.java,v 1.1 2008/04/07 01:37:22 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz.test;

import uniandes.cupi2.collections.matriz.DimensionesInvalidasException;
import uniandes.cupi2.collections.matriz.IMatriz;
import uniandes.cupi2.collections.matriz.MatrizDispersa.MatrizDispersa;

/**
 * Pruebas concretas para la clase MatrizDispersa
 */
public class MatrizDispersaTest extends AbstractMatrizVariableTest
{

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.matriz.test.AbstractMatrizTest#instanciarMatriz()
     */
    @Override
    public IMatriz<String> instanciarMatriz( )
    {
        try
        {
            return new MatrizDispersa<String>(2000, 2000);
        }
        catch( DimensionesInvalidasException e )
        {
            fail(e.getMessage( ));
            return null;
        }
    }

}
