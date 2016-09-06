package uniandes.cumbia.bpel.test.instantiation.utils;

public class Result
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    protected java.lang.String localResult ;


    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    

    /**
     * @return the localResult
     */
    public java.lang.String getResult( )
    {
        return localResult;
    }

    /**
     * @param localResult the localResult to set
     */
    public void setResult( java.lang.String localResult )
    {
        this.localResult = localResult;
    }
    
    @Override
    public String toString()
    {
        return localResult;
    }
    
    
}
