package lab2;

public interface Linkeable<E> {
	boolean add(E element);
	boolean add(int index, E element);
	boolean remove(E element);
	boolean remove(int index);
	E get(int index);
	boolean set(int index, E element);
	int size();
	
}
