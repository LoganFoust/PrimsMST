import java.util.Comparator;

//this method is passed to the TreeSet data structure to allow it to compare KeyNode objects by their key attribute
public class NodeCompare implements Comparator<KeyNode>{
	public int compare(KeyNode node0, KeyNode node1) {
		return node0.key - node1.key;
	}
}
