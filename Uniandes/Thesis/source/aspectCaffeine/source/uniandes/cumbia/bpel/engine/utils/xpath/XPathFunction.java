/*
 * Copyright 1999-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uniandes.cumbia.bpel.engine.utils.xpath;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.InfoSetUtil;
import org.apache.commons.jxpath.ri.model.NodePointer;

import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.process.IProcess;

/**
 * An element of the compile tree representing one of built-in functions like "position()" or "number()".
 * 
 * @author Dmitri Plotnikov
 * @version $Revision: 1.2 $ $Date: 2009/02/10 00:55:04 $
 */
public class XPathFunction extends XPathOperation
{

    private static final Double ZERO = new Double( 0 );
    private int functionCode;
    public final static String STRING_TYPE = "String";

    public final static String INTEGER_TYPE = "Integer";

    public final static String BOOLEAN_TYPE = "Boolean";

    public final static String BYTE_TYPE = "Byte";

    public final static String DOUBLE_TYPE = "Double";

    public final static String CHARACTER_TYPE = "Character";

    public final static String DATE_TYPE = "Date";

    public XPathFunction( int functionCode, XPathExpression args[] )
    {
        super( args );
        this.functionCode = functionCode;
    }

    public int getFunctionCode( )
    {
        return functionCode;
    }

    protected String getFunctionName( )
    {
        switch( functionCode )
        {
            case Compiler.FUNCTION_LAST:
                return "last";
            case Compiler.FUNCTION_POSITION:
                return "position";
            case Compiler.FUNCTION_COUNT:
                return "count";
            case Compiler.FUNCTION_ID:
                return "id";
            case Compiler.FUNCTION_LOCAL_NAME:
                return "local-name";
            case Compiler.FUNCTION_NAMESPACE_URI:
                return "namespace-uri";
            case Compiler.FUNCTION_NAME:
                return "name";
            case Compiler.FUNCTION_STRING:
                return "string";
            case Compiler.FUNCTION_CONCAT:
                return "concat";
            case Compiler.FUNCTION_STARTS_WITH:
                return "starts-with";
            case Compiler.FUNCTION_CONTAINS:
                return "contains";
            case Compiler.FUNCTION_SUBSTRING_BEFORE:
                return "substring-before";
            case Compiler.FUNCTION_SUBSTRING_AFTER:
                return "substring-after";
            case Compiler.FUNCTION_SUBSTRING:
                return "substring";
            case Compiler.FUNCTION_STRING_LENGTH:
                return "string-length";
            case Compiler.FUNCTION_NORMALIZE_SPACE:
                return "normalize-space";
            case Compiler.FUNCTION_TRANSLATE:
                return "translate";
            case Compiler.FUNCTION_BOOLEAN:
                return "boolean";
            case Compiler.FUNCTION_NOT:
                return "not";
            case Compiler.FUNCTION_TRUE:
                return "true";
            case Compiler.FUNCTION_FALSE:
                return "false";
            case Compiler.FUNCTION_LANG:
                return "lang";
            case Compiler.FUNCTION_NUMBER:
                return "number";
            case Compiler.FUNCTION_SUM:
                return "sum";
            case Compiler.FUNCTION_FLOOR:
                return "floor";
            case Compiler.FUNCTION_CEILING:
                return "ceiling";
            case Compiler.FUNCTION_ROUND:
                return "round";
            case Compiler.FUNCTION_KEY:
                return "key";
            case Compiler.FUNCTION_FORMAT_NUMBER:
                return "format-number";
            case Compiler.FUNCTION_BPEL_GET_VARIABLE_DATA:
                return "getVariableData";
        }
        return "unknownFunction" + functionCode + "()";
    }

    public XPathExpression getArg1( )
    {
        return args[ 0 ];
    }

    public XPathExpression getArg2( )
    {
        return args[ 1 ];
    }

    public XPathExpression getArg3( )
    {
        return args[ 2 ];
    }

    public int getArgumentCount( )
    {
        if( args == null )
        {
            return 0;
        }
        return args.length;
    }

    /**
     * Returns true if any argument is context dependent or if the function is last(), position(), boolean(), local-name(), name(), string(), lang(), number().
     */
    public boolean computeContextDependent( )
    {
        if( super.computeContextDependent( ) )
        {
            return true;
        }

        switch( functionCode )
        {
            case Compiler.FUNCTION_LAST:
            case Compiler.FUNCTION_POSITION:
                return true;

            case Compiler.FUNCTION_BOOLEAN:
            case Compiler.FUNCTION_LOCAL_NAME:
            case Compiler.FUNCTION_NAME:
            case Compiler.FUNCTION_NAMESPACE_URI:
            case Compiler.FUNCTION_STRING:
            case Compiler.FUNCTION_LANG:
            case Compiler.FUNCTION_NUMBER:
                return args == null || args.length == 0;

            case Compiler.FUNCTION_COUNT:
            case Compiler.FUNCTION_ID:
            case Compiler.FUNCTION_CONCAT:
            case Compiler.FUNCTION_STARTS_WITH:
            case Compiler.FUNCTION_CONTAINS:
            case Compiler.FUNCTION_SUBSTRING_BEFORE:
            case Compiler.FUNCTION_SUBSTRING_AFTER:
            case Compiler.FUNCTION_SUBSTRING:
            case Compiler.FUNCTION_STRING_LENGTH:
            case Compiler.FUNCTION_NORMALIZE_SPACE:
            case Compiler.FUNCTION_TRANSLATE:
            case Compiler.FUNCTION_NOT:
            case Compiler.FUNCTION_TRUE:
            case Compiler.FUNCTION_FALSE:
            case Compiler.FUNCTION_SUM:
            case Compiler.FUNCTION_FLOOR:
            case Compiler.FUNCTION_CEILING:
            case Compiler.FUNCTION_ROUND:
                return false;

            case Compiler.FUNCTION_FORMAT_NUMBER:
                return args != null && args.length == 2;
        }

        return false;
    }

    public String toString( )
    {
        StringBuffer buffer = new StringBuffer( );
        buffer.append( getFunctionName( ) );
        buffer.append( '(' );
        XPathExpression args[] = getArguments( );
        if( args != null )
        {
            for( int i = 0; i < args.length; i++ )
            {
                if( i > 0 )
                {
                    buffer.append( ", " );
                }
                buffer.append( args[ i ] );
            }
        }
        buffer.append( ')' );
        return buffer.toString( );
    }

    /**
     * Computes a built-in function
     */
    public Object compute( BPELContext context )
    {
        switch( functionCode )
        {
            case Compiler.FUNCTION_LAST:
                return functionLast( context );
            case Compiler.FUNCTION_POSITION:
                return functionPosition( context );
            case Compiler.FUNCTION_COUNT:
                return functionCount( context ); // TODO ARREGLAR
            case Compiler.FUNCTION_LANG:
                // return functionLang(context); //TODO ARREGLAR
                return null;
            case Compiler.FUNCTION_ID:
                return functionID( context );
            case Compiler.FUNCTION_LOCAL_NAME:
                return functionLocalName( context );
            case Compiler.FUNCTION_NAMESPACE_URI:
                // return functionNamespaceURI(context); //TODO ARREGLAR
                return null;
            case Compiler.FUNCTION_NAME:
                return functionName( context );
            case Compiler.FUNCTION_STRING:
                return functionString( context );
            case Compiler.FUNCTION_CONCAT:
                return functionConcat( context );
            case Compiler.FUNCTION_STARTS_WITH:
                return functionStartsWith( context );
            case Compiler.FUNCTION_CONTAINS:
                return functionContains( context );
            case Compiler.FUNCTION_SUBSTRING_BEFORE:
                return functionSubstringBefore( context );
            case Compiler.FUNCTION_SUBSTRING_AFTER:
                return functionSubstringAfter( context );
            case Compiler.FUNCTION_SUBSTRING:
                return functionSubstring( context );
            case Compiler.FUNCTION_STRING_LENGTH:
                return functionStringLength( context );
            case Compiler.FUNCTION_NORMALIZE_SPACE:
                return functionNormalizeSpace( context );
            case Compiler.FUNCTION_TRANSLATE:
                return functionTranslate( context );
            case Compiler.FUNCTION_BOOLEAN:
                return functionBoolean( context );
            case Compiler.FUNCTION_NOT:
                return functionNot( context );
            case Compiler.FUNCTION_TRUE:
                return functionTrue( context );
            case Compiler.FUNCTION_FALSE:
                return functionFalse( context );
            case Compiler.FUNCTION_NULL:
                return functionNull( context );
            case Compiler.FUNCTION_NUMBER:
                return functionNumber( context );
            case Compiler.FUNCTION_SUM:
                return functionSum( context );
            case Compiler.FUNCTION_FLOOR:
                return functionFloor( context );
            case Compiler.FUNCTION_CEILING:
                return functionCeiling( context );
            case Compiler.FUNCTION_ROUND:
                return functionRound( context );
            case Compiler.FUNCTION_KEY:
                return functionKey( context );
            case Compiler.FUNCTION_FORMAT_NUMBER:
                return functionFormatNumber( context );
            case Compiler.FUNCTION_BPEL_GET_VARIABLE_DATA:
                return functionBpelGetVariableData( context );
        }
        return null;
    }

    protected Object functionLast( BPELContext context )
    {
        /*
         * assertArgCount(0); // Move the position to the beginning and iterate through // the context to count nodes. int old = context.getCurrentPosition(); context.reset();
         * int count = 0; while (context.nextNode()) { count++; }
         *  // Restore the current position. if (old != 0) { context.setPosition(old); } return new Double(count);
         */
        return null;
    }

    protected Object functionPosition( BPELContext context )
    {
        assertArgCount( 0 );
        // return new Integer(context.getCurrentPosition());
        return null;
    }

    protected Object functionCount( BPELContext context )
    {
        assertArgCount( 1 );
        XPathExpression arg1 = getArg1( );
        int count = 0;
        Object value = arg1.compute( context ); // TODO ARREGLAR
        if( value instanceof NodePointer )
        {
            value = ( ( NodePointer )value ).getValue( );
        }
        if( value instanceof EvalContext )
        {
            EvalContext ctx = ( EvalContext )value;
            while( ctx.hasNext( ) )
            {
                ctx.next( );
                count++;
            }
        }
        else if( value instanceof Collection )
        {
            count = ( ( Collection )value ).size( );
        }
        else if( value == null )
        {
            count = 0;
        }
        else
        {
            count = 1;
        }
        return new Double( count );
    }

    protected Object functionLang(/* ElementMemory context */)
    {
        /*
         * assertArgCount(1); String lang = InfoSetUtil.stringValue(getArg1().compute(context)); NodePointer pointer = (NodePointer) context.getSingleNodePointer(); if
         * (pointer == null) { return Boolean.FALSE; } return pointer.isLanguage(lang) ? Boolean.TRUE : Boolean.FALSE;
         */
        // TODO ARREGLAR
        return null;
    }

    protected Object functionID( BPELContext context )
    {
        /*
         * assertArgCount(1); String id = InfoSetUtil.stringValue(getArg1().computeValue(context)); JXPathContext jxpathContext = context.getJXPathContext(); NodePointer
         * pointer = (NodePointer) jxpathContext.getContextPointer(); return pointer.getPointerByID(jxpathContext, id);
         */
        // TODO ARREGLAR
        return null;
    }

    protected Object functionKey( BPELContext context )
    {
        /*
         * assertArgCount(2); String key = InfoSetUtil.stringValue(getArg1().computeValue(context)); String value = InfoSetUtil.stringValue(getArg2().computeValue(context));
         * JXPathContext jxpathContext = context.getJXPathContext(); NodePointer pointer = (NodePointer) jxpathContext.getContextPointer(); return
         * pointer.getPointerByKey(jxpathContext, key, value);
         */
        // TODO ARREGLAR
        return null;
    }

    protected Object functionNamespaceURI( BPELContext context )
    {
        /*
         * if (getArgumentCount() == 0) { NodePointer ptr = context.getCurrentNodePointer(); String str = ptr.getNamespaceURI(); return str == null ? "" : str; }
         * assertArgCount(1); Object set = getArg1().compute(context); if (set instanceof EvalContext) { EvalContext ctx = (EvalContext) set; if (ctx.hasNext()) { NodePointer
         * ptr = (NodePointer) ctx.next(); String str = ptr.getNamespaceURI(); return str == null ? "" : str; } }
         */// TODO ARREGLAR
        return "";
    }

    protected Object functionLocalName( BPELContext context )
    {
        /*
         * if (getArgumentCount() == 0) { NodePointer ptr = context.getCurrentNodePointer(); return ptr.getName().getName(); } assertArgCount(1); Object set =
         * getArg1().compute(context); if (set instanceof EvalContext) { EvalContext ctx = (EvalContext) set; if (ctx.hasNext()) { NodePointer ptr = (NodePointer) ctx.next();
         * return ptr.getName().getName(); } }
         */// TODO ARREGLAR
        return "";
    }

    protected Object functionName( BPELContext context )
    {
        /*
         * if (getArgumentCount() == 0) { NodePointer ptr = context.getCurrentNodePointer(); return ptr.getName().toString(); } assertArgCount(1); Object set =
         * getArg1().compute(context); if (set instanceof EvalContext) { EvalContext ctx = (EvalContext) set; if (ctx.hasNext()) { NodePointer ptr = (NodePointer) ctx.next();
         * return ptr.getName().toString(); } }
         */// TODO ARREGLAR
        return "";
    }

    protected Object functionString( BPELContext context )
    {
        /*
         * if (getArgumentCount() == 0) { return InfoSetUtil.stringValue(context.getCurrentNodePointer()); } assertArgCount(1); return
         * InfoSetUtil.stringValue(getArg1().computeValue(context));
         */
        return null;
        // TODO ARREGLAR
    }

    protected Object functionConcat( BPELContext context )
    {
        if( getArgumentCount( ) < 2 )
        {
            assertArgCount( 2 );
        }
        StringBuffer buffer = new StringBuffer( );
        XPathExpression args[] = getArguments( );
        for( int i = 0; i < args.length; i++ )
        {
            //System.out.println( " -------------- NUMERO DE ARGUMENTO PROCESADO ----------- " + i + " con valor " + args[ i ] + " Y CON CLASE " + args[ i ].getClass( ).getName( ) );
            Object val = args[ i ].compute( context );
            //System.out.println( " type del valor calculado " + val.getClass( ).getName( ) + " con valor " + val );
            String stringVal = "";

            if( ! ( val instanceof String ) )
            {
                stringVal = objectToString( val );
            }
            else
                stringVal = ( String )val;

            buffer.append( stringVal );
        }
        return buffer.toString( );
    }

    protected Object functionStartsWith( BPELContext context )
    {
        assertArgCount( 2 );
        String s1 = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        String s2 = InfoSetUtil.stringValue( getArg2( ).compute( context ) );
        return s1.startsWith( s2 ) ? Boolean.TRUE : Boolean.FALSE;
    }

    protected Object functionContains( BPELContext context )
    {
        assertArgCount( 2 );
        String s1 = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        String s2 = InfoSetUtil.stringValue( getArg2( ).compute( context ) );
        return s1.indexOf( s2 ) != -1 ? Boolean.TRUE : Boolean.FALSE;

    }

    protected Object functionSubstringBefore( BPELContext context )
    {
        assertArgCount( 2 );
        String s1 = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        String s2 = InfoSetUtil.stringValue( getArg2( ).compute( context ) );
        int index = s1.indexOf( s2 );
        if( index == -1 )
        {
            return "";
        }
        return s1.substring( 0, index );
    }

    protected Object functionSubstringAfter( BPELContext context )
    {
        assertArgCount( 2 );
        String s1 = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        String s2 = InfoSetUtil.stringValue( getArg2( ).compute( context ) );
        int index = s1.indexOf( s2 );
        if( index == -1 )
        {
            return "";
        }
        return s1.substring( index + s2.length( ) );

    }

    protected Object functionSubstring( BPELContext context )
    {
        int ac = getArgumentCount( );
        if( ac != 2 && ac != 3 )
        {
            assertArgCount( 2 );
        }

        String s1 = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        double from = InfoSetUtil.doubleValue( getArg2( ).compute( context ) );
        if( Double.isNaN( from ) )
        {
            return "";
        }

        from = Math.round( from );
        if( ac == 2 )
        {
            if( from < 1 )
            {
                from = 1;
            }
            return s1.substring( ( int )from - 1 );
        }
        else
        {
            double length = InfoSetUtil.doubleValue( getArg3( ).compute( context ) );
            length = Math.round( length );
            if( length < 0 )
            {
                return "";
            }

            double to = from + length;
            if( to < 1 )
            {
                return "";
            }

            if( to > s1.length( ) + 1 )
            {
                if( from < 1 )
                {
                    from = 1;
                }
                return s1.substring( ( int )from - 1 );
            }

            if( from < 1 )
            {
                from = 1;
            }
            return s1.substring( ( int )from - 1, ( int ) ( to - 1 ) );
        }
    }

    protected Object functionStringLength( BPELContext context )
    {
        String s;
        if( getArgumentCount( ) == 0 )
        {
            s = "";
        }
        else
        {
            assertArgCount( 1 );
            s = InfoSetUtil.stringValue( getArg1( ).compute( context ) );
        }
        return new Double( s.length( ) );
    }

    protected Object functionNormalizeSpace( BPELContext context )
    {
        /*
         * assertArgCount(1); String s = InfoSetUtil.stringValue(getArg1().computeValue(context)); char chars[] = s.toCharArray(); int out = 0; int phase = 0; for (int in = 0;
         * in < chars.length; in++) { switch(chars[in]) { case 0x20: case 0x9: case 0xD: case 0xA: if (phase == 0) { // beginning ; } else if (phase == 1) { // non-space phase =
         * 2; chars[out++] = ' '; } break; default: chars[out++] = chars[in]; phase = 1; } } if (phase == 2) { // trailing-space out--; } return new String(chars, 0, out);
         */
        return null;
        // TODO ARREGLAR
    }

    protected Object functionTranslate( BPELContext context )
    {
        /*
         * assertArgCount(3); String s1 = InfoSetUtil.stringValue(getArg1().computeValue(context)); String s2 = InfoSetUtil.stringValue(getArg2().computeValue(context));
         * String s3 = InfoSetUtil.stringValue(getArg3().computeValue(context)); char chars[] = s1.toCharArray(); int out = 0; for (int in = 0; in < chars.length; in++) { char
         * c = chars[in]; int inx = s2.indexOf(c); if (inx != -1) { if (inx < s3.length()) { chars[out++] = s3.charAt(inx); } } else { chars[out++] = c; } } return new
         * String(chars, 0, out);
         */// TODO ARREGLAR
        return null; // TODO ARREGLAR
    }

    protected Object functionBoolean( BPELContext context )
    {
        assertArgCount( 1 );
        return InfoSetUtil.booleanValue( getArg1( ).compute( context ) ) ? Boolean.TRUE : Boolean.FALSE;
    }

    protected Object functionNot( BPELContext context )
    {
        assertArgCount( 1 );
        return InfoSetUtil.booleanValue( getArg1( ).compute( context ) ) ? Boolean.FALSE : Boolean.TRUE;
    }

    protected Object functionTrue( BPELContext context )
    {
        assertArgCount( 0 );
        return Boolean.TRUE;
    }

    protected Object functionFalse( BPELContext context )
    {
        assertArgCount( 0 );
        return Boolean.FALSE;
    }

    protected Object functionNull( BPELContext context )
    {
        assertArgCount( 0 );
        return null;
    }

    protected Object functionNumber( BPELContext context )
    {
        if( getArgumentCount( ) == 0 )
        {
            return InfoSetUtil.number( getArg1( ).compute( context ) );
        }
        assertArgCount( 1 );
        return InfoSetUtil.number( getArg1( ).compute( context ) );
    }

    protected Object functionSum( BPELContext context )
    {
        assertArgCount( 1 );
        Object v = getArg1( ).compute( context );
        if( v == null )
        {
            return ZERO;
        }
        else if( v instanceof EvalContext )
        {
            double sum = 0.0;
            EvalContext ctx = ( EvalContext )v;
            while( ctx.hasNext( ) )
            {
                NodePointer ptr = ( NodePointer )ctx.next( );
                sum += InfoSetUtil.doubleValue( ptr );
            }
            return new Double( sum );
        }
        throw new JXPathException( "Invalid argument type for 'sum': " + v.getClass( ).getName( ) ); // TODO CAMBIAR EL TIPO DE EXCEPCION
    }

    protected Object functionFloor( BPELContext context )
    {
        assertArgCount( 1 );
        double v = InfoSetUtil.doubleValue( getArg1( ).compute( context ) );
        return new Double( Math.floor( v ) );
    }

    protected Object functionCeiling( BPELContext context )
    {
        assertArgCount( 1 );
        double v = InfoSetUtil.doubleValue( getArg1( ).compute( context ) );
        return new Double( Math.ceil( v ) );
    }

    protected Object functionRound( BPELContext context )
    {
        assertArgCount( 1 );
        double v = InfoSetUtil.doubleValue( getArg1( ).compute( context ) );
        return new Double( Math.round( v ) );
    }

    private Object functionFormatNumber( BPELContext context )
    {
        /*
         * int ac = getArgumentCount(); if (ac != 2 && ac != 3) { assertArgCount(2); }
         * 
         * double number = InfoSetUtil.doubleValue(getArg1().compute(context)); String pattern = InfoSetUtil.stringValue(getArg2().compute(context));
         * 
         * DecimalFormatSymbols symbols = null; if (ac == 3) { String symbolsName = InfoSetUtil.stringValue(getArg3().compute(context)); symbols =
         * context.getJXPathContext().getDecimalFormatSymbols(symbolsName); } else { NodePointer pointer = context.getCurrentNodePointer(); Locale locale; if (pointer != null) {
         * locale = pointer.getLocale(); } else { locale = context.getJXPathContext().getLocale(); } symbols = new DecimalFormatSymbols(locale); }
         * 
         * DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(); format.setDecimalFormatSymbols(symbols); format.applyLocalizedPattern(pattern); return
         * format.format(number);
         */
        return null;
    }

    private void assertArgCount( int count )
    {
        if( getArgumentCount( ) != count )
        {
            throw new JXPathException( "Incorrect number of argument: " + this );
        }
    }

    private Object functionBpelGetVariableData( BPELContext context )
    {
        //System.out.println("arg1 " + getArg1( ));
        //System.out.println("arg2 " + getArg2( ));
        //System.out.println("arg3 " + getArg3( ));
        //System.out.println("ArgumentCount " + getArgumentCount( ));
        
        IData variable = (( IProcess )context.getElement( ) ).getData( ( String )getArg1( ).compute( context ) );
        //System.out.println( "__________________________________------+++++++++++++++ESTA BUSCANDO EL ELEMENTO  " + variable.getName( ) );
        Object resul = variable.getPart( "payload" );
        //System.out.println( "__________________________________------+++++++++++++++OBTUVO EL RESULTADO 1 " + variable + " " + resul);
        if( getArgumentCount( ) > 1 )
        {
            JXPathContext jxcontext = JXPathContext.newContext( resul );
            Object part = null;

            try
            {
                part = jxcontext.getValue( ( String )getArg2( ).compute( context ) );
                //System.out.println( "__________________________________------+++++++++++++++OBTUVO EL RESULTADO w " + part );
            }
            catch( JXPathException e )
            {
            }

            if( getArgumentCount( ) == 3 )
            {
                if( part != null )
                    jxcontext = JXPathContext.newContext( part );
                try
                {
                    resul = jxcontext.getValue( ( String )getArg3( ).compute( context ) );
                    //System.out.println( "ESTO ES LO QUE ESTA RETORNANDO LA FUNCION DE BPEL " + resul );
                }
                catch( JXPathException e )
                {
                }
            }
            else
                resul = part;
        }

        return resul;
    }

    @Override
    public String getType( )
    {
        return null;
    }

    @Override
    public boolean isContextDependent( )
    {
        return computeContextDependent( );
    }

    private String objectToString( Object obj )
    {
        String resul = "";

        Class clazz = obj.getClass( );

        //System.out.println( "/*/*/*/*/*/*/*/*/*/ESTA MIRANDO ES EL OBJETO " + obj );

        Field[] attributes = clazz.getDeclaredFields( );

        for( int i = 0; i < attributes.length; i++ )
        {

            Field actual = attributes[ i ];
            Class classType = actual.getType( );
            //System.out.println( "EL TIPO DEL ATRIBUTO QU ESTA MIRANDO ES " + classType + " " + classType );

            if( actual.getModifiers( ) == Modifier.PROTECTED )
            {
                String name = actual.getName( );
                //System.out.println( "ESTA MIRANDO EL ATRIBUTO " + name );
                String nameWLocal = name.substring( 5 );
                nameWLocal = convertToUpperCase( nameWLocal );
                try
                {
                    Method method = clazz.getMethod( "get" + nameWLocal, new Class[0] );
                    //System.out.println("method name " + method.getName( ));
                    Object value = method.invoke( obj, new Object[0] );
                    String stringType = getType( classType.getName( ) );

                    //System.out.println( "EL VALOR QUE RECUPERO " + value );
                    if( stringType != null )
                    {
                        resul += value.toString( ) + " ";
                    }
                    else
                    {
                        resul += objectToStringAux( value );
                    }
                }
                catch( SecurityException e )
                {
                    e.printStackTrace( );
                }
                catch( NoSuchMethodException e )
                {
                    e.printStackTrace( );
                }
                catch( IllegalArgumentException e )
                {
                    e.printStackTrace( );
                }
                catch( IllegalAccessException e )
                {

                    e.printStackTrace( );
                }
                catch( InvocationTargetException e )
                {

                    e.printStackTrace( );
                }

            }
        }
        //System.out.println( "********** ESTO ES LO QUE ESTA DEVOLVIENDO EN EL OBJEC TO STRING " + resul );
        return resul;
    }

    private String objectToStringAux( Object obj )
    {
        String resul = "";

        Class clazz = obj.getClass( );

        Field[] attributes = clazz.getDeclaredFields( );

        for( int i = 0; i < attributes.length; i++ )
        {

            Field actual = attributes[ i ];
            Class classType = actual.getType( );

            if( actual.getModifiers( ) == Modifier.PROTECTED )
            {

                String name = actual.getName( );
                String nameWLocal = name.substring( 5 );
                nameWLocal = convertToUpperCase( nameWLocal );
                try
                {
                    Method method = clazz.getMethod( "get" + nameWLocal, new Class[0] );
                    Object value = method.invoke( obj, new Object[0] );
                    String stringType = getType( classType.getName( ) );

                    if( stringType != null )
                    {
                        resul += value.toString( ) + " ";
                    }
                    else
                    {
                        resul += objectToStringAux( value );
                    }
                }
                catch( SecurityException e )
                {

                    e.printStackTrace( );
                }
                catch( NoSuchMethodException e )
                {

                    e.printStackTrace( );
                }
                catch( IllegalArgumentException e )
                {

                    e.printStackTrace( );
                }
                catch( IllegalAccessException e )
                {

                    e.printStackTrace( );
                }
                catch( InvocationTargetException e )
                {

                    e.printStackTrace( );
                }

            }
        }

        return resul;
    }

    /**
     * Converts the first letter of the String to uopper case
     * @param word The string to convert
     * @return The string with the first letter in upper case
     */
    private String convertToUpperCase( String word )
    {
        char[] newWord = word.toCharArray( );

        newWord[ 0 ] = Character.toUpperCase( newWord[ 0 ] );

        return new String( newWord );
    }

    /**
     * Returns the type of the object if it is a a basic typ
     * @param object The object to get the type
     * @return The type in string or null if is a complex type
     */
    private String getType( String object )
    {
        String type = null;
        String className = object;

        if( className.equals( String.class.getName( ) ) )
        {
            type = STRING_TYPE;
        }
        else if( className.equals( Integer.class.getName( ) ) )
        {
            type = INTEGER_TYPE;
        }
        else if( className.equals( Double.class.getName( ) ) )
        {
            type = DOUBLE_TYPE;
        }
        else if( className.equals( Boolean.class.getName( ) ) )
        {
            type = BOOLEAN_TYPE;
        }
        else if( className.equals( Character.class.getName( ) ) )
        {
            type = CHARACTER_TYPE;
        }
        else if( className.equals( Date.class.getName( ) ) )
        {
            type = DATE_TYPE;
        }

        return type;
    }
}