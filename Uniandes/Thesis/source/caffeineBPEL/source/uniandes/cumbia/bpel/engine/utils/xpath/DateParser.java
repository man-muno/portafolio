package uniandes.cumbia.bpel.engine.utils.xpath;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;
public class DateParser 
{
	
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
	
	public final static String DEAD_LINE_DATE_FORMAT= "yyyy-mm-dd";
	
	public final static String DEAD_LINE_DATE_TIME_FORMAT= "yyyy-MMdd'T'HH':'mm':'ss";
		    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	
	/**
	 * Parses the duration expression specified
	 * @param expression The expression to be parsed
	 * @return The time, in milleseconds equivalent to the expression 
	 */
	public static final long  parseDurationExpression(String expression)
	{
		DatatypeFactory dataFactory;
		long duration= 0;
		try 
		{
			dataFactory = DatatypeFactoryImpl.newInstance();
			Duration dur= dataFactory.newDuration(expression);
			duration= dur.getTimeInMillis(new Date(System.currentTimeMillis()));
		} 
		catch (DatatypeConfigurationException e) 
		{			 
			e.printStackTrace();
		}
		
		return duration;		
	}
	
	/**
	 * Parses the dead line expression expression specified
	 * @param expression The expression to be parsed
	 * @return The time, in milleseconds equivalent to the expression 
	 */
	public static final long  parseDeadlineExpression(String expression)
	{
		SimpleDateFormat sdf= new SimpleDateFormat(DEAD_LINE_DATE_TIME_FORMAT);
		Date date;
		
		try 
		{			
			date= sdf.parse(expression);
			return date.getTime();
		} 
		catch (ParseException e) 
		{			
			try 
			{	
				sdf= new SimpleDateFormat(DEAD_LINE_DATE_FORMAT);
				date= sdf.parse(expression);
				return date.getTime();
				
			} 
			catch (ParseException e1) 
			{			
				e.printStackTrace();
			}
			
			
		}		
		return 0;
		
	}
	
	public static void main(String[] args) 
	{
		long dur= parseDurationExpression("P1Y2M3DT10H30M");
		
		//System.out.println(dur);
		
		dur= parseDurationExpression("P120D");
		
		//System.out.println(dur);
		
		long deadLine= parseDeadlineExpression("2007-05-10T10:20:00-05:00");
		
		//System.out.println(deadLine);
		
		deadLine= parseDeadlineExpression("2007-05-10");
		
		//System.out.println(deadLine);
	}
}
