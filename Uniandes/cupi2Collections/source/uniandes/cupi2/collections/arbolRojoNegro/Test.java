package uniandes.cupi2.collections.arbolRojoNegro;

public class Test
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        ArbolRojoNegro<Integer> arbol = new ArbolRojoNegro<Integer>( );
        try
        {
            arbol.insertar( new Integer( 10 ) );
            arbol.insertar( new Integer( 85 ) );
            arbol.insertar( new Integer( 15 ) );
            arbol.insertar( new Integer( 70 ) );
            arbol.insertar( new Integer( 20 ) );
            arbol.insertar( new Integer( 60 ) );
            arbol.insertar( new Integer( 30 ) );
            arbol.insertar( new Integer( 50 ) );
            arbol.insertar( new Integer( 65 ) );
            arbol.insertar( new Integer( 80 ) );
            arbol.insertar( new Integer( 90 ) );
            arbol.insertar( new Integer( 40 ) );
            arbol.insertar( new Integer( 5 ) );
            arbol.insertar( new Integer( 55 ) );
        }
        catch( ElementoExisteException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }
    }

}
