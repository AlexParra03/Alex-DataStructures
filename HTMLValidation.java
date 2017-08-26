
package assignmentOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class HTMLValidation {
    
    private Stack<String> stack;
    
    public HTMLValidation(){
        stack = new Stack();
    }
    
    /*
    @param input: HTML text to buffer
    TODO: Get opening (< > </ > ) and closing tags from HTML Text.
    */
    public void readLine(String input){
        // Removing all spaces from string
        input = input.replaceAll("\\s+","");
        
        // Casting every letter to lower case
        input = input.toLowerCase();
        
        boolean inTag = false;
        String tag = "";
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            
            if(c  == '<'){
                inTag = true;
            }
            if(inTag){
                tag += c;
            }     
            if(c == '>'){
                inTag = false;
                manageTag(tag);
                tag = "";
            }
        }
    }
    
    /*
    @param filename: name of the HTML file to read
    TODO: Buffer text from a external html/text file
    */
    public void readTextFile(String filename){
        try {
            Scanner input = new Scanner(new File(filename));
            
            while(input.hasNextLine()){
                readLine(input.nextLine());
            }
            if(stack.isEmpty()){
                System.out.println("HTML File '" + filename + "' is correctly balanced");
            }else{
                System.out.println("Error: HTML File '" + filename + "' has tag errors. ");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    
    /*
    @param tag: Individual HTML tag (<> </>)
    TODO: Add oppening tags to the stack and pop closing tags
    */
    public void manageTag(String tag){
        if(tag.length() > 1){
            if(tag.charAt(1) == '/'){
                //Removing '<' and '</' from tags
                String openingTag = stack.peek().substring(1);
                String closingTag = tag.substring(2);
                if(openingTag.equals(closingTag)){
                    stack.pop();
                }
            }else{
                stack.add(tag);
            }
        }
    }
}