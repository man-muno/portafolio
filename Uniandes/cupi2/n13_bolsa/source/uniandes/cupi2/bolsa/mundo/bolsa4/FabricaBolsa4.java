package uniandes.cupi2.bolsa.mundo.bolsa4;

import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IFabrica;

/**
 * Fabrica que retorna una instancia de una bolsa de tipo4
 * 
 */
public class FabricaBolsa4 implements IFabrica
{
    /**
     * Método que retorna un instancia de una bolsa de tipo 1
     * @param superior Limite superior que tendrá la bolsa
     * @param inferior Limite inferior que tendrá la bolsa
     * @return Una instancia de una bolsa de tipo 1. Diferente de null
     */
    public IBolsa crearBolsa( int superior, int inferior )
    {
        return new Bolsa4( superior, inferior );
    }
}
