package uniandes.cupi2.cupEbay.mundo;

import uniandes.cupi2.collections.avl.ArbolAVL;
import uniandes.cupi2.collections.avl.ExisteException;

public class Main
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        CupEbay cup = new CupEbay();
        ArbolAVL<Categoria> arbol = new ArbolAVL<Categoria>();
        try
        {
            arbol.insertar( new Categoria("a") );
            arbol.insertar( new Categoria("aa") );
            arbol.insertar( new Categoria("b") );
            arbol.insertar( new Categoria("c") );
        }
        catch( ExisteException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        

    }

}
