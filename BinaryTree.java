/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

/**
 *
 * @author David A. Parra
 */
public class BinaryTree<E extends Comparable>  {
    
    public class Node<E>{
        E data;
        Node left;
        Node right;
        
        private Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    public Node root;
    
    public BinaryTree(){
        root = null;
    }
    
    public BinaryTree(E data){
        root = new Node(data);
    }
    
    
    
    public String visitPreOrder(){
        String output = "";
        preOrder(root, output);
        return output;
    }
    
    private void preOrder(Node node, String buffer){
        if(node == null){
            return;
        }
        
        preOrder(node.left, buffer);
        buffer += " " + node.data;
        preOrder(node.right, buffer);
    }
    
}
