
package lab7;

import java.io.PrintWriter;


public class SortingAlgorithms {
    
   
    /*
    @param array: array to be printed
     print the current array to the screen
    */
    public void printArray(int[] array){
        System.out.println();
        for( Integer n : array){
            System.out.print(n + " ");
        }
    }
    
    /*
    @param array to be performed operations on
    @param a, b: index to be swaped
     swaps element in position a to element in position b
    */
    private void swap(int[] array, int a, int b){
        if(a >= 0 && a < array.length && b > 0 && b < array.length && a != b){
            int holder = array[a];
            array[a] = array[b];
            array[b] = holder;
        }
    }
    
    /*
    @param array to be sorted
     sorts the array using selection sort algorithm
    */
    public void selectionSort(int[] array){
        
        int comparisons = 0;
        int exchanges = 0;
        
        System.out.print("  [SELECTION SORT]  ");
        for(int i=0; i < array.length-1; i++){
            int index = i;
            for(int j=i+1; j<array.length; j++){
                comparisons++;
                if(array[j] < array[index]){
                    index = j;
                }
            }
            
            exchanges++;

            swap(array, i, index);
            printArray(array);  
        }
        System.out.println("\nComparisons: " + comparisons + " Exchanges: " + exchanges);
    }
    
    /*
    @param array to be sorted
     sorts the array using selection sort algorithm
    */
    public void insertionSort(int[] array){
        int comparisons = 0;
        int exchanges = 0;
        System.out.print(" [INSERTION SORT]  ");

        for(int i=1; i<array.length; i++ ){
            int sorted=i;
            
            comparisons++;
            while( (sorted >= 1) && (array[sorted-1] > array[sorted])){
                exchanges++;
                swap(array, sorted-1, sorted);
                sorted--;
            }
            printArray(array);
        }

        System.out.println("\n Comparisons: " + comparisons + " Exchanges: " + exchanges);

    }
           
    /*
    @param array to be sorted
     sorts the array using selection sort algorithm
    */
    public void bubbleSort(int[] array){
        int comparisons = 0;
        int exchanges = 0;
        System.out.print("  [BUBBLE SORT]  ");

        for(int i=array.length; i>= 0; i--){
            for(int j=0; j<i-1; j++){
                ++comparisons;
                if(array[j] > array[j+1]){
                    swap(array, j, j+1);
                    ++exchanges;
                }
            }
            printArray(array);
        }
        System.out.println("\n Comparisons: " + comparisons + " Exchanges: " + exchanges);

    }
    
    // merge to arrays into one
    public int[] merge(int[] arrayOne, int[] arrayTwo){
        int[] merged = new int[arrayOne.length + arrayTwo.length]; 
        int i = 0; int j=0; int k=0;
        
        while(i < arrayOne.length && j < arrayTwo.length){
            if(arrayOne[i] > arrayTwo[j]){
                merged[k++] = arrayTwo[j++];
            }else{
                merged[k++] = arrayOne[i++];
            }
        }
        
        while( i < arrayOne.length){
            merged[k++] = arrayOne[i++];
        }
        while( j < arrayTwo.length){
            merged[k++] = arrayTwo[j++];
        }
        
        return merged;
    }
    
    // Perform merge-sort algorithm
    private int[] mergeSortRecursive(int[] array, int low, int high){
        
        if( low >= high){
            int[] output = new int[1];
            output[0] = array[high];
            return output;
        }
        
        int mid = (low+ high)/2;
        int[] a = mergeSortRecursive(array, low, mid);
        int[] b = mergeSortRecursive(array, mid+1, high);
        return merge(a, b);
    }
    
    public int[] mergeSort(int[] array){
        return mergeSortRecursive(array, 0, array.length-1);
    }
    
    public void createFile(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename + ".txt", "UTF-8");
            int range = 10000;
            for(int i=1; i<= 10000; i++){
                int randomNumber = (int) (Math.random()*range);
                writer.print(""+randomNumber + " ");
            }
            
            writer.close();
        } catch (Exception e) {
           
        }
    }
}
