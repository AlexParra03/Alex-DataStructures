
package alex.datastructures;

/**
 * @author David A. Parra
 */
public interface Queue<E> {
    E poll();
    boolean offer(E element);
    E peek();
    boolean isEmpty();
    int size();
    
}
