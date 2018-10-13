
/*************************************************************************
 
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Heli Rawal
 *  Collaborators: None
 *  References: None
 
 *  Project: 1
 *  Problem: Testing the runnning time .
 *  Description: This program measures the running time of adding various numbers of entries to the hash table and the AVL tree
		 and the running time of searching for key that is not in the hash table and the AVL tree.
 
 *  Input: Hash table and the AVL tree size.
 *  Output: The running time taken by hash table and the AVL tree to add number of entries and search a number not in the AVL tree and Hash table.
 
 *  Visible data fields:
 *  none.
 
 *  Visible methods:
 * public void insert( AnyType x )
 * public boolean contains( AnyType x )
 * public V put(K key, V value)
 * public boolean containsKey(K key)
 
 *   Remarks
 *   -------
	1) Separate-chaining hash table: Separate chaining hash table resolves the problem when more than one value is to be inserted in the same index that is the key.
           ----------------------------
					
                                        
				  -->Insert elements into the table:
                                     -------------------------------
                                   Separate Chaining hash tables take O(n) running time to insert n elements considering the time taken by the hash operation
                                   and list operation;
                                         
                                          h(key)= key % table_size

					So, considering below given example it can be assumed that the value is mapped 
					to the index obtained by this function rather than traversing the whole table that
					is the reason it takes O(1) to compute. 
				
		
	        -> For example: Value: 1231, 1232, 1233, 1333.
				table_size: 10

				--> h(1231)= 1231 % 10.
					   = 1
			
					1231 will be inserted in index 1.

				
				--> h(1232)= 1232 % 10.
  					   = 2
			 
					1232 will be inserted in index 2.
			
				--> h(1233)= 1233 % 10.
					   = 3
			
					1233 will be inserted in index 3.


				--> h(1333)= 1333 % 10.
					= 3
			
				        1333 will be inserted in index 3.
		
                                --> T(n) = O(n).


		                --> To search an element not in the hash table:
		                    -------------------------------------------

				       Now when it comes to search an element from the table, it is still O(1) to arrive at the correct place in the array and it 
				       is O(1) if an element to be searched is found at the first position, but O(n) potentially a linear search down a (hopefully short) 
				       list if there are multiple values at the index.

		-> Considering the above example, if 1433 is an element to search from the table then it will compute in O(1) and reach to the index 3 but there are 
                   list of elements at that index so, the whole list of elements will be checked to find an element which takes linear time to compute; O(n).
		   Also, if 1233 is to be searched then it will take O(1) to compute as it is the first element at index 3.


				 --> T(n) = O(n).



	  2) AVL tree: AVL tree is a self-balancing tree.
             ----------

				--> Insert elements into the table:
                            ------------------------------
               				 AVL tree takes o(nlogn) to compute for inserting values in a tree because the insertion takes place through the height and 
					 it is the Balanced Binary Search Tree only, but it balances itself if any manipulation performed on the tree.


				--> To search an element not in the hash table:
		                     -------------------------------------------

					 The AVL tree takes O(logn) times to search a number not in 
                                         a tree because either it will search for a number in left subtree
		                         or a right subtree depending upon the condition rather than searching 
                                         height and it is the Balanced Binary Search Tree only, but it balances
                                         itself if any manipulation performed on the tree so, it always have at most 
				         1 level difference between child subtrees for all nodes.



		-> For example: Considering tree with 16 nodes with sorted numbers it is to be 
		   		divided four times depending upon the conditions to search for
				an element.

			 	 16 * (1/2)^4 = 1 
		          	 n  * (1/2)^k = 1 (n=16, k=4)
		          	 2^k n/2^k = 2^k
			  	 n=2^k
		          	 logn=k
	
                                 T(n) = O(logn).


                
	
	3) After Observation:
           ------------------

		The results match with the conjecture observed above because Avl tree increases O(nlogn) times
	        for insertion and O(logn) for search operations and Hashtable increases O(n) times for insertion and O(n) 
		for search operation.
 
 *   Chart of running times observed in nanoseconds:
 *
 * Construction Time:
 *
 *  Size     |    Hashtable          |   Tree
 *  ---------------------------------------------------------------
 *  100      |   14489               | 106708
 *  ---------------------------------------------------------------
 *  1000     |   15697               | 133426
 *  ---------------------------------------------------------------
 *  10000    |   21130               | 295835
 *  ---------------------------------------------------------------
 *  100000   |   53129               | 350172  
 *  ---------------------------------------------------------------
 
 * Search Time:
 *
 *  Size     |    Hashtable          |   Tree
 *  ---------------------------------------------------------------
 *  100      |   15093               | 16772
 *  ---------------------------------------------------------------
 *  1000     |   18715               | 23546
 *  ---------------------------------------------------------------
 *  10000    |   22942               | 24753
 *  ---------------------------------------------------------------
 *  100000   |   30187               | 42866
 *  ---------------------------------------------------------------
 


 
*************************************************************************/


import java.util.*;

// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public AvlTree( )
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

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
  
      public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    public AvlNode<AnyType> balance( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    public int checkBalance( AvlNode<AnyType> t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    public AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
    {
        if( t == null )
            return new AvlNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }

    public boolean contains( AnyType x, AvlNode<AnyType> t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
  public AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
    {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    public AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
    {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    public AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
  public AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode<AnyType>
    {
            // Constructors
        AvlNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

      /** The tree root. */
    private AvlNode<AnyType> root;
	private int x;
	


        // Test program
    @SuppressWarnings("unchecked")
	public static void main( String [ ] args )
    {
        //Instance for AVL tree
    	AvlTree<Integer> t = new AvlTree<Integer>();
    	//Instance for hashtable
    	Hashtable<Integer, Integer> h=new Hashtable<Integer, Integer>(1000, (float) 0.75);
       
        
       
        System.out.println( "The running time taken by AVL tree and Hashtable is : " );
        
       Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        
                // To insert values in an AVL tree.
                for(int i=1;i<=x;i++)
                {
                	
                	t.insert(i);
                }
                
                
                // Insert values in hashtable.
               for(int i=1; i<=x; i++)
               {
            	   
                h.put(i, i);
                
               }               
                
                
               long startTime = System.nanoTime();
               
               //Measure time for insertion in an AVL tree.
                startTime = System.nanoTime();
    			
    			t.insert(x);
    			System.out.println("The time taken by  AVL Tree to insert is " + (System.nanoTime() - startTime) + " nanoseconds.");
    			
    			//Measure time for finding key in an AVL tree.
    			startTime = System.nanoTime();
    			
    			t.contains(x+1);
    			System.out.println("The time taken by  AVL Tree to search for key is " + (System.nanoTime() - startTime) + " nanoseconds.");
    			
    			//Measure time for insertion in hashtable.
    			 startTime = System.nanoTime();
     			
     			h.put(x, x);
     			System.out.println("The time taken by  Hashtable to insert is " + (System.nanoTime() - startTime) + " nanoseconds.");
            
     			//Measure time for finding key in Hashtable.
     			 startTime = System.nanoTime();
      			
      			h.containsKey(x+1);
      			System.out.println("The time taken by  Hashtable to search for key is " + (System.nanoTime() - startTime) + " nanoseconds.");
             
                }
    	
	

  



    }


