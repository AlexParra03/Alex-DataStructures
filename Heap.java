package AlexDataStructures;

// Priority Queue
public class Heap {

	// left child: 2i+1
	// right child 2i+2
	
	AlexArrayList<Integer> array = new AlexArrayList<Integer>();
	
	//Max Heapify add
	public void add(Integer number){
		array.add(number);
		
	}
	
	private void switchNumbers(int indexOne, int indexTwo){
		Integer holder = array.get(indexOne);
		array.replace( array.get(indexTwo), indexOne);
		array.replace(holder, indexTwo);
	}
	
	private void maxHeapify(int i){
		int parent = (int) (i-1)/2;
		if(parent < 0){
			return;
		}
		if(array.get(i) > array.get(parent)){
			switchNumbers( i, parent);
			maxHeapify(parent);
		}
	}
	
}
