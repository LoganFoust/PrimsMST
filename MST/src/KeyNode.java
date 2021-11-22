//This object represents a graph node in the MST
public class KeyNode {
	int vertex; //id for this vertex
	int key;	//the cheapest weight to add this vertex found so far
	int parent; //id for the vertex that connects this one to the mst
	boolean added = false; //whether it is connected to mst or not
}
