

public class StackArray<E> {
    
    private static final int START_SIZE = 3;
    private static final int GROWTH_RATE = 2;
    private E[] data;
    private int index;
    
    public StackArray(){
        data = (E[]) new Object[START_SIZE];
        this.index = 0;
    }
    
    private void grow(){
        E[] newArray = (E[]) new Object[ this.data.length * GROWTH_RATE ];
        
        for(int i=0; i<this.data.length; i++){
            newArray[i] = this.data[i];
        }
        this.data = newArray;
    }
    
    public E push(E element){
        if(this.index == this.data.length){
            grow();
        }
        this.data[index] = element;
        index++;
        return this.data[index-1];
    }
    
    public E pop(){
        if(index > 0){
            E item = this.data[this.index -1];
            index--;
            return item;
        }
        return null;
    }
    
    public boolean isEmpty(){
        return (this.index == 0);
    }
    
    public int size(){
        return this.index;
    }
    
    public E peek(){
        if(this.index > 0){
            return this.data[index-1];
        }
        return null;
    }
    
    
    
}
