/**
 *Karl Butler
 * 5/31/2020
 * Sorting efficiencies
 * This program tests the efficiency of different sorting algorithms
 */
package sortingefficiencies;
import javax.swing.JOptionPane;

/**
 *
 * @author Karl Butler
 */
public class SortingEfficiencies {

    /**
     * @param args the command line arguments
     */
    static int mergloop;
    static int mergcomparison;
    static int mergShift;
    public static void main(String[] args) {
        //the varables gX,x and serch are used to retrive the users inputs.

        String gX;
        int x,randNum,serch;
        
       gX = JOptionPane.showInputDialog("Enter the number of randome numbers that you want");
       x=Integer.parseInt(gX);

//this for loop puts the randome numbers in the array
       
       
       int [] numbers = randNums(x);
       
       insertionSort(numbers);
       int [] numbers2 = randNums(x);
       mergeSort(numbers2);
       int [] numbers3 = randNums(x);
       selectionSort2(numbers3);
    }
    
    public static int[] randNums (int x){
       int [] numbers = new int[x];
        for(int i =0; i <= x-1;i++){
           numbers[i]=(int) (Math.random()*1000);
       }
        return numbers;
    }
    public static void insertionSort (int [] arr){
       int loop = 0;
       int comparison = 0;
       int shiftCounter = 0;
       long startTimg = System.nanoTime();
        
        
        for (int i = 1; i < arr.length; i++)
        {
            loop++;
            int curNumber = arr[i];
            
            // Set index to be place to the left
            int curIndex = i-1;
            
            // We are still inbounds and the current number
            // is less than the current index
            while ( curIndex >= 0 && arr[curIndex] > curNumber)
            {
                loop++;
                shiftCounter++;
                comparison++;
                // Shift the value at curIndex to the right one place
                arr[curIndex+1] = arr[curIndex];
                curIndex--;
            }
            
            // Put this number in the proper location
            arr[curIndex + 1] = curNumber;
            shiftCounter++;
        }
        long endTime =System.nanoTime();
        long time= endTime-startTimg;
        
        System.out.println("preforming Insertion Sort");
        System.out.println("it took "+ time/1000000 + "ms");
        System.out.println("It took " + loop + " loops");
        System.out.println("there were " + comparison +" comparisons");
        System.out.println("there were "+ shiftCounter + " shifts");
        System.out.println("--------------------------");
    }
    
    public static void mergeSort(int[] arr) 
    {
        int[] temp = new int[arr.length];
        long start=System.nanoTime();
        
        mergeSortHelper(arr, 0, arr.length - 1, temp);
        long end = System.nanoTime();
        long time =end-start;
        
        System.out.println("preforming merg Sort");
        System.out.println("it took " + time/1000000 + "ms");
        System.out.println("there were " + mergloop +" loops");
        System.out.println("there were " + mergcomparison + " comparisons");
        System.out.println("there were " + mergShift + " shifts");
        System.out.println("--------------------------");
        
    }
    
    private static void mergeSortHelper(int[] arr, int from, int to, int[] temp)
    {
        // If the array length is greater than 1
        if(to - from >= 1)
        {
            mergcomparison++;
            int mid = (from + to) / 2;
            mergeSortHelper(arr, from, mid, temp);
            mergeSortHelper(arr, mid + 1, to, temp);
            merge(arr, from, mid, to, temp);
            
        }
    }
    
    public static void merge(int[] arr, int from, int mid, int to, int[] temp) 
    {
        int i = from;       
        int j = mid + 1;    
        int k = from;       
        
        while(i <= mid && j <= to)
        {
            mergcomparison++;
            mergloop++;
           
            if(arr[i] < arr[j])
            {
                temp[k] = arr[i];
                i++;
                mergcomparison++;
            }
            else
            {
                temp[k] = arr[j];
                j++;
                mergcomparison=mergcomparison+2;
            }
            k++;
        }
        
        // We may have missed elements from either list
        while(i <= mid)
        {
            mergloop++;
            temp[k] = arr[i];
            i++;
            k++;
            mergcomparison++;
            mergShift++;
        }
        
        while(j <= to)
        {
            mergloop++;
            temp[k] = arr[j];
            j++;
            k++;
            mergcomparison++;
            mergShift++;
        }
        
        // Copy over from temp to elements
        for(k = from; k <= to; k++)
        {
            mergloop++;
            arr[k] = temp[k];
            mergcomparison++;
            mergShift++;
        }

    }

    public static void selectionSort2(int[] arr)
    {
        
        int currentMinIndex,loop,swap,comparison;
        comparison=0;
        swap=0;
        loop=0;
        long start = System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++)
        {
            loop++;
            comparison++;

            currentMinIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                loop++;
                comparison++;
                if(arr[j] < arr[currentMinIndex])
                {
                    comparison++;
                    currentMinIndex = j;
                }
            }
            
            // swap numbers if needed
            if (i != currentMinIndex)
            {
                swap++;
                comparison++;
                int temp = arr[currentMinIndex];
                arr[currentMinIndex] = arr[i];
                arr[i] = temp;
            }
        }
    long end=System.nanoTime();
    long time=end-start;

        System.out.println("preforming selection sort");
        System.out.println("it took "+ time/1000000 + "ms");
        System.out.println("there were "+ loop+ "loops");
        System.out.println("there were "+ swap +" swaps");
        System.out.println("there were "+ comparison + "comparisons");
    }
    
}