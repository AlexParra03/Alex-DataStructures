/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;


public class ArrayQueue<E> {
    
    private E[] data;
    private int start;
    private int end;
    private int size;
    
    private static final int INITIAL_CAPACITY = 5;
    private static final int GROWTH_RATE = 2;
    
    /*
    Copy elements into new array in order
    */
    private void grow(){
        E[] holder = (E[]) new Object[this.data.length * 2];
        for(int i=start; i<this.data.length; i++){
            holder[i - start] = this.data[i];
        }
        for(int i=0; i<end; i++){
            holder[this.data.length +i] = this.data[i];
        }
        this.start = 0;
        this.end = this.data.length;
        
        this.data = holder;
    }
    
    public ArrayQueue(){
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.start = 0;
        this.end = 0;
        this.size = 0;
    }
    
    /*
    @param element: add item to the queue
    */
    public boolean offer(E element){
        // If queue is full, grow
        if( (start == end) && (size >= this.data.length) ){
            grow();         
        }
        this.data[end] = element;
        end =  ( (end+1) % (this.data.length));
        size++;
        return true;
    }
    
    public E peek(){
        return data[start];
    }
    
    // Return first item and remove it from the list
    public E poll(){
        //If queue is empty, return null
        if((start == end) && (size == 0) ){
            return null;
        }
        E object = this.data[start];
        start = (start+1 % (this.data.length));
        size--;
        return object;
    }
    
    //Return the current size
    public int size(){
        return this.size;
    }
    
    //Return if the Queue is empty
    public boolean isEmpty(){
        return (size == 0);
    }
    
}
