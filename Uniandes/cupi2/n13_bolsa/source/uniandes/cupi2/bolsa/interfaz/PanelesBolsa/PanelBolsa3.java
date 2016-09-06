/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Muñoz - Aug 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.interfaz.PanelesBolsa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.bolsa.interfaz.PanelInfoBolsa;
import uniandes.cupi2.bolsa.interfaz.PanelOperaciones;
import uniandes.cupi2.bolsa.interfaz.PanelRepresentacion;
import uniandes.cupi2.bolsa.mundo.FueraLimiteException;
import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IFabrica;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

public class PanelBolsa3 extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identifica la fabrica a utilizar
     */
    private static final String NOMBRE_FABRICA = "fabrica3";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Objeto donde se encuentran los nombres de las fábricas.
     */
    private Properties propiedades;

    /**
     * Bolsa de tipo 3
     */
    private IBolsa bolsa3;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se encuentra la información de los elementos de la lista
     */
    private PanelRepresentacion panelRepresentacion;

    /**
     * Panel donde se muestra la información de los limites y la longitud de la bolsa
     */
    private PanelInfoBolsa panelInfoBolsa;

    /**
     * Panel donde se encuentran las opciones de la bolsa
     */
    private PanelOperaciones panelOperaciones;

    /**
     * Método constructor por defecto de la bolsa
     * @param propiedades Objeto donde se encuentran los nombres de las fábricas. Diferente de null
     */
    public PanelBolsa3( Properties propiedades )
    {
        this.propiedades = propiedades;
        initialize( );
    }

    /**
     * Método que inicializa y coloca los componentes del panel
     */
    private void initialize( )
    {
        // Crea y coloca el coloca el panel de representación
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        add( getPanelRepresentacion( ), constraint );
        // Crea y coloca el coloca el panel de información de la bolsa
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        add( getPanelInfoBolsa( ), constraint );
        // Crea y coloca el coloca el panel de operaciones de la bolsa
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 2;
        constraint.fill = GridBagConstraints.BOTH;
        add( getPanelOperaciones( ), constraint );

    }

    /**
     * Retorna el panel de representación, crea uno si no esta instanciado
     * @return El panel donde se muestran los elementos de la bolsa
     */
    private PanelRepresentacion getPanelRepresentacion( )
    {
        if( panelRepresentacion == null )
        {
            panelRepresentacion = new PanelRepresentacion( );
        }
        return panelRepresentacion;
    }

    /**
     * Retorna el panel de información, crea uno si no esta instanciado
     * @return El panel donde se muestran las caracteristicas de la bolsa
     */
    private PanelInfoBolsa getPanelInfoBolsa( )
    {
        if( panelInfoBolsa == null )
        {
            panelInfoBolsa = new PanelInfoBolsa( );
        }
        return panelInfoBolsa;
    }

    /**
     * Retorna el panel de las operaciones, crea uno si no esta instanciado
     * @return El panel donde se muestran las operaciones que se pueden hacer sobre la bolsa
     */
    private PanelOperaciones getPanelOperaciones( )
    {
        if( panelOperaciones == null )
        {
            panelOperaciones = new PanelOperaciones( this );
        }
        return panelOperaciones;
    }

    /**
     * Metodo que es llamando cuando se hace click en cualquier botón
     */
    public void actionPerformed( ActionEvent e )
    {
        // Accion cuando se hace click sobre el metodo iniciar
        if( PanelOperaciones.INICIAR.equals( e.getActionCommand( ) ) )
        {
            iniciarBolsa( );
        }
        // Accion cuando se hace click sobre el método agregar
        else if( PanelOperaciones.AGREGAR.equals( e.getActionCommand( ) ) && bolsa3 != null )
        {
            agregar( );
        }
        // Accion cuando se hace click sobre eliminar
        else if( PanelOperaciones.ELIMINAR.equals( e.getActionCommand( ) ) && bolsa3 != null )
        {
            eliminar( );
        }
        // Accion cuando se hace click en buscar
        else if( PanelOperaciones.BUSCAR.equals( e.getActionCommand( ) ) && bolsa3 != null )
        {
            buscar( );
        }
        // Accion que se ejecuta cuando se hace click en eliminar
        else if( PanelOperaciones.RETORNAR.equals( e.getActionCommand( ) ) && bolsa3 != null )
        {
            retornar( );
        }

        // Evalua si la bolsa ha sido inicializada para que un click no saque NullPointerException
        if( bolsa3 == null )
        {
            JOptionPane.showMessageDialog( this, "Debe iniciar la bolsa primero", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            // Si esta inicializada, se actualiza la interfaz
            try
            {
                getPanelInfoBolsa( ).actualizar( bolsa3 );
                getPanelRepresentacion( ).actualizar( bolsa3 );
                this.updateUI( );
            }
            catch( NoExisteException e1 )
            {
                // No deberia ocurrir, ya que la actualizacion se hace sobre los elementos actuales de la bolsa
            }
        }
    }

    /**
     * Método que pide los datos para iniciar la bolsa
     */
    private void iniciarBolsa( )
    {
        String inferior = JOptionPane.showInputDialog( this, "Límite inferior:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        String superior = JOptionPane.showInputDialog( this, "Límite superior:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        try
        {
            int numInferior = Integer.parseInt( inferior );
            int numSuperior = Integer.parseInt( superior );
            if( numInferior >= numSuperior && numInferior > 0 && numSuperior > 0)
            {
                JOptionPane.showMessageDialog( this, "El límite inferior debe ser menor y diferente que el mayor", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                IFabrica fabrica = ( IFabrica )Class.forName( propiedades.getProperty( NOMBRE_FABRICA ) ).newInstance( );
                bolsa3 = fabrica.crearBolsa( numInferior, numSuperior );
            }
        }
        catch( NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this, "El valor debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( InstantiationException e )
        {
            JOptionPane.showMessageDialog( this, "Problemas al instanciar la fábrica. " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IllegalAccessException e )
        {
            JOptionPane.showMessageDialog( this, "Problemas al instanciar la fábrica. " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( ClassNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "Problemas al instanciar la fábrica. " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método que pide la información para agregar un elemento
     */
    private void agregar( )
    {
        String agregar = JOptionPane.showInputDialog( this, "Número a agregar:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        try
        {
            int numAgregar = Integer.parseInt( agregar );
            try
            {
                bolsa3.agregar( numAgregar );
            }
            catch( FueraLimiteException e )
            {
                JOptionPane.showMessageDialog( this, "El valor debe estar entre los límites", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        catch( NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this, "El valor debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Método que pide la información para eliminar un elemento
     */
    private void eliminar( )
    {
        String eliminar = JOptionPane.showInputDialog( this, "Número a eliminar:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        try
        {
            int numEliminar = Integer.parseInt( eliminar );
            bolsa3.eliminar( numEliminar );
        }
        catch( NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this, "El valor debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( NoExisteException e2 )
        {
            JOptionPane.showMessageDialog( this, "El elemento a eliminar no existe", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método que pide la información para buscar un elemento
     */
    private void buscar( )
    {
        String buscar = JOptionPane.showInputDialog( this, "Número a buscar:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        try
        {
            int numBuscar = Integer.parseInt( buscar );
            String mensaje = "";
            if( bolsa3.buscar( numBuscar ) )
            {
                mensaje = "El número se encuentra en la bolsa";
            }
            else
            {
                mensaje = "El número NO se encuentra en la bolsa";
            }
            JOptionPane.showMessageDialog( this, mensaje, "Búsqueda en la Bolsa", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this, "El valor debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método que pide la información para retornar un elemento
     */
    private void retornar( )
    {
        String retornar = JOptionPane.showInputDialog( this, "Posición del número a retornar:", "Bolsa", JOptionPane.QUESTION_MESSAGE );
        try
        {
            int numRetornar = Integer.parseInt( retornar );
            if( numRetornar < 1 )
            {
                JOptionPane.showMessageDialog( this, "La posición debe ser mayor o igual a 1", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                int retorno = bolsa3.retornar( numRetornar );
                JOptionPane.showMessageDialog( this, "El valor es: " + retorno, "Número retornado", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        catch( NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this, "El valor debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( NoExisteException e2 )
        {
            JOptionPane.showMessageDialog( this, "El elemento a retornar no existe", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
}