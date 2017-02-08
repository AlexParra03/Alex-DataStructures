package test;

import java.lang.Comparable;


public class TreeAVL<E extends Comparable<E>>  {

	class Node{
		
		E data;
		Node left;
		Node right;
		
		private Node(E data, Node left, Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
	}
	
	private Node root;
	
	public TreeAVL(E data){
		this.root = new Node(data, null, null);
	}
	
	public void insert(E data){
		
	}
	
	public void leftRotation(Node pivot){
		Node a = pivot;
		pivot = a.right;
		pivot.right = pivot.right.right;
		pivot.left = a;
	}
	
	
}
