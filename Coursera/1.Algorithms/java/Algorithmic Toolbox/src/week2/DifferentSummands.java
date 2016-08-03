package week2;
import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        
        int k = n;
        int l=1;
        
        while(!(k<=2*l)){
        	summands.add(new Integer(l));
        	k-=1;
        	l*=2;
        }
        
        if(k >= 4)
        	summands.add(new Integer(k-1));
        else 
        	summands.add(new Integer(k));
        
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

