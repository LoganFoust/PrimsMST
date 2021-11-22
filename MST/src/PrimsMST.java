import java.util.LinkedList;
import java.util.TreeSet;

class PrimsMST {
	public LinkedList<AdjacencyNode>[] list;
	
	public static void main(String args[]) {
		PrimsMST soln = new PrimsMST();
		mst(soln.init());
	}
	
	static void mst(LinkedList<AdjacencyNode>[] list) {
		//Our solution is an array of KeyNode objects which represent a node in the graph, the cheapest edge to get to that node, and the node on the other side of that edge
		KeyNode[] n = new KeyNode[list.length];  	
		for(int i = 0; i < list.length; i++) { 
			n[i] = new KeyNode();	  
			n[i].added = false; //did we add this node to the MST yet?
			n[i].vertex = i;
			n[i].key = Integer.MAX_VALUE; //what is the cheapest known edge that connects this node so far? initialized to MAX_VALUE to indicate that no edge has been found yet.
			n[i].parent = -1; //what other node allows us to connect this node? initialized to -1 to indicate that none has been found yet.
		}
		
		n[0].key = 0; //no cost to add our starting node to the MST
		
		//We use the Java TreeSet data structure to sort our AdjacencyNode objects and select the best node to add according to Prim's algorithm
		
		TreeSet<KeyNode> q = new TreeSet<KeyNode>(new NodeCompare()); //tree is sorted ascending, smallest weight is root (using as min-heap)
		
		for(int i=0; i<list.length; i++) {
			q.add(n[i]); //add all vertices to min-heap
		}
		
		while(!q.isEmpty()) {
			KeyNode r = q.pollFirst(); //cheapest vertex to add will be root of min-heap
			n[r.vertex].added = true; //connect that vertex to the mst
			
			for(AdjacencyNode vadded : list[r.vertex]) { //search edges coming off of added vertex in adjacency list.
				if(!n[vadded.destination].added) { //if the edge leads to an unconnected vertex
					if(n[vadded.destination].key > vadded.weight) { //and is cheaper than the previously recorded best edge to that vertex
						q.remove(n[vadded.destination]); //remove the vertex from the min-heap.
						n[vadded.destination].key = vadded.weight; //change the best recorded weight to the new one
						q.add(n[vadded.destination]); //add the modified vertex back to the queue
						n[vadded.destination].parent = r.vertex; //record what vertex that edge came from
					}
				}
			}
		}
		
		System.out.println("Starting node is: " + n[0].key);
		for(int i=1; i<list.length; i++) {
			System.out.println("From " + n[i].parent + " to " + i + " for " + n[i].key + ".");
		}
	}
			
	LinkedList<AdjacencyNode>[] init() {	
		//Our graph is represented as an adjacency list. Each index in the array represents a node in the graph, and contains a linked list that represents each edge going out from that node.
		list = new LinkedList[7];
		for(int i=0; i<7; i++) {
			list[i] = new LinkedList<AdjacencyNode>();
		}
		
		list[0].add(new AdjacencyNode(2, 1));
		list[0].add(new AdjacencyNode(4, 2));
		list[0].add(new AdjacencyNode(7, 3));
		list[0].add(new AdjacencyNode(5, 5));
		
		list[1].add(new AdjacencyNode(2, 0));
		list[1].add(new AdjacencyNode(6, 3));
		list[1].add(new AdjacencyNode(3, 4));
		list[1].add(new AdjacencyNode(8, 6));
		
		list[2].add(new AdjacencyNode(4, 0));
		list[2].add(new AdjacencyNode(6, 5));
		
		list[3].add(new AdjacencyNode(7, 0));
		list[3].add(new AdjacencyNode(6, 1));
		list[3].add(new AdjacencyNode(1, 5));
		list[3].add(new AdjacencyNode(6, 6));
		
		list[4].add(new AdjacencyNode(3, 1));
		list[4].add(new AdjacencyNode(7, 6));
				
		list[5].add(new AdjacencyNode(5, 0));
		list[5].add(new AdjacencyNode(6, 2));
		list[5].add(new AdjacencyNode(1, 3));
		list[5].add(new AdjacencyNode(6, 6));
		
		list[6].add(new AdjacencyNode(8, 1));
		list[6].add(new AdjacencyNode(6, 3));
		list[6].add(new AdjacencyNode(7, 4));
		list[6].add(new AdjacencyNode(6, 5));
		return list;
	}
	
}
