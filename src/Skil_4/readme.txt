/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name:    Baldur Már Pétursson
Login:   baldurp12
Hópur:	 3

Partner name:     Þorgerður Edda Eiríksdóttir
Partner login:    thorgerdur14
Hópur:		  1


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/

	We decided to use the ST container from the algs4 library.
	With the ST storing its <Key> as an Integer(converted from String)
	and the <Value> as a string.
	ST is an ordered KeyMap structure which uses java.util.TreeMap
	to store the <Key> and <Value>
	Both of us were familiar with its functions and knew that the time
	complexity for put() and contains() was log(N), which was our main
	concern.
		


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/

	We decided to use the ST container from the algs4 library.
	With ST storing its data in a TreeMap we knew that we could 
	have the <Key> values stored as String and still have a log(N)
	lookup( contains() ) and insert( put() ).

	Since we needed to store an array or set of Integers as our <Value>
	we decided to use the Bag<Integer> to store these values.
	Even though lookup in Bag<Integer> has time complexity of O(N)
	we needed a mutable data type with constant time complexity for insert.
	Bag also has a small memory foot print.
	
	
		
	

/**********************************************************************
 *  Describe concisely the algorithm you used to check if the digraph 
 *  is rooted and the algorithm you used to check if the digrah is a DAG.  
 *  What is the order of growth of the best case 
 *  running time as a function of the number of vertices V and the 
 *  number of edges E in the digraph? And what is the order of growth 
 *  of the worst case running time?
 *
 *  Be careful! It is very easy to get these wrong. Keep in mind
 *  what the 'best case' and 'worst case' entail. Don't forget about
 *  the fact that starting a breadth first search in Java means 
 *  initializing edgeTo[] arrays, etc.
 **********************************************************************/

Description:

To check if the digraph contained a cycle we used DirectedCycle.java
from the algs4 library, insertion time complexity for this is O(V + E).
This added to our memory footprint, put saved us time.

To check if the digraph was rooted we iterated through the vertices and 
used Digraph.java function outdegree() to lookup the outdegree for each
vertice, and if we encountered more than one vertice with outdegree == 0
we knew the digraph wasn't rooted




method                               best case            worst case
------------------------------------------------------------------------
rooted check				constant(V = 1)	     O(V)   

DAG check				constant(V == 1)     O(V+E)



/**********************************************************************
 *  Describe concisely your algorithm to compute the shortest ancestral
 *  path in SAP.java? What is the order of growth of the worst-case
 *  running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 **********************************************************************/

Description:

 To find the sap in SAP.java, we used BreadthFirstDirectedPaths.java from
 the algs4 library. Constructing 2x BFDP takes 2x O(V + E), then we iterate 
 through the vertices in our digraph O(V), lookups (hasPathTo() and distTo()) 
 in BFDP have constant time complexity, resulting in O(3V + 2E)



method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)		     O(3V+ 2E)	     	    O(3V + 2E)

ancestor(int v, int w)		     O(3V+ 2E)	     	    O(3V + 2E)

length(Iterable<Integer> v,	     O(3V+ 2E)	     	    O(3V + 2E)
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,	     O(3V+ 2E)	     	    O(3V + 2E)
         Iterable<Integer> w)




/**********************************************************************
 *  If you implemented an extra credit optimization describe it here.
 **********************************************************************/


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and recitation classes, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/

 Most of the code we did together using pair-programming, all problem
 solving was done in unison.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
