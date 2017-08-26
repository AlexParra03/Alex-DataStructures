
package assignmentTwo;

import java.util.HashMap;
import java.util.HashSet;


public class Trie {
    // Number of words contained by the tree
    private int words;
    // Starting node
    private Node root;
    
    public Trie(){
        this.words = 0;
        this.root = new Node(null);
    }
    
    /*
    @param word sequence of characters to be added as nodes in the trie
    @return if the adding operation was sucessful
    */
    public boolean add(String word){
        //Removing uppercase letters
        word = word.toLowerCase();
        // Removing spaces
        word = word.replaceAll("\\s","");
        // Adding a empty word in the tree
        if(word.length() == 0){
            this.root.addChild(null);
            return true;
        }
        
        Node node = this.root;
        int index = 0;
        Character c = word.charAt(index);
        while(node.children.contains( c ) ){
            // Getting the next node
            node = node.map.get(c);
            // When processing the last letter, putting a null end to indicate that the word is done
            if(index == word.length() -1){
                node.addChild(null);
                this.words++;
                return true;
            }
            c = word.charAt(++index);
        }
        
        // Starting from the nodes that don't already exists
        while(index < word.length()){
            c = word.charAt(index);
            node.addChild(c);
            // getting the node prevously added
            node = node.map.get(c);
            index++;
        }
        //Adding a null to indicate that the word is done
        node.addChild(null);
        this.words++;
        return true;
     
    }
    
   /*
    @param word sequence of letters used to traverse the path
    return if the full word is contained in the trie
    */
    public boolean contains(String word){
        //Removing uppercase letters
        word = word.toLowerCase();
        //Removing spaces
        word = word.replaceAll("\\s","");
        
        Node current = this.root;
        // Traversing the tree, looking for nodes that are based on the characters of the word
        for(int i=0; i<word.length(); i++){
            Character c = word.charAt(i);
            if(current.children.contains( c )){
                current = current.map.get(c);
            }else{
                return false;
            }
        }
        //There exists a full path, returning true if the word is complete (contains a null child)
        return (current.children.contains(null));
    }
    
    /*
    @param prefix sequence of characters used to traverse the path
    @return if there exists words starting with 'prefix' in the dictionary
    */
    public boolean containsPrefix(String prefix){
        //Removing uppercase letters
        prefix = prefix.toLowerCase();
        //Removing spaces
        prefix = prefix.replaceAll("\\s","");
        
        Node current = this.root;
        for(int i=0; i<prefix.length(); i++){
            Character c = prefix.charAt(i);
            if(current.children.contains(c)){
                current = current.map.get(c);
            }else{
                return false;
            }
        }
        return true;
    }
    
    /*
    @return number of words contained in the dictionary/tree
    */
    public int size(){
        return this.words;
    }
    
    /*
    TODO: Delete all the words from the tree
    */
    public void clear(){
        this.root = new Node(null);
        this.words = 0;
    }
 
    private class Node{
        //Characted represented by the node
        Character letter;
        //All the childs if this Node, stored as characters
        HashSet<Character> children;
        // HashMap used to map from Characters to Nodes
        HashMap<Character, Node> map;
        
        
        private Node(Character letter){
            this.letter = letter;
            children = new HashSet<Character>();
            map = new HashMap<Character, Node>();
        }
        
        /*
        @param c child of this node to be added
        TODO adds a child based on the character, also handles the node creation and mapping
        */
        private void addChild(Character c){
            if(c == null){
                children.add(null);
            }else{
                Node child = new Node(c);
                this.children.add(c);
                map.put(c, child);
            }
        }
        
    }
}
