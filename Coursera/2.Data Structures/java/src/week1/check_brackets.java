package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Bracket problem = null;
        
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length() && problem == null; ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            	opening_brackets_stack.push(new Bracket(next,position+1));
            }

            if ((next == ')' || next == ']' || next == '}')) {
                // Process closing bracket, write your code here
            	Bracket bracket = opening_brackets_stack.size() > 0 ? opening_brackets_stack.pop(): null;
            	if(bracket != null && !bracket.match(next)){
            		problem = bracket;
            		problem.position = position+1;
            	}
            	if(bracket==null)
            		problem = new Bracket(next, position+1);
            }
        }

        // Printing answer, write your code here
        
        if(problem == null)
        	if(opening_brackets_stack.isEmpty())
        		System.out.println("Success");
	        else
	        	System.out.println(opening_brackets_stack.pop().position);
	    else
	    	System.out.println(problem.position);
    }
}
