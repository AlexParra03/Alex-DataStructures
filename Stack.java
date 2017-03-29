
package alex.datastructures;

/**
 * @author David A. Parra
 */
public interface Stack<E> {
    boolean push(E element);
    E pop();
    E peek();
    boolean isEmpty();
    int size();
}