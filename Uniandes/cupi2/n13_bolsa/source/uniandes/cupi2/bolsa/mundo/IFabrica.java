/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IFabrica.java,v 1.3 2007/02/01 15:18:00 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

/**
 * Interfaz que contiene los servicios que debe prestar cualquier fábrica de bolsas
 */
public interface IFabrica
{
    /**
     * Método que retorna un instancia de una bolsa
     * @param superior Limite superior que tendrá la bolsa
     * @param inferior Limite inferior que tendrá la bolsa
     * @return Una instancia de una bolsa. Diferente de null
     */
    public IBolsa crearBolsa( int superior, int inferior );
}
