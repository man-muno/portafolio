package uniandes.cupi2.collections.tablaHashing.test;

import uniandes.cupi2.collections.tablaHashing.TablaHashing;

/**
 * Clase utilizada para verificar la estructura de la tabla de hashing
 * 
 */
public class VerificardorEstructura<L, V>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que la estructura de la tabla sea correcta
     * @param tabla Tabla a la que se va a verificar la estructura
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarFactorCarga( TablaHashing<L, V> tabla )
    {
        boolean correcta = true;

        if( tabla.darNumeroElementos( ) / tabla.darTamanhoTabla( ) >= TablaHashing.FACTOR_CARGA )
        {
            correcta = false;
        }

        return correcta;

    }

    /**
     * Verifica que el factor de carga de la tabla sea correcto
     * @param tabla Tabla a la que se va a verificar el factor de carga
     * @return True si el factor de carga de la tabla es correcto o false de lo contrario
     */
    public boolean verificarTablaHashing( TablaHashing<L, V> tabla )
    {
        return verificarFactorCarga( tabla );
    }
}
