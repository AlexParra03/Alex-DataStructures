package lab2;

public class DavidLinkedList<E> implements Linkeable<E> {

	private Node root;
	private Node end;
	private int size;
	
	private class Node{
		E data;
		Node next;
		
		public Node(E data){
			this.next = null;
			this.data = data;
		}
	}
	
	public DavidLinkedList(){
                this.root = null;
                this.end = null;
		this.size = 0;
	}
	
	// O(1)
	public boolean add(E element) {
            if(size == 0){
                this.root = new Node(element);
                this.end = this.root;
                size++;
                
            }else{
                this.end.next = new Node(element);
                this.end = this.end.next;
                size++;
            }

            return true;
	}

	// O(N)
	@Override
	public boolean add(int index, E element) {
		
		if(index < 0 || index >= size ){
                    return false;
		}
		
		Node node = root;
		for(int i=0; i<index-1; i++){
			node = node.next;
		}
		
		Node next = node.next;
		Node newNode = new Node(element);
		node.next = newNode;
		newNode.next = next;
		
		size++;
		
		return true;
	}

	// O(N)
	@Override
	public boolean remove(E element) {
            Node node = root;
            if(root.data.equals(element)){
                root = root.next;
                size--;
            }
            
            while(node.next != null){
                    if(node.next.data.equals(element)){
                            Node skipNext = node.next.next;
                            node.next = skipNext;
                            size--;
                            return true;
                    }
                    node = node.next;
            }
            return false;
	}

	// O(N)
	@Override
	public boolean remove(int index) {
            if(index < 0 || index >= size || size == 0){
                    return false;
            }else if(index == 0){
                this.root = this.root.next;
                size--;
                return true;
            }else{

                Node node = this.root;
                for(int i=0; i<index-1; i++){
                    node = node.next;
                }
                node.next = node.next.next;
                size--;
                return true;
            }
	}

	
	// O(N)
	@Override
	public E get(int index) {
            if(index <0 || index >= this.size){
                return null;
            }
            Node node = this.root;
            for(int i=0; i<index; i++){
                    node = node.next;
            }

            return node.data;
	}

	// O(N)
	@Override
	public boolean set(int index, E element) {
            if(index <0 || index >= this.size){
                return false;
            }
            Node node = root;
            for(int i=0; i<index-1; i++){
                    node = node.next;
            }
            node.data = element;
            return true;
	}

	// O(1)
	@Override
	public int size() {
		return this.size;
	}

}
