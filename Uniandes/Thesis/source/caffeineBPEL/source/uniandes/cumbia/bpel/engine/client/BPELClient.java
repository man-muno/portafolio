package uniandes.cumbia.bpel.engine.client;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.caffeine.deployer.deposit.BPELProcessInfo;

public class BPELClient implements Serializable
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
	
	public final static String WSDL_DIRECTORY= "wsdl";
	
	public final static String SRC_DIRECTORY= "src";
	
	public final static String STUB_SUFIX= "Stub"; 

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	/**
	 * The name of the process
	 */
	private String processName;
	
	/**
	 * The info of the process being consulted
	 */
	private BPELProcessInfo processInfo; 
	
	/**
	 * Loader for manipulated the information of the services
	 */
	private URLClassLoader loader; 	
	
	/**
	 * The stub
	 */
	private Class stub; 
	
	/**
	 * The url of the services 
	 */
	private String url; 
	
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	/**
	 * Contructor of the client
	 * @param pName The process name
	 */
	public BPELClient(String pName, String ur)
	{
		processName= pName;
		loadServiceClasses();
		url= ur; 
		
	}
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	/**
	 * Loads the classes associated with the services
	 */
	private void loadServiceClasses()
	{
		File axisFile= processInfo.getAxisFile();
		try 
		{
			loader= new URLClassLoader(new URL[]{axisFile.toURL()}, this.getClass().getClassLoader());
			
		} 
		catch (MalformedURLException e) 
		{			
			e.printStackTrace();
		}
		
		String clazzName= processName+STUB_SUFIX; 
		String packName= calculateClassPackageName(clazzName); 
		String clazzNameComplete= packName+clazzName; 
		
		if(!packName.endsWith("."))
			clazzNameComplete= packName+"."+clazzName;
		
		try 
		{
			stub= loader.loadClass(clazzNameComplete);
		} catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retrieves the packages name
	 * @param clazzName The name of the class
	 * @return The package name
	 */
	private String calculateClassPackageName(String clazzName)
	{
		File file= searchFile(clazzName); 
		
		String path= file.getParentFile().getAbsolutePath(); 
		
		int index= path.indexOf(SRC_DIRECTORY); 
		
		//Gets the part of the folders excluding the src
		String aux= path.substring(index+SRC_DIRECTORY.length()+1);
		
		//Remplaces the file separator by points
		String pack= aux.replace(file.separator, ".");
				
		return pack; 
	}
	
	
	/**
	 * Search the firs ocurrence of the file with the name provided
	 * @param namePart Part name of the file
	 * @return The file containig the name provided
	 */
	private File searchFile(String namePart)
	{
		File searched= null;
		
		File infoLocation= processInfo.getInformationLocation(); 
		
		File src= new File(infoLocation, WSDL_DIRECTORY+File.separator+SRC_DIRECTORY); 
		//System.out.println("EL CLIENTE ESTA MIRANDO EN EL ARCHIVO "+src.getAbsolutePath());
		//if(src.exists())
			//System.out.println("ESTA BIEN");
		//else
			//System.out.println("ESTA malllllllllllll");
		searched= searchFileInDirectory(src,  namePart);
				
		return searched; 
	}
	
	/**
	 * Searches the file in the directory provided
	 * @param dir The dir
	 * @param namePart The part name of the file
	 */
	private File searchFileInDirectory(File dir, String namePart)
	{
		File searched= null; 
		File[] files= dir.listFiles(); 
		
		for(int i=0; i<files.length && searched==null; i++)
		{	//System.out.println("ESTA MIRANDO EL ARCHIVO "+files[i].getAbsolutePath()+" con el nomPart "+namePart);
			if(files[i].isFile() && files[i].getName().contains(namePart))
			{
				searched= files[i];
			}
			else if(!files[i].isFile())
			{
				searched= searchFileInDirectory(files[i], namePart); 
			}
		}
		
		return searched; 
	}
	/**
	 * Returns the name of the operation
	 * @return The list of operations
	 */
	public List<String> getOperationsName(Class clazz)
	{
		Method[] methods= clazz.getMethods();
		
		List<String> names= new ArrayList<String>(); 
		String stubClassName= clazz.getName(); 
		
		for(int i=0; i<methods.length; i++)
		{
			if(methods[i].getDeclaringClass().getName().equals(stubClassName))
			{
				String actual= methods[i].getName();
				names.add(actual);
			}			 
		}
		
		return names; 
	}
	
	/**
	 * Returns the parameters of the operation
	 * @param methodName The method name
	 * @return The list of parameters 
	 */
	public List<String> getOperationParamatreNames(Class clazz, String methodName)
	{
		List<String> parameters= new ArrayList<String>();
					
		Method[] methods= clazz.getMethods();
		
		String stubClassName= clazz.getName();
		
		Method method= null; 
		
		for(int i=0; i<methods.length && method==null; i++)
		{
			if(methods[i].getDeclaringClass().getName().equals(stubClassName) && methods[i].getName().equals(methodName))
			{
				method= methods[i];
			}			 
		}
		
		TypeVariable<Method>[] parametersArray= method.getTypeParameters(); 
		
		for(int i=0; i<parametersArray.length; i++)
		{
			parameters.add(parametersArray[i].getName());
		}
		
		return parameters; 
	}
	
	/**
	 * Returns the parameters of the operation
	 * @param methodName The method name
	 * @return The list of parameters 
	 */
	public List<String> getOperationParamatreTypes(Class clazz, String methodName)
	{
		List<String> parameters= new ArrayList<String>();
									
		Method method= searchMethod(clazz, methodName);
		
		TypeVariable<Method>[] parametersArray= method.getTypeParameters(); 
		
		for(int i=0; i<parametersArray.length; i++)
		{
			parameters.add(parametersArray[i].getName());
		}
		
		return parameters; 
	}
	
	/**
	 * Returns the parameters of the operation
	 * @param methodName The method name
	 * @return The list of parameters 
	 */
	public List<Class> getOperationParamatreTypesClasses(Class clazz, String methodName)
	{		
									
		Method method= searchMethod(clazz, methodName);
		
		Class[] parametersArray= method.getParameterTypes(); 
		
		List<Class> parameters= new ArrayList<Class>();
		
		for(int i=0; i<parametersArray.length; i++)
		{
			parameters.add(parametersArray[i]);
		}
		
		return parameters; 
	}
	
	/**
	 * Returns the stub class
	 * @return The stub class
	 */
	public Class getStub()
	{
		return stub; 
	}
	
	/**
	 * Invokes the operation specified
	 * @param clazz The clazz to invoke the method 
	 * @param operationName The operation name 
	 * @param parameters The parameters of the operation
	 * @return The response. It can be null; 
	 */
	public Object invokeOperation(Class clazz, String operationName, Object[] parameters)
	{
		Object response= null;
		
		//Searches the method
		Method method= searchMethod(clazz, operationName); 
					
		try 
		{
			//System.out.println("el servicio que va a invocar es "+url); 
			//Searches the constructor
			Constructor constructor= clazz.getConstructor(new Class[]{String.class});
			
			//Create an instance
			Object service= constructor.newInstance(new Object[]{url});			
			response= method.invoke(service, parameters); 			 
		} 
		catch (SecurityException e) 
		{
		
			e.printStackTrace();
		} 
		catch (NoSuchMethodException e) 
		{
			
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) 
		{		
			e.printStackTrace();
		} 
		catch (InstantiationException e) 
		{		
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{		
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) 
		{		
			e.printStackTrace();
		}
		
		
		return response; 
	}

	/**
	 * Searches the method with the provided name
	 * @param clazz The clazz
	 * @param methodName The method name
	 * @return The method
	 */
	public Method searchMethod(Class clazz, String methodName)
	{
		Method[] methods= clazz.getMethods();
		
		String stubClassName= clazz.getName();
		
		Method method= null; 
		
		for(int i=0; i<methods.length && method==null; i++)
		{
			if(methods[i].getDeclaringClass().getName().equals(stubClassName) && methods[i].getName().equals(methodName))
			{
				method= methods[i];
			}			 
		}
		
		return method; 
	}
}
