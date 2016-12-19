package AlexDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AlexArrayList<T> implements List<T> {

	private T[] data;
	private int size;
	private int index;
	
	public AlexArrayList(){
		data = null;
		size = 5;
		data = (T[]) new Object[this.size];
		index = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void manageSlots(int slotsToCheck ){
		if( this.index + slotsToCheck >= (size-1) ){
			//Grow by half, x1.5
			int newSize = (int) (1.5 * this.size);
			
			T[] newData = (T[]) new Object[newSize];
			for(int i=0; i<this.size; i++){
				newData[i] = this.data[i];
			}
			this.data = newData;
			this.size = newSize;
			manageSlots(slotsToCheck);
		}
		
	}
	

	private void shiftArray(int start, int emptySlots){
		manageSlots(emptySlots);
		if(start >= 0 && start < index){
			int newSize = this.index - start + emptySlots;
			T[] endChunk = (T[]) new Object[newSize];
			for(int i=0; i<newSize; i++){
				
				if(i< emptySlots-1){
					endChunk[i] = null ;
				} else {
					endChunk[i] = this.data[start+i-emptySlots];
				}
			}
			
			for(int i=0; i<newSize; i++){
				this.data[start+i] = endChunk[i]; 
			}
			
			
		}
		
		
	}
	
	@Override
	public boolean add(T arg0) {
		manageSlots(1);
		this.data[index] = arg0;
		index++;
		return true;
	}

	@Override
	public void add(int arg0, T arg1) {
		if(arg0 >= 0 && arg0 <= index){
			shiftArray(arg0, 1);
			this.data[arg0] = arg1;
			index++;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		manageSlots(arg0.size());
		Iterator i = arg0.iterator();
		while(i.hasNext()){
			this.data[index] = (T) i.next();
			index++;
		}
		return true;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		this.size = 0;
		this.index = 0;
		this.data = null;
		
	}

	@Override
	public boolean contains(Object arg0) {
		for(T t : this.data){
			if(t.getClass().equals(arg0)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		Iterator i = arg0.iterator();
		while(i.hasNext()){
			if(contains(i.next())){
				return true;
			}
		}
		return false;
	}

	@Override
	public T get(int arg0) {
		if(arg0 >= 0 && arg0 <= this.index -1){
			return this.data[arg0];
		}
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		for(int i=0; i<= index; i++){
			if(this.data[i].getClass().equals(arg0)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if(index == 0){
			return true;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		//Not fully implemented yet
		return indexOf(arg0);
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		int iElement = indexOf(arg0);
		if(iElement != -1){
			for(int i=iElement; i<this.index; i++ ){
				this.data[i] = this.data[i+1];
			}
			index--;
			return true;
		}
		return false;
	}

	@Override
	public T remove(int arg0) {
		int iElement = arg0;
		T element = null;
		if(iElement >= 0 && iElement <= index){
			element = this.data[arg0];
			for(int i=iElement; i<this.index; i++ ){
				this.data[i] = this.data[i+1];
			}
			index--;
		}
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T set(int arg0, T arg1) {
		if(arg0 >= 0 && arg0 <= index){
			this.data[arg0] = arg1;
		}
		return arg1;
	}

	@Override
	public int size() {
		return this.index;
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
