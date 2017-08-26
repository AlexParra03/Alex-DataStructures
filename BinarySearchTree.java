
package assignmentTwo;


public class BinarySearchTree <E extends Comparable> {
    
 
    
    Node root;
    
    public BinarySearchTree(){
        this.root = null;
    }
    
    public BinarySearchTree(E data){
        this.root = new Node(data);
    }
    
    /*
    @param item to insert
    return if the item was succesfully inserted
    TODO calls one of the private insert methods
    */
    public boolean insert(E item){
        if(item == null){
            return false;
        }
        
        this.root = recursiveInsert(this.root, item);
        return true;
    }
    
    /*
    @param node to be inserted
    @param item to be encapsulated inside the node
    return new node created or root
    */
    private Node recursiveInsert(Node node, E item){
        
        if(node == null){
            node = new Node(item);
            return node;
        }
        
        if(item.compareTo(node.data) < 0){
            node.left = recursiveInsert(node.left, item);
        }else if(item.compareTo(node.data) > 0){
            node.right = recursiveInsert(node.right, item);
        }
        
        return node;
    }
    
    /*
    @param item to search
    @return if the item is in the tree
    TODO calls one of the private search methods
    */
    public boolean search(E item){
        return recursiveSearch(root, item);
    }
    
    /*
    @param node in which we are currently focusing
    @param item to be found
    TODO recursively traverses the tree looking for the item
    */
    private boolean recursiveSearch(Node node, E item){
        if(node == null){
            return false;
        }
        
        if(item.compareTo(node.data) < 0){
            return recursiveSearch(node.left, item);
        }else if(item.compareTo(node.data) > 0){
            return recursiveSearch(node.right, item);
        }else{
            return true;
        }
        
    }
    

    // Calls the In Order Traversal inner method
    public void print(){
        System.out.println(" Binary Search Tree: ");
        inOrderTraversal(root);
        System.out.println();
        
    }
    
    /*
    @param node is the root in which the tree is built on
    TODO traverse the tree in-order
    */
    private void inOrderTraversal(Node node){
        if(node == null){
            return;
        }
        
        inOrderTraversal(node.left);
        System.out.print(node.data + ", " );
        inOrderTraversal(node.right);
    }
    
    //Calls one of the delete methods
    public boolean delete(E item){
        return iterativeDelete(item);
    }
    
    /*
    @param item to be deleted in the tree
    return if the item was succesfully found and deleted from the tree
    TODO delete an item from the tree
    */
    private boolean iterativeDelete( E item){
        Node current = this.root;
        Node parent = this.root;
        while(current != null){
            if(item.compareTo(current.data) < 0){
                parent = current;
                current = current.left;
            }else if(item.compareTo(current.data) > 0){
                parent = current;
                current = current.right;
            }else{
                break;
            }
        }
        
        // Item is not found in the tree
        if(current == null){
            return false;
        }
        
        if(current == parent){ // Deleting the root item, root has special case because it has no parent
            if(current.left == null && current.right == null){ // Case 1: root has no children
                this.root = null;
            }else if(current.left == null && current.right != null){ // Case 2: root has one child
                this.root = current.right;
            }else if(current.left != null && current.right == null){
                this.root = current.left;
            }else{ // Case 3: root has two children
                Node temp = current.right;
                while(temp.left != null){
                    parent = temp;
                    temp = temp.left;
                }
                current.data = temp.data;
                current = temp;
            }
        }
        
        while(current != null){
            if(current.left == null && current.right == null){ // Case 1: Node has no children
                if(parent.left == current){
                    parent.left = null;
                }else if(parent.right == current){
                    parent.right = null;
                }
                break;
            }else if(current.left == null && current.right != null){  //Case 2:  Node has one child
                parent.right = current.right;
                break;
            }else if(current.left != null && current.right == null){
                parent.left = current.left;
                break;
            }else{  // Case 3: Node has two children
                // Get the smalles value of the right branch and its parent
                Node temp = current.right;
                while(temp.left != null){
                    parent = temp;
                    temp = temp.left;
                }
                current.data = temp.data;
                parent = current;
                current = temp;
            }
        }
        return true;
    }
    
       private class Node{
        Node left;
        Node right;
        E data;
        
        public Node(E item, Node left, Node right){
            this.data = item;
            this.left = left;
            this.right = right;
        }
        
        public Node(E item){
            this.data = item;
            this.left = null;
            this.right = null;
        }
        
    }
    
    
}
