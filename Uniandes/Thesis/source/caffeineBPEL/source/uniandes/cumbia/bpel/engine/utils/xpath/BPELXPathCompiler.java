package uniandes.cumbia.bpel.engine.utils.xpath;



public class BPELXPathCompiler implements Compiler 
{
	
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
	
	private static final XPathQName QNAME_NAME = new XPathQName(null, "name");
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
	public BPELXPathCompiler()
	{
		
	}
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

	public Object and(Object[] arguments) 
	{
		
		return new XPathOperationAnd(
	            toExpressionArray(arguments));
	}

	public Object divide(Object left, Object right) 
	{	
		return new XPathOperationDivide((XPathExpression) left, (XPathExpression) right);
	}

	public Object equal(Object left, Object right) 
	{
		if (isNameAttributeTest((XPathExpression) left)) {
            return new XPathNameAttributeTest((XPathExpression) left, (XPathExpression) right);
        }
        else {
            return new XPathOperationEqual(
                (XPathExpression) left,
                (XPathExpression) right);
        }
		
	}

	public Object expressionPath(Object expression, Object[] predicates, Object[] steps) 
	{	
		return new XPathExpressionPath(
	            (XPathExpression) expression,
	            toExpressionArray(predicates),
	            toStepArray(steps));
	}

	public Object function(int code, Object[] args) 
	{	
		return new XPathFunction(code, toExpressionArray(args));
	}

	public Object function(Object name, Object[] args)
	{	
		return new XPathExtensionFunction((XPathQName) name, toExpressionArray(args));
	}

	public Object greaterThan(Object left, Object right) 
	{	
		return new XPathOperationGreaterThan(
	            (XPathExpression) left,
	            (XPathExpression) right);
	}

	public Object greaterThanOrEqual(Object left, Object right) 
	{	
		return new XPathOperationGreaterThanOrEqual(
	            (XPathExpression) left,
	            (XPathExpression) right);
	}

	public Object lessThan(Object left, Object right) 
	{
		return new XPathOperationLessThan((XPathExpression) left, (XPathExpression) right);
		
	}

	public Object lessThanOrEqual(Object left, Object right) 
	{	
		return new XPathOperationLessThanOrEqual(
	            (XPathExpression) left,
	            (XPathExpression) right);
	}

	public Object literal(String value) 
	{
		return new XPathConstant(value);
	}

	public Object locationPath(boolean absolute, Object[] steps) 
	{	
		return new XPathLocationPath(absolute, toStepArray(steps));
	}

	public Object minus(Object argument) 
	{	
		return new XPathOperationNegate((XPathExpression) argument);
	}

	public Object minus(Object left, Object right) 
	{
	
		return new XPathOperationSubtract(
	            (XPathExpression) left,
	            (XPathExpression) right);
		
	}

	public Object mod(Object left, Object right) 
	{	
		return new XPathOperationMod((XPathExpression) left, (XPathExpression) right);
	}

	public Object multiply(Object left, Object right) 
	{	
		return new XPathOperationMultiply((XPathExpression) left, (XPathExpression) right);
	}

	public Object nodeNameTest(Object qname) 
	{	
		return new XPathNodeNameTest((XPathQName) qname);
	}

	public Object nodeTypeTest(int nodeType) 
	{	
		 return new XPathNodeTypeTest(nodeType);
	}

	public Object notEqual(Object left, Object right) 
	{
	
		 return new XPathOperationNotEqual(
		            (XPathExpression) left,
		            (XPathExpression) right);
	}

	public Object number(String value) 
	{
	
		return new XPathConstant(new Double(value));
	}

	public Object or(Object[] arguments) 
	{
	
        return new XPathOperationOr(
                toExpressionArray(arguments));
	}

	public Object processingInstructionTest(String instruction) 
	{
		return new XPathProcessingInstructionTest(instruction);
	}

	public Object qname(String prefix, String name) 
	{	
		return new XPathQName(prefix, name);
	}

	public Object step(int axis, Object nodeTest, Object[] predicates) 
	{
	
        return new XPathStep(
                axis,
                (XPathNodeTest) nodeTest,
                toExpressionArray(predicates));
	}

	public Object sum(Object[] arguments) 
	{	
		return new XPathOperationAdd(toExpressionArray(arguments));
	}

	public Object union(Object[] arguments) 
	{	
        return new XPathOperationUnion(
                toExpressionArray(arguments));
	}

	public Object variableReference(Object qName) 
	{	
		return new XPathVariableReference((XPathQName) qName);
	}
	
    private XPathExpression[] toExpressionArray(Object[] array) 
    {
        XPathExpression expArray[] = null;
        if (array != null) {
            expArray = new XPathExpression[array.length];
            for (int i = 0; i < expArray.length; i++) {
                expArray[i] = (XPathExpression) array[i];
            }
        }
        return expArray;
    }
    
    private boolean isNameAttributeTest(XPathExpression arg) {
        if (!(arg instanceof XPathLocationPath)) {
            return false;
        }

        XPathStep[] steps = ((XPathLocationPath) arg).getSteps();
        if (steps.length != 1) {
            return false;
        }
        if (steps[0].getAxis() != Compiler.AXIS_ATTRIBUTE) {
            return false;
        }
        XPathNodeTest test = steps[0].getNodeTest();
        if (!(test instanceof XPathNodeNameTest)) {
            return false;
        }
        if (!((XPathNodeNameTest) test).getNodeName().equals(QNAME_NAME)) {
            return false;
        }
        return true;
    }
    
    private XPathStep[] toStepArray(Object[] array) {
        XPathStep stepArray[] = null;
        if (array != null) {
            stepArray = new XPathStep[array.length];
            for (int i = 0; i < stepArray.length; i++) {
                stepArray[i] = (XPathStep) array[i];
            }
        }
        return stepArray;
    }

}
