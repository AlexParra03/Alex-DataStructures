
package alex.datastructures;

/**
 * @author David A. Parra
 */
public interface List <E> {
    E get(int index);
    boolean remove(int index);
    boolean remove(E element);
    boolean add(E element);
    boolean add(int index, E element);
    int size();
    boolean set(int index, E element);
    boolean contains(E element);
}
