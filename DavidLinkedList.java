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
		this.root = new Node(null);
		this.end = this.root;
		this.size = 0;
	}
	
	// O(1)
	public boolean add(E element) {
		Node node = new Node(element);
		this.end.next = node;
		this.end = node;
		size++;
		
		return true;
	}

	// O(N)
	@Override
	public boolean add(int index, E element) {
		
		if(index < 0 || index >= size ){
			return false;
		}
		
		Node node = root;
		for(int i=0; i<index; i++){
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
		if(index < 0 || index >= size){
			return false;
		}
		
		Node node = root;
		for(int i=0; i<index-1; i++){
			node = node.next;
		}
		
		Node nextTwo = node.next.next;
		node.next = nextTwo;
		return true;
	}

	
	// O(N)
	@Override
	public E get(int index) {
		Node node = this.root;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		
		return node.data;
	}

	// O(N)
	@Override
	public boolean set(int index, E element) {
		Node node = root;
		for(int i=0; i<index; i++){
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
