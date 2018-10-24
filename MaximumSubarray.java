import java.util.*;

public class MaximumSubarray {
	public static void main(String[] args){
		
		//input array size from the users
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter size of an array");
				
				int s=sc.nextInt();
					// create an array of numbers
				int[] ar = new int[s];
				System.out.println("Enter "+s+" elements");

				 	for(int i=0; i < s; i++)
					ar[i] = sc.nextInt();
				 	
		            System.out.println("The maximum subarray sum by bruteforce Algorithm =" + " " + max_sum(ar));
		            System.out.println("The maximum Subarray by DNC Algorithm is =" + " " +MAX_SUBARRAY(ar));
		            
		            	//Store the time taken by the Algorithms
		            		long startTime = System.nanoTime();
		            	// Running time of Brute Force Algorithm
		            		max_sum(ar);
		            	// display the time elapsed
		            		System.out.println("The running time of BruteForce Algorithm is " + (System.nanoTime() - startTime) + " nanoseconds");
		        	
		        	// prepare to measure the time elapsed again
		    		startTime = System.nanoTime();
		            // DNc for size
		    		MAX_SUBARRAY(ar);            // display the time elapsed
		    		System.out.println("The running time of Divide and Conquer Algorithm is " + (System.nanoTime() - startTime) + " nanoseconds.");
	
	}
	
	public static int max_sum(int[] ar){
		int s=ar.length;
		int Smax= Integer.MIN_VALUE; //Initialize the minimum value
		//Loop to add the maxSubarray elements
			for(int i=0; i < s; i++){
				
				int sum=0;
				
					for(int j=i; j < s; j++)
					{
						sum= sum+ar[j];
							if(sum>Smax)
								Smax=sum;
					}
					
			
						
			}
			return Smax; //Returns the maximum sum	
	}
	
	static int SumMax = Integer.MIN_VALUE;
		public static int MAX_SUBARRAY(int[] ar){
			return MAX_SUBARRAY(ar, 0, ar.length-1);
		}
	
	public static int MAX_SUBARRAY(int[] ar, int low, int high){
			if(low > high)
			{
					return Integer.MIN_VALUE;
			}
					// divide the array in two parts
				int mid= (low+high) / 2;
					// Adding the leftmax
				int left_max = MAX_SUBARRAY(ar, low, mid-1);
					//Adding the rightmax
				int right_max = MAX_SUBARRAY(ar, mid+1, high);
					// Select the maximum value
				SumMax= Math.max(SumMax, left_max);
				SumMax= Math.max(SumMax, right_max);
				
				//Across the mid
				int left_sum=0;
				int sum=0;
				for(int i=mid-1; i >= low; i--){
					sum=sum+ar[i];
					left_sum= Math.max(left_sum, sum);
				}
				
				int right_sum=0;
				sum=0;
				
				for(int i=mid+1; i <= high; i++){
					sum=sum+ar[i];
					right_sum= Math.max(right_sum, sum);
					
				}
				 
				SumMax = Math.max(SumMax, left_sum + ar[mid] + right_sum);
				 return SumMax;
						
	}

}

