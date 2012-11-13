import java.util.LinkedList;
import java.util.List;


public class Node {
	public String name;
	public List<Node> in_nodes;
	public List<Node> out_nodes;
	
	public Node(String name)
	{
		in_nodes = new LinkedList<Node>();
		out_nodes = new LinkedList<Node>();
		this.name = name;
	}
}
