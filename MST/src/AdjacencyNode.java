//this object represents each edge attached to a particular node in our adjacency list
public class AdjacencyNode { 
	public int destination;
	public int weight;
	
	AdjacencyNode(int w, int d){
		destination = d;
		weight = w;
	}
}
