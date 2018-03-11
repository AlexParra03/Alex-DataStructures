
package assignmentOne;

import java.util.ArrayList;

public class LispCalculator {
    
    // Global helper to keep track of the current digit while recursing
    private static int index;
    // The formula to be computed
    private static String operation;
    
    /*
    Call the recursive function to process the operation
    */
    public double enter(String operation){
        //Setting global helpers
        LispCalculator.operation = operation;
        LispCalculator.index = 0;
        //Calling recursive stack computation
        return recursiveComputation();
    }
    
    
    /*
    Using recursive stack call to compute operands and return the total value of the operation
    Storing operands in a list, and call the compute function based on the operation
    */
    public double recursiveComputation(){
        ArrayList<Double> operands = new ArrayList<>();
        char operator = 0;
        char c = 0;
        
        if(operation.charAt(index) == '('){
            index++;
        }
        
        while(index < operation.length()){
            c = operation.charAt(index);
            
            int ascii = (int) c;
            // ascii characters from '0' to '9'
            if(ascii >= 48 && ascii <= 57){
                operands.add((double) Character.getNumericValue(c));
            }
            
            if(c == '('){
                index++;
                operands.add( recursiveComputation() );
            }else if(c == ')'){
                index++;
                break;
            }else if(c == '*' || c == '/' || c == '+' || c == '-'){
                operator = c;
            }
            
            index++;
        }
        
        
        return arithmetic(operator, operands);
        
        
    }
    
    /*
    @param operator: the type of computing arithmetic gate
    @param operands: digits to be processed
    Compute all the operands with the correct operation
    */
    private double arithmetic(char operator, ArrayList<Double> operands){
        switch(operator){
            case '*':
                return multiply(operands);
            case '/':
                return divide(operands);
            case '+':
                return add(operands);
            case '-':
                return subtract(operands);
            default:
                System.out.println("Error: Operator not recognized.");
                return 0;
        }
    }
    
    
    /*
    @param operands: numbers to be computed
    Multiply the contained numbers
    */
    private double multiply(ArrayList<Double> operands){
        if(operands.size() == 0){
            return 1;
        }else{
            double total = 1;
            for(double x : operands){
                total *= x;
            }
            return total;
        }
    }
    
        /*
    @param operands: numbers to be computed
    Divide the contained numbers
    */
    private double divide(ArrayList<Double> operands){
        if(operands.size() == 0){
            System.out.println("Error: Divide operator needs at least one operand");
            return 0;
        }else if(operands.size() == 1){
            return 1 / ((double)operands.get(0));
        }else{
            double total = (double) operands.get(0);
            for(int i=1; i<operands.size(); i++){
                total = total / (double)operands.get(i);
            }
            return total;
        }
    }
    
        /*
    @param operands: numbers to be computed
     Add the contained numbers
    */
    private double add(ArrayList<Double> operands){
        if(operands.size() == 0){
            return 0;
        }else{
            double total = 0;
            for(double x : operands){
                total += x;
            }
            return total;
        }
    }
    
        /*
    @param operands: numbers to be computed
    Subtract the contained numbers
    */
    private double subtract(ArrayList<Double> operands){
        if(operands.size() == 0){
            System.out.println("Error: Subtract operator needs at least one operand");
            return 0;
        }if(operands.size() == 1){
            return -operands.get(0);
        }
        else{
            double total = (double) operands.get(0);
            for(int i= 1; i<operands.size(); i++){
                total -= (double) operands.get(i);
            }
            return total;
        }
    }
    
}
