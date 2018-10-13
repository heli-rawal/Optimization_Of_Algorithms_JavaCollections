

/*************************************************************************
 
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Heli Rawal and Karishma Rao.
 *  Collaborators: None
 *  References: None
 
 *  Project: 2
 *  Problem: Testing the running time .
 *  Description: This program measures the running time taken by Skewed Binary tree and Binary Search tree to search a number not in the tree.
 
 *  Input: Tree size
 *  Output: The running time taken by Skewed Binary tree and Binary Search tree to search a number not in the tree.
 
 *  Visible data fields:
 *  none.
 
 *  Visible methods:
 * public void insert( AnyType x )
 * public boolean contains( AnyType x )
 
 
 *   Remarks
 *   -------
	1) Skewed BST:
       ----------

		        The skewed BST takes O(n) times to search a number not in a tree
                because it traverses every node in a tree to look for a number.

	        -> For example: There is a tree with 16 nodes, then every node will be checked
		                    to search for a number. So,

			        T(n) = O(n)

    2) Balanced BST:
       -------------

                The Balanced BST takes O(logn) times to search a number not in 
                a tree because either it will search for a number in left subtree
		        or a right subtree depending upon the condition rather than searching 
                every node in a tree.

		-> For example: Considering tree with 16 nodes with sorted numbers it is to be 
		   		        divided four times depending upon the conditions.

			 	     16 * (1/2)^4 = 1 
		          	 n  * (1/2)^k = 1 (n=16, k=4)
		          	 2^k n/2^k = 2^k
			  	     n=2^k
		          	 logn=k
	
                     T(n) = O(logn)
                    
	
	3) After Observation:
       ------------------

		    The results match with the conjecture observed above because Skewed BST increases O(n) times
	        and Balanced BST increases O(logn) times.
 
 *   Chart of running times observed in nanoseconds:
 *
 *  Size     |    Skewed BST         |   Balanced BST
 *  ---------------------------------------------------------------
 *  10       |   31394               |    11471
 *  ---------------------------------------------------------------
 *  100      |   65204               |    17508
 *  ---------------------------------------------------------------
 *  1000     |   407524              |    39243
 *  ---------------------------------------------------------------
 *  10000    |   1057139             |    89957
 *  ---------------------------------------------------------------
 
 


 
*************************************************************************/


import java.util.Random;
import java.util.Scanner;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }
    
    public boolean isEmpty( )
    {
        return root == null;
    }
    
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    public BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

        private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

   private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    public static class BinaryNode<AnyType>
    {
          BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    
    private BinaryNode<AnyType> root;
		private static Scanner input;


        // Test program
    @SuppressWarnings("unchecked")
	public static void main( String [ ] args ) 
    {
        BinarySearchTree<Integer> Ts = new BinarySearchTree<Integer>( );
        BinarySearchTree<Integer> Tr = new BinarySearchTree<Integer>( );
        
        System.out.println( "The running time taken by tree S and tree R" );
        input = new Scanner(System.in);
        int x = input.nextInt();
        //To insert skewed BST
        for(int i=1;i<=x;i++)
        {
        	Ts.insert(i);
        	
        }
        //To insert Balanced BST
        for(int i=1;i<=x;i++)
        {
        	Tr.insert((int) ((double)1000*i*Math.random()));
        	
        }
        long startTime = System.nanoTime();
			// Time taken by Skewed BST to insert 
        
			Ts.insert(x);
			
			// display the time elapsed
			
			System.out.println("The time taken by Skewed Binary to insert item is " + (System.nanoTime() - startTime) + " nanoseconds.");
			
			// prepare to measure the time elapsed again

		startTime = System.nanoTime();
		
			// Time taken by Balanced BST to insert 
		
			Tr.insert(x);            // display the time elapsed
			System.out.println("The time taken by  Binary Search Tree to insert item is " + (System.nanoTime() - startTime) + " nanoseconds.");
			
			// Time taken by Skewed BST to search for a number not in the tree
			startTime = System.nanoTime();
			
			Ts.contains(x+1);
			System.out.println("The time taken by  Skewed Binary Search Tree to search for number not in the tree is " + (System.nanoTime() - startTime) + " nanoseconds.");
			
			// Time taken by Balanced BST to search for a number not in the tree
			startTime = System.nanoTime();
			
			Tr.contains(x+1);
			System.out.println("The time taken by  Binary Search Tree to search for random number is " + (System.nanoTime() - startTime) + " nanoseconds.");
       
        }
}