/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IFabrica.java,v 1.3 2007/02/01 15:18:00 jvillalo2 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

/**
 * Interfaz que contiene los servicios que debe prestar cualquier f�brica de bolsas
 */
public interface IFabrica
{
    /**
     * M�todo que retorna un instancia de una bolsa
     * @param superior Limite superior que tendr� la bolsa
     * @param inferior Limite inferior que tendr� la bolsa
     * @return Una instancia de una bolsa. Diferente de null
     */
    public IBolsa crearBolsa( int superior, int inferior );
}
