
/*************************************************************************
 
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Heli Rawal.
 *  Collaborators: None
 *  References: None
 
 *  Project: 4
 *  Problem: Testing the runnning time .
 *  Description: This program measures the running time of Selection Sort and Quicksort with choosing different pivot.
 
 *  Input: Elements to be sorted.
 *  Output: The running time taken by Selection Sort and Quicksort with choosing different pivot to sort the input elements.
.
 
 *  Visible data fields:
 *  none.
 
 *  Visible methods:
 * public void sort( int[] arr )
 * public void quicksort( int[] arr )
 * public void Rsort( int[] arr )
 
 
 *   Remarks
 *   -------
	4) By Observing the table below it can be said that the running time for the selection sort is quadratic, no matter whether the input is given in increasing 
           order, decreasing order or randomly. In quicksort it is a different case, it can be observed in the following table that when the pivot is first element 
           it takes quadratic time i.e. O(n2) to sort the elements, while if the pivot is taken as a median of 3 numbers there is improvement in running time i.e it
           takes less time compared to the other one to sort the elements in majority of cases i.e. O(nlogn).             
 
 *   Chart of running times observed in nanoseconds:
 *
 * Construction Time:
 *
 *  The runtime is measured for 10000 elements:  
 *
 *  Version | Increasing Order | Decreasing Order | Random
 *  ---------------------------------------------------------------
 *  1       |   121936538      |    122075399     |  230769463
 *  ---------------------------------------------------------------
 *  2a      |   78078918       |    78104878      |  54046972
 *  ---------------------------------------------------------------
 *  2b      |   21385987        |    20693131      |  1042365
 *  ---------------------------------------------------------------
 
 
 


 
*************************************************************************/



public class Sort {

	
// 1) Selection sort
    public void sort(int arr[])
    {
    	int n=arr.length;       
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
        	
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    
    
    // 2a)Implementing quicksort, choosing first element as a pivot
    
    public void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }
    
       public void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            // select pivot element (left-most)  
            int pivot = arr[low];
            
            // partition and shuffle around pivot 
            int i = low;
            int j = high;
              while (i < j)
               {
                // move right to avoid pivot element 
                i= i + 1;
                
                // scan right: find elements greater than pivot 
                while (i <= high && arr[i] < pivot) 
                {
                    i= i+1;
                }
                
                
                // scan left: find elements smaller than pivot
                while (j >= low && arr[j] > pivot) 
                {
                    j = j-1;
                }
                
                
                if (i <= high && i < j) 
                {
                    // swap around pivot  
                    swap(arr, i, j);
                }
               }
              
            // put pivot in correct place
            swap(arr, low, j);
            
            // sort partitions 
           quicksort(arr, low, j - 1);
            quicksort(arr, j + 1, high);
        }
    }
    
    public void swap(int[] arr, int i, int j)
    {
        if (i >= 0 && j >= 0 && i < arr.length && j < arr.length)
        {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
    
    
    //2b) Implementing quicksort using Randomization

    public void Rsort(int[] arr){        

        quickSort(arr, 0, arr.length-1);

    }

     

   

    // It takes left and the right end of the array as two cursors

    public static void quickSort(int[] arr, int low,int high){

         

        // If both cursor scanned the complete array, quicksort exits

        if(low >= high)

            return;

         

        // Pivot using median of 3 approach

        int pivot = getMedian(arr, low, high);

        int partition = partition(arr, low, high, pivot);

         

        // Recursively, calls the quicksort with the different left and right parameters of the sub-array

        quickSort(arr, 0, partition-1);

        quickSort(arr, partition+1, high);

    }

     

    // This method is used to partition the given array and returns the integer which points to the sorted pivot index

    public static int partition(int[] arr, int low,int high,int pivot){

    	
        int leftCursor = low-1;

        int rightCursor = high;

        while(leftCursor < rightCursor){

        while(arr[++leftCursor] < pivot);

        while(rightCursor > 0 && arr[--rightCursor] > pivot);

            if(leftCursor >= rightCursor){
                break;

            }else{

                Sswap(arr,leftCursor, rightCursor);

            }

        }

        Sswap(arr,leftCursor, high);

        return leftCursor;

    }

     
  //To get the median of 3 approach
    public static int getMedian(int[] arr, int low,int high)
    {

    	
        int center = (low+high)/2;

         

        if(arr[low] > arr[center])

        	Sswap(arr,low,center);

         

        if(arr[low] > arr[high])

        	Sswap(arr,low, high);

         

        if(arr[center] > arr[high])

            Sswap(arr, center, high);
        

        return arr[high];

    }
     

    // This method is used to swap the values between the two given index

    public static void Sswap(int[] arr, int low,int high)
    {

        int temp = arr[low];
        arr[low] = arr[high];

        arr[high] = temp;

    }
   
     

 
    // Prints the array to crosscheck
  /*  void printArray(int arr[])
    {
    	int n=arr.length;
         for (int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
            System.out.println();
    }*/
    
 
 
    // Code to test above methods
    public static void main(String args[])
    {
    
    	Sort s1 = new Sort(); 
    	Sort s2 = new Sort();
    	Sort s3 = new Sort();
    	Sort q1 = new Sort();
    	Sort q2 = new Sort();
    	Sort q3 = new Sort();
    	Sort r1 = new Sort();
    	Sort r2 = new Sort();
    	Sort r3 = new Sort();
    	
    	
    	
   System.out.println("Displaying the running time of Sorting");

   int[] arr=new int[10000];

   int item=0;

   //loop for selection sort in ascending
   
   for(int i = 1; i<arr.length; i++)
    {
	    arr[i]= i;
	}

   s1.sort(arr);
   
   long startTime = System.nanoTime();

          //Measure time for Selection sort in Ascending order.
             startTime = System.nanoTime();
             s1.sort(arr);
  	         System.out.println("The time taken by  Selection sort in Ascending order " + (System.nanoTime() - startTime) + " nanoseconds.");
   
   
  //loop for selection sort in descending

   for(int i=arr.length-1; i>=0; i--)
   {

	     arr[i]=i;
	
   }
	s2.sort(arr);
	
	    //Measure time for Selection sort in Descending order.
           startTime = System.nanoTime();
           s2.sort(arr);
           System.out.println("The time taken by  Selection sort in Descending order " + (System.nanoTime() - startTime) + " nanoseconds.");

	

 //loop for selection sort in random numbers
	
   for(int i=1; i<arr.length; i++)
    {
	   
	      item = (int)(Math.random()*100);
 	      arr[i]=item;
 	      
	}
    s3.sort(arr);
    
        //Measure time for Selection sort for random numbers.
  		  startTime = System.nanoTime();
  		  s3.sort(arr);
  		  System.out.println("The time taken by Selection sort for random numbers " + (System.nanoTime() - startTime) + " nanoseconds.");


 
 //loop for quicksort 1st element as pivot in ascending
    
   for(int i = 1; i<arr.length; i++)
    {
	   	   arr[i]=i;

	}
	q1.quicksort(arr);
	
	   //Measure time for Quicksort in Ascending order.
          startTime = System.nanoTime();
	      q1.quicksort(arr);
	      System.out.println("The time taken by Quicksort in Ascending order is " + (System.nanoTime() - startTime) + " nanoseconds.");

	
	

 //loop for quicksort 1st element as pivot in descending
	
   for(int i=arr.length-1; i>1; i--)
    {
	       arr[i]=i;
	
    }
   q2.quicksort(arr);	
  
       //Measure time for Quicksort in Descending order.
 		  startTime = System.nanoTime();
 		  q2.quicksort(arr);
 		  System.out.println("The time taken by Quicksort in Descending order is " + (System.nanoTime() - startTime) + " nanoseconds.");

 //loop for quicksort 1st element as pivot in random numbers
   
   for(int i=1; i<arr.length; i++)
   {
	   
	        item = (int)(Math.random()*100);
        	arr[i]=item;
	
	}
   q3.quicksort(arr);
   
     //Measure time for Quicksort for random numbers.
        startTime = System.nanoTime();
	    q3.quicksort(arr);
	    System.out.println("The time taken by Quicksort for random numbers is " + (System.nanoTime() - startTime) + " nanoseconds.");
  
   
 //loop for quicksort median of random number as pivot in ascending
   
   for(int i = 1; i<arr.length; i++)
	   
   {
	   
	         arr[i]=i;
	
   }
	r1.Rsort(arr);
	
	   //Measure time for Randomization in Ascending order.
          startTime = System.nanoTime();
          r1.Rsort(arr);
	      System.out.println("The time taken by Randomization in Ascending order is " + (System.nanoTime() - startTime) + " nanoseconds.");

	
	
 //loop for quicksort median of random number as pivot in descending
	      
   for(int i=arr.length-1; i>1; i--)
    {
		
		      arr[i]=i;
		
	}
   r2.Rsort(arr);
   
       //Measure time for Randomization in Descending order.
	      startTime = System.nanoTime();
	      r2.Rsort(arr);
	      System.out.println("The time taken by  Randomization in Descending order. is " + (System.nanoTime() - startTime) + " nanoseconds.");

   
   
 //loop for quicksort median of random number as pivot for random generated numbers

   for(int i=1; i<arr.length; i++)
   {
	   
		       item = (int)(Math.random()*100);
		       arr[i]=item;
		
		
	}
   r3.Rsort(arr);
   
        //Measure time for Randomization for random numbers.
	       startTime = System.nanoTime();
	       r3.Rsort(arr);
	       System.out.println("The time taken by Randomization for random numbers " + (System.nanoTime() - startTime) + " nanoseconds.");
   }
    

     }


