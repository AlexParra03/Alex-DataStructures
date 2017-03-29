
package alex.datastructures;

/**
 * @author David A. Parra
 */
public class AlexArrayList<E> implements List<E> {


    private E[] data;
    private int capacity;
    private int size;
    private static final int INITIAL_CAPACITY = 5;
    private static final double GROWTH_RATE = 1.5;

    public AlexArrayList(){
            size = 0;
            data = (E[]) new Object[INITIAL_CAPACITY];
            capacity = data.length;
    }

    @SuppressWarnings("unchecked")
    private void manageSlots(int slotsToCheck ){
            if( this.size + slotsToCheck >= (this.capacity-1) ){
                    //Grow by half, x1.5
                    int newCapacity = (int) (GROWTH_RATE * this.capacity);

                    E[] newData = (E[]) new Object[newCapacity];
                    for(int i=0; i<this.capacity; i++){
                            newData[i] = this.data[i];
                    }
                    this.data = newData;
                    this.capacity = newCapacity;
                    manageSlots(slotsToCheck);
            }

    }
    
    private void shiftArray(int start, int emptySlots){
        manageSlots(emptySlots);
        if(start >= 0 && start < this.capacity){
            
            int newCapacity = this.size - start + emptySlots;
            
            E[] endChunk = (E[]) new Object[newCapacity];
            for(int i=0; i<newCapacity; i++){
                if(i< emptySlots-1){
                        endChunk[i] = null ;
                } else {
                        endChunk[i] = this.data[start+i-emptySlots];
                }
            }

            for(int i=0; i<newCapacity; i++){
                    this.data[start+i] = endChunk[i]; 
            }

        }
    }
    
    @Override
    public boolean contains(E element) {
        for(E e : this.data){
            if(e.equals(element)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public E get(int index) {
        if(index >= 0 && index < this.size){
            return this.data[index];
        }
        return null;
    }

    @Override
    public boolean remove(int index) {
        if(index >= 0 && index < size){
            for(int i=index; i<size; i++ ){
                    this.data[i] = this.data[i+1];
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E element) {
        for(int i=0; i<size; i++){
            if(this.data[i].equals(element)){
                if(remove(i)){
                    return true;
                }
                
            }
        }
        return false;
    }

    @Override
    public boolean add(E element) {
        manageSlots(1);
        this.data[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if( index >= 0 && index < size){
            shiftArray(index, 1);
            this.data[index] = element;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean set(int index, E element) {
        if(index >= 0 && index < size){
            this.data[index] = element;
            return true;
        }
        return false;
    }
    
}
