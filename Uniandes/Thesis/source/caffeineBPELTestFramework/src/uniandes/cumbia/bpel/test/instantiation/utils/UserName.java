package uniandes.cumbia.bpel.test.instantiation.utils;

public class UserName
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    protected java.lang.String localUserName ;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    


    /**
     * @return the localUserName
     */
    public java.lang.String getUserName( )
    {
        return localUserName;
    }

    /**
     * @param localUserName the localUserName to set
     */
    public void setUserName( java.lang.String localUserName )
    {
        this.localUserName = localUserName;
    }
    
    @Override
    public String toString()
    {
        return localUserName;
    }
}
