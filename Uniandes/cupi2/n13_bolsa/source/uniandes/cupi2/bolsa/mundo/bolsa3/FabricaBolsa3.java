package uniandes.cupi2.bolsa.mundo.bolsa3;

import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IFabrica;

/**
 * Fabrica que retorna una instancia de una bolsa de tipo3
 * 
 */
public class FabricaBolsa3 implements IFabrica
{
    /**
     * Método que retorna un instancia de una bolsa de tipo 4
     * @param superior Limite superior que tendrá la bolsa
     * @param inferior Limite inferior que tendrá la bolsa
     * @return Una instancia de una bolsa de tipo 1. Diferente de null
     */
    public IBolsa crearBolsa( int superior, int inferior )
    {
        return new Bolsa3( superior, inferior );
    }

}
