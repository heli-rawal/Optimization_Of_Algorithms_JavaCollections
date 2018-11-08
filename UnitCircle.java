
/*************************************************************************
 
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Heli Rawal
 *  Collaborators: None
 *  References: None
 
 *  Project: 3
 *  Problem: Testing the runnning time .
 *  Description: This program measures the running time of QuickSort and BucketSort Algorithm for n points of a unit circle
                 with radius 1.
 
 *  Input: n points p=(x,y) of the unit circle with radius 1.
 *  Output: The running time taken by BucketSort and Quicksort to sort the n input points.
.
 
 *  Visible data fields:
 *  none.
 
 *  Visible methods:
 * public void quickSort(double[] array, int low, int high)
 * public void BucketSort(double[] array, double maxValue)

 
 *   Remarks
 *   -------
	4) By Observing the table below it can be said that the running time for the QuickSort is O(nlogn) and BucketSort is O(n).
           Also, it can be observed that for the larger inputs QuickSort runs faster than BucketSort that is because in BucketSort
           major portion of processing time is wasted on creating buckets, sorting data in them, and moving the sorted elements from
           the buckets to the resultant array. Thus, although Bucket Sort requires fewer comparisons than Quick Sort, due to different
           overhead expenses it consumes much more time than expected. Also, when it comes for smaller inputs, Bucket Sort may be a better
           choice as it has fewer comparisions to make than QuickSort. 
                    
 
 *   Chart of running times observed in nanoseconds:
 *
 * Construction Time: 
 *
 *            |    100  |    1000  |  10000   | 100000
 *  ---------------------------------------------------------------
 *  QuickSort |  583815 | 4787644  |  9609702 |  57033339
 *  ---------------------------------------------------------------
 *  BucketSort|  48763  | 2291172  | 10035819 |  223771099
 *  ---------------------------------------------------------------
 
 
 
 


 
*************************************************************************/


import java.util.Scanner;

public class UnitCircle {

    public void quickSort(double[] array, int low, int high) {
        //check for null array
        if (array == null || array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        //Choose pivot the middle element of an array
        int middle = low + (high - low) / 2;
        Double pivot = array[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            //Loop until all values on left side array are lower than pivot
            while (array[i] < pivot) {
                i++;
            }
            //Loop until all values on left side array are greater than pivot
            while (array[j] > pivot) {
                j--;
            }
            //Now compare values from both side of lists to see if they need swapping
            //After swapping move the iterator on both lists
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        //Do same operation as above recursively to sort two sub arrays
        if (low < j) {
            quickSort(array, low, j);
        }
        if (high > i) {
            quickSort(array, i, high);
        }
    }

    public static void swap(double[] array, int x, int y) {
        Double temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    
    
    //BucketSort Implementation for n input points
    public void BucketSort(double[] array, double maxValue) {

        double n = (int) array.length;
        double[] Bucket = new double[(int)n];
        
    //Creating an empty bucket
        for (int i = 1; i < n; i++) {
            Bucket[(int) array
                   [i]]++;
        }
        
        //Storing values into the bucket
        for (int i = 1; i < n; i++) {
            if (array[i] < 1) {
                double distance = Math.pow(array[i], 2);
                int abs = (int) Math.abs(distance * maxValue);
                Bucket[abs] = array[i];
            } else if (array[i] == 1) {
                Bucket[(int) (maxValue - 1)] = array[i];
            }
        }
       //Sorting the bucket with multiple values
        for (int i = 0; i <= n - 1; i++) {
            Bucket = insertionSort(Bucket);
            
        }
        
    }
    

    private double[] insertionSort(double[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            double key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

        }
        return arr;

    }

    private static int maxVal(double[] array) {
        int maxValue = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = (int) (array[i] * 10);
            }
        }
        return maxValue;
    }
  
     // Main method to test the code
    public static void main(String[] args) {

        System.out.println("Enter number of n points:");
        Scanner input = new Scanner(System.in);
        int user = input.nextInt();
        double[] array = new double[user];

       UnitCircle uc = new UnitCircle();
        //Start of Generating and printing numbers of array
        for (int i = 0; i < user; i++) {
            double t, r, u, x, y, distance;

            t = (2 * Math.PI * Math.random());
            u = Math.random() + Math.random();
            if (u > 1) {
                r = 2 - u;
            } else {
                r = u;
            }
            x = r * Math.cos(t);
            y = r * Math.sin(t);
            distance = Math.sqrt(Math.pow((x), 2) + Math.pow((y), 2));
            array[i] = distance;
        }
  
        //Testing runtime of QuickSort
        long startTime = System.nanoTime();
        uc.quickSort(array, 0, array.length - 1);
        System.out.println("The time taken by  QuickSort is: " + (System.nanoTime() - startTime) + " nanoseconds.");

        //Bucket Sort
        //Start of Generating and printing numbers of array
        for (int i = 0; i < user; i++) {
            double t, r, u, x, y, distance;

            t = (2 * Math.PI * Math.random());
            u = Math.random() + Math.random();
            if (u > 1) {
                r = 2 - u;
            } else {
                r = u;
            }

            x = r * Math.cos(t);
            y = r * Math.sin(t);
            distance = Math.sqrt(Math.pow((x), 2) + Math.pow((y), 2));
            array[i] = distance;
        }
        int maxValue = maxVal(array);
        
        //Testing runtime of BucketSort
        startTime = System.nanoTime();
        uc.BucketSort(array, 1);
        System.out.println("The time taken by  BucketSort is: " + (System.nanoTime() - startTime) + " nanoseconds.");

    }
}

