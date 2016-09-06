/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TableModelPrisioneros.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 15, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import uniandes.cupi2.prision.mundo.Prisionero;

/**
 * Modelo de datos para la tabla de prisioneros
 */
public class TableModelPrisioneros extends AbstractTableModel
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Prisioneros a mostrar
     */
    private ArrayList prisioneros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del modelo de tabla
     * @param prisionerosP Los prisioneros que se encuentran en el sector
     */
    public TableModelPrisioneros( ArrayList prisionerosP )
    {
        prisioneros = prisionerosP;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el Numero de filas
     * @return Número de filas
     */
    public int getRowCount( )
    {
        return prisioneros.size( );
    }

    /**
     * Devuelve el numero de columnas
     * @return Número de columnas
     */
    public int getColumnCount( )
    {
        return 3;
    }

    /**
     * Devuelve el elemento en la fila y columna especificada
     * @param row Fila del valor que se desea
     * @param col Columna del valor que se desea
     */
    public Object getValueAt( int row, int col )
    {
        switch( col )
        {
            case 0:
                int numero= ( ( Prisionero )prisioneros.get( row ) ).darNumero( );
                return new Integer(numero);
            case 1:
                return ( ( Prisionero )prisioneros.get( row ) ).darNombre( );
            default:
                return ( ( Prisionero )prisioneros.get( row ) ).darApellido( );
        }
    }

    /**
     * Devuelve el nombre de la columna
     * @param col Columna de la que se desea el nombre
     * @return El nombre de la columna
     */
    public String getColumnName( int col )
    {
        switch( col )
        {
            case 0:
                return "Número";
            case 1:
                return "Nombre";
            default:
                return "Apellido";
        }
    }

}
